package com.company.servlet.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.auth.UserAuth;
import com.company.auth.UserAuthImpl;	
import com.company.data.UserStruct;

/**
 * Servlet implementation class UserLoginServlet
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserAuth user = new UserAuthImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserStruct ans = user.login(username, password);

		if (ans != null) {
		    // Successful login
		    HttpSession session = request.getSession();
		    session.setAttribute("user", ans);
		    response.sendRedirect("todo.jsp");
		} else {
			request.setAttribute("status", "error");
			RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
			dispatch.forward(request, response);
		}
	}

}
