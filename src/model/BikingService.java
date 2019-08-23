package model;

import java.io.IOException;
import java.sql.SQLException;

import model.dto.BUserDTO;

public class BikingService {
	
	//user CRUD
	public static boolean userAddd(String id, String pw, String name, String phone) throws SQLException, IOException{
		return BUserDAO.addUser(id, pw, name, phone);
	}
	
	public static boolean userUpdate(String id, String pw, String name, String phone) throws SQLException, IOException{
		return BUserDAO.updateUser(id, pw, name, phone);
	}
	
	public static BUserDTO userGet(String id) throws SQLException, IOException{
		return BUserDAO.getUser(id);
	}
	
	public static boolean userDelete(String id) throws SQLException, IOException{
		return BUserDAO.deleteUser(id);
	}
	
	//login
	public static BUserDTO userLogIn(String id) throws SQLException, IOException{
		return BUserDAO.logIn(id);
	}
}

