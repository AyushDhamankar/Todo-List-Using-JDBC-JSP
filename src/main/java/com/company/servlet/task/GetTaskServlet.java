package com.company.servlet.task;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.company.data.TodoStruct;
import com.company.todo.Todo;
import com.company.todo.TodoImpl;

/**
 * Servlet implementation class GetTaskServlet
 */
public class GetTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Todo todo = new TodoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		TodoStruct[] ans = todo.getpending(user_id);
		TodoStruct[] ans1 = todo.getcompleted(user_id);
		System.out.println("GetTaskServlet: "+ ans + " " + ans1);
		request.setAttribute("pendingtodoArray", ans);
		request.setAttribute("completedtodoArray", ans1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
