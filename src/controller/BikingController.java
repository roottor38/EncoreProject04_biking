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

import model.BUserDAO;
import model.BikingService;
import model.dto.BUserDTO;
@WebServlet("/bikingcontroller")
public class BikingController extends HttpServlet {
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      


      HttpSession session = request.getSession(); // 생성 또는 이미 해당 user만의 고유한 세션이 존재할 경우 반환
      System.out.println("===" + session.getAttribute("id"));

   
      String command = request.getParameter("command");
      try {
         if (command.equals("getUser")) {
            getUser(request, response);
         } else if (command.equals("addUser")) {
            addUser(request, response);
         }else if (command.equals("logIn")) {
            logIn(request, response);
         }else if(command.equals("updateUserReq")){//재능 기부자 정보 수정요청
            updateUserReq(request, response);
         } else if (command.equals("updateUser")) {
            updateUser(request, response);
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
      BUserDTO user = new BUserDTO(id,pw, name, phone);
      try {
         if(BUserDAO.addUser(user)) {
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
         if(pw.equals(user.getPw())) {
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
   
   //유저 수정 요구
   public void updateUserReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
      
      String url = "showError.jsp";
      HttpSession session = request.getSession();
      try {
         request.setAttribute("data", BUserDAO.getUser((String) session.getAttribute("id")));
         url = "userDetail.jsp";
      }catch(Exception s){
         request.setAttribute("errorMsg", s.getMessage());
      }
      request.getRequestDispatcher(url).forward(request, response);
   }
   
   public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String id=request.getParameter("id");
      String pw=request.getParameter("pw");
      String name=request.getParameter("name");
      String phone=request.getParameter("phone");
      
      if(id == null || id.trim().length() == 0 ||
         name == null || name.trim().length() == 0){
               response.sendRedirect("bikingcontroller");
               return;
         }
         boolean result = false;
         try {
            result = BUserDAO.updateUser(id, pw, name, phone);
            request.setAttribute("user", BikingService.userGet(id));
         } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "유저 수정 실패");
         }
         if(result){
         request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
         }
         request.getRequestDispatcher("showError.jsp").forward(request, response);
      }
}