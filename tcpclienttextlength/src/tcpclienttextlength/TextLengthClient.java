package tcpclienttextlength;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class for client side of TCP-Based application to process length of text
 * @author Nur Ezza Jeslin
 *
 */
public class TextLengthClient {
	/**
	 * This is the main function to run the client application
	 * @param args
	 */
    public static void main(String[] args) {
        try {
    		
        	// Connect to the server @ localhost, port 1234
            Socket socket = new Socket("localhost", 1234);
            
            // Read from network
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outstream = new PrintWriter(socket.getOutputStream(), true);
            
            // Read input of text
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter text: ");
            String text = userInput.readLine();
            
            // Send text to the server
            outstream.println(text);
            
            // Read length sent by the server
            String lengthStr = inStream.readLine();
            int length = Integer.parseInt(lengthStr);
            
            // Display the text inserted with the length
            System.out.println("Text received: " + text);
            System.out.println("Text length: " + length + " characters.");
             
    		// Close everything
            outstream.close();
    		inStream.close();
    		socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
