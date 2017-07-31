import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

// the performance review class - displays he performance review of a specific employee
public class PerformanceReview implements ActionListener{
	private JFrame frame;
	private Container container;
	private JPanel mainMenu;
	private JPanel panel;
	private DBConnect database;
	private int id;
	private String name;
	private JLabel header;
	private JLabel managerLabel;
	private JLabel secondManagerLabel;
	private JLabel sectionLabel;
	private JLabel jobLabel;
	private JLabel reviewSentenceLabel;
	private JLabel noLabel;
	private JLabel objectivesLabel;
	private JLabel achievementLabel;
	private JLabel performanceLabel;
	private JLabel previewSentenceLabel;
	private JLabel no2Label;
	private JLabel appraiseeCommentsLabel;
	private JLabel recommendationLabel;
	private JLabel appraiseeVerificationLabel;
	private JLabel managerVerificationLabel;
	private JLabel secondManagerVerificationLabel;
	private JTextField managerTextField;
	private JTextField secondManagerTextField;
	private JTextField sectionTextField;
	private JTextField jobTextField;
	private JTextArea noTextArea;
	private JTextArea objectivesTextArea;
	private JTextArea achievementTextArea;
	private JTextArea performanceTextArea;
	private JTextArea no2TextArea;
	private JTextArea previewTextArea;
	private JTextArea appraseeCommentsTextArea;
	private JComboBox<String> recommendation;
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	private JButton backButton;
	private JButton saveButton;
	private boolean newRecord;
	
	// constructor
	public PerformanceReview(JFrame frame, Container container, JPanel mainMenu, DBConnect database, int id, String name, boolean newRecord){
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
		ResultSet resultSet = database.getResults("SELECT * FROM performanceReview WHERE staffID ='" + id + "'");
		try {
			if(resultSet.next()){
				managerTextField.setText(resultSet.getString("manager"));
				secondManagerTextField.setText(resultSet.getString("secondManager"));
				sectionTextField.setText(resultSet.getString("section"));
				jobTextField.setText(resultSet.getString("jobTitle"));
				noTextArea.setText(resultSet.getString("noBox"));
				objectivesTextArea.setText(resultSet.getString("objectives"));
				achievementTextArea.setText(resultSet.getString("achievements"));
				performanceTextArea.setText(resultSet.getString("performance"));
				no2TextArea.setText(resultSet.getString("noBox2"));
				previewTextArea.setText(resultSet.getString("preview"));
				appraseeCommentsTextArea.setText(resultSet.getString("appraiseeComments"));
				recommendation.setSelectedItem(resultSet.getString("recommendation"));
				checkbox1.setSelected(resultSet.getBoolean("appraiseeVerification"));
				checkbox2.setSelected(resultSet.getBoolean("managerVerification"));
				checkbox3.setSelected(resultSet.getBoolean("secondManagerVerification"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e);
		}
	}
	
	private void buildHeader(SpringLayout layout){
		
	}
	
	// fills the frame
	private void buildPanel(){
		panel = new JPanel();
		container.add(panel);
		
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		header = new JLabel();
		header.setText("Performance review for " + name + " staff ID " + id);
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
		
		//initialise and add all labels
		managerLabel = new JLabel();
		managerLabel.setText("Manager/Director:");
		panel.add(managerLabel);
		
		secondManagerLabel = new JLabel();
		secondManagerLabel.setText("Second Manager/Director:");
		panel.add(secondManagerLabel);
		
		sectionLabel = new JLabel();
		sectionLabel.setText("Section:");
		panel.add(sectionLabel);
		
		jobLabel = new JLabel();
		jobLabel.setText("Job Title:");
		panel.add(jobLabel);
		
		reviewSentenceLabel = new JLabel();
		reviewSentenceLabel.setText("A review of past performance: achievements and outcomes:");
		panel.add(reviewSentenceLabel);
		
		noLabel = new JLabel();
		noLabel.setText("No:");
		panel.add(noLabel);
		
		objectivesLabel = new JLabel();
		objectivesLabel.setText("Objectives:");
		panel.add(objectivesLabel);
		
		achievementLabel = new JLabel();
		achievementLabel.setText("Achivement:");
		panel.add(achievementLabel);
		
		performanceLabel = new JLabel();
		performanceLabel.setText("Performance Summary:");
		panel.add(performanceLabel);
		
		previewSentenceLabel = new JLabel();
		previewSentenceLabel.setText("A preview of future performance :  goals/planned outcomes");
		panel.add(previewSentenceLabel);
		
		no2Label = new JLabel();
		no2Label.setText("No:");
		panel.add(no2Label);
		
		appraiseeCommentsLabel = new JLabel();
		appraiseeCommentsLabel.setText("Appraisee comments:");
		panel.add(appraiseeCommentsLabel);
		
		recommendationLabel = new JLabel();
		recommendationLabel.setText("Recommendation:");
		panel.add(recommendationLabel);
		
		appraiseeVerificationLabel = new JLabel();
		appraiseeVerificationLabel.setText("Appraisee Verification:");
		panel.add(appraiseeVerificationLabel);
		
		managerVerificationLabel = new JLabel();
		managerVerificationLabel.setText("Manager Verification:");
		panel.add(managerVerificationLabel);
		
		secondManagerVerificationLabel = new JLabel();
		secondManagerVerificationLabel.setText("Second Manager Verification:");
		panel.add(secondManagerVerificationLabel);
		
		//initialise and add all text fields
		managerTextField = new JTextField(12);
		panel.add(managerTextField);
		
		secondManagerTextField = new JTextField(12);
		panel.add(secondManagerTextField);
		
		sectionTextField = new JTextField(12);
		panel.add(sectionTextField);
		
		jobTextField = new JTextField(12);
		panel.add(jobTextField);
		
		noTextArea = new JTextArea(3, 2);
		noTextArea.setLineWrap(true);
		noTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(noTextArea);
		panel.add(scrollPane);
		
		objectivesTextArea = new JTextArea(3, 10);
		objectivesTextArea.setLineWrap(true);
		objectivesTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane2 = new JScrollPane(objectivesTextArea);
		panel.add(scrollPane2);
		
		achievementTextArea = new JTextArea(3, 15);
		achievementTextArea.setLineWrap(true);
		achievementTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane3 = new JScrollPane(achievementTextArea);
		panel.add(scrollPane3);
		
		performanceTextArea = new JTextArea(3, 15);
		performanceTextArea.setLineWrap(true);
		performanceTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane4 = new JScrollPane(performanceTextArea);
		panel.add(scrollPane4);
		
		no2TextArea = new JTextArea(3, 2);
		no2TextArea.setLineWrap(true);
		no2TextArea.setWrapStyleWord(true);
		JScrollPane scrollPane5 = new JScrollPane(no2TextArea);
		panel.add(scrollPane5);
		
		previewTextArea = new JTextArea(3, 15);
		previewTextArea.setLineWrap(true);
		previewTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane6 = new JScrollPane(previewTextArea);
		panel.add(scrollPane6);
		
		appraseeCommentsTextArea = new JTextArea(3, 15);
		appraseeCommentsTextArea.setLineWrap(true);
		appraseeCommentsTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane7 = new JScrollPane(appraseeCommentsTextArea);
		panel.add(scrollPane7);
		
		recommendation = new JComboBox<String>();
		recommendation.addItem("Stay in post");
		recommendation.addItem("Salary increase");
		recommendation.addItem("Promotion");
		recommendation.addItem("Probation");
		recommendation.addItem("Termination");
		panel.add(recommendation);
		
		checkbox1 = new JCheckBox();
		panel.add(checkbox1);
		checkbox2 = new JCheckBox();
		panel.add(checkbox2);
		checkbox3 = new JCheckBox();
		panel.add(checkbox3);
		
		//position the managerLabel
		layout.putConstraint(SpringLayout.NORTH, managerLabel, 20, SpringLayout.NORTH, header);
		layout.putConstraint(SpringLayout.WEST, managerLabel, 160, SpringLayout.WEST, container);
		//position the managerTextField
		layout.putConstraint(SpringLayout.NORTH, managerTextField, 20, SpringLayout.NORTH, header);
		layout.putConstraint(SpringLayout.WEST, managerTextField, 50, SpringLayout.EAST, managerLabel);
		//position the secondManagerLabel
		layout.putConstraint(SpringLayout.NORTH, secondManagerLabel, 10, SpringLayout.SOUTH, managerLabel);
		layout.putConstraint(SpringLayout.WEST, secondManagerLabel, 160, SpringLayout.WEST, container);
		//position the secondManagerTextField
		layout.putConstraint(SpringLayout.NORTH, secondManagerTextField, 6, SpringLayout.SOUTH, managerTextField);
		layout.putConstraint(SpringLayout.WEST, secondManagerTextField, 50, SpringLayout.EAST, secondManagerLabel);
		//position the sectionLabel
		layout.putConstraint(SpringLayout.NORTH, sectionLabel, 10, SpringLayout.SOUTH, secondManagerLabel);
		layout.putConstraint(SpringLayout.WEST, sectionLabel, 160, SpringLayout.WEST, container);
		//position the sectionTextField
		layout.putConstraint(SpringLayout.NORTH, sectionTextField, 6, SpringLayout.SOUTH, secondManagerTextField);
		layout.putConstraint(SpringLayout.WEST, sectionTextField, 50, SpringLayout.EAST, sectionLabel);
		//position the jobLabel
		layout.putConstraint(SpringLayout.NORTH, jobLabel, 10, SpringLayout.SOUTH, sectionLabel);
		layout.putConstraint(SpringLayout.WEST, jobLabel, 160, SpringLayout.WEST, container);
		//position the jobTextField
		layout.putConstraint(SpringLayout.NORTH, jobTextField, 6, SpringLayout.SOUTH, sectionTextField);
		layout.putConstraint(SpringLayout.WEST, jobTextField, 50, SpringLayout.EAST, jobLabel);
		//position the reviewSentenceLabel
		layout.putConstraint(SpringLayout.NORTH, reviewSentenceLabel, 10, SpringLayout.SOUTH, jobLabel);
		layout.putConstraint(SpringLayout.WEST, reviewSentenceLabel, 160, SpringLayout.WEST, container);
		//position the noLabel
		layout.putConstraint(SpringLayout.NORTH, noLabel, 5, SpringLayout.SOUTH, reviewSentenceLabel);
		layout.putConstraint(SpringLayout.WEST, noLabel, 160, SpringLayout.WEST, container);
		//position the objectivesLabel
		layout.putConstraint(SpringLayout.NORTH, objectivesLabel, 5, SpringLayout.SOUTH, reviewSentenceLabel);
		layout.putConstraint(SpringLayout.WEST, objectivesLabel, 28, SpringLayout.WEST, noLabel);
		//position the achievementLabel
		layout.putConstraint(SpringLayout.NORTH, achievementLabel, 5, SpringLayout.SOUTH, reviewSentenceLabel);
		layout.putConstraint(SpringLayout.WEST, achievementLabel, 120, SpringLayout.WEST, objectivesLabel);
		//position the noTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, noLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 162, SpringLayout.EAST, container);
		//position the objectivesTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane2, 6, SpringLayout.SOUTH, objectivesLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane2, 1, SpringLayout.EAST, scrollPane);
		//position the achievementTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane3, 6, SpringLayout.SOUTH, achievementLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane3, 1, SpringLayout.EAST, scrollPane2);
		//position the performanceLabel
		layout.putConstraint(SpringLayout.NORTH, performanceLabel, 5, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, performanceLabel, 160, SpringLayout.WEST, container);
		//position the performanceTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane4, 6, SpringLayout.SOUTH, performanceLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane4, 160, SpringLayout.EAST, container);
		//position the previewSentenceLabel
		layout.putConstraint(SpringLayout.NORTH, previewSentenceLabel, 5, SpringLayout.SOUTH, scrollPane4);
		layout.putConstraint(SpringLayout.WEST, previewSentenceLabel, 160, SpringLayout.WEST, container);
		//position the no2Label
		layout.putConstraint(SpringLayout.NORTH, no2Label, 5, SpringLayout.SOUTH, previewSentenceLabel);
		layout.putConstraint(SpringLayout.WEST, no2Label, 160, SpringLayout.WEST, container);
		//position the no2TextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane5, 6, SpringLayout.SOUTH, no2Label);
		layout.putConstraint(SpringLayout.WEST, scrollPane5, 162, SpringLayout.EAST, container);
		//position the previewTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane6, 6, SpringLayout.SOUTH, no2Label);
		layout.putConstraint(SpringLayout.WEST, scrollPane6, 1, SpringLayout.EAST, scrollPane5);
		//position the appraiseeCommentsLabel
		layout.putConstraint(SpringLayout.NORTH, appraiseeCommentsLabel, 5, SpringLayout.SOUTH, scrollPane5);
		layout.putConstraint(SpringLayout.WEST, appraiseeCommentsLabel, 160, SpringLayout.WEST, container);
		//position the previewTextArea
		layout.putConstraint(SpringLayout.NORTH, scrollPane7, 6, SpringLayout.SOUTH, appraiseeCommentsLabel);
		layout.putConstraint(SpringLayout.WEST, scrollPane7, 160, SpringLayout.EAST, container);
		//position the recommendationLabel
		layout.putConstraint(SpringLayout.NORTH, recommendationLabel, 10, SpringLayout.SOUTH, scrollPane7);
		layout.putConstraint(SpringLayout.WEST, recommendationLabel, 160, SpringLayout.WEST, container);
		//position the recommendation
		layout.putConstraint(SpringLayout.NORTH, recommendation, 6, SpringLayout.SOUTH, scrollPane7);
		layout.putConstraint(SpringLayout.WEST, recommendation, 20, SpringLayout.EAST, recommendationLabel);
		//position the appraiseeVerificationLabel
		layout.putConstraint(SpringLayout.NORTH, appraiseeVerificationLabel, 5, SpringLayout.SOUTH, recommendationLabel);
		layout.putConstraint(SpringLayout.WEST, appraiseeVerificationLabel, 160, SpringLayout.WEST, container);
		//position checkbox1
		layout.putConstraint(SpringLayout.NORTH, checkbox1, 3, SpringLayout.SOUTH, recommendationLabel);
		layout.putConstraint(SpringLayout.WEST, checkbox1, 20, SpringLayout.EAST, appraiseeVerificationLabel);
		//position the managerVerificationLabel
		layout.putConstraint(SpringLayout.NORTH, managerVerificationLabel, 5, SpringLayout.SOUTH, appraiseeVerificationLabel);
		layout.putConstraint(SpringLayout.WEST, managerVerificationLabel, 160, SpringLayout.WEST, container);
		//position checkbox2
		layout.putConstraint(SpringLayout.NORTH, checkbox2, 1, SpringLayout.SOUTH, checkbox1);
		layout.putConstraint(SpringLayout.WEST, checkbox2, 20, SpringLayout.EAST, managerVerificationLabel);
		//position the secondManagerVerificationLabel
		layout.putConstraint(SpringLayout.NORTH, secondManagerVerificationLabel, 5, SpringLayout.SOUTH, managerVerificationLabel);
		layout.putConstraint(SpringLayout.WEST, secondManagerVerificationLabel, 160, SpringLayout.WEST, container);
		//position checkbox3
		layout.putConstraint(SpringLayout.NORTH, checkbox3, 0, SpringLayout.SOUTH, checkbox2);
		layout.putConstraint(SpringLayout.WEST, checkbox3, 20, SpringLayout.EAST, secondManagerVerificationLabel);
		
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
			if(newRecord){
				String query = "INSERT INTO performanceReview(staffID, manager, secondManager, section, jobTitle, noBox, objectives, achievements"
						+ ", performance, noBox2, preview, appraiseeComments, recommendation, appraiseeVerification, managerVerification, secondManagerVerification) "
						+ "VALUES ('" + id + "', '" + managerTextField.getText().trim() + "', '" + secondManagerTextField.getText().trim() + "', '"
						+ sectionTextField.getText().trim() + "', '" + jobTextField.getText().trim() + "', '" + noTextArea.getText().trim() + "', '" 
						+ objectivesTextArea.getText().trim() + "', '" + achievementTextArea.getText().trim() + "', '" + performanceTextArea.getText().trim() + "', '"
						+ no2TextArea.getText().trim() + "', '" + previewTextArea.getText().trim() + "', '" + appraseeCommentsTextArea.getText().trim() + "', '"
						+ recommendation.getSelectedItem() + "', " + checkbox1.isSelected() + ", " + checkbox2.isSelected() + ", " + checkbox3.isSelected() + ")";
				database.updateDatabase(query);
			}else{
				String query = "UPDATE performanceReview SET manager='" + managerTextField.getText().trim() + "', secondManager='" + secondManagerTextField.getText().trim() + "', section='"
						+ sectionTextField.getText().trim() + "', jobTitle='" + jobTextField.getText().trim() + "', noBox='" + noTextArea.getText().trim() + "', objectives='" 
						+ objectivesTextArea.getText().trim() + "', achievements='" + achievementTextArea.getText().trim() + "', performance='" + performanceTextArea.getText().trim() + "', noBox2='"
						+ no2TextArea.getText().trim() + "', preview='" + previewTextArea.getText().trim() + "', appraiseeComments='" + appraseeCommentsTextArea.getText().trim() + "', recommendation='"
						+ recommendation.getSelectedItem() + "', appraiseeVerification=" + checkbox1.isSelected() + ", managerVerification=" + checkbox2.isSelected() + ", secondManagerVerification=" + checkbox3.isSelected()
						+ " WHERE staffID=" + id;
				database.updateDatabase(query);
			}
			JOptionPane.showMessageDialog(frame, "Record successfully saved.");
			backButton.doClick();
		}
	}
}
