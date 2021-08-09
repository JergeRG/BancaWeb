<%-- 
    Document   : serviciosm
    Created on : 03-dic-2018, 2:14:03
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
  <script>alert("Ingrese un monto valido.");</script>

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
              <li><a href="">Pago de servicios</a></li>
              <li><a href="transferencias.jsp">Transferencias</a></li>
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
      
    <!--==========================
      About Section
    ============================-->
    <section id="team" class="wow fadeInUp">
      <div class="container">
        <div class="section-header">
          <h2>Servicios a pagar</h2>
          <p>La plataforma la facilidad de pagar los siguientes servicios indicando el monto del pago: </p>
        </div>
        <div class="row">
          <div class="col-lg-3 col-md-6">
            <div class="member">
              <div class="pic"><img src="img/telefono.jpg" alt=""></div>
              <div class="details">
                <h4><form action="../pagarServicios">Telefono<input type="hidden" name="servicio" value="Telefono" />
                          <br/><br/><input type="text" name="monto" class="form-control" id="monto" placeholder="Monto" data-rule="minlen:4" data-msg="Ingresa el monto" required="Ingrese el monto"/>
                      <br/><button type="submit">Pagar</button>
                      </form></h4>
                <span></span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6">
            <div class="member">
              <div class="pic"><img src="img/agua.jpg" alt=""></div>
              <div class="details">
                <h4><form action="../pagarServicios?servicio=Agua">Agua<input type="hidden" name="servicio" value="Agua" />
                          <br/><br/><input type="text" name="monto" class="form-control" id="monto" placeholder="Monto" data-rule="minlen:4" data-msg="Ingresa el monto" required="Ingrese el monto"/>
                      <br/><button type="submit">Pagar</button>
                      </form></h4>
                <span></span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6">
            <div class="member">
              <div class="pic"><img src="img/predial.png" alt=""></div>
              <div class="details">
                  <h4><form action="../pagarServicios?servicio=Predial">Predial<input type="hidden" name="servicio" value="Predial" />
                          <br/><br/><input type="text" name="monto" class="form-control" id="monto" placeholder="Monto" data-rule="minlen:4" data-msg="Ingresa el monto" required="Ingrese el monto"/>
                      <br/><button type="submit">Pagar</button>
                      </form></h4>
                <span></span>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-md-6">
            <div class="member">
              <div class="pic"><img src="img/luz.jpg" alt=""></div>
              <div class="details">
                  <h4><form action="../pagarServicios?servicio=Luz">Luz<input type="hidden" name="servicio" value="Luz" />
                          <br/><br/><input type="text" name="monto" class="form-control" id="monto" placeholder="Monto" data-rule="minlen:4" data-msg="Ingresa el monto" required="Ingrese el monto"/>
                      <br/><button type="submit">Pagar</button>
                      </form></h4>
                <span></span>
              </div>
            </div>
          </div>
        </div>

      </div>
    </section><!-- #team -->
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
