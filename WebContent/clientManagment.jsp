<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Client" %>
<%@ page import="entities.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" type="image/png" sizes="48x48" href="images/logo-utn.png">

    <title>ABMC Client</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plug in CSS-->
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
                    <%=((User)session.getAttribute("usuario")).getUsername() %>
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
                    <span>ABM</span>
                </a>
                <div class="dropdown-menu" aria-labelledby="pagesDropdown">
						<a class="dropdown-item" href="providersManagmentServlet">Proveedores</a>
						<a class="dropdown-item">Insumos</a>
						<a class="dropdown-item">Etapas</a>
						<a class="dropdown-item" href="activityManagmentServlet">Actividades</a>
						<a class="dropdown-item">Empleados</a>
						<a class="dropdown-item active" href="clientsManagmentServlet">Clientes</a>
						<a class="dropdown-item" href="userManagmentServlet">Usuarios</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="userManagmentServlet">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Gestion Capacitaciones</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="projectManagmentServlet">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Gestion Proyectos</span></a>
            </li>
        </ul>
        <div id="content-wrapper">
            <div class="container-fluid">

                <!-- Formulario -->
                <form action="clientsManagmentServlet" method="POST" onsubmit="return validateForm()">
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Gestion de Clientes
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr class="table-info">
                                            <th></th>
                                            <th>#</th>
                                            <th>Cliente</th>
                                            <th>CUIT/CUIL</th>
                                            <th>Dirección</th>
                                            <th>Email</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        ArrayList<Client> clients = (ArrayList<Client>) request.getAttribute("clientes");
                                        for(Client cliActual : clients){
                                        %>
                                        <tr>
                                            <td>
                                                <input type="radio" name="radioABM" value="<%= cliActual.getId() %>">
                                            </td>
                                            <td>
                                                <%= cliActual.getId() %>
                                            </td>
                                            <td>
                                                <%= cliActual.getBusiness_name() %>
                                            </td>
                                            <td>
                                                <%= cliActual.getCUIT_CUIL() %>
                                            </td>
                                            <td>
                                                <%= cliActual.getAddress() %>
                                            </td>
                                            <td>
                                                <%= cliActual.getEmail() %>
                                            </td>
                                        </tr>
                                        <%
                                        }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer small text-muted">
                            <button type="submit" name="addButton" class="btn btn-success" id="addButton" onclick="setHiddenValue(this.id)">Nuevo</button>
                            <button type="submit" name="editButton" class="btn btn-info" id="editButton" onclick="setHiddenValue(this.id)">Editar</button>
                            <button type="submit" name="deleteButton" class="btn btn-danger float-right" id="deleteButton"
                                onclick="setHiddenValue(this.id)">Eliminar</button>
                        </div>
                        <input type="hidden" name="rowValue" id="rowIdValue" />
                        <input type="hidden" name="clickedButton" id="idClickedButton" />
                    </div>
                </form>
            </div>
            <!-- /.container-fluid -->
            <!-- Sticky Footer -->
            <footer class="sticky-footer">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright © Your Website 2018</span>
                    </div>
                </div>
            </footer>
        </div>
        <!-- /.content-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Esta seguro que desea salir?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Selecciona "Logout" si desea salir y finalizar la session</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="logIn.html">Logout</a>
                </div>
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
    <!-- Own JavaScript-->
    <script src="js/userManagment.js"></script>
</body>

</html>