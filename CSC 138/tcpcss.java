//Elijah ******
//Server
import java.io.*;
import java.net.*;
import java.util.*;

public class tcpcss implements Runnable { //Code uses runnable to implement threading
    private static final int port = 12345;//Static port due to project requirements

    private static volatile boolean running = true;//Flag for wether or not server is running
    private static List<ClientHandler> clients = new ArrayList<>();//List to track active clients
    private static int threadCount = 0;
    private ServerSocket serverSocket;

    public tcpcss() {
        try {//Initializes server socket to listen on required port
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(1);
        }
    }

    @Override
    public void run() {
        System.out.println("Listener on port " + port);//Necessary messages on startup
        System.out.println("Waiting for connections ...");

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept(); //Wait for and accept connections
                String clientInfo = clientSocket.getInetAddress().getHostAddress() +
                        ", port: " + clientSocket.getPort();

                ClientHandler clientHandler = new ClientHandler(clientSocket, clients.size());//initialize client handler
                Thread clientThread = new Thread(clientHandler, "Thread-" + threadCount);
                threadCount++;

                //Print connection details
                System.out.println("New connection, thread name is " + clientThread.getName() + ", ip is: " + clientInfo);
                System.out.println("Adding to list of sockets as " + clients.size());

                synchronized(clients) {//Add client to list
                    clients.add(clientHandler);
                }
                clientThread.start();

            } catch (IOException e) {
                if (running) {
                    System.out.println("Error accepting client connection: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        //Shutdown hook for server closing unexpectedly
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown signal received.");
            running = false;
        }));//Code citation: from assignment guide

        //Start server
        tcpcss server = new tcpcss();
        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            System.out.println("Server thread interrupted");
        }
    }

    static class ClientHandler implements Runnable {
        private Socket serverSocket;//Socket to client
        private PrintWriter serverOutput; //Output to client
        private BufferedReader serverInput;// Input from client
        private final int clientId;
        private boolean isActive = true;//Flag to track client connection

        public ClientHandler(Socket serverSocket, int clientId) {//Set up input output streams
            this.serverSocket = serverSocket;
            this.clientId = clientId;
            try {
                serverOutput = new PrintWriter(serverSocket.getOutputStream(), true);
                serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Error setting up client handler: " + e.getMessage());
                isActive = false;
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while (isActive && (message = serverInput.readLine()) != null && !message.trim().isEmpty()) {//Continue to read messages from client
                    System.out.println("client: " + message);//Print to server output
                    broadcast(message);//Broadcast message from client to other clients
                }
            } catch (IOException e) {
                // Client disconnected.  Doesn't need message because handled in disconnect command.
            } finally {
                disconnect();
            }
        }

        private void broadcast(String message) {//Broadcast message to all clients using synchronization
            synchronized(clients) {
                for (ClientHandler client : clients) {
                    if (client != this && client.isActive) {
                        client.serverOutput.println(message);
                    }
                }
            }
        }

        private void disconnect() {
            isActive = false;
            try {
                System.out.println("Host disconnected, ip " + serverSocket.getInetAddress().getHostAddress() + ", port " + serverSocket.getPort());//Disconnect information printed
                if (serverOutput != null) serverOutput.close();//Close streams + socket
                if (serverInput != null) serverInput.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error during client cleanup: " + e.getMessage());
            }
        }
    }
}