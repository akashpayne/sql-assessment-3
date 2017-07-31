import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnect {
	private Connection con;
	private Statement statement;
	private ResultSet resultSet;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			/* update connection */
			con = DriverManager.getConnection("jdbc:****", "co510", "ap567");
			statement = con.createStatement();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	public ResultSet getResults(String query){
		try{
			resultSet = statement.executeQuery(query);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return resultSet;
	}
	
	public void updateDatabase(String query){
		try{
			statement.executeUpdate(query);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
}
