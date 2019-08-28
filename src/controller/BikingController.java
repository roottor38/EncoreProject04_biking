package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.BikingService;
import model.dto.BUserDTO;

@WebServlet("/bikingcontroller")
public class BikingController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(); // 생성 또는 이미 해당 user만의 고유한 세션이 존재할 경우 반환
		System.out.println("===" + session.getAttribute("id"));

		String command = request.getParameter("command");
		try {
			if (command.equals("getUser")) {
				getUser(request, response);
			} else if (command.equals("addUser")) {
				addUser(request, response);
			} else if (command.equals("logIn")) {
				logIn(request, response);
			} else if (command.equals("updateUserReq")) {// 재능 기부자 정보 수정요청
				updateUserReq(request, response);
			} else if (command.equals("userInfoReq")) {
				userInfoReq(request, response);
			} else if (command.equals("updateUser")) {
				updateUser(request, response);
			} else if (command.equals("getRentSpot")) {
				getRentSpot(request, response);
			} else if (command.equals("logOut")) {
				logOut(request, response);
			} else if (command.equals("addRentInfo")) {
				addRentInfo(request, response);
			} else if (command.equals("getBike")) {
				getBike(request, response);
			}
		} catch (Exception s) {
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
		BUserDTO user = new BUserDTO(id, pw, name, phone);
		try {
			if (BikingService.userAdd(user)) {
				request.getRequestDispatcher("successSignUp.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("failView.jsp");
		}
	}

	public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			BUserDTO user = BikingService.userGet(id);
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
		String url = "showError.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		try {
			BUserDTO user = BikingService.userLogIn(id);
			if (pw.equals(user.getPw())) {
				HttpSession session = request.getSession(); // 생성 또는 이미 해당 user만의 고유한 세션이 존재할 경우 반환
				session.setAttribute("id", id);
				url = "idCheck.jsp";
			} else {
				url = "showError.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("showError.jsp");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 유저 수정 요구
	public void userInfoReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "showError.jsp";
		HttpSession session = request.getSession();
		try {
			request.setAttribute("data", BikingService.userGet((String) session.getAttribute("id")));
			url = "userInfo.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String url = "showError.jsp";

		BUserDTO user = new BUserDTO(id, pw, name, phone);

		if (id == null || id.trim().length() == 0 || name == null || name.trim().length() == 0) {
			response.sendRedirect("bikingcontroller");
			return;
		}
		boolean result = false;
		try {
			result = BikingService.userUpdate(user);
			if (result) {
				request.setAttribute("data", BikingService.userGet(id));
				url = "userInfo.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "유저 수정 실패");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void updateUserReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "showError.jsp";
		HttpSession session = request.getSession();
		try {
			request.setAttribute("data", BikingService.userGet((String) session.getAttribute("id")));
			url = "userUpdate.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.html");
	}

	public void getRentSpot(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// HttpSession session = request.getSession();
		String url = "showError.jsp";
		try {
			request.setAttribute("rentSpot", BikingService.rentSpotGet(request.getParameter("rentSpotName")));
			url = "daum.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void getBike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		String rentSpotName = request.getParameter("rentSpotName");
		try {
			HttpSession session = request.getSession(); // 생성 또는 이미 해당 user만의 고유한 세션이 존재할 경우 반환
			session.setAttribute("rentSpotName", rentSpotName);
			request.setAttribute("rentSpot", BikingService.bikeGet(request.getParameter("rentSpotName")));

			url = "rentSpotDetail.jsp";
			System.out.println(session.getAttribute("rentSpotName"));
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void addRentInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int bikeId = Integer.parseInt(request.getParameter("bikeId"));
		String url = "showError.jsp";
		try {
			request.setAttribute("rentSpot",
					BikingService.rentInfoAdd((String) session.getAttribute("id"), bikeId, (String) session.getAttribute("rentSpotName")));
			url = "returnDaumAPI.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
