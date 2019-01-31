package servlets;

import entities.*;
import java.util.ArrayList;
import controllers.ActivityController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class activityManagmentServlet
 */
@WebServlet("/activityManagmentServlet")
public class activityManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Variables 
	ActivityController aController;
	Activity a;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activityManagmentServlet() {
        super();
        aController = new ActivityController();
        a = null;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		this.redirectToTable(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void redirectToTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Metodo para completar tabla en archivo "activitiesManagment.jsp"
		ArrayList<Activity> activities = aController.getAll();
		request.setAttribute("actividades", activities);	
		request.getRequestDispatcher("activitiesManagment.jsp").forward(request, response);
		
	}

}
