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
	
	public static boolean updateReturnInfo(String rentSpotName, String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.updateReturnInfo"));
				pstmt.setString(1, rentSpotName);
				pstmt.setString(2, id);
				
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
		
	public static BUserDTO getReturnInfo(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BUserDTO user;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.getReturnInfo"));
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
	
	public static boolean deleteReturnInfo(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.deleteReturnInfo"));
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
