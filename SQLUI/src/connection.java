import java.sql.*;
public class connection {
	public static void main(String[] args) {

	      String connectionUrl = "jdbc:sqlserver://titan.csse.rose-hulman.edu:1433;" +
	         "databaseName=metal;user=zhangb2;password=Hyhy5920!";

	      Connection con = null;
	      Statement stmt = null;
	      ResultSet rs = null;
   try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         con = DriverManager.getConnection(connectionUrl);
	         String SQL = "SELECT name FROM Album ";
	         stmt = con.createStatement();
	         rs = stmt.executeQuery(SQL);
	         while (rs.next()) {
	            System.out.println(rs.getString(1));
	         }
	      }

	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         if (rs != null) try { rs.close(); } catch(Exception e) {}
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	         if (con != null) try { con.close(); } catch(Exception e) {}
	      }
	   }
}
