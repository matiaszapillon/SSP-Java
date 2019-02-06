package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ProjectController;
import controllers.ProviderController;
import controllers.SupplyController;
import entities.Project;
import entities.Project_stage;
import entities.Provider;
import entities.Supply;

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
		SupplyController sController = new SupplyController();
		ProviderController provController = new ProviderController();
	
		//Info de ProjectManagment
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

		/* Info de ProjectDetails */
		if(request.getParameter("suppliesName") != null) {
			String idP = request.getParameter("idProjectName") ;
			int idProject = Integer.parseInt(idP);			
			Project projectWithSupplies = pController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		
			// Manejo para ver etapas del proyecto
		if(request.getParameter("stagesButton") != null) {
		 // int idProject = Integer.parseInt(request.getParameter("idProjectName"));
			Project p = pController.getProjectWithStages(1);
			request.setAttribute("projectStages", p);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		
		/* FIN ProjectDetails */
		
		if(request.getParameter("calculateCostName") != null) {
			
			/* ESTO ME DEVUELVE NULL EN EL VALUE. POR QUE? EN PANTALLA APARECE.(Y en agregar insumo tambien)
			 	<label>ID</label>
			<input class="form-control" type="text" name="idProjectName" readonly <%if (project != null){ %>
			value=<%=project.getId()%><%}else{%> value=<%=projectWithSupplies.getId()%><%} %>>
			 */
			String idP = request.getParameter("idProjectName");
//			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = pController.getProjectById(1);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			projectWithSupplies.calculateTotalCost(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		
		}
		
		if(request.getParameter("addSupplyName") != null) {
				/*MUESTRO INSUMOS QUE NO ESTEN EN EL PROYECTO
   				SE DEBE SELECCIONAR EL QUE DESEE AGREGAR Y LUEGO ELEGIR EL PROVEEDOR
   				(SOLO SE PODRA SELECCIONAR LOS PROVEEDORES QUE ESTAN APROBADOS)
				 */
			
			String idP = request.getParameter("idProjectName") ;//  <<< retorna null - PReguntar porque.
//			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = pController.getProjectById(1);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			ArrayList<Supply> allSupplies = sController.getAllSupplies();
			allSupplies.removeAll(supplies);			
			request.setAttribute("allSupplies", allSupplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("addSupplyToProject.jsp").forward(request, response);
			
		}
		
		//Info de addSupplyToProject.jsp
		
		if(request.getParameter("selectProvider") != null) {
			String idP = request.getParameter("hiddenId");			
			int idProject = Integer.parseInt(idP);			
			Project project = pController.getProjectById(idProject);			
			String idSup = request.getParameter("radioAddSupply");
			int idSupply = Integer.parseInt(idSup);
			ArrayList<Supply> suppliesProviders = provController.getProvidersByIdSupply(idSupply);
			int id= suppliesProviders.get(0).getId();
			request.setAttribute("project", project);
			request.setAttribute("suppliesProviders", suppliesProviders);
			request.getRequestDispatcher("selectProvider.jsp").forward(request, response);
		}
		
		//info de selectProvider.jsp
		
		if(request.getParameter("saveProviderName") != null) {
			int quantity = Integer.parseInt(request.getParameter("quantityName"));
			int idProvider = Integer.parseInt(request.getParameter("radioSelectProvider"));
			int idSupply = Integer.parseInt(request.getParameter("numberSupplyName")) ;
			int idProject = Integer.parseInt(request.getParameter("numberProjectName"));
			pController.addSupplyToProject(idProject,idSupply,idProvider,quantity) ;
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);

		}
		
		
	}

}
