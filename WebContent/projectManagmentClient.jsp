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
      <a class="navbar-brand mr-1" href="indexAdmin.jsp">Menu Cliente</a>
      <!-- Navbar Search -->
      <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
      </form>
      <!-- Navbar -->
      
      <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            
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
          <a class="nav-link" href="indexClient.jsp">
            SSP
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-archive"></i>
            <span>ABM</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <a class="dropdown-item" >Etapas</a>
          </div>
        </li>
          <li class="nav-item active">
            <a class="nav-link" href="projectManagmentServlet">
              <i class="fas fa-tasks"></i>
              <span>Seguimiento de Proyectos</span></a>
            </li>
          </ul>
          <div id="content-wrapper">
            <div class="container-fluid">
              
              <!-- Projects list -->
              <div class="container">
                <%Project clickedProject = (Project)request.getAttribute("project"); %>
                <div class="row">
                  <!-- Columna con primer form -->
                  <div class="col-md-4">
                    <form method="post" action="projectManagmentServlet">
                      <div class="form-group">
                        <input class="form-control" type="text" name="" placeholder="Buscar proyecto">
                      </div>
                      
                      <div class="form-group">
                        <%ArrayList<Project> projects = (ArrayList<Project>)request.getAttribute("projects");
                          for(Project p: projects){
                          %>
                          <button type="submit" id=<%=p.getId()%> name="buttonProject"
                          <%if(clickedProject != null) {
                          if(clickedProject.getId() == p.getId()){
                          %> class="list-group-item list-group-item-action btn btn-light active"
                          <%}else {%>class="list-group-item list-group-item-action btn btn-light" <% }} else { %> class="list-group-item list-group-item-action btn btn-light"
                          <%}%> value=<%=p.getId()%> > <%=p.getName() %> </button>
                          <%} %>
                        </div>
                      </form>
                    </div>
                    <!-- Columna con segundo form -->
                    <div class="col-md-8">
                      <form method="post" action="projectManagmentServlet">
                        <div class="form-group">
                          <h4>Detalles del proyecto</h4>
                        </div>
                        <div class="row">
                          <div class="form-group col-2">
                            <label>ID</label>
                            <input class="form-control" type="text" name="idProjectName" readonly <%if (clickedProject != null){ %>
                            value= "<%= clickedProject.getId()%>"<%} %>>
                          </div>
                          <div class="form-group col">
                            <label> Descripcion </label>
                            <input class="form-control" type="text" name="descriptionName"  readonly <%if (clickedProject != null){ %>
                            value= "<%=clickedProject.getDescription() %><% } %>">
                          </div>
                          <div class="form-group col">
                            <label> Nombre del Proyecto </label>
                            <input class="form-control" type="text" name="nameName" readonly <%if (clickedProject != null){ %>
                            value= "<%=clickedProject.getName() %>" <% } %>>
                          </div>
                        </div>
                        <div class= "row">
                          <div class="form-group col">
                            <label>Cliente</label>
                            <input class="form-control" type="text" name="clientName" readonly<%if (clickedProject != null) {if(clickedProject.getClient() != null){ %>
                            value= "<%= clickedProject.getClient().getBusiness_name() %>"  <% }}%>>
                          </div>
                          <div class="form-group col">
                            <label>CUIT / CUIL</label>
                            <input class="form-control" type="text" name="CUIT_CUILName" readonly <%if (clickedProject != null) { if(clickedProject.getClient() != null){%>
                            value= "<%= clickedProject.getClient().getCUIT_CUIL() %>" <% }} %>>
                          </div>
                        </div>
                        <div class="row">
                          <div class="form-group col">
                            <label>Estado proyecto</label>
                            <input class="form-control" type="text" name="stateName" readonly <%if (clickedProject != null) { %>
                            value = " <%= clickedProject.getState()%><% } %>">
                          </div>
                          <div class="form-group col">
                            <label>Etapa actual</label>
                            <input class="form-control" type="text" name="currentStageName" readonly <%if (clickedProject != null) {
                            if(clickedProject.getCurrentStage() != null){ %> value = "<%=clickedProject.getCurrentStage().getName() %>"
                            <%} else { %> value =  "<%=" - " %><% } } %>">
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
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
          <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
          
          <!-- Own JavaScript-->
          <script src="js/projectManagment.js"></script>
        </body>
      </html>