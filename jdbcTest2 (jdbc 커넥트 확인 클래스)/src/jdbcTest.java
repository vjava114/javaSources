import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class jdbcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://220.85.13.250:3306/push", "rcm", "rcm123");
			System.out.println("Success!");
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}


	}

}
