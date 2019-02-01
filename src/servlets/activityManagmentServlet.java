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
		// doGet(request, response);
		
		// Manejo de Create
		if(request.getParameter("addButton") != null) {
			request.getRequestDispatcher("activitiesRegistration.jsp").forward(request, response);
		}
		
		// Manejo de Update
		if(request.getParameter("editButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idActividad = Integer.parseInt(IdRow);
			
			a = aController.getActivityById(idActividad);
			// Pasar la actividad a la pagina redireccionada 
			request.setAttribute("actividad", a);
			request.getRequestDispatcher("activitiesRegistration.jsp").forward(request, response);
		}

		// Manejo de Delete
		if(request.getParameter("deleteButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idActividad = Integer.parseInt(IdRow);
			
			aController.deleteActivity(idActividad);
			// Redireccionar
			this.redirectToTable(request, response);
		}
		
		// Manejo de formulario
		if(request.getParameter("saveButton") != null){
			a = new Activity();
			
			a.setDescription(request.getParameter("activityDescription"));
			a.setDuration(request.getParameter("activityDuration"));
			
			if(request.getParameter("hiddenID").equals("")) {
				// Create
				aController.createActivity(a);
			} else {
				// Update
				a.setId(Integer.parseInt(request.getParameter("hiddenID")));
				aController.updateActivity(a);
			}
			
			// Redireccionar
			this.redirectToTable(request, response);				
		}
		
	}
	
	public void redirectToTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Metodo para completar tabla en archivo "activitiesManagment.jsp"
		ArrayList<Activity> activities = aController.getAll();
		request.setAttribute("actividades", activities);	
		request.getRequestDispatcher("activitiesManagment.jsp").forward(request, response);
		
	}

}
