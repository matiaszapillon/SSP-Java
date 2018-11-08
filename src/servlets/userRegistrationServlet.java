package servlets;

import entities.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ClientController;
import controllers.EmployeeController;
import controllers.UserController;

/**
 * Servlet implementation class userRegistrationServlet
 */
@WebServlet("/userRegistrationServlet")
public class userRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userRegistrationServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserController uController = new UserController();
		ClientController cController = new ClientController();
		EmployeeController eController = new EmployeeController();

		String nameuser = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String typeUser = request.getParameter("typeUserSelect");

		if (request.getParameter("hiddenUser") != "") {
			int idUser = Integer.parseInt(request.getParameter("hiddenUser"));
			User u = new User();
			u.setEmail(email);
			u.setId(idUser);
			u.setPassword(password);
			u.setUsername(nameuser);
			if (typeUser.equalsIgnoreCase("Cliente")) {
				u.setType(User.CLIENT);
			}
			if (typeUser.equalsIgnoreCase("Empleado")) {
				u.setType(User.EMPLOYEE);
			}
			if (typeUser.equalsIgnoreCase("Administrador")) {
				u.setType(User.ADMINISTRATOR);
			}
			uController.update(u);

		} else {
			int idPerson = Integer.parseInt(request.getParameter("hiddenIdPerson"));
			if (typeUser.equalsIgnoreCase("Cliente")) {
				try {
					Client c = cController.getClientById(idPerson);
					User user = new User(email, nameuser, password, User.CLIENT, null, c);
					uController.create(user);
					c.setUser(user);
					cController.addUser(c);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				Employee e = eController.getEmployeeById(idPerson);
				try {
					if (typeUser.equalsIgnoreCase("Empleado")) {
						User user = new User(email, nameuser, password, User.EMPLOYEE, e, null);
						uController.create(user);
						e.setUser(user);
						eController.addUser(e);
					} else {
						User user = new User(email, nameuser, password, User.ADMINISTRATOR, e, null);
						uController.create(user);
						e.setUser(user);
						eController.addUser(e);

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
