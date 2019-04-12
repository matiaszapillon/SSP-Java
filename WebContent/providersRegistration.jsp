<%@page import="entities.Provider"%>
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
    <link rel="icon" type="image/png" sizes="48x48" href="images/logo-utn.png">
    <title>Provider Registration</title>

    <!-- Bootstrap core CSS-->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="bootstrap/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">
    <% Provider p = (Provider)request.getAttribute("proveedor"); %>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">
                Registrar / Editar Proveedor
            </div>
            <div class="card-body">
                <form action="providersManagmentServlet" method="post">

                    <!-- Fila 1 -->
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderNumber" name="providerID"
                                        placeholder="Id Proveedor" readonly="readonly" <% if(p !=null) { %> value="<%=p.getId() %>"
                                    <% } %> >
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderBssName" name="providerBssName" autofocus
                                        placeholder="Proveedor" required="required" <% if(p !=null) { %> value="<%=p.getBusiness_name() %>"<% } %> >
                                    <label for="idProviderBssName">Proveedor</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Fila 2 -->
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderName" name="providerName"
                                        placeholder="Nombre" required="required" <% if(p !=null) { %> value="<%=p.getName() %>"
                                    <% } %> >
                                    <label for="idProviderName">Nombre</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderSurname" name="providerSurname"
                                        placeholder="Apellido" required="required" <% if(p !=null) { %> value="<%=p.getSurname() %>"
                                    <% } %> >
                                    <label for="idProviderSurname">Apellido</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Fila 3 -->
                    <div class="form-group">
                        <div class="form-row">
                        	<div class="col-md-6 input-group">
                                <select class="custom-select" id="inputStateGroup" name="providerState">
                                    <option <% if(p != null && p.getState().equals(null)){ %> selected <% } %> >
                                    	Estado ..
                                    </option>
                                    <option value="<%= Provider.APROBADO %>" <% if(p != null && p.getState().equalsIgnoreCase("APROBADO")){ %> selected <% } %> >
                                    	APROBADO
                                    </option>
                                    <option value="<%= Provider.DESAPROBADO %>" <% if(p != null && p.getState().equalsIgnoreCase("DESAPROBADO")){ %> selected <% } %> >
                                    	DESAPROBADO
                                    </option>
                                </select>
                            </div>                            
                            <div class="col-md-6">                                
                                  <select class="custom-select" id="inputCategorySelect" name="providerCategory">
                                    <option <% if( p != null && p.getCategory().equals(null)){ %> selected <% } %> >
                                    	Categoria ..
                                    </option>
                                    <option value="<%= Provider.CATEGORY_A %>" <% if( p != null && p.getCategory().equalsIgnoreCase("A") ){ %> selected <% } %> >
                                    	A
                                    </option>
                                    <option value="<%= Provider.CATEGORY_B %>" <% if( p != null && p.getCategory().equalsIgnoreCase("B") ){ %> selected <% } %> >
                                    	B
                                    </option>
                                    <option value="<%= Provider.CATEGORY_C %>" <% if( p != null && p.getCategory().equalsIgnoreCase("C") ){ %> selected <% } %> >
                                    	C
                                    </option>
                                  </select>
                        	</div>
                        </div>
                    </div>

                    <!-- Fila 4 -->
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderEmail" name="providerEmail"
                                        placeholder="Email" required="required" <% if(p !=null) { %> value="<%= p.getEmail() %>"
                                    <% } %> >
                                    <label for="idProviderEmail">Email</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderDescription" name="providerDescription"
                                        placeholder="Descripcion" required="required" <% if(p !=null) { %> value="<%= p.getDescription() %>"
                                    <% } %> >
                                    <label for="idProviderDescription">Descripcion</label>
                                </div>
                            </div>                            
                        </div>
                    </div>

                    <!-- Fila 5 -->
                    <div class="form-group">
                        <div class="form-row">
                        	<div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderAddress" name="providerAddress"
                                        placeholder="Direccion" required="required" <% if(p !=null) { %> value="<%= p.getAddress() %>"
                                    <% } %> >
                                    <label for="idProviderAddress">Direccion</label>
                                </div>
                            </div>                        	
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" class="form-control" id="idProviderPhone" name="providerPhone"
                                        placeholder="Telefono" required="required" <% if(p !=null) { %> value="<%= p.getPhone() %>"
                                    <% } %> >
                                    <label for="idProviderPhone">Telefono</label>
                                </div>
                            </div>                            
                        </div>
                    </div>

                    <button class="btn btn-primary btn-block" type="submit" name="saveButton">Guardar</button>

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

</body>

</html>