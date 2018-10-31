package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.*;
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
			throws ServletException, IOException {
		// TODO Auto-generated method stub

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
				HttpSession session = request.getSession();
				session.setAttribute("usuario", u);
				response.sendRedirect("index.jsp");
			} else {
				EmployeeController eController = new EmployeeController();
				Employee e = new Employee();
				e = eController.getEmployeeByIdUser(u.getId());
				u.setEmployee(e);
				e.setUser(u);
				HttpSession session = request.getSession(true); // EL PARAMETRO TRUE INDICA QUE LA SESSION ES NUEVA,
				//SI DESPUES QUEREMOS VOLVER A RECOGER LA SESSION Y GUARDARLA EN UN OBJETO HAY QUE PASARLE FALSE COMO PARAMETRO
				//PARA INDICAR QUE NO HAY QUE CREAR UNA NUEVA, RE UTILIZAR LA EXISTENTE.
				session.setAttribute("usuario", u);
				request.getRequestDispatcher("indexAdmin.jsp").forward(request, response);
				// VER CUAL ES LA DIFERENCIA ENTRE LO DE ARRIBA Y LO DE ABAJO
				//response.sendRedirect("index.jsp");
			}
		} else {
			// Contraseña Incorrecta.
			response.sendRedirect("");
			
		}
	}

}
