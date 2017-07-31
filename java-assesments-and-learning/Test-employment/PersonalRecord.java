import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class PersonalRecord implements ActionListener{
	private JFrame frame;
	private Container container;
	private JButton backButton;
	private JButton saveButton;
	private JPanel panel;
	private JPanel mainMenu;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel DateOfBirthLabel;
	private JLabel slashLabel1;
	private JLabel slashLabel2;
	private JLabel addressLabel;
	private JLabel townOrCityLabel;
	private JLabel countyLabel;
	private JLabel postcodeLabel;
	private JLabel telephoneLabel;
	private JLabel mobileLabel;
	private JLabel nextOfKinLabel;
	private JLabel nextOfKinMobileLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JTextField DOBdayTextField;
	private JTextField DOBmonthTextField;
	private JTextField DOByearTextField;
	private JTextField addressTextField;
	private JTextField townOrCityTextField;
	private JTextField countyTextField;
	private JTextField postcodeTextField;
	private JTextField telephoneTextField;
	private JTextField mobileTextField;
	private JTextField nextOfKinTextField;
	private JTextField nextOfKinMobileTextField;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JPasswordField confirmPasswordTextField;
	private String DOBText;
	private DBConnect database;
	private int id;
	private String dateOfBirth;
	
	//this constructor is used when creating a new record
	public PersonalRecord(JFrame frame, Container container, JPanel mainMenu, DBConnect database){
		this.frame = frame;
		this.container = container;
		this.mainMenu = mainMenu;
		this.database = database;
		SpringLayout layout = new SpringLayout();
		buildPanel(layout);
		addUsernamePasswordFields(layout);
	}
	
	//this constructor is used when modifying a record
	public PersonalRecord(JFrame frame, Container container, JPanel mainMenu, DBConnect database, int id){
		this.frame = frame;
		this.container = container;
		this.mainMenu = mainMenu;
		this.database = database;
		this.id = id;
		SpringLayout layout = new SpringLayout();
		buildPanel(layout);
		getText();
	}
	
	private void getText(){
		ResultSet resultSet = database.getResults("SELECT * FROM personalRecord WHERE staffID ='" + id + "'");
		try {
			if(resultSet.next()){
				nameTextField.setText(resultSet.getString("name"));
				surnameTextField.setText(resultSet.getString("name"));
				DOBText = resultSet.getString("DateOfBirth");
				DOBdayTextField.setText(DOBText.substring(0,2));
				DOBmonthTextField.setText(DOBText.substring(3,5));
				DOByearTextField.setText(DOBText.substring(6));
				addressTextField.setText(resultSet.getString("name"));
				townOrCityTextField.setText(resultSet.getString("name"));
				countyTextField.setText(resultSet.getString("name"));
				postcodeTextField.setText(resultSet.getString("name"));
				telephoneTextField.setText(resultSet.getString("name"));
				mobileTextField.setText(resultSet.getString("name"));
				nextOfKinTextField.setText(resultSet.getString("name"));
				nextOfKinMobileTextField.setText(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Error1: " + e);
		}
	}
	
	private void buildPanel(SpringLayout layout){
		panel = new JPanel();
		container.add(panel);
		
		panel.setLayout(layout);
		
		//initialise and add all labels
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		panel.add(nameLabel);
		
		surnameLabel = new JLabel();
		surnameLabel.setText("Surname:");
		panel.add(surnameLabel);
		
		DateOfBirthLabel = new JLabel();
		DateOfBirthLabel.setText("Date of Birth:");
		panel.add(DateOfBirthLabel);
		
		slashLabel1 = new JLabel();
		slashLabel1.setText("/");
		panel.add(slashLabel1);
		
		slashLabel2 = new JLabel();
		slashLabel2.setText("/");
		panel.add(slashLabel2);
		
		addressLabel = new JLabel();
		addressLabel.setText("Address:");
		panel.add(addressLabel);
		
		townOrCityLabel = new JLabel();
		townOrCityLabel.setText("Town/City:");
		panel.add(townOrCityLabel);
		
		countyLabel = new JLabel();
		countyLabel.setText("County:");
		panel.add(countyLabel);
		
		postcodeLabel = new JLabel();
		postcodeLabel.setText("Postcode:");
		panel.add(postcodeLabel);
		
		telephoneLabel = new JLabel();
		telephoneLabel.setText("Telephone:");
		panel.add(telephoneLabel);
		
		mobileLabel = new JLabel();
		mobileLabel.setText("Mobile:");
		panel.add(mobileLabel);
		
		nextOfKinLabel = new JLabel();
		nextOfKinLabel.setText("Next of Kin:");
		panel.add(nextOfKinLabel);
		
		nextOfKinMobileLabel = new JLabel();
		nextOfKinMobileLabel.setText("Next of Kin Mobile:");
		panel.add(nextOfKinMobileLabel);
		
		
		
		//initialise and add all text fields
		nameTextField = new JTextField(12);
		panel.add(nameTextField);
		
		surnameTextField = new JTextField(12);
		panel.add(surnameTextField);
		
		DOBdayTextField = new JTextField(2);
		panel.add(DOBdayTextField);
		
		DOBmonthTextField = new JTextField(2);
		panel.add(DOBmonthTextField);
		
		DOByearTextField = new JTextField(3);
		panel.add(DOByearTextField);
		
		addressTextField = new JTextField(12);
		panel.add(addressTextField);
		
		townOrCityTextField = new JTextField(10);
		panel.add(townOrCityTextField);
		
		countyTextField = new JTextField(10);
		panel.add(countyTextField);
		
		postcodeTextField = new JTextField(5);
		panel.add(postcodeTextField);
		
		telephoneTextField = new JTextField(7);
		panel.add(telephoneTextField);
		
		mobileTextField = new JTextField(7);
		panel.add(mobileTextField);
		
		nextOfKinTextField = new JTextField(10);
		panel.add(nextOfKinTextField);
		
		nextOfKinMobileTextField = new JTextField(7);
		panel.add(nextOfKinMobileTextField);
		
		//initialise and add all buttons
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		panel.add(backButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		panel.add(saveButton);
		
		//position the nameLabel
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, nameLabel, 160, SpringLayout.WEST, container);
		//position the nameTextField
		layout.putConstraint(SpringLayout.NORTH, nameTextField, 10, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, nameTextField, 79, SpringLayout.EAST, nameLabel);
		//position the surnameLabel
		layout.putConstraint(SpringLayout.NORTH, surnameLabel, 10, SpringLayout.SOUTH, nameLabel);
		layout.putConstraint(SpringLayout.WEST, surnameLabel, 160, SpringLayout.WEST, container);
		//position the surnameTextField
		layout.putConstraint(SpringLayout.NORTH, surnameTextField, 6, SpringLayout.SOUTH, nameTextField);
		layout.putConstraint(SpringLayout.WEST, surnameTextField, 60, SpringLayout.EAST, surnameLabel);
		//position the DateOfBirthLabel
		layout.putConstraint(SpringLayout.NORTH, DateOfBirthLabel, 10, SpringLayout.SOUTH, surnameLabel);
		layout.putConstraint(SpringLayout.WEST, DateOfBirthLabel, 160, SpringLayout.WEST, container);
		//position the DOBdayTextField
		layout.putConstraint(SpringLayout.NORTH, DOBdayTextField, 5, SpringLayout.SOUTH, surnameTextField);
		layout.putConstraint(SpringLayout.WEST, DOBdayTextField, 62, SpringLayout.EAST, DateOfBirthLabel);
		//position the slashLabel1
		layout.putConstraint(SpringLayout.NORTH, slashLabel1, 5, SpringLayout.SOUTH, surnameTextField);
		layout.putConstraint(SpringLayout.WEST, slashLabel1, 5, SpringLayout.EAST, DOBdayTextField);
		//position the DOBmonthTextField
		layout.putConstraint(SpringLayout.NORTH, DOBmonthTextField, 5, SpringLayout.SOUTH, surnameTextField);
		layout.putConstraint(SpringLayout.WEST, DOBmonthTextField, 5, SpringLayout.EAST, slashLabel1);
		//position the slashLabel2
		layout.putConstraint(SpringLayout.NORTH, slashLabel2, 5, SpringLayout.SOUTH, surnameTextField);
		layout.putConstraint(SpringLayout.WEST, slashLabel2, 5, SpringLayout.EAST, DOBmonthTextField);
		//position the DOByearTextField
		layout.putConstraint(SpringLayout.NORTH, DOByearTextField, 5, SpringLayout.SOUTH, surnameTextField);
		layout.putConstraint(SpringLayout.WEST, DOByearTextField, 5, SpringLayout.EAST, slashLabel2);
		//position the addressLabel
		layout.putConstraint(SpringLayout.NORTH, addressLabel, 10, SpringLayout.SOUTH, DateOfBirthLabel);
		layout.putConstraint(SpringLayout.WEST, addressLabel, 160, SpringLayout.WEST, container);
		//position the addressTextField
		layout.putConstraint(SpringLayout.NORTH, addressTextField, 7, SpringLayout.SOUTH, DOByearTextField);
		layout.putConstraint(SpringLayout.WEST, addressTextField, 64, SpringLayout.EAST, addressLabel);
		//position the townOrCityLabel
		layout.putConstraint(SpringLayout.NORTH, townOrCityLabel, 10, SpringLayout.SOUTH, addressLabel);
		layout.putConstraint(SpringLayout.WEST, townOrCityLabel, 160, SpringLayout.WEST, container);
		//position the townOrCityTextField
		layout.putConstraint(SpringLayout.NORTH, townOrCityTextField, 6, SpringLayout.SOUTH, addressTextField);
		layout.putConstraint(SpringLayout.WEST, townOrCityTextField, 79, SpringLayout.EAST, townOrCityLabel);
		//position the countyLabel
		layout.putConstraint(SpringLayout.NORTH, countyLabel, 10, SpringLayout.SOUTH, townOrCityLabel);
		layout.putConstraint(SpringLayout.WEST, countyLabel, 160, SpringLayout.WEST, container);
		//position the countyTextField
		layout.putConstraint(SpringLayout.NORTH, countyTextField, 6, SpringLayout.SOUTH, townOrCityTextField);
		layout.putConstraint(SpringLayout.WEST, countyTextField, 95, SpringLayout.EAST, countyLabel);
		//position the postcodeLabel
		layout.putConstraint(SpringLayout.NORTH, postcodeLabel, 10, SpringLayout.SOUTH, countyLabel);
		layout.putConstraint(SpringLayout.WEST, postcodeLabel, 160, SpringLayout.WEST, container);
		//position the postcodeTextField
		layout.putConstraint(SpringLayout.NORTH, postcodeTextField, 6, SpringLayout.SOUTH, countyTextField);
		layout.putConstraint(SpringLayout.WEST, postcodeTextField, 135, SpringLayout.EAST, postcodeLabel);
		//position the telephoneLabel
		layout.putConstraint(SpringLayout.NORTH, telephoneLabel, 10, SpringLayout.SOUTH, postcodeLabel);
		layout.putConstraint(SpringLayout.WEST, telephoneLabel, 160, SpringLayout.WEST, container);
		//position the telephoneTextField
		layout.putConstraint(SpringLayout.NORTH, telephoneTextField, 6, SpringLayout.SOUTH, postcodeTextField);
		layout.putConstraint(SpringLayout.WEST, telephoneTextField, 108, SpringLayout.EAST, telephoneLabel);
		//position the mobileLabel
		layout.putConstraint(SpringLayout.NORTH, mobileLabel, 10, SpringLayout.SOUTH, telephoneLabel);
		layout.putConstraint(SpringLayout.WEST, mobileLabel, 160, SpringLayout.WEST, container);
		//position the mobileTextField
		layout.putConstraint(SpringLayout.NORTH, mobileTextField, 6, SpringLayout.SOUTH, telephoneTextField);
		layout.putConstraint(SpringLayout.WEST, mobileTextField, 130, SpringLayout.EAST, mobileLabel);
		//position the nextOfKinLabel
		layout.putConstraint(SpringLayout.NORTH, nextOfKinLabel, 10, SpringLayout.SOUTH, mobileLabel);
		layout.putConstraint(SpringLayout.WEST, nextOfKinLabel, 160, SpringLayout.WEST, container);
		//position the nextOfKinTextField
		layout.putConstraint(SpringLayout.NORTH, nextOfKinTextField, 6, SpringLayout.SOUTH, mobileTextField);
		layout.putConstraint(SpringLayout.WEST, nextOfKinTextField, 72, SpringLayout.EAST, nextOfKinLabel);
		//position the nextOfKinMobileLabel
		layout.putConstraint(SpringLayout.NORTH, nextOfKinMobileLabel, 10, SpringLayout.SOUTH, nextOfKinLabel);
		layout.putConstraint(SpringLayout.WEST, nextOfKinMobileLabel, 160, SpringLayout.WEST, container);
		//position the nextOfKinMobileTextField
		layout.putConstraint(SpringLayout.NORTH, nextOfKinMobileTextField, 6, SpringLayout.SOUTH, nextOfKinTextField);
		layout.putConstraint(SpringLayout.WEST, nextOfKinMobileTextField, 65, SpringLayout.EAST, nextOfKinMobileLabel);
		
		
		//position the back button
		layout.putConstraint(SpringLayout.SOUTH, backButton, 360, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, container);
		//position the saveButtonn
		layout.putConstraint(SpringLayout.SOUTH, saveButton, 360, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.EAST, saveButton, 580, SpringLayout.WEST, container);
		
		panel.setVisible(true);
	}
	
	private void addUsernamePasswordFields(SpringLayout layout){
		usernameLabel = new JLabel();
		usernameLabel.setText("Username:");
		panel.add(usernameLabel);
		
		passwordLabel = new JLabel();
		passwordLabel.setText("Password:");
		panel.add(passwordLabel);
		
		confirmPasswordLabel = new JLabel();
		confirmPasswordLabel.setText("Confirm Password:");
		panel.add(confirmPasswordLabel);
		
		usernameTextField = new JTextField(12);
		panel.add(usernameTextField);
		
		passwordTextField = new JPasswordField(12);
		panel.add(passwordTextField);
		
		confirmPasswordTextField = new JPasswordField(12);
		panel.add(confirmPasswordTextField);
		
		//position the usernameLabel
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 10, SpringLayout.SOUTH, nextOfKinMobileLabel);
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 160, SpringLayout.WEST, container);
		//position the usernameTextField
		layout.putConstraint(SpringLayout.NORTH, usernameTextField, 6, SpringLayout.SOUTH, nextOfKinMobileTextField);
		layout.putConstraint(SpringLayout.WEST, usernameTextField, 53, SpringLayout.EAST, usernameLabel);
		//position the passwordLabel
		layout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, passwordLabel, 160, SpringLayout.WEST, container);
		//position the passwordTextField
		layout.putConstraint(SpringLayout.NORTH, passwordTextField, 6, SpringLayout.SOUTH, usernameTextField);
		layout.putConstraint(SpringLayout.WEST, passwordTextField, 54, SpringLayout.EAST, passwordLabel);
		//position the confirmPasswordLabel
		layout.putConstraint(SpringLayout.NORTH, confirmPasswordLabel, 10, SpringLayout.SOUTH, passwordLabel);
		layout.putConstraint(SpringLayout.WEST, confirmPasswordLabel, 160, SpringLayout.WEST, container);
		//position the confirmPasswordTextField
		layout.putConstraint(SpringLayout.NORTH, confirmPasswordTextField, 6, SpringLayout.SOUTH, passwordTextField);
		layout.putConstraint(SpringLayout.WEST, confirmPasswordTextField, 6, SpringLayout.EAST, confirmPasswordLabel);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == backButton){
			panel.setVisible(false);
			frame.setTitle("Main Menu");
			mainMenu.setVisible(true);
		}
		if(event.getSource() == saveButton){
			if(validFields()){
				//if the usernameTextField is not null then the record is being created for the first time
				if(usernameTextField!=null){
					String username = usernameTextField.getText().trim();
					
					//create a new record for this staff member
					String query = "INSERT INTO staff(username, password) VALUES('" + username + "', '" + new String(passwordTextField.getPassword()) + "')";
					database.updateDatabase(query);
					//get the staffID of the new staff member
					String query2 = "SELECT staffID FROM staff WHERE username='" + username + "' AND password='" + new String(passwordTextField.getPassword()) + "'";
					ResultSet resultSet = database.getResults(query2);
					try {
						while(resultSet.next()){
							id = resultSet.getInt("staffID");
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(frame, "Error: " + e);
					}
					dateOfBirth = DOBdayTextField.getText().trim() + "/" + DOBmonthTextField.getText().trim() + "/" + DOByearTextField.getText().trim();
					//insert into the personalRecord table
					String query3 = "INSERT INTO personalRecord(staffID, name, surname, DateOfBirth, address, townOrCity, county, postcode, telephone, mobile, nextOfKin, nextOfKinMobile)"
							+ "VALUES ('" + id + "', '" + nameTextField.getText().trim() + "', '" + surnameTextField.getText().trim() + "', '" + dateOfBirth + "', '" + addressTextField.getText().trim() + "', '" 
							+ townOrCityTextField.getText().trim() + "', '" + countyTextField.getText().trim() + "', '" + postcodeTextField.getText().trim() + "', '" + telephoneTextField.getText().trim() + "', '"
							+ mobileTextField.getText().trim() + "', '" + nextOfKinTextField.getText().trim() + "', '" + nextOfKinMobileTextField.getText().trim() + "')";
					database.updateDatabase(query3);
				}else{//update the record
					dateOfBirth = DOBdayTextField.getText().trim() + "/" + DOBmonthTextField.getText().trim() + "/" + DOByearTextField.getText().trim();
					
					String query = "UPDATE personalRecord SET name='" + nameTextField.getText().trim() + "', surname='" + surnameTextField.getText().trim()
							+ "', DateOfBirth='" + dateOfBirth + "', address='" + addressTextField.getText().trim() + "', townOrCity='" + townOrCityTextField.getText().trim()
							+ "', county='" + countyTextField.getText().trim() + "', postcode='" + postcodeTextField.getText().trim() + "', telephone='" + telephoneTextField.getText().trim()
							+ "', mobile='" + mobileTextField.getText().trim() + "', nextOfKin='" + nextOfKinTextField.getText().trim() + "', nextOfKinMobile='" + nextOfKinMobileTextField.getText().trim()
							+ "' WHERE staffID=" + id;
							
					database.updateDatabase(query);
				}
				JOptionPane.showMessageDialog(frame, "Record successfully saved.");
				backButton.doClick();
			}
		}
	}
	
	private boolean validFields(){
		if(passwordTextField==null){
			return noNulls() & validLengths();
		}else{
			return noNulls() & validLengths() & passwordsMatch();
		}
	}
	
	private boolean noNulls(){
		if (!nameTextField.getText().trim().equals("") && !surnameTextField.getText().trim().equals("") && !DOBdayTextField.getText().trim().equals("") &&
			!DOBmonthTextField.getText().trim().equals("") && !DOByearTextField.getText().trim().equals("") && !addressTextField.getText().trim().equals("") && 
			!townOrCityTextField.getText().trim().equals("") && !countyTextField.getText().trim().equals("") && !postcodeTextField.getText().trim().equals("") && 
			!telephoneTextField.getText().trim().equals("") && !mobileTextField.getText().trim().equals("") && !nextOfKinTextField.getText().trim().equals("") && 
			!nextOfKinMobileTextField.getText().trim().equals("")) {
			
			if(usernameTextField == null){
				return true;
			}else{
				if(!usernameTextField.getText().trim().equals("") && !new String(passwordTextField.getPassword()).equals("") && 
				!new String(confirmPasswordTextField.getPassword()).equals("")){
					return true;
				}else{
					JOptionPane.showMessageDialog(frame, "The fields must not be empty");
					return false;
				}
			}
		}else{
			JOptionPane.showMessageDialog(frame, "The fields must not be empty");
			return false;
		}
	}
	
	private boolean validLengths(){
		//to be done later
		return verifyDate();
	}
	
	private boolean verifyDate(){
		if(!DOBdayTextField.getText().matches("[0-9]+") || DOBdayTextField.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The day field must be of length 2 and only numbers");
			return false;
		}
		if(!DOBmonthTextField.getText().matches("[0-9]+") || DOBmonthTextField.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The month field must be of length 2 and only numbers");
			return false;
		}
		if(!DOByearTextField.getText().matches("[0-9]+") || DOByearTextField.getText().length()!=4){
			JOptionPane.showMessageDialog(frame, "The year field must be of length 4 and only numbers");
			return false;
		}
		return true;
	}
	
	private boolean passwordsMatch(){
		if(new String(passwordTextField.getPassword()).equals(new String(confirmPasswordTextField.getPassword()))){
			return true;
		}else{
			JOptionPane.showMessageDialog(frame, "The password fields must match");
			return false;
		}
	}
}
