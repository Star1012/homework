package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBBean {
	
	private Connection conn = null; 
	 /** 
     * 取得一个MySql数据库连接 
     *  
     * @return 
     * @throws SQLException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws ClassNotFoundException 
     */  
    public Connection getConnection() throws SQLException,  
            InstantiationException, IllegalAccessException,  
            ClassNotFoundException {  
         
        // 加载数据库驱动类  
        /** 
         * 这是SqlServer的情形； 
         * Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver" 
         * ).newInstance(); 
         */  
        Class.forName("com.mysql.jdbc.Driver");  
        // 数据库连接URL  
        /** 
         * 这是SqlServer的情形； 
         *  
         * String url = 
         * "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=Northwind"; 
         */  
        String url = "jdbc:mysql://localhost:3306/58project";  
        // 数据库用户名  
        String user = "root";  
        // 数据库密码  
        String password = "153532";  
        // 根据数据库参数取得一个数据库连接  
        conn = DriverManager.getConnection(url, user, password);  
        return conn;  
    }  

    /** 
     * 根据传入的SQL语句返回一个结果集 
     *  
     * @param sql 
     * @return 
     * @throws Exception 
     */  
    public ResultSet select(String sql) throws Exception {    
        Statement stmt = null;  
        ResultSet rs = null;  
        try {  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql);  
            return rs;  
        } catch (SQLException sqle) {  
            throw new SQLException("select data exception: "  
                    + sqle.getMessage());  
        } catch (Exception e) {  
            throw new Exception("System e exception: " + e.getMessage());  
        }  

    }  

    /** 
     * 根据传入的SQL语句向数据库增加一条记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
    public void insert(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("insert data exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }  

    /** 
     * 根据传入的SQL语句更新数据库记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
    public void update(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("update exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }  

    /** 
     * 根据传入的SQL语句删除一条数据库记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
    public void delete(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("delete data exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }  
    
    public void colseConn()
    {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
