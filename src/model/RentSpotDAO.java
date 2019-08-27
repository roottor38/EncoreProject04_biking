package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.BUserDTO;
import model.dto.RentSpotDTO;
import model.util.DBUtil;

public class RentSpotDAO {
	
	public static boolean addRentSpot(RentSpotDTO RentSpot) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentSpotDAO.addRentSpotr"));
				pstmt.setString(1, RentSpot.getRentSpotName());
				pstmt.setInt(2, RentSpot.getNumBike());

				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateRentSpot(String rentSpotName, int numBike) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentSpotDAO.updateRentSpot"));
				pstmt.setInt(1, numBike);
				pstmt.setString(2, rentSpotName);

				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
		
	public static ArrayList<RentSpotDTO> getAllRentSpot() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RentSpotDTO> spot = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentSpotDAO.getAllRentSpot"));
				rset = pstmt.executeQuery();
				spot = new ArrayList<RentSpotDTO>();
				while(rset.next()) {
					spot.add(new RentSpotDTO(rset.getString(1), rset.getInt(2)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return spot;
	}
	
	public static RentSpotDTO getRentSpot(String rentSpotName) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RentSpotDTO spot;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentSpotDAO.getRentSpot"));
				pstmt.setString(1, rentSpotName);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					spot = new RentSpotDTO(rset.getString(1), rset.getInt(2));
					return spot;
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
	
	public static boolean deleteRentSpot(String rentSpotName) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("RentSpotDAO.deleteUser"));
				pstmt.setString(1, rentSpotName);
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
