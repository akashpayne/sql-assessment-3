import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

// employment record class. displays an employee's employment records
public class EmploymentRecord implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	private JPanel mainMenu;
	private Container container;
	private JButton backButton;
	private JButton saveButton;
	private JLabel firstNameLabel;
	private JLabel surnameLabel;
	private JLabel firstInterviewerLabel;
	private JLabel secondInterviewerLabel;
	private JLabel interviewSummaryLabel;
	private JLabel jobOfferedLabel;
	private JLabel departmentLabel;
	private JLabel initialRoleLabel;
	private JLabel salaryLabel;
	private JLabel startDateLabel;
	private JLabel slashLabel1;
	private JLabel slashLabel2;
	private JLabel header;
	private JTextField firstInterviewerTextField;
	private JTextField secondInterviewerTextField;
	private JTextArea interviewSummaryTextArea;
	private JRadioButton yesRadio;
	private JRadioButton noRadio;
	private JTextField departmentTextField;
	private JTextField initialRoleTextField;
	private JTextField salaryTextField;
	private JTextField dayTextField;
	private JTextField monthTextField;
	private JTextField yearTextField;
	private DBConnect database;
	private boolean newRecord = true;
	private boolean jobOffered;
	private ButtonGroup buttonGroup;
	private int id;
	
	// this constructor is used when creating a new record
	public EmploymentRecord(JFrame frame, Container container, JPanel mainMenu, DBConnect database){
		this.frame = frame;
		this.container = container;
		this.mainMenu = mainMenu;
		this.database = database;
		buildPanel();
	}
	
	// this constructor is used when modifying a record
	public EmploymentRecord(JFrame frame, Container container, JPanel mainMenu, DBConnect database, int id){
		this.frame = frame;
		this.container = container;
		this.mainMenu = mainMenu;
		this.database = database;
		this.id = id;
		buildPanel();
		getText();
	}
	
	// method to get the employee record of the 'id'/employee entered 
	private void getText(){
		ResultSet resultSet = database.getResults("SELECT * FROM employeeRecord WHERE staffID ='" + id + "'");
		try {
			if(resultSet.next()){
				newRecord = false;
				firstInterviewerTextField.setText(resultSet.getString("firstInterviewer"));
				secondInterviewerTextField.setText(resultSet.getString("secondInterviewer"));
				interviewSummaryTextArea.setText(resultSet.getString("interviewSummary"));
				if(resultSet.getBoolean("jobOffered")){
					yesRadio.setSelected(true);
				}else{
					noRadio.setSelected(true);
				}
				initialRoleTextField.setText(resultSet.getString("initialRole"));
				departmentTextField.setText(resultSet.getString("department"));
				salaryTextField.setText(resultSet.getString("salary"));
				dayTextField.setText(resultSet.getString("startDate").substring(0,2));
				monthTextField.setText(resultSet.getString("startDate").substring(3,5));
				yearTextField.setText(resultSet.getString("startDate").substring(6));
			}else{
				JOptionPane.showMessageDialog(frame, "This person does not have an employee record. Please make one now.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e);
		}
	}
	
	// fills the frame
	private void buildPanel(){
		panel = new JPanel();
		container.add(panel);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		//initialise and add all labels
		header = new JLabel();
		if(id!=0){
			header.setText("Employment record for staff ID " + id);
		}else{
			header.setText("Create new employment record");
		}
		panel.add(header);
		
		firstInterviewerLabel = new JLabel();
		firstInterviewerLabel.setText("First Interviewer:");
		panel.add(firstInterviewerLabel);
				
		secondInterviewerLabel = new JLabel();
		secondInterviewerLabel.setText("Second Interviewer:");
		panel.add(secondInterviewerLabel);
				
		interviewSummaryLabel = new JLabel();
		interviewSummaryLabel.setText("Interview Summary:");
		panel.add(interviewSummaryLabel);
				
		slashLabel1 = new JLabel();
		slashLabel1.setText("/");
		panel.add(slashLabel1);
				
		slashLabel2 = new JLabel();
		slashLabel2.setText("/");
		panel.add(slashLabel2);
				
		jobOfferedLabel = new JLabel();
		jobOfferedLabel.setText("Job Offered:");
		panel.add(jobOfferedLabel);
				
		initialRoleLabel = new JLabel();
		initialRoleLabel.setText("Initial Role:");
		panel.add(initialRoleLabel);
		
		departmentLabel = new JLabel();
		departmentLabel.setText("Department:");
		panel.add(departmentLabel);
				
		salaryLabel = new JLabel();
		salaryLabel.setText("Salary (£):");
		panel.add(salaryLabel);
				
		startDateLabel = new JLabel();
		startDateLabel.setText("Start Date:");
		panel.add(startDateLabel);
		
		//initialise and add all text fields
		firstInterviewerTextField = new JTextField(12);
		panel.add(firstInterviewerTextField);
				
		secondInterviewerTextField = new JTextField(12);
		panel.add(secondInterviewerTextField);
			
		interviewSummaryTextArea = new JTextArea(3, 20);
		interviewSummaryTextArea.setLineWrap(true);
		interviewSummaryTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(interviewSummaryTextArea);
		panel.add(scrollPane);
		
		yesRadio = new JRadioButton("Yes");
		panel.add(yesRadio);
				
		noRadio = new JRadioButton("No");
		panel.add(noRadio);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(yesRadio);
		buttonGroup.add(noRadio);
				
		initialRoleTextField = new JTextField(12);
		panel.add(initialRoleTextField);
		
		departmentTextField = new JTextField(12);
		panel.add(departmentTextField);
				
		salaryTextField = new JTextField(6);
		panel.add(salaryTextField);
		
		dayTextField = new JTextField(2);
		panel.add(dayTextField);
		
		monthTextField = new JTextField(2);
		panel.add(monthTextField);
		
		yearTextField = new JTextField(3);
		panel.add(yearTextField);
		
		//initialise and add the buttons
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		panel.add(backButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		panel.add(saveButton);
		
		//position the header text
		layout.putConstraint(SpringLayout.NORTH, header, 20, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, header, 160, SpringLayout.WEST, container);
		//position the firstInterviewerLabel
		layout.putConstraint(SpringLayout.NORTH, firstInterviewerLabel, 30, SpringLayout.NORTH, header);
		layout.putConstraint(SpringLayout.WEST, firstInterviewerLabel, 160, SpringLayout.WEST, container);
		//position the firstInterviewerTextField
		layout.putConstraint(SpringLayout.NORTH, firstInterviewerTextField, 30, SpringLayout.NORTH, header);
		layout.putConstraint(SpringLayout.WEST, firstInterviewerTextField, 50, SpringLayout.EAST, firstInterviewerLabel);
		//position the secondInterviewerLabel
		layout.putConstraint(SpringLayout.NORTH, secondInterviewerLabel, 10, SpringLayout.SOUTH, firstInterviewerLabel);
		layout.putConstraint(SpringLayout.WEST, secondInterviewerLabel, 160, SpringLayout.WEST, container);
		//position the secondInterviewerTextField
		layout.putConstraint(SpringLayout.NORTH, secondInterviewerTextField, 6, SpringLayout.SOUTH, firstInterviewerTextField);
		layout.putConstraint(SpringLayout.WEST, secondInterviewerTextField, 50, SpringLayout.EAST, secondInterviewerLabel);
		//position the interviewSummaryLabel
		layout.putConstraint(SpringLayout.NORTH, interviewSummaryLabel, 10, SpringLayout.SOUTH, secondInterviewerLabel);
		layout.putConstraint(SpringLayout.WEST, interviewSummaryLabel, 160, SpringLayout.WEST, container);
		//position the interviewSummaryTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, secondInterviewerTextField);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 50, SpringLayout.EAST, interviewSummaryLabel);
		//position the jobOfferedLabel
		layout.putConstraint(SpringLayout.NORTH, jobOfferedLabel, 40, SpringLayout.SOUTH, interviewSummaryLabel);
		layout.putConstraint(SpringLayout.WEST, jobOfferedLabel, 160, SpringLayout.WEST, container);
		//position yesRadio
		layout.putConstraint(SpringLayout.NORTH, yesRadio, 6, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, yesRadio, 50, SpringLayout.EAST, jobOfferedLabel);
		//position noRadio
		layout.putConstraint(SpringLayout.NORTH, noRadio, 6, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, noRadio, 10, SpringLayout.EAST, yesRadio);
		//position the initialRoleLabel
		layout.putConstraint(SpringLayout.NORTH, initialRoleLabel, 10, SpringLayout.SOUTH, jobOfferedLabel);
		layout.putConstraint(SpringLayout.WEST, initialRoleLabel, 160, SpringLayout.WEST, container);
		//position the initialRoleTextField
		layout.putConstraint(SpringLayout.NORTH, initialRoleTextField, 6, SpringLayout.SOUTH, yesRadio);
		layout.putConstraint(SpringLayout.WEST, initialRoleTextField, 50, SpringLayout.EAST, initialRoleLabel);
		//position the departmentLabel
		layout.putConstraint(SpringLayout.NORTH, departmentLabel, 10, SpringLayout.SOUTH, initialRoleLabel);
		layout.putConstraint(SpringLayout.WEST, departmentLabel, 160, SpringLayout.WEST, container);
		//position the departmentTextField
		layout.putConstraint(SpringLayout.NORTH, departmentTextField, 6, SpringLayout.SOUTH, initialRoleTextField);
		layout.putConstraint(SpringLayout.WEST, departmentTextField, 50, SpringLayout.EAST, departmentLabel);
		//position the salaryLabel
		layout.putConstraint(SpringLayout.NORTH, salaryLabel, 10, SpringLayout.SOUTH, departmentLabel);
		layout.putConstraint(SpringLayout.WEST, salaryLabel, 160, SpringLayout.WEST, container);
		//position the salaryTextField
		layout.putConstraint(SpringLayout.NORTH, salaryTextField, 6, SpringLayout.SOUTH, departmentTextField);
		layout.putConstraint(SpringLayout.WEST, salaryTextField, 50, SpringLayout.EAST, salaryLabel);
		//position the startDateLabel
		layout.putConstraint(SpringLayout.NORTH, startDateLabel, 10, SpringLayout.SOUTH, salaryLabel);
		layout.putConstraint(SpringLayout.WEST, startDateLabel, 160, SpringLayout.WEST, container);
		//position the dayTextField
		layout.putConstraint(SpringLayout.NORTH,dayTextField, 5, SpringLayout.SOUTH, salaryTextField);
		layout.putConstraint(SpringLayout.WEST, dayTextField, 62, SpringLayout.EAST, startDateLabel);
		//position the slashLabel1
		layout.putConstraint(SpringLayout.NORTH, slashLabel1, 5, SpringLayout.SOUTH, salaryTextField);
		layout.putConstraint(SpringLayout.WEST, slashLabel1, 5, SpringLayout.EAST, dayTextField);
		//position the DOBmonthTextField
		layout.putConstraint(SpringLayout.NORTH, monthTextField, 5, SpringLayout.SOUTH, salaryTextField);
		layout.putConstraint(SpringLayout.WEST, monthTextField, 5, SpringLayout.EAST, slashLabel1);
		//position the slashLabel2
		layout.putConstraint(SpringLayout.NORTH, slashLabel2, 5, SpringLayout.SOUTH, salaryTextField);
		layout.putConstraint(SpringLayout.WEST, slashLabel2, 5, SpringLayout.EAST, monthTextField);
		//position the DOByearTextField
		layout.putConstraint(SpringLayout.NORTH, yearTextField, 5, SpringLayout.SOUTH, salaryTextField);
		layout.putConstraint(SpringLayout.WEST, yearTextField, 5, SpringLayout.EAST, slashLabel2);
		
		//position the back button
		layout.putConstraint(SpringLayout.SOUTH, backButton, 560, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, container);
		//position the saveButtonn
		layout.putConstraint(SpringLayout.SOUTH, saveButton, 560, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.EAST, saveButton, 580, SpringLayout.WEST, container);
		
		panel.setVisible(true);
	}
	
	// if a button is pressed the associated action is performed
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == backButton){
			panel.setVisible(false);
			frame.setTitle("Main Menu");
			mainMenu.setVisible(true);
		}
		if(event.getSource() == saveButton){
			if(validFields()){
				if(yesRadio.isSelected()){
					jobOffered = true;
				}else if(noRadio.isSelected()){
					jobOffered = false;
				}
				String startDate = dayTextField.getText().trim() + "/" + monthTextField.getText().trim() + "/" + yearTextField.getText().trim();
				//if creating a new record
				if(newRecord){
					if(id==0){ //if id is 0 this staff member doesn't have an employeeRecord
						//insert into the employeeRecord table
						String query = "INSERT INTO employeeRecord(firstInterviewer, secondInterviewer, interviewSummary, jobOffered, initialRole, salary, startDate) "
								+ "VALUES ('" + firstInterviewerTextField.getText().trim() + "', '" + secondInterviewerTextField.getText().trim() + "', '"
								+ interviewSummaryTextArea.getText().trim() + "', " + jobOffered + ", '" + initialRoleTextField.getText().trim() + "', '" 
								+ departmentTextField.getText().trim() + "', '" + salaryTextField.getText().trim() + "', '" + startDate + "')";
						database.updateDatabase(query);
					}else{
						//insert into the employeeRecord table
						String query = "INSERT INTO employeeRecord(staffID, firstInterviewer, secondInterviewer, interviewSummary, jobOffered, initialRole, salary, startDate) "
								+ "VALUES ('" + id + "', '" + firstInterviewerTextField.getText().trim() + "', '" + secondInterviewerTextField.getText().trim() + "', '"
								+ interviewSummaryTextArea.getText().trim() + "', " + jobOffered + ", '" + initialRoleTextField.getText().trim() + "', '" 
								+ departmentTextField.getText().trim() + "', '" + salaryTextField.getText().trim() + "', '" + startDate + "')";
						database.updateDatabase(query);
					}
				}else{//else we are modifying an existing record
					String query = "UPDATE employeeRecord SET firstInterviewer='" + firstInterviewerTextField.getText().trim() + "', secondInterviewer='" + secondInterviewerTextField.getText().trim()
							+ "', interviewSummary='" + interviewSummaryTextArea.getText().trim() + "', jobOffered=" + jobOffered + ", initialRole='" + initialRoleTextField.getText().trim()
							+ "', department='" + departmentTextField.getText().trim() + "', salary='" + salaryTextField.getText().trim() + "', startDate='" + startDate
							+ "' WHERE staffID=" + id;
					JOptionPane.showMessageDialog(frame, query);
					database.updateDatabase(query);
				}
				JOptionPane.showMessageDialog(frame, "Record successfully saved.");
				backButton.doClick();
			}
		}
	}
	
	//checks if the fields are valid
	private boolean validFields(){
			return noNulls() & validLengths();
	}
	
	// checks if any fields are empty
	private boolean noNulls(){
		if (!firstInterviewerTextField.getText().trim().equals("") && !secondInterviewerTextField.getText().trim().equals("") && !interviewSummaryTextArea.getText().trim().equals("") &&
			!initialRoleTextField.getText().trim().equals("") && !departmentTextField.getText().trim().equals("") && !salaryTextField.getText().trim().equals("") && !dayTextField.getText().trim().equals("") && 
			!monthTextField.getText().trim().equals("") && !yearTextField.getText().trim().equals("") && buttonGroup.getSelection()!=null){
			return true;
		}else{
			JOptionPane.showMessageDialog(frame, "The fields must not be empty");
			return false;
		}
	}
	
	private boolean validLengths(){
		//to be done later
		return verifyTypes();
	}
	
	// checks that the entered values are suitable
	private boolean verifyTypes(){
		if(!dayTextField.getText().matches("[0-9]+") || dayTextField.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The day field must be of length 2 and only numbers");
			return false;
		}
		if(!monthTextField.getText().matches("[0-9]+") || monthTextField.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The month field must be of length 2 and only numbers");
			return false;
		}
		if(!yearTextField.getText().matches("[0-9]+") || yearTextField.getText().length()!=4){
			JOptionPane.showMessageDialog(frame, "The year field must be of length 4 and only numbers");
			return false;
		}
		if(!salaryTextField.getText().trim().matches("[0-9]+")){
			JOptionPane.showMessageDialog(frame, "The salary field must only contain numbers");
			return false;
		}
		return true;
	}
}
