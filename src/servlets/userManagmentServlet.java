package servlets;

import entities.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ParseConversionEvent;

import controllers.ClientController;
import controllers.EmployeeController;
import controllers.UserController;

/**
 * Servlet implementation class userManagmentServlet
 */
@WebServlet("/userManagmentServlet")
public class userManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userManagmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Codigo para traer todos los usuarios de base de datos y mostrar en
		// usuariosManagment.jsp
		// (Deberia hacerse mediante doPost (En el hipervinculo de indexAdmin la unica
		// forma de hacerlo es agregando
		// evento onclick y por javascript aclarar accion method POST)
		UserController usController = new UserController();
		ArrayList<User> usuarios = new ArrayList<User>();
		usuarios = usController.getAll();
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("userManagment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserController uController = new UserController();
		EmployeeController eController = new EmployeeController();
		ClientController cController = new ClientController();
		
		
		if (request.getParameter("addButton") != null) {
			ArrayList<Client> clients = new ArrayList<Client>();
			clients =  cController.getClientsWithoutUser();
			ArrayList<Employee> employees = new ArrayList<Employee>();
			employees = eController.getEmployeesWithoutUser();
			request.setAttribute("clients", clients);
			request.setAttribute("employees", employees);
			request.getRequestDispatcher("userRegistration.jsp").forward(request, response);
		}
		if (request.getParameter("editButton") != null) {
			int userType = Integer.parseInt(request.getParameter("rowValue"));
			String IdRow = (String) request.getParameter("radioABM");
			int idUser = Integer.parseInt(IdRow);
			User u = uController.getUserById(idUser);
			if (userType == User.CLIENT) {
			u.setClient(cController.getClientByIdUser(idUser));
			} else {
			u.setEmployee(eController.getEmployeeByIdUser(idUser));
			}
			request.setAttribute("user", u);
			request.getRequestDispatcher("userRegistration.jsp").forward(request, response);
		}
		if (request.getParameter("deleteButton") != null) {
			int userType = Integer.parseInt(request.getParameter("rowValue"));
			String IdRow = (String) request.getParameter("radioABM");
			int idUser = Integer.parseInt(IdRow);
			if (userType == User.CLIENT) {
				cController.deleteUser(idUser);
			} else {
				eController.deleteUser(idUser);
			}

		}


		}

	}


