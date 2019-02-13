<%@page import="entities.*"%>
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

    <title>Registrar Proyecto</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">
    <!--  Traer usuario -->
    <% User u = (User)request.getAttribute("usuario"); %>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">
                <i><b>Registrar Proyecto</b></i>
            </div>
            <div class="card-body">
                <form action="clientsManagmentServlet" method="post">
                    <!-- Fila 1-->
                    <div class="form-group row">
                        <label for="idProjectName" class="col-md-4 col-form-label">Nombre</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="idProjectName" placeholder="Nombre" name="projectName"
                                required="required">
                        </div>
                    </div>
                    <!-- Fila 2-->
                    <div class="form-group row">
                        <label for="idProjectDescription" class="col-md-4 col-form-label">Descripción</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="idProjectDescription" placeholder="Descripción"
                                name="projectDescription" required="required">
                        </div>
                    </div>
                    <!-- Fila 3-->
                    <div class="form-group row">
                        <!--  Button addons for employee -->
                        <div class="input-group col-md-12">
                            <input type="text" class="form-control" disabled="disabled" aria-label="" aria-describedby="btnCollapseClients"
                                placeholder="Seleccione Cliente" value="">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="submit" name="btnCollapseClients" data-toggle="collapse"
                                    data-target="#" aria-expanded="false">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!--  Botones -->
                    <div class="form-group">
                        <button class="btn btn-success btn-block" type="submit" name="saveNewProject">Guardar</button>
                    </div>
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