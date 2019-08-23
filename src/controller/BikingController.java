package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BUserDAO;
import model.dto.BUserDTO;

@WebServlet("/bikingcontroller")
public class BikingController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		try {
			if (command.equals("getUser")) {
				getUser(request, response);
			} else if (command.equals("addUser")) {
				addUser(request, response);
			}else if (command.equals("logIn")) {
				logIn(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		try {
			if(BUserDAO.addUser(id, pw, name, phone)) {
				request.getRequestDispatcher("view.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
		}
	}
	
	public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			BUserDTO user = BUserDAO.getUser(id);
			if (user != null) {
				request.setAttribute("id", user.getId());
				request.setAttribute("pw", user.getPw());
				request.setAttribute("name", user.getName());
				request.setAttribute("phone", user.getPhone());
				request.getRequestDispatcher("view.jsp").forward(request, response);
			} else {
				request.setAttribute("name", "없는 직원");
				request.getRequestDispatcher("view.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
			
		}
	}
	
	public void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			BUserDTO user = BUserDAO.logIn(id);
			if(pw.equals(user.getPw())) {
				request.getRequestDispatcher("success.html").forward(request, response);
			} else {
				response.sendRedirect("failView.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
			
		}
	}

}
