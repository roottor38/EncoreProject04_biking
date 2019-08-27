package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BUserDAO;
import model.BikeDAO;
import model.RentSpotDAO;
import model.dto.BUserDTO;
import model.dto.RentSpotDTO;


@WebServlet("/testcontroller")
public class TestController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		try {
			if (command.equals("getAll")) {
				getAllTest(request, response);
			} else if(command.equals("add")) {
				addTest(request, response);
			}else if(command.equals("getOne")) {
				getOneTest(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}
	
	public void getAllTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			request.setAttribute("dataAll", BikeDAO.getAllBike());
			request.getRequestDispatcher("view.jsp").forward(request, response);
		
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
			
		}
	}
	
	public void addTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rentSpotName = request.getParameter("rentSpotName");
		try {
				boolean result = BikeDAO.addBike(rentSpotName);
				if(result) {
					System.out.println("성공");
				}
		
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
			
		}
	}
	
	public void getOneTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			
			request.setAttribute("data", BUserDAO.getUser(id));
			request.getRequestDispatcher("view.jsp").forward(request, response);
		
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
			
		}
	}
	
	
}
