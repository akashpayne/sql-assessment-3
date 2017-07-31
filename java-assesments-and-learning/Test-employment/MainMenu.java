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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class MainMenu implements ActionListener{
	private JFrame frame;
	private Container container;
	private JPanel loginPanel;
	private JPanel panel;
	private JButton logoutButton;
	private JButton createNewEmploymentRecord;
	private JButton createNewPersonalRecord;
	private DBConnect database;
	private JLabel searchLabel;
	private JTextField searchBox;
	private JButton searchButton;
	private JLabel resultLabel;
	private JButton modifyPersonalRecordButton;
	private JButton modifyEmploymentRecordButton;
	private int id;
	
	public MainMenu(JFrame frame, Container container, JPanel loginPanel, DBConnect database){
		this.frame = frame;
		this.container = container;
		this.loginPanel = loginPanel;
		this.database = database;
		buildPanel();
	}

	private void buildPanel(){
		panel = new JPanel();
		container.add(panel);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);
		panel.add(logoutButton);
		
		createNewEmploymentRecord = new JButton("Create New Employment Record");
		createNewEmploymentRecord.addActionListener(this);
		panel.add(createNewEmploymentRecord);
		
		createNewPersonalRecord = new JButton("Create New Personal Record");
		createNewPersonalRecord.addActionListener(this);
		panel.add(createNewPersonalRecord);
		
		searchLabel = new JLabel();
		searchLabel.setText("Search for a record:");
		panel.add(searchLabel);
		
		searchBox = new JTextField("FirstName Surname", 20);
		panel.add(searchBox);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		panel.add(searchButton);
		
		resultLabel = new JLabel();
		panel.add(resultLabel);
		resultLabel.setVisible(false);
		
		modifyEmploymentRecordButton = new JButton("Modify Empoyment Record");
		modifyEmploymentRecordButton.addActionListener(this);
		panel.add(modifyEmploymentRecordButton);
		modifyEmploymentRecordButton.setVisible(false);
		
		modifyPersonalRecordButton = new JButton("Modify Personal Record");
		modifyPersonalRecordButton.addActionListener(this);
		panel.add(modifyPersonalRecordButton);
		modifyPersonalRecordButton.setVisible(false);
		
		//layout for the createNewEmploymentRecord button
		layout.putConstraint(SpringLayout.NORTH, createNewEmploymentRecord, 30, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, createNewEmploymentRecord, 180, SpringLayout.WEST, container);
		//layout for the createNewPersonalRecord button
		layout.putConstraint(SpringLayout.NORTH, createNewPersonalRecord, 10, SpringLayout.SOUTH, createNewEmploymentRecord);
		layout.putConstraint(SpringLayout.WEST, createNewPersonalRecord, 180, SpringLayout.WEST, container);
		//layout for the logoutButton
		layout.putConstraint(SpringLayout.SOUTH, logoutButton, 360, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, logoutButton, 10, SpringLayout.WEST, container);
		
		//layout for the searchLabel
		layout.putConstraint(SpringLayout.NORTH, searchLabel, 30, SpringLayout.SOUTH, createNewPersonalRecord);
		layout.putConstraint(SpringLayout.WEST, searchLabel, 180, SpringLayout.WEST, container);
		//layout for the searchBox
		layout.putConstraint(SpringLayout.NORTH, searchBox, 10, SpringLayout.SOUTH, searchLabel);
		layout.putConstraint(SpringLayout.WEST, searchBox, 180, SpringLayout.WEST, container);
		//layout for the searchButton
		layout.putConstraint(SpringLayout.NORTH, searchButton, 10, SpringLayout.SOUTH, searchBox);
		layout.putConstraint(SpringLayout.WEST, searchButton, 180, SpringLayout.WEST, container);
		
		//layout for the resultLabel
		layout.putConstraint(SpringLayout.NORTH, resultLabel, 10, SpringLayout.SOUTH, searchButton);
		layout.putConstraint(SpringLayout.WEST, resultLabel, 180, SpringLayout.WEST, container);
		//layout for the modifyEmploymentRecordButton
		layout.putConstraint(SpringLayout.NORTH, modifyEmploymentRecordButton, 10, SpringLayout.SOUTH, resultLabel);
		layout.putConstraint(SpringLayout.WEST, modifyEmploymentRecordButton, 180, SpringLayout.WEST, container);
		//layout for the modifyPersonalRecordButton
		layout.putConstraint(SpringLayout.NORTH, modifyPersonalRecordButton, 10, SpringLayout.SOUTH, modifyEmploymentRecordButton);
		layout.putConstraint(SpringLayout.WEST, modifyPersonalRecordButton, 180, SpringLayout.WEST, container);
		
		
		panel.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == logoutButton){
			panel.setVisible(false);
			frame.setTitle("Login");
			loginPanel.setVisible(true);
		}
		if(event.getSource() == createNewEmploymentRecord){
			panel.setVisible(false);
			frame.setTitle("Create New Employment Record");
			new EmploymentRecord(frame, container, panel, database);
		}
		if(event.getSource() == createNewPersonalRecord){
			panel.setVisible(false);
			frame.setTitle("Create New Personal Record");
			new PersonalRecord(frame, container, panel, database);
		}
		if(event.getSource() == searchButton){
			String searchText = searchBox.getText().trim();
			int pos = searchText.indexOf(" ");
			String name = searchText.substring(0, pos);
			String surname = searchText.substring(pos+1);
			
			String query = "SELECT staffID FROM personalRecord WHERE name='" + name + "' AND surname='" + surname + "'";
			ResultSet resultSet = database.getResults(query);
			
			try {
				if(resultSet.next()){
					id = resultSet.getInt("staffID");
					
					resultLabel.setText("A result was found.");
					resultLabel.setVisible(true);
					modifyEmploymentRecordButton.setVisible(true);
					modifyPersonalRecordButton.setVisible(true);
				}else{
					resultLabel.setText("No result was found.");
					resultLabel.setVisible(true);
					modifyEmploymentRecordButton.setVisible(false);
					modifyPersonalRecordButton.setVisible(false);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error: " + e);
			}
		}
		if(event.getSource() == modifyEmploymentRecordButton){
			panel.setVisible(false);
			new EmploymentRecord(frame, container, panel, database, id);
		}
		
		if(event.getSource() == modifyPersonalRecordButton){
			panel.setVisible(false);
			new PersonalRecord(frame, container, panel, database, id);
		}
	}
}
