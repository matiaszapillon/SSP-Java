<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" sizes="48x48" href="images/logo-utn.png">
    <title>Pagina de errores</title>
    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="bootstrap/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">
        <a class="navbar-brand mr-1" href="indexAdmin.jsp">Menu Administrador</a>
        <!-- Navbar Search -->
        <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        </form>

        <!-- Navbar -->
        <ul class="navbar-nav ml-auto ml-md-0">
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user-circle fa-fw"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="#">Settings</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="logIn.html" data-toggle="modal" data-target="#logoutModal">Logout</a>
                </div>
            </li>
        </ul>
    </nav>

    <div id="wrapper">
        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="indexAdmin.jsp">
                    SSP
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-archive"></i>
                    <span>ABM</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item" href="providersManagmentServlet">Proveedores</a>
                    <a class="dropdown-item">Insumos</a>
                    <a class="dropdown-item">Etapas</a>
                    <a class="dropdown-item" href="activityManagmentServlet">Actividades</a>
                    <a class="dropdown-item">Empleados</a>
                    <a class="dropdown-item" href="clientsManagmentServlet">Clientes</a>
                    <a class="dropdown-item" href="userManagmentServlet">Usuarios</a>
                </div>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="reportsServlet">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Reportes</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="projectManagmentServlet">
                    <i class="fas fa-project-diagram"></i>
                    <span>Gestion Proyectos</span>
                </a>
            </li>
        </ul>

        <%
        Integer statusCode = (Integer) request.getAttribute("statusCode");
        String servletName = (String) request.getAttribute("servletName");
        Throwable throwable = (Throwable) request.getAttribute("throwable");
        String requestUri = (String) request.getAttribute("requestUri");
        %>

        <!-- Content wrapper -->
        <div id="content-wrapper">
            <div class="container-fluid">
                <div class="jumbotron">                    
                    <% if (throwable == null && statusCode == null) { %>
                    <p class="lead">Error Information Is Missing</p>
                    <% } else if (statusCode != 500) { %>
                    <p class="lead">Error Details:</p>
                    <ul>
                        <li><b>Status Code</b>: <%= statusCode %></li>
                        <li><b>Requested URI</b>: <%= requestUri %></li>
                    </ul>
                    <p>La pagina solicitada no existe</p>
                    <% } else { %>
                    <p class="lead">Exception Details:</p>
                    <ul>
                        <li><b>Servlet Name</b>: <%= servletName %></li>
                        <li><b>Exception Name</b>: <%= throwable.getClass().getName() %></li>
                        <li><b>Requested URI</b>: <%= requestUri %></li>
                        <li><b>Exception Message</b>: <%= throwable.getMessage() %></li>
                    </ul>
                    <% } %>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="bootstrap/jquery/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="bootstrap/jquery-easing/jquery.easing.min.js"></script>
        <!-- Page level plugin JavaScript-->
        <script src="bootstrap/chart.js/Chart.min.js"></script>
        <script src="bootstrap/datatables/jquery.dataTables.js"></script>
        <script src="bootstrap/datatables/dataTables.bootstrap4.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>
        <!-- Demo scripts for this page-->
        <script src="js/demo/datatables-demo.js"></script>
        <script src="js/demo/chart-area-demo.js"></script>
</body>

</html>