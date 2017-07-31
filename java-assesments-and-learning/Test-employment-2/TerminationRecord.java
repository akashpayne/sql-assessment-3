import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

// termination record. display's an employee's termination record
public class TerminationRecord implements ActionListener{
	private JPanel panel;
	private JFrame frame;
	private Container container;
	private JPanel mainMenu;
	private DBConnect database;
	private int id;
	private String name;
	private boolean newRecord;
	private JButton backButton;
	private JButton saveButton;
	private JLabel header;
	private JLabel dateOfReviewLabel;
	private JLabel warningsLabel;
	private JLabel reviewsLabel;
	private JLabel misconductLabel;
	private JLabel dateOfTerminationLabel;
	private JLabel conditionsLabel;
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
	private JRadioButton yesRadio1;
	private JRadioButton noRadio1;
	private JRadioButton yesRadio2;
	private JRadioButton noRadio2;
	private JRadioButton yesRadio3;
	private JRadioButton noRadio3;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private ButtonGroup buttonGroup3;
	private JTextArea conditionsArea;
	private JCheckBox firstReview;
	private JCheckBox secondReview;
	private JCheckBox sectionApproval;
	private JCheckBox personnelApproval;
	private String dateOfReview;
	private String dateOfTermination;
	
	// constructor
	public TerminationRecord(JFrame frame, Container container, JPanel mainMenu, DBConnect database, int id, String name, boolean newRecord){
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
		ResultSet resultSet = database.getResults("SELECT * FROM terminationRecord WHERE staffID ='" + id + "'");
		try {
			if(resultSet.next()){
				if(resultSet.getBoolean("warnings")){
					yesRadio1.setSelected(true);
				}else{
					noRadio1.setSelected(true);
				}
				if(resultSet.getBoolean("reviews")){
					yesRadio2.setSelected(true);
				}else{
					noRadio2.setSelected(true);
				}
				if(resultSet.getBoolean("misconduct")){
					yesRadio3.setSelected(true);
				}else{
					noRadio3.setSelected(true);
				}
				dateOfReview = resultSet.getString("dateOfReview");
				year.setText(dateOfReview.substring(0,4));
				month.setText(dateOfReview.substring(5, 7));
				day.setText(dateOfReview.substring(8));
				dateOfTermination = resultSet.getString("dateOfTermination");
				year2.setText(dateOfTermination.substring(0,4));
				month2.setText(dateOfTermination.substring(5, 7));
				day2.setText(dateOfTermination.substring(8));
				conditionsArea.setText(resultSet.getString("conditions"));
				firstReview.setSelected(resultSet.getBoolean("firstReviewerAuthorisation"));
				secondReview.setSelected(resultSet.getBoolean("secondReviewerAuthorisation"));
				sectionApproval.setSelected(resultSet.getBoolean("headOfSectionApproval"));
				personnelApproval.setSelected(resultSet.getBoolean("headOfPersonnelApproval"));
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
		
		header = new JLabel();
		header.setText("Termination record for " + name + " staff ID " + id);
		panel.add(header);
		
		
		//initialise and add the buttons
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		panel.add(backButton);
				
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		panel.add(saveButton);
		
		//build all the labels
		//buildLabel(dateOfReviewLabel, "Date of review:");
		
		dateOfReviewLabel = new JLabel();
		dateOfReviewLabel.setText("Date of review:");
		panel.add(dateOfReviewLabel);
		
		warningsLabel = new JLabel();
		warningsLabel.setText("Has the employee recieved two warning letters?:");
		panel.add(warningsLabel);
		
		reviewsLabel = new JLabel();
		reviewsLabel.setText("Has the employee had three reviews?");
		panel.add(reviewsLabel);

		misconductLabel = new JLabel();
		misconductLabel.setText("Is termination because of gross misconduct?:");
		panel.add(misconductLabel);

		dateOfTerminationLabel = new JLabel();
		dateOfTerminationLabel.setText("Date of termination to take effect:");
		panel.add(dateOfTerminationLabel);

		conditionsLabel = new JLabel();
		conditionsLabel.setText("Termination conditions:");
		panel.add(conditionsLabel);

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
		
		//build text fields
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
		
		yesRadio1 = new JRadioButton("Yes");
		panel.add(yesRadio1);
				
		noRadio1 = new JRadioButton("No");
		panel.add(noRadio1);
		
		yesRadio2 = new JRadioButton("Yes");
		panel.add(yesRadio2);
				
		noRadio2 = new JRadioButton("No");
		panel.add(noRadio2);
		
		yesRadio3 = new JRadioButton("Yes");
		panel.add(yesRadio3);
				
		noRadio3 = new JRadioButton("No");
		panel.add(noRadio3);
		
		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(yesRadio1);
		buttonGroup1.add(noRadio1);
		
		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(yesRadio2);
		buttonGroup2.add(noRadio2);
		
		buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(yesRadio3);
		buttonGroup3.add(noRadio3);
		
		firstReview = new JCheckBox();
		panel.add(firstReview);
		
		secondReview = new JCheckBox();
		panel.add(secondReview);
		
		sectionApproval = new JCheckBox();
		panel.add(sectionApproval);
		
		personnelApproval = new JCheckBox();
		panel.add(personnelApproval);
		
		conditionsArea = new JTextArea(3, 15);
		conditionsArea.setLineWrap(true);
		conditionsArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(conditionsArea);
		panel.add(scrollPane);
		
		//position the header text
		layout.putConstraint(SpringLayout.NORTH, header, 8, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, header, 160, SpringLayout.WEST, container);
				
		//position components
		layout.putConstraint(SpringLayout.NORTH, dateOfReviewLabel, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, dateOfReviewLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, day, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, day, 20, SpringLayout.EAST, dateOfReviewLabel);
		
		layout.putConstraint(SpringLayout.NORTH, month, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, month, 6, SpringLayout.EAST, day);
		
		layout.putConstraint(SpringLayout.NORTH, year, 30, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, year, 6, SpringLayout.EAST, month);
		
		
		layout.putConstraint(SpringLayout.NORTH, warningsLabel, 10, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, warningsLabel, 160, SpringLayout.WEST, container);
		//position yesRadio
		layout.putConstraint(SpringLayout.NORTH, yesRadio1, 6, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, yesRadio1, 50, SpringLayout.EAST, warningsLabel);
		//position noRadio
		layout.putConstraint(SpringLayout.NORTH, noRadio1, 6, SpringLayout.SOUTH, dateOfReviewLabel);
		layout.putConstraint(SpringLayout.WEST, noRadio1, 10, SpringLayout.EAST, yesRadio1);
		
		layout.putConstraint(SpringLayout.NORTH, reviewsLabel, 10, SpringLayout.SOUTH, warningsLabel);
		layout.putConstraint(SpringLayout.WEST, reviewsLabel, 160, SpringLayout.WEST, container);
		//position yesRadio
		layout.putConstraint(SpringLayout.NORTH, yesRadio2, 6, SpringLayout.SOUTH, warningsLabel);
		layout.putConstraint(SpringLayout.WEST, yesRadio2, 50, SpringLayout.EAST, reviewsLabel);
		//position noRadio
		layout.putConstraint(SpringLayout.NORTH, noRadio2, 6, SpringLayout.SOUTH, warningsLabel);
		layout.putConstraint(SpringLayout.WEST, noRadio2, 10, SpringLayout.EAST, yesRadio2);
		
		layout.putConstraint(SpringLayout.NORTH, misconductLabel, 10, SpringLayout.SOUTH, reviewsLabel);
		layout.putConstraint(SpringLayout.WEST, misconductLabel, 160, SpringLayout.WEST, container);
		//position yesRadio
		layout.putConstraint(SpringLayout.NORTH, yesRadio3, 6, SpringLayout.SOUTH, reviewsLabel);
		layout.putConstraint(SpringLayout.WEST, yesRadio3, 50, SpringLayout.EAST, misconductLabel);
		//position noRadio
		layout.putConstraint(SpringLayout.NORTH, noRadio3, 6, SpringLayout.SOUTH, reviewsLabel);
		layout.putConstraint(SpringLayout.WEST, noRadio3, 10, SpringLayout.EAST, yesRadio3);
		
		
		layout.putConstraint(SpringLayout.NORTH, dateOfTerminationLabel, 10, SpringLayout.SOUTH, misconductLabel);
		layout.putConstraint(SpringLayout.WEST, dateOfTerminationLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, day2, 6, SpringLayout.SOUTH, misconductLabel);
		layout.putConstraint(SpringLayout.WEST, day2, 20, SpringLayout.EAST, dateOfTerminationLabel);
		
		layout.putConstraint(SpringLayout.NORTH, month2, 6, SpringLayout.SOUTH, misconductLabel);
		layout.putConstraint(SpringLayout.WEST, month2, 6, SpringLayout.EAST, day2);
	    
		layout.putConstraint(SpringLayout.NORTH, year2, 6, SpringLayout.SOUTH, misconductLabel);
		layout.putConstraint(SpringLayout.WEST, year2, 6, SpringLayout.EAST, month2);
		
		layout.putConstraint(SpringLayout.NORTH, conditionsLabel, 10, SpringLayout.SOUTH, dateOfTerminationLabel);
		layout.putConstraint(SpringLayout.WEST, conditionsLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, dateOfTerminationLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.EAST, conditionsLabel);
		
		layout.putConstraint(SpringLayout.NORTH, firstAuthorisationLabel, 10, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, firstAuthorisationLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, firstReview, 10, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, firstReview, 20, SpringLayout.EAST, firstAuthorisationLabel);
		
		layout.putConstraint(SpringLayout.NORTH, secondAuthorisationLabel, 10, SpringLayout.SOUTH, firstAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, secondAuthorisationLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, secondReview, 10, SpringLayout.SOUTH, firstReview);
		layout.putConstraint(SpringLayout.WEST, secondReview, 20, SpringLayout.EAST, secondAuthorisationLabel);
		
		layout.putConstraint(SpringLayout.NORTH, headSectionApprovalLabel, 10, SpringLayout.SOUTH, secondAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, headSectionApprovalLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, sectionApproval, 10, SpringLayout.SOUTH, secondAuthorisationLabel);
		layout.putConstraint(SpringLayout.WEST, sectionApproval, 20, SpringLayout.EAST, headSectionApprovalLabel);
		
		layout.putConstraint(SpringLayout.NORTH, headPersonnelApprovalLabel, 10, SpringLayout.SOUTH, headSectionApprovalLabel);
		layout.putConstraint(SpringLayout.WEST, headPersonnelApprovalLabel, 160, SpringLayout.WEST, container);
		
		layout.putConstraint(SpringLayout.NORTH, personnelApproval, 10, SpringLayout.SOUTH, headSectionApprovalLabel);
		layout.putConstraint(SpringLayout.WEST, personnelApproval, 20, SpringLayout.EAST, headPersonnelApprovalLabel);
		
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
				dateOfReview = year.getText().trim() + "-" + month.getText().trim() + "-" + day.getText().trim();
				dateOfTermination = year2.getText().trim() + "-" + month2.getText().trim() + "-" + day2.getText().trim();
				if(newRecord){
					String query = "INSERT INTO terminationRecord(staffID, dateOfReview, warnings, reviews, misconduct, dateOfTermination, conditions, firstReviewerAuthorisation, secondReviewerAuthorisation, "
							+ " headOfSectionApproval, headOfPersonnelApproval) "
							+ "VALUES ('" + id + "', '" + dateOfReview + "', " + yesRadio1.isSelected() + ", "
							+ yesRadio2.isSelected() + ", " + yesRadio3.isSelected() + ", '" + dateOfTermination + "', '" + conditionsArea.getText().trim() + "', " + firstReview.isSelected() + ", " 
							+ secondReview.isSelected() + ", " + sectionApproval.isSelected() + ", " + personnelApproval.isSelected() + ")";
					database.updateDatabase(query);
				}else{
					String query = "UPDATE terminationRecord SET dateOfReview='" + dateOfReview + "', warnings=" + yesRadio1.isSelected() + ", reviews="
							+ yesRadio2.isSelected() + ", misconduct=" + yesRadio3.isSelected() + ", dateOfTermination='" + dateOfTermination + "', conditions='" 
							+ conditionsArea.getText().trim() + "', firstReviewerAuthorisation=" + firstReview.isSelected() + ", secondReviewerAuthorisation=" 
							+ secondReview.isSelected() + ", headOfSectionApproval=" + sectionApproval.isSelected() + ", headOfPersonnelApproval=" + personnelApproval.isSelected()
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
