import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import junit.framework.*;

// tests class. runs some suitable tests on the project
public class Tests extends TestCase{
	
	private DBConnect db;
	private ResultSet resultSet;
	
	// connect to the database in order to test project functionality
	public void setUp(){
		db = new DBConnect();
	}
	
	// test the login feature
	public void testLogin(){
		//test a string which should work
		String correctLogin = "SELECT username,password FROM staff WHERE username='test1' AND password = 'test1'";
		resultSet = db.getResults(correctLogin);
		try {
			assertEquals(resultSet.next(), true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		//test a string which should fail
		String incorrectLogin = "SELECT username,password FROM staff WHERE username='abc' AND password = 'abc'";
		resultSet = db.getResults(incorrectLogin);
		try {
			assertEquals(resultSet.next(), false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	// test the search feature
	public void testSearch(){
		//should return no results
		String query = "SELECT staffID FROM personalRecord WHERE name='john' AND surname=''";
		resultSet = db.getResults(query);
		try {
			assertEquals(resultSet.next(), false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		//should return true
		String query2 = "SELECT staffID FROM personalRecord WHERE name='john' AND surname='smith'";
		resultSet = db.getResults(query2);
		try {
			assertEquals(resultSet.next(), true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
