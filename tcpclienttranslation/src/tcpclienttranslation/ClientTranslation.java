package tcpclienttranslation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTranslation {
	private static Socket socket;
	
	public static void connectServer() throws IOException {
		// Connect to the server, port 
        socket = new Socket("localhost", 35);
	}
	
	public static void main(String[] args) {
        try {
        	// Connect to server
        	connectServer();

        	// Create stream to read from network
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outStream = new PrintWriter(socket.getOutputStream(), true);

            // Read input from user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
        	// Get text to translate
            System.out.print("Enter text to translate: ");
            String textToTranslate = userInput.readLine();
            
            // Get language to translate
            System.out.print("Enter target language ('my'-Bahasa Malaysia, 'kr'-Korean, 'ar'-Arabic): ");
            String targetLanguage = userInput.readLine();                 

            // Send text and language to the server
            outStream.println(textToTranslate);
            outStream.println(targetLanguage);

            // Receive the translation, the print
            String translatedText = inStream.readLine();
            System.out.println("Translation: " + translatedText);
            
            // Close socket and streams
            outStream.close();
            inStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}