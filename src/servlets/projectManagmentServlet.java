package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ProjectController;
import entities.Project;

/**
 * Servlet implementation class projectManagmentServlet
 */
@WebServlet("/projectManagmentServlet")
public class projectManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public projectManagmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectController pController = new ProjectController();
		ArrayList<Project> projects = pController.getAll();
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("projectManagment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectController pController = new ProjectController();
		
		if(request.getParameter("detallesName") != null) {
			int idProj = Integer.parseInt(request.getParameter("idProjectName"));
			Project project = pController.getProjectById(idProj);
			request.setAttribute("project", project);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		if(request.getParameter("buttonProject") != null) {
			int idProject = Integer.parseInt(request.getParameter("buttonProject")) ;
			Project project = pController.getProjectById(idProject);
			request.setAttribute("project", project);
			ArrayList<Project> projects = pController.getAll();
			request.setAttribute("projects", projects);
			request.getRequestDispatcher("projectManagment.jsp").forward(request, response);
		}

	}

}
