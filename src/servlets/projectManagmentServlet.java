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

		//Info de DetailsProject
		if(request.getParameter("suppliesName") != null) {
			String idP = request.getParameter("idProjectName") ;
			int idProject = Integer.parseInt(idP);			
			Project projectWithSupplies = pController.getProjectById(idProject);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		}
		if(request.getParameter("calculateCostName") != null) {
			
			/* ESTO ME DEVUELVE NULL EN EL VALUE. POR QUE? EN PANTALLA APARECE.
			 	<label>ID</label>
			<input class="form-control" type="text" name="idProjectName" readonly <%if (project != null){ %>
			value=<%=project.getId()%><%}else{%> value=<%=projectWithSupplies.getId()%><%} %>>
			 */
//			String idP = request.getParameter("idProjectName") ;
//			int idProject = Integer.parseInt(idP);
			Project projectWithSupplies = pController.getProjectById(1);
			ArrayList<Supply> supplies = sController.getSuppliesByProject(projectWithSupplies.getId());
			projectWithSupplies.setSupplies(supplies);
			projectWithSupplies.calculateTotalCost(supplies);
			request.setAttribute("projectWithSupplies", projectWithSupplies);
			request.getRequestDispatcher("projectDetails.jsp").forward(request, response);
		
		}
		if(request.getParameter("addSupplyName") != null) {
			//MUESTRO INSUMOS QUE NO ESTEN EN EL PROYECTO
			//SE DEBE SELECCIONAR EL QUE DESEE AGREGAR Y LUEGO ELEGIR EL PROVEEDOR
			// (SOLO SE PODRA SELECCIONAR LOS PROVEEDORES QUE ESTAN APROBADOS)
//			String idP = request.getParameter("idProjectName") ;
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
		if(request.getParameter("selectProvider") != null) {
			
			int idProject = Integer.parseInt(request.getParameter("hiddenProjectName"));
			Project project = pController.getProjectById(idProject);
			String idSup = request.getParameter("radioAddSupply");
			int idSupply = Integer.parseInt(idSup);
			ArrayList<Provider> providers = provController.getProvidersByIdSupply(idSupply);
			request.setAttribute("projectWithSupplies", project);
		}
	}

}
