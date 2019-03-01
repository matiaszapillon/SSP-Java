package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.*;
import entities.*;

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
		
		// Mostrar datos proyecto
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
		
		// Ir a detalles de proyecto
		if (request.getParameter("detallesName") != null) {
			int idProj = Integer.parseInt(request.getParameter("idProjectName"));
			Project project = projController.getProjectById(idProj);
			request.setAttribute("project", project);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		
		// Crear nuevo proyecto
		if(request.getParameter("btnCreateProject") != null) {
			// Redireccionar a projectRegistration.jsp
			request.getRequestDispatcher("projectRegistration.jsp").forward(request, response);
		}
		
		// Eliminar proyecto
		if(request.getParameter("btnDeleteProject") != null) {
			
			// Traer ID de proyecto a eliminar
			int idProjecToDelete = Integer.parseInt(request.getParameter("idProjectName"));
			// Enviar consulta
			projController.deleteProject(idProjecToDelete);
			// Redireccionar
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
		if(request.getParameter("updateButton") != null) {
			String idSup = request.getParameter("radioSelectedSupply");
			int idSupply = Integer.parseInt(idSup);
			String quantity = request.getParameter(idSup);
			int quantityId = Integer.parseInt(quantity);
			int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			sController.updateQuantityFromProject(idProject,idSupply,quantityId);
			Project projectWithSupplies = projController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		if(request.getParameter("deleteSupplyName") != null) {
			int idSupply = Integer.parseInt((request.getParameter("radioSelectedSupply")));
			String idP = request.getParameter("idProjectName");
			int idProject = Integer.parseInt(idP);
			sController.deleteSupplyFromProject(idSupply,idProject);
			Project projectWithSupplies = projController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
			
		}
		// Metodo que calcula el costo del proyecto
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
		
		// Manejo para redireccionar a "updateProjectStage"
		if(request.getParameter("modifyStageForm") != null) {
			int idStage = Integer.parseInt(request.getParameter("radioSelectedStage"));		
			int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			// Buscar etapa a modificar
			Stage stageToUpdate = projController.getProjectSage(idProject, idStage);
			// Agregar etapa a la sesion
			HttpSession session = request.getSession(false);
			if(session != null) {
				User u = (User) session.getAttribute("usuario");
				u.setCurrentStage(stageToUpdate);
			}			
			// Redireccionar
			request.getRequestDispatcher("updateProjectStage.jsp").forward(request, response);
		}

		// Eliminar etapa de un proyecto especifico
		if(request.getParameter("deleteStage") != null) {
			// Traer ids
			int idStageToDelete = Integer.parseInt(request.getParameter("radioSelectedStage"));
			int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			// Ejecutar consulta
			projController.deleteStageFromProject(idStageToDelete, idProject);
			// Redireccionar
			Project p = projController.getProjectWithStages(idProject);
			request.setAttribute("projectStages", p);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
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
		
		
		/* INFO de updateProjectStage */
		
		if(request.getParameter("btnCollapseAttendant") != null) {
			// Traer todos los empleados para poder seleccionarlos despues
			EmployeeController eController = new EmployeeController();
			ArrayList<Employee> employees = eController.getAll();
			// Agregar empleados
			request.setAttribute("empleados", employees);	
			// Redireccionar
			request.getRequestDispatcher("updateProjectStage.jsp").forward(request, response);
		}
		
		if(request.getParameter("addSelectedEmployee") != null) {
			int idEmp = Integer.parseInt(request.getParameter("radioEmployee"));
			// Traer encargado
			EmployeeController eController = new EmployeeController();
			Employee attendant = eController.getEmployeeById(idEmp);
			// Cargar encargado nuevo a la sesion
			HttpSession session = request.getSession(false);
			if(session != null) {
				User u = (User) session.getAttribute("usuario");
				u.getCurrentStage().getEmployee().setId(attendant.getId());
				u.getCurrentStage().getEmployee().setName(attendant.getName());
				u.getCurrentStage().getEmployee().setSurname(attendant.getSurname());
			}
			// Redireccionar
			request.getRequestDispatcher("updateProjectStage.jsp").forward(request, response);
		}
		
		if(request.getParameter("updateStage") != null) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				User u = (User) session.getAttribute("usuario");
				int idProject = u.getCurrentProject().getId();
				int idStage = u.getCurrentStage().getId();
				int state = Integer.parseInt(request.getParameter("stageState"));
				int idEmployee = u.getCurrentStage().getEmployee().getId();
				projController.updateProjectStage(idProject, idStage, state, idEmployee); 
				
				// Redireccionar 
				// Volver a projectDetails con las etapas pertenecientes al proyecto actual
				Project projectWithStages = projController.getProjectWithStages(idProject);
				
				if(projectWithStages.isFinished()) {
					Client c = projController.getClientByIdProject(projectWithStages.getId());
					projectWithStages.sendEmailToClient(c,projectWithStages);
				}
				request.setAttribute("projectStages", projectWithStages);
				request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
			}
		}
		
		// Setear datos a null
		if(request.getParameter("goBack") != null) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				User u = (User) session.getAttribute("usuario");
				u.setCurrentStage(null);
				// Volver a projectDetails con las etapas pertenecientes al proyecto actual
				Project projectWithStages = projController.getProjectWithStages(u.getCurrentProject().getId());
				request.setAttribute("projectStages", projectWithStages);
				request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
			}
		}
				
		/* FIN de updateProjectStage */
		
		
		/* INFO de projectRegistration */
		
		if(request.getParameter("btnCollapseClients") != null) {
			// Buscar todos los clientes para seleccionar luego
			ClientController cController = new ClientController();
			ArrayList<Client> clients = cController.getAll();
			// Mandar como par�metros
			request.setAttribute("clientes", clients);
			// Redireccionar
			request.getRequestDispatcher("projectRegistration.jsp").forward(request, response);
		}
		
		if(request.getParameter("addSelectedClient") != null) {
			// Recupero el id del cliente y lo busco
			int idCliSelected = Integer.parseInt(request.getParameter("radioClientID"));
			ClientController cController = new ClientController();
			Client cliSelected = cController.getClientById(idCliSelected);
			// Enviar cliente
			request.setAttribute("clienteSeleccionado", cliSelected);
			// Redireccionar
			request.getRequestDispatcher("projectRegistration.jsp").forward(request, response);
		}
		
		if(request.getParameter("saveNewProject") != null) {
			// Recupero el id del cliente y lo busco
			int idCliSelected = Integer.parseInt(request.getParameter("hiddenIdClient"));
			ClientController cController = new ClientController();
			Client c = cController.getClientById(idCliSelected);
			// Creo proyecto y asigno datos
			Project p = new Project();
			p.setName(request.getParameter("projectName"));
			p.setDescription(request.getParameter("projectDescription"));
			p.setClient(c);
			// Enviar consulta  a DB
			projController.createProject(p);
			// Redireccionar
			this.redirectToProjectManagment(request, response);
		}
		/* FIN de projectRegistration */

		
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
		HttpSession session = request.getSession(false);
		if(session != null) {
			User u = (User) session.getAttribute("usuario");
			if(u.getClient() != null) {
				//Trae los proyecto de el cliente especifico
				ArrayList<Project> projects = projController.getProjectsByClient(u.getClient().getId());
				request.setAttribute("projects", projects);
				request.getRequestDispatcher("projectManagmentClient.jsp").forward(request, response);
			
				
			}else {
				ArrayList<Project> projects = projController.getAll();
				request.setAttribute("projects", projects);
				request.getRequestDispatcher("projectManagment.jsp").forward(request, response);
				// Trae todos los proyectos (Es un empleado)
			}
		}
		

	}

}
