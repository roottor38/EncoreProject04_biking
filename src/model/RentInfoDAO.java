package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.RentInfoDTO;
import model.util.DBUtil;

public class RentInfoDAO {
	
	public static int checkRentStatus(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.checkRentStatus"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1) ;
				return result;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return 0;
	}
	
	public static boolean addRentInfo(String id, int bikeId) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				if(checkRentStatus(id) == 0) {
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.addRentInfo"));
					pstmt.setInt(1, bikeId);
					pstmt.setString(2, id);
					pstmt.setInt(3, bikeId);
	
					int result = pstmt.executeUpdate();
					if (result == 1) {
						updateUserStatus(1, id);
						return true;
					}
				}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateUserStatus(int userStatus, String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.updateUserStatus"));
				pstmt.setInt(1, userStatus);
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
		
	public static ArrayList<RentInfoDTO> getAllRentInfo() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RentInfoDTO> info = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.getAllRentInfo"));
				rset = pstmt.executeQuery();
				info = new ArrayList<RentInfoDTO>();
				while(rset.next()) {
					info.add(new RentInfoDTO(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4),rset.getString(5)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return info;
	}
	
	public static RentInfoDTO getRentInfo(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RentInfoDTO info;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.getRentInfo"));
				pstmt.setString(1, id);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					info = new RentInfoDTO(rset.getInt(1), rset.getString(2), rset.getInt(3), rset.getString(4),rset.getString(5));
					return info;
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
	public static boolean deleteRentInfo(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentInfoDAO.deleteRentInfo"));
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
