package connectdb;

import java.sql.Connection;
import java.sql.SQLException;
public class connect {
	public static Connection getMyConnect() throws SQLException, ClassNotFoundException{
		
		return myPhpConnect.getMySqlConnection();
		
	}
	
	public static void main(String[] arg ) throws SQLException, ClassNotFoundException {
		
		System.out.println("Connecting db.....");
		Connection conn = connect.getMyConnect();
		System.out.println("connected db " + conn);
	}
}