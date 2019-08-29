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
	
	public static boolean addReturnInfo(String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("running addReturnInfo ");

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.addReturnInfo"));
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
	
	public static boolean returnBike(String rentSpotName, String id) throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(DBUtil.getproperties().getProperty("ReturnInfoDAO.returnBike"));
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

}
