package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.BUserDTO;
import model.util.DBUtil;

public class ReturnInfoDAO {
	
	public static boolean addReturnInfo(int id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.addReturnInfo"));
				pstmt.setInt(1, id);
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateReturnInfo() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.updateUser"));
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
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.getUser"));
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
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.deleteUser"));
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
	

}
