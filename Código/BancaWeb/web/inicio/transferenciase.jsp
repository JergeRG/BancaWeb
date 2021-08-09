<%-- 
    Document   : transferenciase
    Created on : 03-dic-2018, 2:58:12
    Author     : Jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>BancaWeb</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Raleway:300,400,500,700,800|Montserrat:300,400,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/magnific-popup/magnific-popup.css" rel="stylesheet">
  <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">
  <script>alert("Su transferencia se realizo con éxito.");</script>
</head>

<body id="body">
    
    <%
        HttpSession z = request.getSession(true);
        String w= (String)z.getAttribute("usuario");
        if(w==null)
            response.sendRedirect("..\\index.html");
    %>

  <!--==========================
    Header
  ============================-->
  <header id="header">
    <div class="container">

      <div id="logo" class="pull-left">
        <h1><a href="#body" class="scrollto">Banca<span>Web-<a href="Cuentas.jsp"> ${cuenta}</a></span></a></h1>
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-has-children"><a href="inicio.jsp#body">Inicio</a></li>
          <li><a href="inicio.jsp#call-to-action">Perfil</a></li>
          <li class="menu-has-children"><a href="inicio.jsp#services">Servicios</a>
            <ul>
              <li><a href="..\consultaSaldo">Consultas</a></li>
              <li><a href="servicios.jsp">Pago de servicios</a></li>
              <li><a href="">Transferencias</a></li>
              <li><a href="..\visualizarMovimientos">Movimientos</a></li>
            </ul>
          </li>
          <li><a href="inicio.jsp#contact">Contactanos</a></li>
          <li><a href="..\cerrarSesion">Cerrar sesión</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <main id="main">
<section id="contact" class="wow fadeInUp">

    <!--==========================
      About Section
    ============================-->
    <div class="container">
        <div class="section-header">
          <h2>Transferencias</h2>
          <p>Le permitimos hacer transferencias a éste u otros bancos, indique el numero de cuenta, la cantidad y su nip:</p>
        </div>
        <div class="form">
          <div id="errormessage"></div>
          <form action="../realizarTransferencia">
            <div class="form-row">
              <div class="form-group col-md-6">
                <input type="text" name="cuenta" class="form-control" id="name" placeholder="Cuenta a la que desea transferir: (XXXXXX)" data-rule="minlen:4" data-msg="Please enter at least 4 chars" required/>
                <div class="validation"></div>
              </div>
              <div class="form-group col-md-6">
                <input type="text" class="form-control" name="cantidad" id="email" placeholder="Monto a transferir: ($XXX.XX)" data-rule="email" data-msg="Please enter a valid email" required/>
                <div class="validation"></div>
              </div>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" name="nip" id="subject" placeholder="NIP: (XXXX)" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" required/>
              <div class="validation"></div>
            </div>

            <div class="text-center"><button type="submit">Transferir</button></div>
          </form>
        </div>

      </div>
</section>
  </main>

  <!--==========================
    Footer
  ============================-->
  <footer id="footer">
    <div class="container">
      <div class="copyright">
       Página desarrollada para la asignatura de Ing. web
      </div>
      <div class="credits">
          &copy; Banca <strong>Web</strong>
      </div>
    </div>
  </footer><!-- #footer -->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/superfish/hoverIntent.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="lib/magnific-popup/magnific-popup.min.js"></script>
  <script src="lib/sticky/sticky.js"></script>

  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

</body>
</html>
