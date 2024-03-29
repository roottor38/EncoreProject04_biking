package model.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
static DataSource source = null;


	static{		 
		try {
			Context initContext = new InitialContext();		
			Context envContext	= (Context)initContext.lookup("java:/comp/env");
			source = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch (NamingException e) {			
			e.printStackTrace();
		}	
	}
	public static Connection getConnection() throws SQLException{
		return source.getConnection();
	}
	
	public static Properties getproperties() throws SQLException, IOException{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties");
		Properties properties = new Properties();
		properties.load(is); 
		return properties; 
	}

	// DML용
	public static void close(Connection con, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// SELECT용
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}