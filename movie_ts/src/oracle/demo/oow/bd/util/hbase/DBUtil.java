package oracle.demo.oow.bd.util.hbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil
{
	
	static String db_username = ConstantsHBase.MYSQL_USERNAME;
	static String db_password = ConstantsHBase.MYSQL_PASSWORD;
	static String DIVER = ConstantsHBase.MYSQL_DRIVER;
	static String URL = ConstantsHBase.MYSQL_URL;
	
	public static Connection getConn()
	{
		Connection conn = null;
		try
		{
			Class.forName(DIVER);
			conn = DriverManager.getConnection(URL, db_username, db_password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Statement state, Connection conn)
	{
		if(state != null)
		{
			try
			{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs, Statement state, Connection conn)
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(state != null)
		{
			try
			{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
