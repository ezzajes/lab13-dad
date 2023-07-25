package guiservertranslation;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;


public class ServerTranslation {
private static ServerSocket serverSocket;
	
	/**
	 * Method to start the server
	 * @throws IOException
	 */
	public static void startServer() throws IOException {
		// Binding to port
		serverSocket = new ServerSocket(1243);
		System.out.println("Server has started. \n > Wait for client connections...");
	}
	
	/**
	 * Method to accept client request
	 * @return
	 * @throws IOException
	 */
	public static Socket acceptClient() throws IOException {
		// Accept client request for connection
    	Socket socket = serverSocket.accept();
    	return socket;
	}
	
	/**
	 * Method to process translation for the text
	 * @param text
	 * @param language
	 * @return
	 */
	public static String translateText(String text, String language) {
		String translation = "";
		switch(language) {
		case "kr":
			if(text.equalsIgnoreCase("Good morning")) {
				translation = "좋은 아침";
			}
			else if(text.equalsIgnoreCase("Good night")) {
				translation = "안녕히 주무세요";
			}
			else if(text.equalsIgnoreCase("How are you?")) {
				translation = "어떻게 지내세요?";
			}
			else if(text.equalsIgnoreCase("Thank you")) {
				translation = "감사합니다";
			}
			else if(text.equalsIgnoreCase("Goodbye")) {
				translation = "안녕";
			}
			else if(text.equalsIgnoreCase("What's up?")) {
				translation = "뭐야?";
			}
			else {
				translation = "Text can't be translated";
			}
			break;
		case "my":
			if(text.equalsIgnoreCase("Good morning")) {
				translation = "Selamat pagi";
			}
			else if(text.equalsIgnoreCase("Good night")) {
				translation = "Selamat malam";
			}
			else if(text.equalsIgnoreCase("How are you?")) {
				translation = "Apa khabar?";
			}
			else if(text.equalsIgnoreCase("Thank you")) {
				translation = "Terima kasih";
			}
			else if(text.equalsIgnoreCase("Goodbye")) {
				translation = "Selamat tinggal";
			}
			else if(text.equalsIgnoreCase("What's up?")) {
				translation = "Ada apa?";
			}
			else {
				translation = "Text can't be translated";
			}
			break;
		case "ar":
			if(text.equalsIgnoreCase("Good morning"))
				translation = "صباح الخير";
			
			else if(text.equalsIgnoreCase("Good night"))
				translation = "طاب مساؤك";
			
			else if(text.equalsIgnoreCase("How are you?"))
				translation = "كيف حالك؟";
			
			else if(text.equalsIgnoreCase("Thank you"))
				translation = "شكرالك";
			
			else if(text.equalsIgnoreCase("Goodbye"))
				translation = "مع السلامة";
			
			else if(text.equalsIgnoreCase("What's up?"))
				translation = "ما أجبارك؟";
			
			else
				translation = "Text can't be translated";
			break;
		default:
			return text;	
		}
		return translation;
	}
	
	/**
	 * Main function to start the translation server application
	 */
	public static void main(String[]args) {
		// Launch the server frame
		ServerTranslationFrame serverFrame = new ServerTranslationFrame();
		serverFrame.setVisible(true);
		try {
			// Start the server
			startServer();
			
			// Counter to keep track the number of requested connection
			int totalRequest = 0;
			
			// Keep the server alive
			while(true) {
				// Message to indicate server is alive
				serverFrame.updateServerStatus(false);
				
				// Accept client
				Socket clientSocket = acceptClient();
				
				// Message to indicate server is connected
            	System.out.println("\nClient connected: " + clientSocket);
            	
            	// Create stream to write data to the network
            	BufferedReader inStream = new BufferedReader(
            			new InputStreamReader(clientSocket.getInputStream()));
            	DataOutputStream outStream = new DataOutputStream(
            			clientSocket.getOutputStream());
            	
            	// Get text input from client
            	String text = inStream.readLine();
            	String language = inStream.readLine();
            	serverFrame.updateRequestStatus(
    					"Text Received: " + text);
            	serverFrame.updateRequestStatus(
    					"Target Language: " + language);
            	
            	// Translate the text
            	String translationText = translateText(text, language);
            	byte[] encodedBytes = translationText.getBytes(Charset.forName("UTF-8"));
            	serverFrame.updateRequestStatus(
    					"Data sent to the client: " + translationText);
            	
            	// Send text translation to the client
            	outStream.write(encodedBytes);
            	
            	// Close socket and streams
            	outStream.close();
            	inStream.close();
            	clientSocket.close();
            	
            	// Display number of request
            	serverFrame.updateRequestStatus("\nAccepted connection to from the "
    					+ "client. Total request = " + ++totalRequest );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
