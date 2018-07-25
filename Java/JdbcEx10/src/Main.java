import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ConnTest1{
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ConnectionPool cp = null;
	
	public void select()
	{
		try
		{
			cp = ConnectionPool.getInstance();
			con = cp.getConnection();
			pstmt = con.prepareStatement("select count(*) from department");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println("count : " + rs.getInt(1) + ", ");
			}
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try 
			{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) cp.releaseConnection(con);
					
			}
			catch(SQLException _sqle)
			{
				_sqle.printStackTrace();
			}
		}
		//cp.closeAll();
	}
	
}

class ConnTest2 extends Thread
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ConnectionPool cp = null;
	
	//run()메소드 재정의
	@Override
	public void run()
	{
		try
		{
			String name = Thread.currentThread().getName();
			cp = ConnectionPool.getInstance();
			con = cp.getConnection();
			pstmt = con.prepareStatement("select count(*) from department");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(name + " : " + rs.getInt(1) + " : " +con );
			}
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			try 
			{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) cp.releaseConnection(con);
					
			}
			catch(SQLException _sqle)
			{
				_sqle.printStackTrace();
			}
		}
	}
}

public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		ConnTest1 ct = new ConnTest1();
		ct.select();
		
		for(int i = 0; i<200; i++)
		{
			Thread test = new ConnTest2();
			test.start();
			//0.01초 쉬기
			Thread.sleep(10);
		}
		
		
	}

}


