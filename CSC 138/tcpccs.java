//Elijah ******
//Client
import java.io.*;
import java.net.*;
import java.util.*;

public class tcpccs {
    private static final int port = 12345;//Port, static number due to requirements.

    private static PrintWriter serverOutput; //Input output streams and socket initilization
    private static BufferedReader serverInput;
    private static Socket serverSocket;

    private static volatile boolean clientIsRunning = true;//Flag for handling if client is running

    private static class MessageReceiver implements Runnable {//Recieves messages, runs in separate thread to constantly listen for incoming messages.
        @Override
        public void run() {
            try {
                String message;
                while (clientIsRunning && (message = serverInput.readLine()) != null) {//If running and there is a message
                    System.out.println(message);//Print message
                }
            } catch (IOException e) {//Prints server connection lost if ioexception error while client running, then turns off client.
                if (clientIsRunning) {
                    System.err.println("Server connection lost");
                    clientIsRunning = false;
                }
            }
        }
    }

    private static class ConnectionMonitor implements Runnable {//Handles server closing unexpectedly. Sends byte every 500 ms to check if server is still connected.  Disconnects if exception is caught.
        @Override
        public void run() {
            while (clientIsRunning) {
                try {
                    serverSocket.getOutputStream().write(0);
                    Thread.sleep(500);
                } catch (Exception e) {
                    if (clientIsRunning) {
                        System.err.println("Server connection lost");
                        clientIsRunning = false;
                        System.exit(1);
                    }
                    break;
                }
            }
        }
    }

    private static class UserInputHandler implements Runnable {//Reads user input then sends to server with username
        private final String username;
        private final BufferedReader userInput;

        public UserInputHandler(String username) {
            this.username = username;
            this.userInput = new BufferedReader(new InputStreamReader(System.in));
        }

        @Override
        public void run() {
            try {
                String message; //Read user input to server
                while (clientIsRunning) {
                    message = userInput.readLine();
                    if (!clientIsRunning || message == null) break;
                    serverOutput.println(username + ": " + message); //Give message with username and formatting
                }
            } catch (IOException e) {
                System.out.println("Error reading user input: " + e.getMessage());
                clientIsRunning = false;
            }
        }
    }

    public static void main(String[] args) {

        if (args.length != 2) {//Check command line arguments throw usage if incorrect
            System.out.println("Usage: ./tcpccs <server_hostname> <username>");
            System.exit(1);
        }

        String hostname = args[0];//Assign arguments to correct variables
        String username = args[1];

        try {
            //Connect to the server
            serverSocket = new Socket(hostname, port);
            serverOutput = new PrintWriter(serverSocket.getOutputStream(), true);
            serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            System.out.println("Connected to the server. You can start sending messages.");

            //Start message receiver thread
            Thread receiveThread = new Thread(new MessageReceiver());
            receiveThread.start();

            //Start thread monitor thread
            Thread monitorThread = new Thread(new ConnectionMonitor());
            monitorThread.start();

            //Start thread to handle user input
            Thread userInputThread = new Thread(new UserInputHandler(username));
            userInputThread.start();


            try {//Wait for threads to complete
                receiveThread.join();
                monitorThread.join();
                userInputThread.join();
            } catch (InterruptedException e) {//Basic error handling
                System.out.println("Thread interruption: " + e.getMessage());
            }

        } catch (UnknownHostException e) {//Error handling
            System.out.println("Could not resolve hostname: " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
            System.exit(1);
        } finally {
            cleanup();//Cleanup to exit program and close sockets correctly
        }
    }

    // Handle cleanup when shutting down
    private static void cleanup() {
        clientIsRunning = false;
        try {
            //Close streams and socket
            if (serverOutput != null) serverOutput.close();
            if (serverInput != null) serverInput.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
        System.exit(0);
    }
}