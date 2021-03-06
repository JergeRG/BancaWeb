<%-- 
    Document   : registrousuario
    Created on : 29-nov-2018, 7:02:13
    Author     : Jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession(true);
    String nombre = request.getParameter("nombre");
    sesion.setAttribute("nombre", nombre);
    String apellidos = request.getParameter("apellidos"); 
    sesion.setAttribute("apellidos", apellidos);
    String email = request.getParameter("email");
    sesion.setAttribute("email", email);
    String telefono = request.getParameter("telefono");
    sesion.setAttribute("telefono", telefono);
    String direccion = request.getParameter("direccion");
    sesion.setAttribute("direccion", direccion);
%>
<html lang="es">
<head>
	<title>Registro</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
    
    <style>
        body{
            
           position: absolute;
           left: 25%;
           top:5%;   
           background: #2E3740;
            color: #435160;
        }
    </style>
</head>
<body>
    
       <div class="container center-align">

		<div class="wrap-contact100" >
			<div class="contact100-form-title" style="background-image: url(images/bg-01.jpg);" >
				<span class="contact100-form-title-1">
                                    Registro de Usuario
				</span>
                            <a  href="../" class="contact100-form-title-1">(Regresar al inicio)</a>
			</div>
                        <div class="error"><center><b>Ya existe una cuenta con el usuario con el cual deseas registrarte, elige otro</b></center></div>
			<form class="contact100-form validate-form" action="../ingresarUsuario">
				<div class="wrap-input100 validate-input" data-validate="Debe ingresar un nombre valido">
					<span class="label-input100">Nombre</span>
					<input class="input100" type="text" name="nombre" value ="${nombre}" placeholder="Nombre (la primer letra debe ir may??cula)">
					<span class="focus-input100"></span>
				</div>
                <div class="wrap-input100 validate-input" data-validate="Debe ingresar apellidos validos">
                 <span class="label-input100">Apellidos</span>
					<input class="input100" type="text" name="apellidos" value ="${apellidos}" placeholder="Apellidos (la primer letra debe ir may??cula)">
					<span class="focus-input100"></span>
                </div>
				<div class="wrap-input100 validate-input" data-validate="Debe ingresar un usuario valido">
					<span class="label-input100">Usuario</span>
					<input class="input100" type="text" name="username" placeholder="Usuario (entre 3 y 10 caracteres)">
					<span class="focus-input100"></span>
				</div>
                 <div class="wrap-input100 validate-input" data-validate="Debe contener: Minimo 8 caracteres/Maximo 15/Al menos una letra may??scula/Al menos una letra minucula/Al menos un d??gito/No espacios en blanco/Al menos 1 caracter especial">
                 <span class="label-input100">Contrase??a</span>
					<input class="input100" type="password" id ="pass1" name="password" class="validate" placeholder="8-15 caracteres (Ejemplo1$)">
                     
					<span class="focus-input100"></span>
                </div>
				<div class="wrap-input100 validate-input" data-validate="Las contrase??as deben coincidir">
                 <span class="label-input100">Confirmar contrase??a</span>
					<input class="input100" type="password" id ="pass2" name="confirm" class="validate" placeholder="8-15 caracteres (Ejemplo1$)">
                     
					<span class="focus-input100"></span>
                </div>
				<div class="wrap-input100 validate-input" data-validate = "Debe ingresar un correo valido: ejemplo@abc.com">
					<span class="label-input100">Correo</span>
					<input class="input100" type="text" name="email" value ="${email}"  placeholder="Ingrese su direccion email" >
					<span class="focus-input100"></span>
				</div>
				<div class="wrap-input100 validate-input" data-validate="Ingrese un numero telefonic validoo">
					<span class="label-input100">Telefono</span>
					<input class="input100" type="text" name="telefono" value ="${telefono}"  placeholder="Ingrese su numero telefonico">
					<span class="focus-input100"></span>
				</div>
                <div class="wrap-input100 validate-input" data-validate="Ingrese una direccion valida">
					<span class="label-input100">Direccion</span>
					<input class="input100" type="text" name="direccion" value ="${direccion}"  placeholder="Ingrese su direccion">
					<span class="focus-input100"></span>
				</div>
				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn">
						<span>
							Registrarse
							<i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
						</span>
					</button>
				</div>
			</form>
		</div>
	</div>
    


	<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
	<script src="js/map-custom.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());

	  gtag('config', 'UA-23581568-13');
	</script>

</body>
</html>

