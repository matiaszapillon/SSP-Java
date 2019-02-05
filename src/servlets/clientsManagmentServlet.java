package servlets;

import entities.Client;
import controllers.ClientController;
import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class clientsManagmentServlet
 */
@WebServlet("/clientsManagmentServlet")
public class clientsManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// Variables
	ClientController cController;
	Client c;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clientsManagmentServlet() {
        super();
        cController = new ClientController();
        c = null;
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		this.redirecToTable(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// Manejo de Create
		if(request.getParameter("addButton") != null) {
			request.getRequestDispatcher("clientRegistration.jsp").forward(request, response);
		}
		
		// Manejo de Update
		if(request.getParameter("editButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idClient = Integer.parseInt(IdRow);
			
			c = cController.getClientById(idClient);
			
			// Pasar el cliente a la pagina redireccionada 
			request.setAttribute("cliente", c);
			request.getRequestDispatcher("clientRegistration.jsp").forward(request, response);
		}

		// Manejo de Delete
		if(request.getParameter("deleteButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idClient = Integer.parseInt(IdRow);
			
			cController.deleteClient(idClient);
			// Redireccionar
			this.redirecToTable(request, response);
		}
		
		// Manejo de formulario
		if(request.getParameter("saveButton") != null) {
			c = new Client();
			
			c.setBusiness_name(request.getParameter("clientName"));
			c.setCUIT_CUIL(request.getParameter("clietnCuitCuil"));
			c.setAddress(request.getParameter("clientDirection"));
			c.setEmail(request.getParameter("clientEmail"));
			
			if(request.getParameter("hiddenID").equals("")) {
				// Create 
				cController.createClient(c);
			} else {
				// Update
				c.setId(Integer.parseInt(request.getParameter("hiddenID")));
				cController.updateClient(c);
			}
			
			// Redireccionar
			this.redirecToTable(request, response);
		}
		
		
	}
	
	public void redirecToTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientController cController = new ClientController();
		ArrayList<Client> clients = cController.getAll();
		request.setAttribute("clientes", clients);
		request.getRequestDispatcher("clientManagment.jsp").forward(request, response);		
	}

}
