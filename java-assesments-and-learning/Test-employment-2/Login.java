import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

// the login class. the first window displayed.
// login is achieved by entering a username and password that match those in the database
public class Login implements ActionListener{
	private JFrame frame;
	private Container container;
	private JPanel panel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private String username;
	private char[] password;
	private DBConnect database;
	
	// constructor
	public Login(Container container, JFrame frame, DBConnect database){
		this.frame = frame;
		this.container = container;
		this.database = database;
		buildPanel();
	}
	
	// fills the frame
	private void buildPanel(){
		panel = new JPanel();
		
		container.add(panel);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		usernameLabel = new JLabel();
		usernameLabel.setText("Username:");
		panel.add(usernameLabel);
		
		passwordLabel = new JLabel();
		passwordLabel.setText("Password:");
		panel.add(passwordLabel);
		
		usernameTextField = new JTextField(12);
		panel.add(usernameTextField);
		
		passwordTextField = new JPasswordField(12);
		panel.add(passwordTextField);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		panel.add(loginButton);
		
		//put usernameLabel at position 130,180
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 230, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 180, SpringLayout.WEST, container);
		//put usernameTextField 5 pixels to the right of usernameLabel
		layout.putConstraint(SpringLayout.NORTH, usernameTextField, 230, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, usernameTextField, 5, SpringLayout.EAST, usernameLabel);
		//put the passwordLabel 5 pixels below the usernameLabel
		layout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, passwordLabel, 180, SpringLayout.WEST, container);
		//put the passwordTextField 5 pixels to the right of passwordLabel
		layout.putConstraint(SpringLayout.NORTH, passwordTextField, 5, SpringLayout.SOUTH, usernameTextField);
		layout.putConstraint(SpringLayout.WEST, passwordTextField, 5, SpringLayout.EAST, passwordLabel);
		//put the login button 10 pixels below the passwordLabel
		layout.putConstraint(SpringLayout.NORTH, loginButton, 10, SpringLayout.SOUTH, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, loginButton, 180, SpringLayout.WEST, container);
		
		panel.setVisible(true);
	}
	
	// when the login button is pressed checks to see if the username and password entered match any in the database
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == loginButton){
			username = usernameTextField.getText();
			password = passwordTextField.getPassword();
			String query = "SELECT username, password, authorityLevel FROM staff WHERE username = '" + username + "' AND password = '" + new String(password) + "'";
			ResultSet resultSet = database.getResults(query);
			
			try {
				if(!resultSet.next()){ //no match is found
					JOptionPane.showMessageDialog(frame, "Username or password incorrect. Please try again.");
				}else{ //match found
					panel.setVisible(false);
					frame.setTitle("Main Menu");
					new MainMenu(frame, container, panel, database, resultSet.getInt("authorityLevel"));
				}
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, e.getMessage());
			}
		}
	}
}
