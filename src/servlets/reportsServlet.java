package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Client;
import entities.Project;
import entities.Supply;
import controllers.ClientController;
import controllers.ProjectController;
import controllers.SupplyController;

/**
 * Servlet implementation class reportsServlet
 */
@WebServlet("/reportsServlet")
public class reportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectController projController;
	ClientController clientController;
	SupplyController supplyController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reportsServlet() {
		super();
		this.projController = new ProjectController();
		this.clientController = new ClientController();
		this.supplyController = new SupplyController();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Client> clients = clientController.getAll();
		request.setAttribute("clients", clients);
		request.getRequestDispatcher("reports.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("applyFilter") != null) {
		
			ArrayList<Project> projectsToReport = new ArrayList<Project>();
			// Aplicar filtros

			Client c = null;
			int startDate = 0;
			int endDate = 0;
			if (Integer.parseInt(request.getParameter("client")) != 0) {
				int idClient = Integer.parseInt(request.getParameter("client"));
				c = clientController.getClientById(idClient);
			}
			if (request.getParameter("startDateName") != "") {
				startDate = Integer.parseInt(request.getParameter("startDateName"));
			}
			if (request.getParameter("endDateName") != "") {
				endDate = Integer.parseInt(request.getParameter("endDateName"));
			}
			ArrayList<Project> projectsByFilter = projController.getProjectsByFilter(c, startDate, endDate);
			ArrayList<Project> projects = new ArrayList<Project>();

			// Verifico si encontro alguno con los filtros realizados
			if (projectsByFilter != null) {

				for (Project p : projectsByFilter) {
					Project proj = projController.getProjectWithStages(p.getId());
					ArrayList<Supply> supplies = supplyController.getSuppliesByProject(p.getId());
					proj.setSupplies(supplies);
					projects.add(proj);
				}
				String stateProject = request.getParameter("stateProject");
				// Eliminar del array proyectos distintos al stateProject.
				
				//ACA HAY UN ERRORRRRRRRRRRRR. 
				// SI STATEPROJECT ES IGUAL A "-" NO DEBERIA ENTRAR AL IF
				// sUPONGO QUE ES ALGO DE IGUALAR STRING NO SE HACE ASI JEJE
				if (stateProject != "-") {
					for (Project proj : projects) {
						if (stateProject == (proj.getState())) {
					//		projects.remove(proj); > Esto me saltaba un error por manipular el propio arraylist del foreach
							projectsToReport.add(proj);
						}
					}

				}
			}
			// Verifico si quedo alguno en el ArrayList despues de eliminar aquellos
			// que no coinciden con el filtro de Estado
			if (projectsToReport != null) {
				if (request.getParameter("chkboxCost") != null) {
					for (Project project : projects) {
						project.calculateTotalCost(project.getSupplies());
					}
				}
			}
			
			request.setAttribute("projects", projectsToReport);
			request.getRequestDispatcher("reports.jsp").forward(request, response);
		}
	}

}
