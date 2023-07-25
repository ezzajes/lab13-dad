package guiclienttranslation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;


public class ClientTranslation {
private static Socket socket;
	
	public static void connectServer() throws IOException {
		// Connect to the server, port 
        socket = new Socket("localhost", 1243);
	}
	
	public static void main(String[] args) {
    	// Launch client-side frame
		ClientTranslationFrame clientTranslationFrame = new ClientTranslationFrame();
		clientTranslationFrame.setVisible(true);

    	// Implement ActionListener
    	clientTranslationFrame.btnSubmit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		// Connect to server
					connectServer();
					
					// Update the status of the connection
					clientTranslationFrame.updateConnectionStatus(socket.isConnected());
					
					// Create stream to read from network
		            DataInputStream inStream = new DataInputStream(socket.getInputStream());
		            PrintWriter outStream = new PrintWriter(
		            		socket.getOutputStream(), true);

		            // Read input from user
			        String text = clientTranslationFrame.getText();
			        String language = clientTranslationFrame.getLanguage();                 

		            // Send text and language to the server
		            outStream.println(text);
		            outStream.println(language);

		            // Receive the translation, the print
		            byte[] encodedBytes = inStream.readAllBytes();
		            String translatedText = new String(encodedBytes, Charset.forName("UTF-8"));
		            clientTranslationFrame.updateServerTranslation(translatedText);
		            
		            // Close socket and streams
		            outStream.close();
		            inStream.close();
		            socket.close();
		        	
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
    }
}
