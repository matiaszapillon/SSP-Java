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
import controllers.ClientController;
import controllers.ProjectController;

/**
 * Servlet implementation class reportsServlet
 */
@WebServlet("/reportsServlet")
public class reportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectController projController;
	ClientController clientController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportsServlet() {
        super();
        this.projController=new ProjectController();
        this.clientController = new ClientController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	ArrayList<Client> clients = clientController.getAll();
	request.setAttribute("clients",clients);
	request.getRequestDispatcher("reports.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Verificar si se aplican filstros
		if(request.getParameter("applyFilter") != null) {
			// Aplicar filtros
			// ArrayList<Project> projectsByFilter = projController.getProjectsByFilter();
		}
	}

}
