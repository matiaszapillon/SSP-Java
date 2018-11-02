<%@page import="entities.User"%>
<%@page import="entities.Client"%>
<%@page import="entities.Employee"%>
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
<%User u = (User)request.getAttribute("user"); %>
    <div class="container">
      <div class="card card-register mx-auto mt-5">
        <div class="card-header">Registrar / Editar Usuario</div>
        <div class="card-body">
          <form action = "userRegistrationServlet" method = "post">
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="text" id="firstName" class="form-control" placeholder="First name" required="required" autofocus="autofocus" 
                    <%if(u != null){ %> value = <%=u.getUsername() %>                    
                    <%
                    } 
                    %>>
                    
                    <label for="firstName">Nombre de usuario</label>
                  </div>
                </div>
                <div class="col-md-6">
              <div class="form-label-group">
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="required">
                <label for="inputEmail">Email</label>
              </div>
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
                    <label for="inputPassword">Contraseña</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                    <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm password" required="required">
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
  	   					  <option>Cliente</option>
    					  <option>Empleado</option>
    					  <option>Administrador</option>
   					 </select>
   				 </div>
   				 <div class="col-md-5">
   				 	<input type="text" class="form-control" placeholder="Seleccionar cliente o empleado" aria-label="Search" aria-describedby="basic-addon2" disabled="">
   				 	 </div>
          <div class="col-md-1">
            <button class="btn btn-primary" type="button" onclick = "findPeople()">
              <i class="fas fa-search"></i>
            </button>
          </div>
   				

 			 </div>   
			<button class="btn btn-primary btn-block" type="submit" name="saveButton" id="idSaveButton"> Guardar</button>
          </form>
          <div class="text-center">
            <a class="d-block small mt-3" href="loginAdmin.jsp">Pagina de inicio</a>
            <a class="d-block small" href="forgot-password.html">Olvidó la contraseña?</a>
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
