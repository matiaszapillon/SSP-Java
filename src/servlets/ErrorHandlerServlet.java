package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorHandlerServlet
 */
@WebServlet("/ErrorHandlerServlet")
public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 
        /***** Analyze The Servlet Exception *****/        
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
 
        if (servletName == null) {
            servletName = "Unknown";
        } 
        
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        /* Enviar parametros a pagina de errores */
        request.setAttribute("statusCode", statusCode);
        request.setAttribute("servletName", servletName);
        request.setAttribute("throwable", throwable);
        request.setAttribute("requestUri", requestUri);
        /* Redireccionar a pagina de errores */
        request.getRequestDispatcher("errorPage.jsp").forward(request, response);
    }

}
