package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.BUserDTO;
import model.util.DBUtil;

public class BUserDAO {
	//CURD
	public static boolean addUser(String id, String pw, String name, String phone) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BUserDAO.addUser"));
				pstmt.setString(1, id);
				pstmt.setString(2, pw); 
				pstmt.setString(3, name);
				pstmt.setString(4, phone);
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateUser(String id, String pw, String name, String phone) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BUserDAO.updateUser"));
				pstmt.setString(1, pw); 
				pstmt.setString(2, name);
				pstmt.setString(3, phone);
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
		
	public static BUserDTO getUser(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BUserDTO user;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BUserDAO.getUser"));
				pstmt.setString(1, id);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					user = new BUserDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4));
					return user;
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
	public static boolean deleteUser(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BUserDAO.deleteUser"));
				pstmt.setString(1, id);
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	
	public static BUserDTO logIn(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BUserDTO user;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BUserDAO.logIn"));
				pstmt.setString(1, id);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					user = new BUserDTO(rset.getString(1), rset.getString(2));
					return user;
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
}
