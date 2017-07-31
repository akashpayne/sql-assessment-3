// the main (runnable) class
public class Main {

	// runs the login class initially
	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		new Frame("Login", db);
	}
}
