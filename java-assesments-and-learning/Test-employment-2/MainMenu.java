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

// the main menu class. the second window seen, displays additional buttons dependent on security level
public class MainMenu implements ActionListener{
	private JFrame frame;
	private Container container;
	private JPanel loginPanel;
	private JPanel panel;
	
	private DBConnect database;
	private JLabel searchLabel;
	private JTextField searchBox;
	private JButton searchButton;
	private JLabel resultLabel;
	private JButton logoutButton;
	private JButton createNewEmploymentRecord;
	private JButton createNewPersonalRecord;
	private JButton modifyPersonalRecordButton;
	private JButton modifyEmploymentRecordButton;
	private JButton createPerformanceReview;
	private JButton salaryIncreaseButton;
	private JButton terminationRecordButton;
	private int id;
	private int pos;
	private String name;
	private String surname;
	private int authorityLevel;
	
	// constructor
	public MainMenu(JFrame frame, Container container, JPanel loginPanel, DBConnect database, int authorityLevel){
		this.frame = frame;
		this.container = container;
		this.loginPanel = loginPanel;
		this.database = database;
		this.authorityLevel = authorityLevel;
		buildPanel();
	}
	
	// fills the frame
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
		searchLabel.setText("Search for a record: \n(Firstname and surname required)");
		panel.add(searchLabel);
		
		searchBox = new JTextField("Firstname Surname", 20);
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
		
		createPerformanceReview = new JButton("Create/Edit Performance Review");
		createPerformanceReview.addActionListener(this);
		panel.add(createPerformanceReview);
		createPerformanceReview.setVisible(false);
		
		salaryIncreaseButton = new JButton("Create/Edit Salary Increase Form");
		salaryIncreaseButton.addActionListener(this);
		panel.add(salaryIncreaseButton);
		salaryIncreaseButton.setVisible(false);
		
		terminationRecordButton = new JButton("Create/Edit Termination Form");
		terminationRecordButton.addActionListener(this);
		panel.add(terminationRecordButton);
		terminationRecordButton.setVisible(false);
		
		//layout for the createNewEmploymentRecord button
		layout.putConstraint(SpringLayout.NORTH, createNewEmploymentRecord, 30, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, createNewEmploymentRecord, 180, SpringLayout.WEST, container);
		//layout for the createNewPersonalRecord button
		layout.putConstraint(SpringLayout.NORTH, createNewPersonalRecord, 10, SpringLayout.SOUTH, createNewEmploymentRecord);
		layout.putConstraint(SpringLayout.WEST, createNewPersonalRecord, 180, SpringLayout.WEST, container);
		//layout for the logoutButton
		layout.putConstraint(SpringLayout.SOUTH, logoutButton, 560, SpringLayout.NORTH, container);
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
		//layout for the createPerformanceReview
		layout.putConstraint(SpringLayout.NORTH, createPerformanceReview, 10, SpringLayout.SOUTH, modifyPersonalRecordButton);
		layout.putConstraint(SpringLayout.WEST, createPerformanceReview, 180, SpringLayout.WEST, container);
		//layout for the salaryIncreaseButton
		layout.putConstraint(SpringLayout.NORTH, salaryIncreaseButton, 10, SpringLayout.SOUTH, createPerformanceReview);
		layout.putConstraint(SpringLayout.WEST, salaryIncreaseButton, 180, SpringLayout.EAST, container);
		//layout for the terminationRecordButton
		layout.putConstraint(SpringLayout.NORTH, terminationRecordButton, 10, SpringLayout.SOUTH, salaryIncreaseButton);
		layout.putConstraint(SpringLayout.WEST, terminationRecordButton, 180, SpringLayout.EAST, container);
		
		panel.setVisible(true);
	}
	
	// if a button is pressed the associated action is performed
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
			if(searchText.contains(" ")){
				pos = searchText.indexOf(" ");
				name = searchText.substring(0, pos);
				surname = searchText.substring(pos+1);
			}
			String query = "SELECT staffID FROM personalRecord WHERE name='" + name + "' AND surname='" + surname + "'";
			ResultSet resultSet = database.getResults(query);
				
			try {
				if(resultSet.next()){
					id = resultSet.getInt("staffID");
					
					resultLabel.setText("A result was found.");
					resultLabel.setVisible(true);
					modifyEmploymentRecordButton.setVisible(true);
					modifyPersonalRecordButton.setVisible(true);
					
					if(authorityLevel>1){
						createPerformanceReview.setVisible(true);
						salaryIncreaseButton.setVisible(true);
						terminationRecordButton.setVisible(true);
					}
				}else{
					resultLabel.setText("No result was found.");
					resultLabel.setVisible(true);
					modifyEmploymentRecordButton.setVisible(false);
					modifyPersonalRecordButton.setVisible(false);
					createPerformanceReview.setVisible(false);
					salaryIncreaseButton.setVisible(false);
					terminationRecordButton.setVisible(false);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error: " + e);
			}
			
		}
		if(event.getSource() == modifyEmploymentRecordButton){
			panel.setVisible(false);
			frame.setTitle("Modify Employment Record");
			new EmploymentRecord(frame, container, panel, database, id);
		}
		
		if(event.getSource() == modifyPersonalRecordButton){
			panel.setVisible(false);
			frame.setTitle("Modify Personal Record");
			new PersonalRecord(frame, container, panel, database, id);
		}
		
		if(event.getSource() == createPerformanceReview){
			ResultSet resultSet2 = database.getResults("SELECT performanceReview.staffID FROM performanceReview JOIN staff on performanceReview.staffID=staff.staffID "
					+ "WHERE performanceReview.staffID=" + id);
			panel.setVisible(false);
			try {
				if(resultSet2.next()){
					resultSet2.close();
					frame.setTitle("Modify Performance Review");
					new PerformanceReview(frame, container, panel, database, id, searchBox.getText().trim(), false);
				}else{
					resultSet2.close();
					frame.setTitle("Create Performance Review");
					new PerformanceReview(frame, container, panel, database, id, searchBox.getText().trim(), true);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error: " + e);
			}
		}
		
		if(event.getSource() == salaryIncreaseButton){
			ResultSet resultSet3 = database.getResults("SELECT salaryIncreaseRecord.staffID FROM salaryIncreaseRecord JOIN staff on salaryIncreaseRecord.staffID=staff.staffID "
					+ "WHERE salaryIncreaseRecord.staffID=" + id);
			panel.setVisible(false);
			try {
				if(resultSet3.next()){
					resultSet3.close();
					frame.setTitle("Modify Salary Increase Record");
					new SalaryIncreaseForm(frame, container, panel, database, id, searchBox.getText().trim(), false);
				}else{
					resultSet3.close();
					frame.setTitle("Create Salary Increase Record");
					new SalaryIncreaseForm(frame, container, panel, database, id, searchBox.getText().trim(), true);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error: " + e);
			}
		}
		
		if(event.getSource() == terminationRecordButton){
			ResultSet resultSet4 = database.getResults("SELECT terminationRecord.staffID FROM terminationRecord JOIN staff on terminationRecord.staffID=staff.staffID "
					+ "WHERE terminationRecord.staffID=" + id);
			panel.setVisible(false);
			try {
				if(resultSet4.next()){
					resultSet4.close();
					frame.setTitle("Modify Termination Record");
					new TerminationRecord(frame, container, panel, database, id, searchBox.getText().trim(), false);
				}else{
					resultSet4.close();
					frame.setTitle("Create Termination Record");
					new TerminationRecord(frame, container, panel, database, id, searchBox.getText().trim(), true);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Error: " + e);
			}
		}
	}
}
