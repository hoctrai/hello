package connectdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.*;

public class myPhpConnect {
	
	public static Connection getMySqlConnection() throws SQLException, ClassNotFoundException {
		
		String hostName = "localhost";
		String dbName = "hello";
		String userName = "root";
		String password ="mysql";
		
		return getServerConnection(hostName, dbName, userName, password );
	}
	
	public static Connection getServerConnection(String hostName, String dbName, String userName, String password) 
	throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
		
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
		
	}
	
	public static int insertDB(String name) throws SQLException, ClassNotFoundException{
		int candidateId;
		ResultSet rs=null;
		String sql="Insert into hello.staff(name) values(?)";
		 try (Connection conn = MySQLJDBCUtil.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			 
			 pstmt.setString(1, name);
			 
			 int rowAffected = pstmt.executeUpdate();
			 if(rowAffected == 1){
				 rs = pstmt.getGeneratedKeys();
				 if(rs.next())
	                    candidateId = rs.getInt(1);
			 }
		 } catch (SQLException ex) {
			 System.out.println(ex.getMessage());
		 } finally {
			 try {
				 if(rs != null)  rs.close();
			 } catch (SQLException e) {
				 System.out.println(e.getMessage());
			 	}
		 }
		 return candidateId;
	}
		
}
