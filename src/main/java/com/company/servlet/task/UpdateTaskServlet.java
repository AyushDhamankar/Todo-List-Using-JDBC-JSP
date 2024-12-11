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
 * Servlet implementation class UpdateTaskServlet
 */
public class UpdateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Todo todo = new TodoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskServlet() {
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
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String new_task = request.getParameter("new_task");
		
		boolean ans = todo.update(task_id, new_task);
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
