import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class test00 {
		public static void main(String[] args)
		{

			String st = "bbbb";
			String name = "aaaa";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"scott",
						"tiger");
				
				String sql = "insert into member(block) values('" + name + ","+ st +"')";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			

		}
}
