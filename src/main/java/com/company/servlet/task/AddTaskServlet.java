package com.company.servlet.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.company.todo.Todo;
import com.company.todo.TodoImpl;

/**
 * Servlet implementation class AddTaskServlet
 */
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Todo todo = new TodoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
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
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String task = request.getParameter("task");
		String due_date = request.getParameter("due_date");
		
		boolean ans = todo.add(user_id, task, due_date);
		if(ans) {
			HttpSession session = request.getSession();
		    session.setAttribute("status", "success");
		    response.sendRedirect("todo.jsp");
		} else {
			HttpSession session = request.getSession();
		    session.setAttribute("status", "fail");
		    response.sendRedirect("todo.jsp");
		}
	}

}
