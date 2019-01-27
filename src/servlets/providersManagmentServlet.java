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
	
	// Variables de instancia
	ProviderController pController;
	Provider p;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public providersManagmentServlet() {
        super();
        // Creo variables de instancia
        pController = new ProviderController();
        p = null;
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Metodo para completar tabla en archivo "providersManagment.jsp" y redireccionar
		ArrayList<Provider> providers = this.getAll();
		request.setAttribute("proveedores", providers);
		request.getRequestDispatcher("providersManagment.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// Manejo de Nuevo Proveedor
		if(request.getParameter("addButton") != null) {
			request.getRequestDispatcher("providersRegistration.jsp").forward(request, response);					
		}
		
		// Manejo de Editar Proveedor
		if(request.getParameter("editButton") != null) {
			String IdRow = (String) request.getParameter("radioABM");
			int idProvider = Integer.parseInt(IdRow);
			
			p = pController.getProviderById(idProvider);
			
			// Pasar el proveedor a la p�gina redireccionada que contiene el formulario
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
		
		// Manejo de datos del formulario providersRegistration.jsp
		if(request.getParameter("saveButton") != null) {
			p = new Provider();
			
			p.setBusiness_name(request.getParameter("providerBssName"));
			p.setName(request.getParameter("providerName"));
			p.setSurname(request.getParameter("providerSurname"));
			switch(request.getParameter("providerState")){
				case "APROBADO": p.setState(Provider.APROBADO);
				case "DESAPROBADO": p.setState(Provider.DESAPROBADO);
			}
			p.setDescription(request.getParameter("providerDescription"));
			switch (request.getParameter("providerCategory")) {
				case "A": p.setCategory(Provider.CATEGORY_A);
				case "B": p.setCategory(Provider.CATEGORY_B);
				case "C": p.setCategory(Provider.CATEGORY_C);
			}
			p.setEmail(request.getParameter("providerEmail"));
			p.setAddress(request.getParameter("providerAddress"));
			p.setPhone(request.getParameter("providerPhone"));
			
			// Pregunto si tiene ID el campo de ID
			if(request.getParameter("providerID").equals(""))
				pController.create(p);
			else {
				p.setId(Integer.parseInt(request.getParameter("providerID")));
				pController.update(p);
			}
		}
		
		// Metodo para completar tabla en archivo "providersManagment.jsp"
		ArrayList<Provider> providers = getAll();
		request.setAttribute("proveedores", providers);
		request.getRequestDispatcher("providersManagment.jsp").forward(request, response);
		
	}
	
	// Codigo para traer a todos los proveedores de DB y mostrar en providersManagment.jsp
	protected ArrayList<Provider> getAll(){
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers = pController.getAll();
		
		return providers;
	}

}
