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
				<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
				 	aria-expanded="false">
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
				<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
				 	aria-expanded="false">
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
					<span>Gestion Capacitaciones</span>
				</a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="projectManagmentServlet">
					<i class="fas fa-fw fa-table"></i>
					<span>Gestion Proyectos</span>
				</a>
			</li>
		</ul>

		<!-- Content -->
		<div id="content-wrapper">
			<div class="container-fluid">
				<div class="container">
				<% Project projectWithSupplies = (Project)request.getAttribute("projectWithSupplies"); %>
				<% Project projectWithStages = (Project)request.getAttribute("projectStages"); %>
					<form method="post" action="projectManagmentServlet" onsubmit="return validateForm()">	
					      <input type="hidden" name="clickedButton" id="idClickedButton" />					
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<h4 class="">Detalles del Proyecto</h4>
								</div>
								<div class="row">
									<div class="form-group col-2">
										<label> ID </label>
										<input class="form-control" type="text" name="idProjectName" readonly="readonly" <% if (u.getCurrentProject() != null) { %>
											value="<%= u.getCurrentProject().getId() %>" <%}%>>
									</div>
									<div class="form-group col-2">
										<label> Nombre </label>
										<input class="form-control" type="text" name="descriptionName" readonly="readonly" <% if (u.getCurrentProject() != null) { %>
											value="<%= u.getCurrentProject().getName() %>" <% }%> >
									</div>
									<div class="form-group col">
										<label> Descripcion </label>
										<input class="form-control" type="text" name="descriptionName" readonly="readonly" <% if (u.getCurrentProject() != null) { %>
											value="<%= u.getCurrentProject().getDescription() %>" <% }%> >
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-auto">
								<button type="submit" name="suppliesName" id="suppliesId" class="btn btn-primary form-group" onclick = "setHiddenValue(this.id)">Ver insumos</button>
							</div>
							<div class="col-auto">
								<button type="submit" name="stagesButton" id="stagesButtonId" class="btn btn-primary form-group" onclick = "setHiddenValue(this.id)">Ver Etapas</button>
							</div>
						</div>
					
					
					<!--  DIV para Stages -->
					<div <% if(projectWithStages == null) { %> style="display:none"  <% } %> >
						<div class="card mb-3">
							<!-- Header -->
							<div class="card-header">
								<i class="fas fa-table">
									Gestion de etapas
								</i>
							</div>
							<!-- Body -->
							<div class="card-body">
								<!-- Table -->
								<div class="table-responsive">
									<table class="table table-bordered" width="100%" cellspacing="0">
										<thead>
											<tr class="table-warning">
												<th></th>
												<th>#</th>
												<th>Etapa</th>
												<th>Descripción</th>
												<th>Estado</th>
												<th>Encargado</th>
											</tr>										
										</thead>
										<tbody>
											<!-- Hacer for -->
											<% if(projectWithStages != null) { 
												for(Stage stageAct: projectWithStages.getStages()) { %>
											<tr>
												<td>
													<input type="radio" name="radioSelectedStage" value=<%= stageAct.getId() %> >
												</td>
												<td><%= stageAct.getId() %></td>
												<td><%= stageAct.getName() %></td>
												<td><%= stageAct.getDescription() %></td>
												<td><%= stageAct.getState() %></td>
												<td>
													<% if(stageAct.getEmployee().getId() != 0) { %>
													<%= stageAct.getEmployee().getSurname() + " " + stageAct.getEmployee().getName() %>
													<% } else { %>Sin encargado<% } %> 													
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
							<!-- Botones etapa -->
							<div class="card-footer small text-muted">
								<div class="form-row">
									<div class="form-group col-auto">
										<button type="submit" name="addStageForm" id="addStageFormId" class="btn form-group btn-success" onclick = "setHiddenValue(this.id)">Agregar Etapa</button>										
									</div>	
									<div class="form-group col-auto">
										<button type="submit" name="modifyStageForm" id="modifyStageId" class="btn form-group btn-info" onclick = "setHiddenValue(this.id)">Modificar Etapa</button>	
									</div>
									<div class="form-group col">
										<button type="submit" name="deleteStage" id="deleteStageId" class="btn form-group btn-danger float-right"
											data-toggle="tooltip" data-placement="left" title="Eliminar Etapa" onclick = "setHiddenValue(this.id)">X</button>		
									</div>																	
								</div>										
							</div>													
						</div>
					</div>
					<!-- Fin gestion de etapas  -->

					<!--  DIV para Supplies -->
					<div <% if(projectWithSupplies == null) { %> style="display:none" <% } %> >
						<div class="card mb-3">
							<div class="card-header">
								<i class="fas fa-table">
									Gestion de insumos
								</i>								
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>#</th>
												<th>Nombre</th>
												<th>Descripcion</th>
												<th>Cantidad</th>
												<th>Unidad</th>
												<th>Stock</th>
												<th>Proveedor</th>
												<th>Categoria Prov</th>
											</tr>
										</thead>
										<tbody>
											<%
											if(projectWithSupplies != null) {
												ArrayList<Supply> supplies = projectWithSupplies.getSupplies();
												for(Supply s : supplies) {
											%>
											<tr>
												<td>
													<input type="radio" name="radioSelectedSupply" value=<%= s.getId() %> >

												</td>
												<td>
													<%= s.getName() %>
												</td>
												<td>
												<%= s.getDescription() %>	
												</td>
												<td> <div class="form-row">
													<div class="col">
														<input type="text" name="<%=s.getId()%>"  size="3" value="<%= s.getQuantity() %>">													
													</div>		
													<div class="col">
														<button  name="updateButton" id="updateButtonId" onclick = "setHiddenValue(this.id)"><img src="images/save.ico" height="25" width="20">
														</button>				
													</div>
													</div>
												</td>
												<td>
													<%=s.getUnity()   %>
												</td>
												<td>
													<%= s.getStock() %>
												</td>
												<%if(s.getProvider().getBusiness_name() != null){ %>
												<td>
													<%= s.getProvider().getBusiness_name() %></td>
												<%} else { %>
												<td>
													<%= s.getProvider().getName() + " " + s.getProvider().getSurname() %>
												</td>
												<%} %>
												<td>
													<%= s.getProvider().getCategory() %>
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
								<!-- Botones insumos -->
								<div class="card-footer small text-muted">
									<div class="row">
										<div class="col-auto">
											<button type="submit" name="addSupplyName" id="addSupplyId" class="btn btn-success" onclick = "setHiddenValue(this.id)">Agregar</button>
										</div>

										<div class="col-auto">
											<button type="submit" name="deleteSupplyName" id="deleteSupplyId" class="btn btn-danger" onclick = "setHiddenValue(this.id)">Eliminar</button>
										</div>
										<div class="col-md-4">
											<button type="submit" name="calculateCostName" id="calculateCostId" class="btn btn-info float-right" onclick = "setHiddenValue(this.id)" >Calcular costo total</button>
										</div>
										<div class="col">
											<input type="text" class="form-control float-right" name="costName" 
												<% if(projectWithSupplies != null && projectWithSupplies.getTotalCost() != 0) { %>
												value="<%= projectWithSupplies.getTotalCost() %>" />	
												<% } %>
										</div>
									</div>
								</div>

						</div>
					</div>
 					<!-- Fin gestion de insumos  -->
					</form>
				</div>
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
	<!-- /.content-wrapper -->
	<!-- /#wrapper -->
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>
	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
	
	    
        <!-- Own scripts-->
        
     <script src="js/projectManagment.js"></script>
	 <!-- Own JavaScript-->
     <script>
	     $(function () {
	    	  $('[data-toggle="tooltip"]').tooltip()
	    	})
	 </script>

</body>

</html>