package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.BUserDTO;
import model.dto.BikeDTO;
import model.dto.RentSpotDTO;
import model.util.DBUtil;

public class BikeDAO {
	
	//CURD
	public static boolean addBike(String rentSpotName) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BikeDAO.addBike"));
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
		
	public static ArrayList<BikeDTO> getAllBike() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BikeDTO> bike = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BikeDAO.getAllBike"));
				rset = pstmt.executeQuery();
				bike = new ArrayList<BikeDTO>();
				while(rset.next()) {
					bike.add(new BikeDTO(rset.getInt(1), rset.getString(2)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return bike;
	}
	
	public static ArrayList<BikeDTO> getBike(String rentSpotName) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BikeDTO> bike = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BikeDAO.getBike"));
				pstmt.setString(1, rentSpotName);
				rset = pstmt.executeQuery();
				bike = new ArrayList<BikeDTO>();
				while(rset.next()) {
					bike.add(new BikeDTO(rset.getInt(1), rset.getString(2)));
				}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return bike;
	}
	
	public static boolean deleteBike(int bikeId) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BikeDAO.deleteBike"));
				pstmt.setInt(1, bikeId);
				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean updateBike(String spot, int bikeId) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("BikeDAO.updateBike"));
				pstmt.setString(1, spot);
				pstmt.setInt(2, bikeId); 

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
