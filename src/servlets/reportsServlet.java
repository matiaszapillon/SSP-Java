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

/*
 * Servlet implementation class reportsServlet
 */

@WebServlet("/reportsServlet")
public class reportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectController projController;
	ClientController clientController;
	SupplyController supplyController;

	/*
	 * @see HttpServlet#HttpServlet()
	 */
	public reportsServlet() {
		super();
		this.projController = new ProjectController();
		this.clientController = new ClientController();
		this.supplyController = new SupplyController();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			// Array para enviar a la pagina
			ArrayList<Project> projectsToReport = new ArrayList<Project>();
			// Traer projects filtrados
			ArrayList<Project> projectsByFilter = new ArrayList<Project>();
			// Nuevo array para hacer la diferencia
			ArrayList<Project> projects = new ArrayList<Project>();
			
			String stateProject;
			Client c = null;
			int startDate = 0;
			int endDate = 0;
			
			// Filtro por cliente
			if (Integer.parseInt(request.getParameter("clientID")) != 0) {
				int idClient = Integer.parseInt(request.getParameter("clientID"));
				c = clientController.getClientById(idClient);
			}
			
			// Filtro por start date
			if (!request.getParameter("startDateName").isEmpty()) {
				startDate = Integer.parseInt(request.getParameter("startDateName"));
			}
			
			// Filtro por end date
			if (!request.getParameter("endDateName").isEmpty()) {
				endDate = Integer.parseInt(request.getParameter("endDateName"));
			}
			
			// Traer projects filtrados
			projectsByFilter = projController.getProjectsByFilter(c, startDate, endDate);

			// Verifico si encontro alguno con los filtros realizados
			if (projectsByFilter != null) {
				
				for (Project p : projectsByFilter) {
					if(p.getClient() == null) {
						//Cargo cliente
						p.setClient(projController.getClientByIdProject(p.getId()));
					} 
					Project proj = projController.getProjectWithStages(p.getId());
					if(proj != null) {
						ArrayList<Supply> supplies = supplyController.getSuppliesByProject(p.getId());
						proj.setSupplies(supplies);
						proj.setClient(p.getClient());
						// LLenar ambos array para luego hacer la dif
						projectsToReport.add(proj);
						projects.add(proj);
					}else {
						projectsToReport.add(p);
						projects.add(p);
					}

				}
				// Traer state
				stateProject = request.getParameter("stateProject");
				// Eliminar del array proyectos distintos al stateProject.
				if (!stateProject.equals("-")) {
					for (Project proj : projects) {
						if (!stateProject.equals(proj.getState())) {
							// Eliminar si no tiene el estado de filtro 
							projectsToReport.remove(proj);
						}
					}
				}
			}
			// Calculo costo si se solicita y si hay elementos en el array
			if (!projectsToReport.isEmpty()) {
				if (request.getParameter("chBoxCost") != null) {
					for (Project project : projectsToReport) {
						project.calculateTotalCost(project.getSupplies());
					}
				}
			}
			
			// Carga de datos y reenvio a la pagina de reportes
			ArrayList<Client> clients = clientController.getAll();
			request.setAttribute("clients", clients);
			request.setAttribute("projects", projectsToReport);
			request.getRequestDispatcher("reports.jsp").forward(request, response);
		}
	}

}
