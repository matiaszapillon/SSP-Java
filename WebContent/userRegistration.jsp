<%@page import="entities.User"%>
<%@page import="entities.Client"%>
<%@page import="entities.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registrar usuario</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

  </head>

  <body class="bg-dark">
<%User u = (User)request.getAttribute("user"); 
ArrayList<Client> clients = (ArrayList<Client>)request.getAttribute("clients");

%>
    <div class="container">
      <div class="card card-register mx-auto mt-5">
        <div class="card-header">Registrar / Editar Usuario</div>
        <div class="card-body">
          <form action = "userRegistrationServlet" method = "post">
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="firstName" class="form-control" placeholder="Username" required="required" autofocus="autofocus" 
                    <%if(u != null){ %> value = <%=u.getUsername() %>                    
                    <%
                    } 
                    %>>
                    
                    <label for="firstName">Nombre de usuario</label>
                  </div>
                </div>
                <div class="col-md-6">
              <div class="form-label-group">
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="required"
                    <%if(u != null){ %> value = <%= u.getEmail() %>                  
                    <%
                    } 
                    %>>
                
                <label for="inputEmail">Email</label>
              </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required"
                    <%if(u != null){ %> value = <%=u.getPassword() %>                    
                    <%
                    } 
                    %>>
                    <label for="inputPassword">Contraseña</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm password" required="required"                    
                    <%if(u != null){ %> value = <%=u.getPassword()%>                    
                    <%
                    } 
                    %>>
                    <label for="confirmPassword">Confirmar contraseña</label>
                  </div>
                </div>
              </div>
            </div>
              <div class="form-group form-row">
   				<div class="col-md-3"> <label for="userType" class="col-form-label">Tipo de usuario</label>
   				 </div>
   				 <div class="col-md-3" >
   				 	 <select class="form-control" id="idUserType">
   				 	      <option <%if(u != null && u.getType() == User.EMPLOYEE) {%> selected <%} %>>Empleado</option>
  	   					  <option  <%if(u != null && u.getType() == User.CLIENT) {%> selected <%} %>>Cliente</option>
    					  <option  <%if(u != null && u.getType() == User.ADMINISTRATOR) {%> selected <%} %>>Administrador</option>
   					 </select>
   				 </div>
   				 <div class="col-md-5">
   				 	<input type="text" class="form-control" placeholder="Seleccionar cliente o empleado" aria-label="Search" aria-describedby="basic-addon2" disabled=""
   				 	<%if(u != null){ if(u.getType() == User.CLIENT){ %> value = <%= u.getClient().getBusiness_name() %>                   
                    <%
                    }else { 
                    %>value = <%=u.getEmployee().getDNI() %>
                    <%}} %>
                    >
   				 	 </div>
          <div class="col-md-1">
            <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modalEmployees" onclick = "findPeople()">
              <i class="fas fa-search"></i>
            </button>
          </div>
   				

 			 </div>   
			<button class="btn btn-primary btn-block" type="submit" name="saveButton" id="idSaveButton" > Guardar</button>
          </form>
          <div class="text-center">
            <a class="d-block small mt-3" href="loginAdmin.jsp">Pagina de inicio</a>
            <a class="d-block small" href="forgot-password.html">Olvidó la contraseña?</a>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal -->
<div class="modal fade" id="modalEmployees" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Empleados</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		 <table class="table table-striped">
				  <thead>
				    <tr>
				      <th scope="col">Seleccionar</th>
				      <th scope="col">DNI</th>
				      <th scope="col">Nombre</th>
				      <th scope="col">Apellido</th>
				    </tr>
				  </thead>
				  <tbody>
				     <%
				     ArrayList<Employee> employees = (ArrayList<Employee>)request.getAttribute("Employees");
                  for(Employee e : employees){
                  %>
                    <tr>
                     <td> <input required type="radio" name="radioEmployee" value= <%= e.getId()%>> </td>
                      <td><%= e.getDNI() %></td>
                      <td> <%= e.getName() %></td>                  
                      <td><%= e.getSurname()%></td>
                    </tr>
                    <%
                  }
                    %>
				  </tbody>
		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary">Seleccionar</button>
      </div>
    </div>
  </div>
</div>

    <!-- Bootstrap core JavaScript-->
    <script src="bootstrap/jquery/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="bootstrap/jquery-easing/jquery.easing.min.js"></script>
   
    <!-- Own JavaScript-->
    <script src="js/userManagment.js"></script>
    

  </body>

</html>
