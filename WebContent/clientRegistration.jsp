<%@page import="entities.Client"%>
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

    <title>Registrar/ Modificar Cliente</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">
    <% Client c = (Client)request.getAttribute("cliente"); %>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">
                Registrar / Editar Actividad
            </div>
            <div class="card-body">
                <form action="clientsManagmentServlet" method="post">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="clientName">Nombre</label>
                                    <input type="text" class="form-control" name="clientName" placeholder="Nombre"
                                       <% if(c !=null) { %> value="<%= c.getBusiness_name() %>" <% } %> >
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="clietnCuitCuil">CUIT/CUIL</label>
                                    <input type="text" class="form-control" name="clietnCuitCuil" placeholder="CUIT/CUIL"
                                       <% if(c !=null) { %> value="<%= c.getCUIT_CUIL() %>" <% } %> >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="clientDirection">Dirección</label>
                                    <input type="text" class="form-control" name="clientDirection" placeholder="Dirección"
                                       <% if(c !=null) { %> value="<%= c.getAddress() %>" <% } %> >
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="clientEmail">Email</label>
                                    <input type="text" class="form-control" name="clientEmail" placeholder="Email"
                                       <% if(c !=null) { %> value="<%= c.getEmail() %>" <% } %> >
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="hiddenID" <% if (c != null) { %> value="<%= c.getId() %>" <% } %> />
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