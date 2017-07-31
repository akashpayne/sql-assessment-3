import java.sql.*;
import javax.swing.JOptionPane;

// dbConnect class. sets up the initial database connection
public class DBConnect {
	private Connection con;
	private Statement statement;
	private ResultSet resultSet;
	private int x = 1;
	
	// constructor - connects to the database
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://dragon.kent.ac.uk/co510_19", "co510_19", "ahlea5d");
			statement = con.createStatement();
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	// Method to search the database
	public ResultSet getResults(String query){
		try{
			resultSet = statement.executeQuery(query);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return resultSet;
	}
	
	// Method to update values in the database
	public void updateDatabase(String query){
		try{
			statement.executeUpdate(query);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
}
