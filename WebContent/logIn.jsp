<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" sizes="48x48" href="images/logo-utn.png">

    <title>Log In</title>

    <!-- Bootstrap core CSS -->
    <link href="css/signInBootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
    <!-- Formulario de Ingreso -->
    <form class="form-signin" action="logInServlet" method="post">
        <img class="mb-4" src="css/bootstrap-solid.html" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please Log in</h1>
        <%
        String msjError = (String) request.getAttribute("msjError");
    	if(msjError != null){
    	%>
        <!-- Alerta de error de ingreso -->
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <p>Usuario y/o contraseña incorrectos.</p>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <% } %>
        <label for="inputUsername" class="sr-only">Username</label>
        <input id="inputUsername" name="username" class="form-control" placeholder="Username" type="text" autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input id="inputPassword" name="password" class="form-control" placeholder="Password" type="password">
        <button class="btn btn-lg btn-outline-primary btn-block" type="submit">Log in</button>
        <p class="mt-5 mb-3 text-muted">© 2018</p>
    </form>

    <!-- Bootstrap core JavaScript-->
    <script src="bootstrap/jquery/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>