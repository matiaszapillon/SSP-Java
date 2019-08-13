<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.*"%>
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
    <title>Bienvenido </title>
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
                    <% User u =(User)session.getAttribute("usuario"); %>
                    <%= u.getUsername() %>
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

        <% ArrayList<Project> projects = (ArrayList<Project>)request.getAttribute("projects"); %>
        <% ArrayList<Client> clients = (ArrayList<Client>)request.getAttribute("clients"); %>
        
        <!-- Content wrapper -->
        <div id="content-wrapper">
            <div class="container-fluid">
                <div class="form-group">
                    <h4 class="text-center">FILTROS</h4>
                </div>
                <!-- Formulario de filtros -->
                <form class="mb-3" action="reportsServlet" method="POST">
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="">Estado</label>
                            <select name="stateProject" id="" class="form-control">
                                <option selected>-</option>
                                <option>No Iniciado</option>
                                <option>En Curso</option>
                                <option>Finalizado</option>
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="">Cliente</label>
                            <select class="form-control" name="clientID">
                                <option value="0" selected>
                                    -
                                </option>
                                <% if(clients != null){
                                      for(Client c : clients) {                                      
                                %>
                                <option value="<%= c.getId() %>">
                                    <%= c.getBusiness_name() %>
                                </option>
                                <% 		
                                  }}
                                %>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <label for=""> Año inicio proyecto</label>
                            <input type="text" name="startDateName" class="form-control">
                        </div>
                        <div class="form-group col-md-2">
                            <label for=""> Año fin proyecto</label>
                            <input type="text" name="endDateName" class="form-control">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-inline">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="gridCheck" name="chBoxCost">
                                <label class="form-check-label" for="gridCheck">
                                    Costo total
                                </label>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-outline-success" name="applyFilter">
                                    <i class="fas fa-check"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <!-- TABLA -->
            <form action="projectManagmentServlet" method="post">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fas fa-table"></i>
                        Proyectos
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead class="bg-dark text-white">
                                    <tr>
                                        <th>Seleccionar</th>
                                        <th>Nombre</th>
                                        <th>Descripcion</th>
                                        <th>Año Inicio</th>
                                        <th>Año Finalizacion</th>
                                        <th>Estado</th>
                                        <th>Cliente</th>
                                        <th>Costo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Hacer for -->
                                    <% if(!projects.isEmpty()) { 
									   		for(Project p: projects) { %>
                                    <tr>
                                        <td>
                                            <input type="radio" name="radioSelectedStage" value="<%= p.getId() %>">
                                        </td>
                                        <td><%= p.getName() %></td>
                                        <td><%= p.getDescription() %></td>
                                        <td><%= p.getStartDate() %></td>
                                        <td>
                                            <% if(p.getEndDate() == null){ %>
                                            No Finalizado
                                            <% } else { %>
                                            <%= p.getEndDate() %>
                                            <% } %>
                                        </td>
                                        <td><%= p.getState() %></td>
                                        <td><%= p.getClient().getBusiness_name() %></td>
                                        <td>
                                            <% if(p.getTotalCost() != 0) { %>
                                            <%= p.getTotalCost() %>
                                            <% } else { %>
                                            Sin calcular
                                            <% } %>
                                        </td>
                                    </tr>
                                    <!-- Cerrar for -->
                                    <% 
											}	
										}
									%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card-footer small text-muted">
                        <div class="row">
                            <div>
                                <label class="col-form-label">Ver si va algo aca que sirva</label>
                            </div>
                            <div class="col-auto">
                                <input type="text" name="quantityName" value="">
                            </div>
                        </div>
                    </div>
                </div>
            </form>

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
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
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
</body>

</html>