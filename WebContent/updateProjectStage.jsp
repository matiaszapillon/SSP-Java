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
    
    <title>Gestion de Proyecto</title>
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
                    <% User u = (User)session.getAttribute("usuario"); %>
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
            <li class="nav-item">
                <a class="nav-link" >
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Gestion Capacitaciones</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="projectManagmentServlet">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Gestion Proyectos</span></a>
            </li>
        </ul>
        <!--  Traer empleados -->
        <% ArrayList<Employee> employees = (ArrayList<Employee>) request.getAttribute("empleados"); %>
		
        <div id="content-wrapper">
            <div class="container">
                <div class="card card-register mx-auto mt-5">
                    <div class="card-header">
                        Editar Etapa
                    </div>
                    <div class="card-body">
                        <!-- Formulario -->
                        <form action="projectManagmentServlet" method="post"> 

                                <!-- FIla 1 -->
                                <div class="form-group">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-label-group">
                                                <input type="text" class="form-control" name="stageName" readonly="readonly" value="<%= u.getCurrentStage().getName() %>" >
                                                <label for="stageName">Etapa</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-label-group">
                                                <input type="text" class="form-control" name="stageDescription" readonly="readonly" value="<%= u.getCurrentStage().getDescription() %>" >
                                                <label for="stageDescription">Descripción</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Fila 2-->
                                <div class="form-group">
                                    <div class="form-row">
                                    	<!-- Select group  -->
                                        <div class="col-md-6 input-group">
                                            <select class="custom-select" id="inputStateGroup" name="stageState">
                                                <option value="<%= Stage.NO_INICIADA %>" <% if(u.getCurrentStage().getState().equalsIgnoreCase("NO INICIADA")){ %> selected <% } %> >
                                                    No Iniciada
                                                </option>
                                                <option value="<%= Stage.EN_CURSO %>" <% if(u.getCurrentStage().getState().equalsIgnoreCase("EN CURSO")){ %> selected <% } %> >
                                                    En Curso
                                                </option>
                                                <option value="<%= Stage.FINALIZADA %>" <% if(u.getCurrentStage().getState().equalsIgnoreCase("FINALIZADA")){ %> selected <% } %> >
                                                    Finalizada
                                                </option>
                                            </select>
                                        </div> 
                                        <!--  Button addons for employee -->
                                        <div class="col-md-6 input-group"> 
											<input type="text" class="form-control" disabled="disabled" aria-label="" aria-describedby="butttonTableEmployee" 
												placeholder="<% if(u.getCurrentStage().getEmployee().getId() == 0) { %>Seleccione Empleado<% } %>"
												value="<% if(u.getCurrentStage().getEmployee().getId() != 0) { %><%= u.getCurrentStage().getEmployee().getSurname() + ' ' + u.getCurrentStage().getEmployee().getName() %><% } %>" >
											<div class="input-group-append">
												<button class="btn btn-primary" type="submit" name="btnCollapseAttendant" data-toggle="collapse" data-target="#collapseEmployeeTable" 
													<%if(employees == null) { %> aria-expanded="false" <% } else { %> aria-expanded="true" <% } %> 
													aria-controls="collapseEmployeeTable">
													<i class="fas fa-search"></i>
								              	</button>
										  	</div>
										</div>                 
                                    </div>                                
                                </div>
                                
                                <!-- Botones -->
                               <button type="submit" name="updateStage" class="btn btn-success">Guardar Cambios</button>
                               <button type="submit" name="goBack" class="btn btn-warning">Volver</button>
                        
                
			                	<!--  DIV para Employee -->
								<div <%if(employees == null) { %> class="collapse mt-3" <% } else { %> class="collapse show mt-3" <% } %> id="collapseEmployeeTable" >
									<div class="card mb-3">
										<!-- Header -->
										<div class="card-header bg-secondary">
											<i class="fas fa-table text-white">
												Empleados
											</i>
										</div>
										<!-- Body -->
										<div class="card-body">
											<!-- Table -->
											<div class="table-responsive">
												<table class="table table-striped" width="100%" cellspacing="0">
													<thead>
														<tr class="table-info">
															<th scope="col"></th>
											                <th scope="col">DNI</th>
											                <th scope="col">Nombre</th>
											                <th scope="col">Apellido</th>
														</tr>										
													</thead>
													<tbody>
														<!-- Hacer for -->
														<%										
														if(employees != null) {
										                	for(Employee e : employees){
										                %>
										            	<tr>
										              		<td> 
										                		<input type="radio" name="radioEmployee" value="<%= e.getId() %>" >
										                	</td>
										                	<td>
										                  		<%= e.getDNI() %>
										                	</td>
											                <td>
											                	<%= e.getName() %>
											                </td>
											                <td>
											                	<%= e.getSurname() %>
											                </td>
										              </tr>
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
												<div class="col-md-4">
													<button type="submit" name="addSelectedEmployee" class="btn btn-success">Seleccionar</button>
												</div>
											</div>										
										</div>	
															
									</div>
								</div>
								<!-- Fin DIV employee  -->
					
						</form>
                    </div>
                </div>				               

                <!-- Sticky Footer -->
                <footer class="sticky-footer">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright © Your Website 2018</span>
                        </div>
                    </div>
                </footer>
                
            </div>
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
                    <h5 class="modal-title" id="exampleModalLabel">Estas seguro que quiere salir?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Selecciona 'Logout' si desea cerrar su session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="logIn.jsp">Logout</a>
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