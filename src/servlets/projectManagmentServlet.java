package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ProjectController;
import controllers.ProviderController;
import controllers.SupplyController;
import entities.Project;
import entities.Supply;
import entities.User;
import entities.Stage;

/**
 * Servlet implementation class projectManagmentServlet
 */
@WebServlet("/projectManagmentServlet")
public class projectManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectController projController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public projectManagmentServlet() {
		super();
		projController = new ProjectController();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.redirectToProjectManagment(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SupplyController sController = new SupplyController();
		ProviderController provController = new ProviderController();

		/* INFO de ProjectManagment */

		if (request.getParameter("detallesName") != null) {
			int idProj = Integer.parseInt(request.getParameter("idProjectName"));
			Project project = projController.getProjectById(idProj);
			request.setAttribute("project", project);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}

		if (request.getParameter("buttonProject") != null) {
			int idProject = Integer.parseInt(request.getParameter("buttonProject"));
			Project project = projController.getProjectById(idProject);
			request.setAttribute("project", project);
			HttpSession session = request.getSession(false);
			if (session != null) {
				User u = (User) session.getAttribute("usuario");
				u.getCurrentProject().setId(project.getId());
				u.getCurrentProject().setName(project.getName());
				u.getCurrentProject().setDescription(project.getDescription());
				session.setAttribute("usuario", u);
			}
			
			this.redirectToProjectManagment(request, response);
		}

		/* FIN projectManagment.jsp */

		/* INFO de ProjectDetails */

		if (request.getParameter("suppliesName") != null) {
			String idP = request.getParameter("idProjectName");
			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = projController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}

		// Manejo para ver etapas del proyecto
		if (request.getParameter("stagesButton") != null) {
			int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			Project p = projController.getProjectWithStages(idProject);
			request.setAttribute("projectStages", p);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		// Manejo para redireccionar a "addStageToProject"
		if(request.getParameter("addStageForm") != null) {
			int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			ArrayList<Stage> stagesOutOfProject = projController.getStagesOutOfProject(idProject);
			// Agregar etapas sin proyectos
			request.setAttribute("stagesOutOfProject", stagesOutOfProject);
			// Redireccionar a formulario
			request.getRequestDispatcher("addStageToProject.jsp").forward(request, response);			
		}

		if (request.getParameter("calculateCostName") != null) {
			String idP = request.getParameter("idProjectName");
			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = projController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			projectWithSupplies.calculateTotalCost(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}

		if (request.getParameter("addSupplyName") != null) {
			/*
			 * MUESTRO INSUMOS QUE NO ESTEN EN EL PROYECTO SE DEBE SELECCIONAR EL QUE DESEE
			 * AGREGAR Y LUEGO ELEGIR EL PROVEEDOR (SOLO SE PODRA SELECCIONAR LOS
			 * PROVEEDORES QUE ESTAN APROBADOS)
			 */
			String idP = request.getParameter("idProjectName");
			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = projController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			ArrayList<Supply> allSupplies = sController.getAllSupplies();
			allSupplies.removeAll(supplies);
			request.setAttribute("allSupplies", allSupplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("addSupplyToProject.jsp").forward(request, response);

		}
		/* FIN ProjectDetails */

		/* INFO de addSupplyToProject.jsp */
		if (request.getParameter("selectProvider") != null) {

			HttpSession session = request.getSession(false);
			if (session != null) {
				User u = (User) session.getAttribute("usuario");
				Project project = projController.getProjectById(u.getCurrentProject().getId());
				String idSup = request.getParameter("radioAddSupply");
				int idSupply = Integer.parseInt(idSup);
				ArrayList<Supply> suppliesProviders = provController.getProvidersByIdSupply(idSupply);
				int id = suppliesProviders.get(0).getId();
				request.setAttribute("project", project);
				request.setAttribute("suppliesProviders", suppliesProviders);
				request.getRequestDispatcher("selectProvider.jsp").forward(request, response);
			}
		}

		/* FIN addSupplyToProject */
		
		/* INFO de addStageToProject.jsp */
		// Manejo para agregar etapa al proyecto
		if(request.getParameter("addStageToProject") != null) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				User u = (User) session.getAttribute("usuario");
				Project p = projController.getProjectById(u.getCurrentProject().getId());
				int idStage = Integer.parseInt(request.getParameter("radioAddStage"));
				projController.addStageToProject(p.getId(), idStage);
				
				// Volver a projectDetails con las etapas pertenecientes al proyecto actual
				Project projectWithStages = projController.getProjectWithStages(p.getId());
				request.setAttribute("projectStages", projectWithStages);
				request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
			}
			
		}
		/* FIN addStageToProject */

		/* INFO de selectProvider.jsp */

		if (request.getParameter("saveProviderName") != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				User u = (User) session.getAttribute("usuario");
				int quantity = Integer.parseInt(request.getParameter("quantityName"));
				int idProvider = Integer.parseInt(request.getParameter("radioSelectProvider"));
				int idSupply = Integer.parseInt(request.getParameter("numberSupplyName"));
				int idProject = u.getCurrentProject().getId();
				projController.addSupplyToProject(idProject, idSupply, idProvider, quantity);
				request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
			}
		}
		/* FIN selectProvider */

	}
	
	public void redirectToProjectManagment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Project> projects = projController.getAll();
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("projectManagment.jsp").forward(request, response);
	}

}
