/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocios.negocios;

/**
 *
 * @author Jorge
 */
public class procesarLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usuario, password;
        int intentos;
        usuario = (String) request.getParameter("username");
        password= (String) request.getParameter("password");
        String user = (String) sesion.getAttribute("usuario");
        String url = request.getServletContext().getRealPath("/")+"\\usuarios.json";
        String url1;
        negocios.convertirJSON(url);
        if(negocios.buscaUsuario(usuario,url)) {
            if(user == null){
                intentos = 3;
                sesion.setAttribute("intentos",intentos);
            }
            else if(!user.equals(usuario)){
                intentos = 3;
                sesion.setAttribute("intentos",intentos);
            }
            else
                intentos =   (int) sesion.getAttribute("intentos");
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("password",password);
            
            if(!negocios.verificaBloqueo(usuario, url)){
                if(!negocios.verificaSesion(usuario, url)){
                    if(negocios.validarDatos(usuario, password, url)){
                        negocios.actualizaSesion(usuario, true);
                        url1 =  request.getServletContext().getRealPath("/")+"\\"+usuario+"cuentas.json";
                        negocios.convertirJSONCuenta(usuario,url1);
                        response.sendRedirect("inicio\\Cuentas.jsp");
                    }
                    else{
                        response.sendRedirect("pass.html");
                        System.out.println("Contraseña erronea");
                        intentos--;
                            if(intentos == 0)
                                negocios.bloqueaUsuario(usuario);
                        System.out.println("Intentos:" + intentos);
                        sesion.setAttribute("intentos",intentos);
                    }
                }
                else{
                        response.sendRedirect("sesion.html");
                        System.out.println("Cuenta ya abierta en otro dispositivo");
                    }
            }
            else{
                response.sendRedirect("cuenta.html");
                System.out.println("El usuario se encuentra bloqueado");
            }
        }
        else {
            response.sendRedirect("usuario.html");
            System.out.println("El usuario no existe");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
