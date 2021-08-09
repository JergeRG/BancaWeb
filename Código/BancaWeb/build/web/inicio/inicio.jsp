<%-- 
    Document   : inicio
    Created on : 10-oct-2018, 21:25:19
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
  <script type="text/javascript"> history.forward(); 
  </script>
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

</head>

<body id="body">
    
    <%
        HttpSession sesion = request.getSession(true);
        String usuario = (String)sesion.getAttribute("usuario");
        if(usuario==null){%>
            <script>alert("Debe iniciar sesión primero");</script>
            <%response.sendRedirect("..\\index.html");
        }
        String cuenta = (String)sesion.getAttribute("cuenta");
        if(cuenta==null){
            cuenta = request.getParameter("cuenta");
            sesion.setAttribute("cuenta", cuenta);
        }%>
    

  <!--==========================
    Header
  ============================-->
  <header id="header">
    <div class="container">

      <div id="logo" class="pull-left">
          <h1><a class="scrollto">Banca<span>Web-<a href="Cuentas.jsp"> ${cuenta}</a></span></a></h1>
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-has-children"><a href="#body">Inicio</a></li>
          <li><a href="#call-to-action">Perfil</a></li>
          <li class="menu-has-children"><a href="#services">Servicios</a>
            <ul>
              <li><a href="..\consultaSaldo">Consultas</a></li>
              <li><a href="servicios.jsp">Pago de servicios</a></li>
              <li><a href="transferencias.jsp">Transferencias</a></li>
              <li><a href="..\visualizarMovimientos">Movimientos</a></li>
            </ul>
          </li>
          <li><a href="#contact">Contactanos</a></li>
          <li><a href="..\cerrarSesion">Cerrar sesión</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!--==========================
    Intro Section
  ============================-->
 <!-- #intro -->

  <main id="main">

    <!--==========================
      About Section
    ============================-->
    <section id="about" class="wow fadeInUp">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 about-img">
            <img src="img/about-img1.jpg" alt="">
          </div>    
          <div class="col-lg-6 content">
            <h2>Bienvenido ${usuario} a nuestra plataforma en línea</h2>
            <h3>Realiza movimientos y consultas desde la comunidad de tu hogar.</h3>

            <ul>
              <li><i class="fa fa-check-circle"></i> Seguridad en todas sus operaciones.</li>
              <li><i class="fa fa-check-circle"></i> Las operaciones son al instante.</li>
              <li><i class="fa fa-check-circle"></i> No debe preocuparse por cargos extra.</li>
                <li><i class="fa fa-check-circle"></i> Servicio que esta disponible las 24 hrs los 365 día del año.</li>
            </ul>

          </div>
          
        </div>

      </div>
    </section>
    <section id="call-to-action" class="wow fadeInUp">
      <div class="container">
        <div class="row">
          <div class="col-lg-9 text-center text-lg-left">
            <h3 class="cta-title">Perfil</h3>
            <p class="cta-text"> Si necesitas modificar tus datos personales, acude a alguna sucursal.</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
          </div>
        </div>

      </div>
    </section>

    <!--==========================
      Services Section
    ============================-->
    <section id="services">
      <div class="container">
        <div class="section-header">
          <h2>Servicios</h2>
          <p>La aplicación le brinda los siguientes servicios.</p>
        </div>

        <div class="row">

          <div class="col-lg-6">
            <div class="box wow fadeInLeft">
              <div class="icon"><i class="fa fa-university"></i></div>
              <h4 class="title"><a href="..\consultaSaldo">Consultas</a></h4>
              <p class="description">Consulte el saldo de su cuenta hasta el momento.</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box wow fadeInRight">
              <div class="icon"><i class="fa fa-file"></i></div>
              <h4 class="title"><a href="servicios.jsp">Pagos de servicios</a></h4>
              <p class="description">Pague gran variedad de servicios sin hacer filas tediosas.</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box wow fadeInLeft" data-wow-delay="0.2s">
              <div class="icon"><i class="fa fa-location-arrow"></i></div>
              <h4 class="title"><a href="transferencias.jsp">Transferencias</a></h4>
              <p class="description">Realice transferencias a otras cuentas bancarias.</p>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="box wow fadeInRight" data-wow-delay="0.2s">
              <div class="icon"><i class="fa fa-address-card"></i></div>
              <h4 class="title"><a href="..\visualizarMovimientos">Movimientos</a></h4>
              <p class="description">Consulte los movimientos de su cuenta.</p>

            </div>
          </div>

        </div>

      </div>
    </section><!-- #services -->


   


    <section id="contact" class="wow fadeInUp">
      <div class="container">
        <div class="section-header">
          <h2>Contactanos</h2>
          <p>Si tienes alguna duda o sugerencia puedes contactarnos por medio de:</p>
        </div>

        <div class="row contact-info">

          <div class="col-md-4">
            <div class="contact-address">
              <i class="ion-ios-location-outline"></i>
              <h3>Dirección</h3>
              <address>Av Instituto Politécnico Nacional 2580, La Laguna Ticoman, 07340 Ciudad de México, CDMX</address>
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-phone">
              <i class="ion-ios-telephone-outline"></i>
              <h3>Número telefonico</h3>
              <p><a href="tel:+5243472584">+52 5543 4725 54</a></p>
            </div>
          </div>

          <div class="col-md-4">
            <div class="contact-email">
              <i class="ion-ios-email-outline"></i>
              <h3>Correo</h3>
              <p><a href="mailto:jorgerupiita@gmail.com">bancawebupiita@gmail.com</a></p>
            </div>
          </div>

        </div>
      </div>

      <div class="container mb-4">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3760.7038933606555!2d-99.12841428509205!3d19.511371286839562!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x85d1f9c228b343cf%3A0x2f403a140132e3e7!2sUnidad+Profesional+Interdisciplinaria+en+Ingenier%C3%ADa+y+Tecnolog%C3%ADas+Avanzadas+IPN!5e0!3m2!1ses!2smx!4v1541299082533" width="100%" height="380" frameborder="0" style="border:0" allowfullscreen></iframe>
      </div>

    </section><!-- #contact -->

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
