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


/**
 * Servlet implementation class TestController
 */
@WebServlet("/testcontroller")
public class TestController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BUserDTO user = BUserDAO.getBUser();
			if(user != null) {
				request.setAttribute("id", user.getId());
				request.setAttribute("pw", user.getPw());
				request.setAttribute("name", user.getName());
				request.setAttribute("phone", user.getPhone());
				request.getRequestDispatcher("view.jsp").forward(request, response);
			}else {
				request.setAttribute("name", "없는 직원");
				request.getRequestDispatcher("view.jsp").forward(request, response);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
		}
	}

}
