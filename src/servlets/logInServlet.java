package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.ClientController;
import controllers.EmployeeController;
import controllers.UserController;
import entities.Client;
import entities.Employee;
import entities.User;

/**
 * Servlet implementation class logInServlet
 */
@WebServlet("/logInServlet")
public class logInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logInServlet() {
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
			throws ServletException, IOException{

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserController usController = new UserController();
		User u = usController.isUserValid(username, password);
		if (u != null) {
			if (u.getType() == User.CLIENT) {
				ClientController cController = new ClientController();
				Client c = new Client();
				c = cController.getClientByIdUser(u.getId());
				u.setClient(c);
				c.setUser(u);
				HttpSession session = request.getSession(true);
				session.setAttribute("usuario", u);
				request.getRequestDispatcher("indexClient.jsp").forward(request,response);
			} else {
				EmployeeController eController = new EmployeeController();
				Employee e = new Employee();
				e = eController.getEmployeeByIdUser(u.getId());
				u.setEmployee(e);
				e.setUser(u);
				HttpSession session = request.getSession(true); 
				session.setAttribute("usuario", u);
				request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
			}
		} else {
			// Mensaje a consola
			System.out.println("Usuario y/o contraseña incorrecto");
			// Mensaje al usuario
			String msjError = "Usuario y/o contraseña incorrecto";
			request.setAttribute("msjError", msjError);
			request.getRequestDispatcher("logIn.jsp").forward(request, response);			
		}
	}

}
