import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test00 {
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String name;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			name = "aaa";
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");
			String sql = "insert into member values(" + "'"+name+"')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if (con != null) con.close();
			}
			catch(SQLException sqle) {}
		}
		
		

	}

}
