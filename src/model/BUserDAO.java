package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import model.dto.BUserDTO;
import model.util.DBUtil;

public class BUserDAO {
	static Properties propertiesInfo = new Properties();
	static {
		try {
			propertiesInfo.load(new FileInputStream("C:\\0.encore\\05.miniProject\\project01_biking\\dao.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BUserDTO getBUser() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BUserDTO user;

		try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(propertiesInfo.getProperty("BUserDAO.getuser"));
				//pstmt.setInt(1, empno);
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
	
}
