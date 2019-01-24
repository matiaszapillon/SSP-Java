package servlets;

import entities.*;
import java.util.ArrayList;
import controllers.ProviderController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class providersManagmentServlet
 */
@WebServlet("/providersManagmentServlet")
public class providersManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public providersManagmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Codigo para traer a todos los proveedores de DB y mostrar en providersManagment.jsp
		// Falta ProviderController
		ProviderController pController = new ProviderController();
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers = pController.getAll();
		request.setAttribute("proveedores", providers);
		request.getRequestDispatcher("providersManagment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		ProviderController pController = new ProviderController();
		
		// Manejo de Nuevo Proveedor
		if(request.getParameter("addButton") != null) {
			request.getRequestDispatcher("providersRegistration.jsp").forward(request, response);					
		}
		
		// Manejo de Editar Proveedor
		if(request.getParameter("editButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idProvider = Integer.parseInt(IdRow);
			
			Provider p = pController.getProviderById(idProvider);
			
			// Pasar el proveedor a la página redireccionada
			request.setAttribute("proveedor", p);
			request.getRequestDispatcher("providersRegistration.jsp").forward(request, response);
		}
		
		// Manejo de Eliminar Proveedor
		if(request.getParameter("deleteButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idProvider = Integer.parseInt(IdRow);
			
			// Borrar proveedor
			pController.delete(idProvider);			
		}
		
	}

}
