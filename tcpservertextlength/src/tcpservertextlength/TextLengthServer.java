package tcpservertextlength;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class for server side of TCP-Based application to process length of text
 * @author Nur Ezza Jeslin
 *
 */
public class TextLengthServer {
	/**
	 * This is the main function to run the server application 
	 * @param args
	 */
    public static void main(String[] args) {
        try {    		
    		// Binding to a port
        	ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server has started. \n > Wait for client connections...");

            // To keep server alive forever
            while (true) {            	
            	// Accept client request for connection
            	Socket clientSocket = serverSocket.accept();
                
                // Message to indicate server is connected
            	System.out.println("Client connected: " + clientSocket);

                // Create stream to write data on the network
            	BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outStream = new PrintWriter(clientSocket.getOutputStream(), true);
                
                // Process length of text
                String text = inStream.readLine();
                int length = text.length();
                String lengthStr = String.valueOf(length);
                
                // Send text length back to the client
                outStream.println(lengthStr);
    			
    			// Close socket and stream
                inStream.close();
                outStream.close();
                clientSocket.close();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}