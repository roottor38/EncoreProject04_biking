package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import model.dto.BUserDTO;
import model.dto.BikeDTO;
import model.dto.RentInfoDTO;
import model.dto.RentSpotDTO;

public class BikingService {
	
	//user CRUD
	public static boolean userAdd(BUserDTO user) throws SQLException, IOException{
		return BUserDAO.addUser(user);
	}
	
	public static boolean userUpdate(BUserDTO user) throws SQLException, IOException{
		return BUserDAO.updateUser(user);
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
	
	//RentSpot CRUD
	public static boolean rnetSpotAdd(RentSpotDTO RentSpot) throws SQLException, IOException{
		return RentSpotDAO.addRentSpot(RentSpot);
	}
	
	public static boolean rentSpotUpdate(String rentSpotName, int numBike) throws SQLException, IOException{
		return RentSpotDAO.updateRentSpot(rentSpotName, numBike);
	}
	
	public static ArrayList<RentSpotDTO> rentSpotGetAll(String rentSpotName, int numBike) throws SQLException, IOException{
		return RentSpotDAO.getAllRentSpot();
	}
	
	public static RentSpotDTO rentSpotGet(String rentSpotName) throws SQLException, IOException{
		return RentSpotDAO.getRentSpot(rentSpotName);
	}
	
	public static boolean rentSpotDelete(String rentSpotName) throws SQLException, IOException{
		return RentSpotDAO.deleteRentSpot(rentSpotName);
	}
	
	//bike CRUD
	public static boolean bikeAdd(String rentSpotName) throws SQLException, IOException{
		return BikeDAO.addBike(rentSpotName);
	}
	
	public static ArrayList<BikeDTO> bikeGetAll() throws SQLException, IOException{
		return BikeDAO.getAllBike();
	}
	
	public static ArrayList<BikeDTO> bikeGet(String rentSpotName) throws SQLException, IOException{
		return BikeDAO.getBike(rentSpotName);
	}
	
	public static boolean bikeDelete(int bikeId) throws SQLException, IOException{
		return BikeDAO.deleteBike(bikeId);
	}
	
	
	//RentInfoDAO
	public static boolean rentInfoAdd(String id, int bikeId, String rentSpotName) throws SQLException, IOException{
		String rent = "대여중";
		if(RentInfoDAO.checkRentStatus(id)==1) {
			return false;
		}else if(RentInfoDAO.checkRentStatus(id) == 0) {
			return RentInfoDAO.addRentInfo(id, bikeId) && RentInfoDAO.updateUserStatus(1, id) && BikeDAO.updateBike(rent, bikeId)
					&& RentSpotDAO.plusNumBike(rent) && RentSpotDAO.minusNumBike(rentSpotName);
		}else {
			return false;
		}
	}
	
	public static ArrayList<RentInfoDTO> getAllRentInfo() throws SQLException, IOException{
		return RentInfoDAO.getAllRentInfo();
	}	
	
}

