import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

// salary increase form. displays an employee's salary increase record
public class SalaryIncreaseForm implements ActionListener{
	private JPanel panel;
	private JFrame frame;
	private Container container;
	private SpringLayout layout;
	private JPanel mainMenu;
	private DBConnect database;
	private int id;
	private String name;
	private boolean newRecord;
	private JButton backButton;
	private JButton saveButton;
	private JLabel header;
	private JLabel dateOfReviewLabel;
	private JLabel dateStartLabel;
	private JLabel currentSalaryLabel;
	private JLabel newSalaryLabel;
	private JLabel firstAuthorisationLabel;
	private JLabel secondAuthorisationLabel;
	private JLabel headSectionApprovalLabel;
	private JLabel headPersonnelApprovalLabel;
	private JTextField day;
	private JTextField day2;
	private JTextField month;
	private JTextField month2;
	private JTextField year;
	private JTextField year2;
	private JTextField currentSalary;
	private JTextField newSalary;
	private JCheckBox firstReview;
	private JCheckBox secondReview;
	private JCheckBox sectionApproval;
	private JCheckBox personnelApproval;
	
	// constructor
	public SalaryIncreaseForm(JFrame frame, Container container, JPanel mainMenu, DBConnect database, int id, String name, boolean newRecord){
		this.frame = frame;
		this.container = container;
		this.mainMenu = mainMenu;
		this.database = database;
		this.id = id;
		this.name = name;
		this.newRecord = newRecord;
		buildPanel();
		if(!newRecord){ //if editing an existing report
			getText();
		}
	}
	
	// gets the text entered and displays the corresponding employees information
	private void getText(){
		ResultSet resultSet1 = database.getResults("SELECT * FROM salaryIncreaseRecord WHERE staffID ='" + id + "'");
		try {
			if(resultSet1.next()){
				String dateOfReview = resultSet1.getString("dateOfReview");
				day.setText(dateOfReview.substring(8));
				month.setText(dateOfReview.substring(5,7));
				year.setText(dateOfReview.substring(0,4));
				String dateStart = resultSet1.getString("dateStart");
				day2.setText(dateStart.substring(8));
				month2.setText(dateStart.substring(5,7));
				year2.setText(dateStart.substring(0,4));
				currentSalary.setText(resultSet1.getString("currentSalary"));
				newSalary.setText(resultSet1.getString("newSalary"));
				firstReview.setSelected(resultSet1.getBoolean("firstReviewerAuthorisation"));
				secondReview.setSelected(resultSet1.getBoolean("secondReviewerAuthorisation"));
				sectionApproval.setSelected(resultSet1.getBoolean("HeadOfSectionApproval"));
				personnelApproval.setSelected(resultSet1.getBoolean("HeadOfPersonnelApproval"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e);
		}
	}
	
	// fills the frame
	private void buildPanel(){
		panel = new JPanel();
		container.add(panel);
		
		layout = new SpringLayout();
		panel.setLayout(layout);
		
		header = new JLabel();
		header.setText("Salary increase record for " + name + " staff ID " + id);
		panel.add(header);
		
		//position the header text
		layout.putConstraint(SpringLayout.NORTH, header, 8, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, header, 160, SpringLayout.WEST, container);
		
		//initialise and add the buttons
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		panel.add(backButton);
				
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		panel.add(saveButton);
		
		//build all the labels
		dateOfReviewLabel = new JLabel();
		dateOfReviewLabel.setText("Date of review:");
		panel.add(dateOfReviewLabel);
		
		dateStartLabel = new JLabel();
		dateStartLabel.setText("Date of salary increase to take effect:");
		panel.add(dateStartLabel);
		
		currentSalaryLabel = new JLabel();
		currentSalaryLabel.setText("Current Salary £");
		panel.add(currentSalaryLabel);
		
		newSalaryLabel = new JLabel();
		newSalaryLabel.setText("New Salary £");
		panel.add(newSalaryLabel);
		
		firstAuthorisationLabel = new JLabel();
		firstAuthorisationLabel.setText("First reviewer authorisation:");
		panel.add(firstAuthorisationLabel);

		secondAuthorisationLabel = new JLabel();
		secondAuthorisationLabel.setText("Second reviewer authorisation:");
		panel.add(secondAuthorisationLabel);

		headSectionApprovalLabel = new JLabel();
		headSectionApprovalLabel.setText("Head of section approval:");
		panel.add(headSectionApprovalLabel);

		headPersonnelApprovalLabel = new JLabel();
		headPersonnelApprovalLabel.setText("Head of personnel approval:");
		panel.add(headPersonnelApprovalLabel);
		
		//build all the text fields
		day = new JTextField(2);
		panel.add(day);
		
		day2 = new JTextField(2);
		panel.add(day2);
		
		month = new JTextField(2);
		panel.add(month);

		month2 = new JTextField(2);
		panel.add(month2);

		year = new JTextField(3);
		panel.add(year);

		year2 = new JTextField(3);
		panel.add(year2);

		currentSalary = new JTextField(8);
		panel.add(currentSalary);

		newSalary = new JTextField(8);
		panel.add(newSalary);
		
		firstReview = new JCheckBox();
		panel.add(firstReview);
		
		secondReview = new JCheckBox();
		panel.add(secondReview);
		
		sectionApproval = new JCheckBox();
		panel.add(sectionApproval);
		
		personnelApproval = new JCheckBox();
		panel.add(personnelApproval);
		
		//position components
		layout.putConstraint(SpringLayout.NORTH, dateOfReviewLabel, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, dateOfReviewLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, day, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, day, 50, SpringLayout.EAST, dateOfReviewLabel);
		
		layout.putConstraint(SpringLayout.NORTH, month, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, month, 6, SpringLayout.EAST, day);
		
		layout.putConstraint(SpringLayout.NORTH, year, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, year, 6, SpringLayout.EAST, month);
		
		
		layout.putConstraint(SpringLayout.NORTH, dateStartLabel, 10, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, dateStartLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, day2, 10, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, day2, 50, SpringLayout.EAST, dateStartLabel);
		
		layout.putConstraint(SpringLayout.NORTH, month2, 10, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, month2, 6, SpringLayout.EAST, day2);
	    
		layout.putConstraint(SpringLayout.NORTH, year2, 10, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, year2, 6, SpringLayout.EAST, month2);
		
		
		layout.putConstraint(SpringLayout.NORTH, currentSalaryLabel, 10, SpringLayout.SOUTH, dateStartLabel);
		layout.putConstraint(SpringLayout.WEST, currentSalaryLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, currentSalary, 10, SpringLayout.SOUTH, dateStartLabel);
		layout.putConstraint(SpringLayout.WEST, currentSalary, 60, SpringLayout.EAST, currentSalaryLabel);
		
		layout.putConstraint(SpringLayout.NORTH, newSalaryLabel, 10, SpringLayout.SOUTH, currentSalaryLabel);
		layout.putConstraint(SpringLayout.WEST, newSalaryLabel, 160, SpringLayout.EAST, container);
		
		layout.putConstraint(SpringLayout.NORTH, newSalary, 10, SpringLayout.SOUTH, currentSalaryLabel);
		layout.putConstraint(SpringLayout.WEST, newSalary, 60, SpringLayout.EAST, newSalaryLabel);
		
		layout.putConstraint(SpringLayout.NORTH, firstAuthorisationLabel, 10, SpringLayout.SOUTH, newSalaryLabel);
		layout.putConstraint(SpringLayout.WEST, firstAuthorisationLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, firstReview, 10, SpringLayout.SOUTH, newSalaryLabel);
		layout.putConstraint(SpringLayout.WEST, firstReview, 60, SpringLayout.EAST, firstAuthorisationLabel);
		
		layout.putConstraint(SpringLayout.NORTH, secondAuthorisationLabel, 10, SpringLayout.SOUTH, firstAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, secondAuthorisationLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, secondReview, 10, SpringLayout.SOUTH, firstReview);
		layout.putConstraint(SpringLayout.WEST, secondReview, 60, SpringLayout.EAST, secondAuthorisationLabel);
		
		layout.putConstraint(SpringLayout.NORTH, headSectionApprovalLabel, 10, SpringLayout.SOUTH, secondAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, headSectionApprovalLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, sectionApproval, 10, SpringLayout.SOUTH, secondAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, sectionApproval, 60, SpringLayout.EAST, headSectionApprovalLabel);
		
		layout.putConstraint(SpringLayout.NORTH, headPersonnelApprovalLabel, 10, SpringLayout.SOUTH, headSectionApprovalLabel);
		layout.putConstraint(SpringLayout.WEST, headPersonnelApprovalLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, personnelApproval, 10, SpringLayout.SOUTH, headSectionApprovalLabel);
		layout.putConstraint(SpringLayout.WEST, personnelApproval, 60, SpringLayout.EAST, headPersonnelApprovalLabel);
		
		//position the back button
		layout.putConstraint(SpringLayout.SOUTH, backButton, 560, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, container);
		//position the saveButtonn
		layout.putConstraint(SpringLayout.SOUTH, saveButton, 560, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.EAST, saveButton, 580, SpringLayout.WEST, container);
	}
	
	// if a button is pressed the associated action is performed
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == backButton){
			panel.setVisible(false);
			frame.setTitle("Main Menu");
			mainMenu.setVisible(true);
		}
		
		if(event.getSource() == saveButton){
			if(verifyDate()){
				String dateOfReview = year.getText().trim() + "-" + month.getText().trim() + "-" + day.getText().trim();
				String dateStart = year2.getText().trim() + "-" + month2.getText().trim() + "-" + day2.getText().trim();
				if(newRecord){
					String query = "INSERT INTO salaryIncreaseRecord(staffID, dateOfReview, dateStart, currentSalary, newSalary, firstReviewerAuthorisation, secondReviewerAuthorisation, "
							+ " HeadOfSectionApproval, HeadOfPersonnelApproval) "
							+ "VALUES ('" + id + "', '" + dateOfReview + "', '" + dateStart + "', '"
							+ currentSalary.getText().trim() + "', '" + newSalary.getText().trim() + "', " + firstReview.isSelected() + ", " 
							+ secondReview.isSelected() + ", " + sectionApproval.isSelected() + ", " + personnelApproval.isSelected() + ")";
					database.updateDatabase(query);
				}else{
					String query = "UPDATE salaryIncreaseRecord SET dateOfReview='" + dateOfReview + "', dateStart='" + dateStart + "', currentSalary='"
							+ currentSalary.getText().trim() + "', newSalary='" + newSalary.getText().trim() + "', firstReviewerAuthorisation=" + firstReview.isSelected() + ", secondReviewerAuthorisation=" 
							+ secondReview.isSelected() + ", HeadOfSectionApproval=" + sectionApproval.isSelected() + ", HeadOfPersonnelApproval=" + personnelApproval.isSelected()
							+ " WHERE staffID=" + id;
					database.updateDatabase(query);
				}
				JOptionPane.showMessageDialog(frame, "Record successfully saved.");
				backButton.doClick();
			}
		}
	}
	
	// checks that the entered values are suitable
	private boolean verifyDate(){
		if(!day.getText().matches("[0-9]+") || day.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The day field must be of length 2 and only numbers");
			return false;
		}
		if(!month.getText().matches("[0-9]+") || month.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The month field must be of length 2 and only numbers");
			return false;
		}
		if(!year.getText().matches("[0-9]+") || year.getText().length()!=4){
			JOptionPane.showMessageDialog(frame, "The year field must be of length 4 and only numbers");
			return false;
		}
		if(!day2.getText().matches("[0-9]+") || day2.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The day field must be of length 2 and only numbers");
			return false;
		}
		if(!month2.getText().matches("[0-9]+") || month2.getText().length()!=2){
			JOptionPane.showMessageDialog(frame, "The month field must be of length 2 and only numbers");
			return false;
		}
		if(!year2.getText().matches("[0-9]+") || year2.getText().length()!=4){
			JOptionPane.showMessageDialog(frame, "The year field must be of length 4 and only numbers");
			return false;
		}
		return true;
	}
}
