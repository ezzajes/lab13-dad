package guiclienttranslation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientTranslationFrame extends JFrame{
private static final long serialVersionUID = 1L;
	
	// Private frame components
	private JLabel lblServerTranslation;
	private JLabel lblStatusValue;
	private JTextField txtFieldText;
	private JTextField txtFieldLanguage;
	public JButton btnSubmit;
	
	// Private attributes for frame size
	private int width = 700;
	private int height = 500;


	/**
	 * The constructor that initialize and organize the Swing components on 
	 * the frame.
	 */
	public ClientTranslationFrame () {
		
		// Default frame setting
		this.setLayout(new FlowLayout());
		this.setTitle("Translation Application: Client Side");
		this.setSize(width, height);
		
		
		// Center the frame on the screen
        this.setLocationRelativeTo(null);
		
		// Initialize default value for label
        lblServerTranslation = new JLabel("-");
		lblStatusValue = new JLabel("-");
		txtFieldText = new JTextField(28);
		txtFieldLanguage = new JTextField(25);
		btnSubmit = new JButton("Submit");
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Organize components
		loadComponent();		
	}
	
	/**
	 * This method arrange the Swing components on the frame.
	 */
	private void loadComponent() {
		
		// Get font
		Font font = this.getFontStyle();
		
		// Get server status's panel and add to frame
		JPanel northPanel = this.getConnectionStatusPanel(font);		
		this.add(northPanel);
		
		// Add text field and button
		JPanel textField = this.getTextFieldTextPanel(font);
		this.add(textField);
		
		JPanel languageField = this.getTextFieldLanguagePanel(font);
		this.add(languageField);
		
		JPanel button = this.getButtonPanel(font);
		this.add(button);
		
		
		// Get server translation's panel and add to frame
		JPanel center = this.getServerTranslationPanel(font);
		this.add(center);
		
	}
	
	/**
	 * This method define a font to a generic style.
	 * 
	 * @return font object
	 */
	private Font getFontStyle() {
		
		Font font = new Font ("Arial Unicode MS", Font.PLAIN, 20);;
		
		return font;
		
	}
	
	/**
	 * This method creates and arrange Swing components to display status of 
	 * client's connection to the server.
	 * 
	 * @param font
	 * @return Swing components organized in a panel.
	 */
	private JPanel getConnectionStatusPanel(Font font) {
		
		// Create component
		JPanel panel = new JPanel();
		JLabel lblConnStatus = new JLabel ("Connection status: ");
		
		// Style the component
		lblConnStatus.setFont(font);
		lblConnStatus.setBackground(Color.WHITE);
		lblConnStatus.setOpaque(true);
		lblStatusValue.setFont(font);
		lblStatusValue.setBackground(Color.WHITE);
		lblStatusValue.setOpaque(true);
		
		// Organize components into panel
		panel.add(lblConnStatus);
		panel.add(lblStatusValue);
		panel.setPreferredSize(new Dimension(350, 100));
		
		return panel;
		
	}
	
	/**
	 * This method creates and arrange Swing components to translation retrieve from 
	 * the server.
 	 *
	 * @param font
	 * @return Swing components organized in panel
	 */
	private JPanel getServerTranslationPanel(Font font) {
		
		// Create component to display translation retrieve from the server
		JPanel panel = new JPanel();
		JLabel lblTranslation = new JLabel ("Translation: ");

		// Style the component
		lblTranslation.setFont(font);
		lblTranslation.setBackground(Color.WHITE);
		lblTranslation.setOpaque(true);
		lblServerTranslation.setFont(font);
		lblServerTranslation.setBackground(Color.WHITE);
		lblServerTranslation.setOpaque(true);

		// Organize components into panel
		panel.add(lblTranslation);
		panel.add(lblServerTranslation);
		panel.setPreferredSize(new Dimension(350, 100));
		
		return panel;
	}
	
	/**
	 * This method creates and arrange Swing components text field
	 * @param font
	 * @return
	 */
	private JPanel getTextFieldTextPanel(Font font) {
		// Create component to display text field
		JPanel panel = new JPanel();
		JLabel lblText = new JLabel ("Text: ");
		
		// Style the component
		lblText.setFont(font);
		lblText.setBackground(Color.WHITE);
		lblText.setOpaque(true);
		txtFieldText.setFont(font);
		txtFieldText.setBackground(Color.WHITE);
		txtFieldText.setOpaque(true);
		
		// Organize components into panel
		panel.add(lblText);
		panel.add(txtFieldText);
		panel.setPreferredSize(new Dimension(650, 50));
		
		return panel;
		
	}
	
	/**
	 * This method creates and arrange Swing components language field
	 * @param font
	 * @return
	 */
	private JPanel getTextFieldLanguagePanel(Font font) {
		// Create component to display language field
		JPanel panel = new JPanel();
		JLabel lblLanguage = new JLabel ("Language: ");
		
		// Style the component
		lblLanguage.setFont(font);
		lblLanguage.setBackground(Color.WHITE);
		lblLanguage.setOpaque(true);
		txtFieldLanguage.setFont(font);
		txtFieldLanguage.setBackground(Color.WHITE);
		txtFieldLanguage.setOpaque(true);
		
		// Organize components into panel
		panel.add(lblLanguage);
		panel.add(txtFieldLanguage);
		panel.setPreferredSize(new Dimension(600, 50));
		
		return panel;
		
	}
	
	/**
	 * This method creates and arrange Swing components button
	 * @param font
	 * @return
	 */
	private JPanel getButtonPanel(Font font) {
		// Create component to display language field
		JPanel panel = new JPanel();
		
		// Style the component
		btnSubmit.setFont(font);
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setOpaque(true);
		
		// Organize components into panel
		panel.add(btnSubmit);
		panel.setPreferredSize(new Dimension(350, 100));
		
		return panel;
		
	}
	
	/**
	 * This method update the value of translation on the frame
	 * 
	 * @param translation: Translation from server
	 */
	public void updateServerTranslation (String translation) {	
		this.lblServerTranslation.setText(translation);	
	}
	
	/**
	 * This method update the status of connection to the server.
	 * 
	 * @param connStatus: Connection status (true/false)
	 */
	public void updateConnectionStatus (boolean connStatus) {
		

		// Default status. Assuming for the worst case scenario.
		String status = "No connection to server.";
		
		// Validate status of connection
		if (connStatus)
			status = "Connection has established.";
		
				
		// Update the status on frame
		this.lblStatusValue.setText(status);
	}
	
	/**
	 * This method retrieve the value of text
	 * @return
	 */
	public String getText() {
		return txtFieldText.getText();
	}
	
	/**
	 * This method retrieve the value of language
	 * @return
	 */
	public String getLanguage() {
		return txtFieldLanguage.getText();
	}
}
