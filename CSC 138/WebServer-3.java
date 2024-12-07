//Elijah ****** CSC 138
import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;
import java.security.*;

public class WebServer {

    private static void printUsage() {//Usage error for incorrect args
        System.err.println("Usage: java WebServer.java <keystore path> <Keystore password>");
    }

    public static void main(String[] args) {//incorporates starter code from assignment
        // Set the port numbers
        int httpPort = 80;
        int httpsPort = 443;

        boolean startHttps = false;//determines if https server starts, default value false because something needs to happen for it to be true

        switch (args.length) {//Checks for proper arguments
            case 0:
                System.out.println("No keystore provided. Starting in HTTP only mode.");//starts in http only if not enough arguments for https
                break;
            case 1:
                System.err.println("Error: Only one argument provided. Expected either no arguments or both keystore path and password.");//Gives usage type error then starts http only
                System.err.println("Defaulting to HTTP only mode.");
                break;
            case 2://in case with both arguments
                try {
                    //Set up SSL properties based on arguments
                    System.setProperty("javax.net.ssl.keyStore", args[0]);
                    System.setProperty("javax.net.ssl.keyStorePassword", args[1]);
                    startHttps = true;
                    System.out.println("Starting in HTTP + HTTPS mode");
                } catch (Exception e) {
                    System.err.println("Error configuring SSL: " + e.getMessage());//Default to http only if error with ssl configuration
                    System.err.println("Defaulting to HTTP only mode");
                    startHttps = false;
                }
                break;
            default:
                System.err.println("Error: Invalid number of arguments");// if any other number of arguments tell user and default to http only
                printUsage();
                System.err.println("Defaulting to HTTP only mode.");
                break;
        }


        //Create http server no matter what
        HttpServer httpServer = new HttpServer(httpPort);
        Thread httpThread = new Thread(httpServer);
        httpThread.start();

        //Create and start HTTPS server thread only if SSL is properly configured and password/ keystore path provided
        if (startHttps) {
            HttpsServer httpsServer = new HttpsServer(httpsPort);
            Thread httpsThread = new Thread(httpsServer);
            httpsThread.start();
        }

    }
}

class HttpServer implements Runnable {//Thread for http connections
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {//Start thread on port
            System.out.println("HTTP Server running on port " + port);

            while (true) {//scan for and accept clients creating new threads for them
                Socket clientSocket = serverSocket.accept();
                HttpRequest requestHandler = new HttpRequest(clientSocket);
                Thread thread = new Thread(requestHandler);
                thread.start();
            }
        } catch (IOException e) {//Catch server exeptions
            System.err.println("HTTP Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class HttpsServer implements Runnable {//Https implementation/server
    private final int port;
    private final SSLServerSocketFactory sslServerSocketFactory;

    public HttpsServer(int port) {
        this.port = port;
        try {
            //Get the default SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");

            //Create KeyStore from the keystore file
            KeyStore keyStore = KeyStore.getInstance("JKS");
            char[] password = System.getProperty("javax.net.ssl.keyStorePassword").toCharArray();
            try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("javax.net.ssl.keyStore"))) {
                keyStore.load(fileInputStream, password);
            }

            //Create key manager
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, password);

            //Initialize SSLContext
            sslContext.init(kmf.getKeyManagers(), null, null);

            //Get the server socket factory
            this.sslServerSocketFactory = sslContext.getServerSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize HTTPS server", e);
        }
    }

    @Override
    public void run() {
        try {
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);//create socket
            System.out.println("HTTPS Server running on port " + port);//announce running on port

            while (true) {
                SSLSocket clientSocket = (SSLSocket) sslServerSocket.accept();//add clients
                HttpRequest requestHandler = new HttpRequest(clientSocket);//create request handler for https
                Thread thread = new Thread(requestHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("HTTPS Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class HttpRequest implements Runnable {
    private Socket socket;

    public HttpRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             DataOutputStream ServerOutput = new DataOutputStream(socket.getOutputStream());
             BufferedReader BufferedReader = new BufferedReader(new InputStreamReader(is))) {

            //Read request line and print to console
            String requestLine = BufferedReader.readLine();
            System.out.println("Received request: " + requestLine);

            String[] requestParts = requestLine.split(" ");//Split line at correct places
            if (requestParts.length != 3) {
                sendBadRequestResponse(ServerOutput);
                return;
            }

            String method = requestParts[0];//set variables based on argument position in http message
            String path = requestParts[1];

            if (!method.equals("GET")) {// for anything not get send method not allowed error
                sendMethodNotAllowedResponse(ServerOutput);
                return;
            }

            //If root path, try to serve index.html, for testing
            /*if (path.equals("/")) {
                path = "/index.html";
            }*/

            //Remove leading slash and serve the file, gives 404 if none
            String filePath = path.substring(1);
            if (!serveFile(ServerOutput, filePath)) {
                sendNotFoundResponse(ServerOutput);
            }

        } catch (IOException e) {//error handling
            System.err.println("Request handling exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket closing exception: " + e.getMessage());
            }
        }
    }

    private boolean serveFile(DataOutputStream ServerOutput, String filePath) {//serve file to user, sanitizes first and prevents path traversal
        try {
            String normalizedPath = PathNormalizer.normalizePath(filePath);//normalize/sanitize file path

            if (normalizedPath == null) {
                sendNotFoundResponse(ServerOutput);
                return false;
            }

            File file = new File(normalizedPath);//open file from sanitized file path

            //path traversal prevention
            if (!file.getCanonicalPath().startsWith(new File(".").getCanonicalPath())) {
                sendNotFoundResponse(ServerOutput);
                return false;
            }

            if (!file.exists() || !file.isFile()) {
                sendNotFoundResponse(ServerOutput);
                return false;
            }

            String contentType = contentType(filePath);//finds content type
            byte[] content = readFileToByteArray(file);//stores content

            //creates http header
            String header = String.format("HTTP/1.1 200 OK\r\n" +
                    "Content-Type: %s\r\n" +
                    "Content-Length: %d\r\n" +
                    "\r\n", contentType, content.length);

            ServerOutput.writeBytes(header);//writes header
            ServerOutput.write(content);//writes content
            return true;

        } catch (IOException e) {
            System.err.println("Error serving file: " + e.getMessage());
            return false;
        }
    }

    private String contentType(String filePath) {//Finds correct MIME type
        if (filePath.endsWith(".html")) {
            return "text/html";
        } else if (filePath.endsWith(".css")) {
            return "text/css";
        } else if (filePath.endsWith(".js")) {
            return "application/javascript";
        } else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filePath.endsWith(".png")) {
            return "image/png";
        } else if (filePath.endsWith(".gif")) {
            return "image/gif";
        }
        return "text/plain";
    }

    //send http responses
    private void sendResponse(DataOutputStream ServerOutput, String status, String contentType, String content) throws IOException {//Send httpmessage response
        try {
            String response = String.format("HTTP/1.1 %s\r\n" +
                    "Content-Type: %s\r\n" +
                    "Content-Length: %d\r\n" +
                    "\r\n" +
                    "%s", status, contentType, content.length(), content);
            ServerOutput.writeBytes(response);
        } catch (SocketException e) {
            System.err.println("Connection closed by client: " + e.getMessage());//handles when connection closes/refreshes/etc
        }
    }

    //errors

    //400
    private void sendBadRequestResponse(DataOutputStream ServerOutput) throws IOException {
        String htmlResponse = "<html><body><h1>400 Bad Request</h1><p>Error 400: The server could not understand your request.</p></body></html>";//creates html error page
        sendResponse(ServerOutput, "400 Bad Request", "text/html", htmlResponse);//sends
    }

    //404
    private void sendNotFoundResponse(DataOutputStream ServerOutput) throws IOException {
        String htmlResponse = "<html><body><h1>404 Not Found</h1><p>Error 404: The requested file could not be found on this server.</p></body></html>";//creates html error page
        sendResponse(ServerOutput, "404 Not Found", "text/html", htmlResponse);//sends
    }

    //405
    private void sendMethodNotAllowedResponse(DataOutputStream ServerOutput) throws IOException {
        String htmlResponse = "<html><body><h1>405 Method Not Allowed</h1><p>Error 405: The requested method is not supported.</p></body></html>";//creates html error page
        sendResponse(ServerOutput, "405 Method Not Allowed", "text/html", htmlResponse);//sends
    }

    private byte[] readFileToByteArray(File file) throws IOException { //reads contents of file into a byte array
        byte[] buffer = new byte[(int) file.length()];//send messages
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(buffer);
        }
        return buffer;
    }

}
//Normalizes path during file serving process
class PathNormalizer {
    public static String normalizePath(String path) throws SecurityException {
        if (path == null || path.isEmpty()) {//Return null if empty
            return null;
        }
        try {
            String decodedPath = URLDecoder.decode(path, "UTF-8");//Decode url pieces
            decodedPath = decodedPath.replace('/', File.separatorChar).replace('\\', File.separatorChar);//replace system slashes with default separator character

            File file = new File(decodedPath);//open file
            return file.getCanonicalPath();//return canonical path, handles cases of directory traversal and repetitive slashes
        } catch (Exception e) {
            throw new SecurityException("Path normalization failed: " + e.getMessage());
        }
    }
}

