<%@page import="entities.Stage"%>
<%@page import="entities.Activity"%>
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
    <!-- Imagen de logo -->
    <link rel="icon" type="image/png" sizes="48x48" href="images/logo-utn.png">

    <title>Registrar/ Modificar Actividad</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">
    <% Activity a = (Activity) request.getAttribute("actividad"); %>
    <% ArrayList<Stage> stages = (ArrayList<Stage>) request.getAttribute("stages"); %>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">
                Registrar / Editar Actividad
            </div>
            <div class="card-body">
                <form action="activityManagmentServlet" method="post">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="idActivityDescription">Descripción</label>
                                    <textarea class="form-control" id="idActivityDescription" name="activityDescription" 
                                    	cols="40" rows="3" placeholder="Descripción" autofocus><% if(a != null) { %><%=a.getDescription()%><% } %></textarea>
                                </div>                                
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="idActivityDuration">Duración</label>
                                    <input type="text" class="form-control" id="idActivityDuration" name="activityDuration" placeholder="Duración"
                                       <% if(a !=null) { %> value="<%= a.getDuration() %>" <% } %> >
                                </div>
                                <div class="form-group">
                                	<label for="idActivityStage">Etapa</label>
                                	<select class="form-control" name="stageName">
	                                	<!--NO ME ANDA EL SELECTED-->
	                                	<option>
	                                		Etapa ..
	                                	</option>
	                                	<% 
	                                	for(Stage s : stages) { 
	                                		if(a != null && a.getStage().getName().equalsIgnoreCase(s.getName())){ 
	                                	%>
	                                		<option selected>
	                                			<%= s.getName() %>
	                                		</option>
	                                	<% } else { %>
	                                		<option>
	                                			<%= s.getName() %>
	                                		</option>
	                                	<% 		
	                                		}
	                                	} %>
                                	</select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="hiddenID" <% if (a != null) { %> value="<%= a.getId() %>" <% } %> />
                    <button class="btn btn-primary btn-block" type="submit" name="saveButton" id="idSaveButton">
                        Guardar
                    </button>
                </form>
                <div class="text-center">
                    <a class="d-block small mt-3" href="indexAdmin.jsp">Pagina de inicio</a>
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
    <script src="js/userRegistration.js"></script>


</body>

</html>