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
 * Servlet implementation class providersRegistrationServlet
 */
@WebServlet("/providersRegistrationServlet")
public class providersRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public providersRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		ProviderController pController = new ProviderController();
		Provider p = new Provider();
		
		p.setBusiness_name(request.getParameter("providerBssName"));
		p.setName(request.getParameter("providerName"));
		p.setSurname(request.getParameter("providerSurname"));
		switch(request.getParameter("providerState")){
			case "1": p.setState(Provider.APROBADO);
			case "2": p.setState(Provider.DESAPROBADO);
		}
		p.setDescription(request.getParameter("providerDescription"));
		switch (request.getParameter("providerCategory")) {
			case "1": p.setCategory(Provider.CATEGORY_A);
			case "2": p.setCategory(Provider.CATEGORY_B);
			case "3": p.setCategory(Provider.CATEGORY_C);
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
		// Redireccionar a otra pagina (verificar que se cargue la tabla, caso contrario hay que volver a mandarlos
		request.getRequestDispatcher("providersManagment.jsp").forward(request, response);
	}

}
