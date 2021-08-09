/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import negocios.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.JPAInsert;

/**
 *
 * @author Jorge
 */
public class ingresarUsuario extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String nombre, usuario, password, apellidos, email, telefono, direccion;
        usuario = (String) request.getParameter("username");
        password= (String) request.getParameter("password");
        nombre = (String) request.getParameter("nombre");
        apellidos = (String) request.getParameter("apellidos");
        email = (String) request.getParameter("email");
        telefono = (String) request.getParameter("telefono");
        direccion = (String) request.getParameter("direccion");
        String url = request.getServletContext().getRealPath("/")+"\\usuarios.json";
        if(negocios.buscaUsuario(usuario, url)){
            System.out.println("El usuario ya existe en la base");
            response.sendRedirect("registro\\registrousuario.jsp?nombre="+nombre+"&apellidos="
                    +apellidos+"&email="+email+"&telefono="+telefono+"&direccion="+direccion);
        }
        else{
            if(negocios.buscaEmail(email, url)){
                System.out.println("El correo con el que estas ");
                response.sendRedirect("registro\\registrocorreo.jsp?nombre="+nombre+"&apellidos="
                    +apellidos+"&usuario="+usuario+"&telefono="+telefono+"&direccion="+direccion);
            }
            else{
                JPAInsert insert = new JPAInsert() {};
                insert.insertTopic(usuario, password, nombre, apellidos, telefono,email, direccion);
                String asunto = "Alta en el sistema BancaWeb";
                String mensaje = "Buen día " + nombre + " " + apellidos + ", se le hace llegar este correo con el fin " 
                        + "de informarle que se ha dado de alta en nuestra plataforma con las siguientes credenciales:\n\n"
                        + "Usuario: " + usuario +"\nPassword: " + password +"\n\nSe le informa que tiene un máximo de 3 días "
                        + "para ligar sus cuentas bancarias o su cuenta en el sistema será dada de baja.\nEl equipo de Bancaweb espera y "
                        + "nuestro portal sea de su agrado y le ahorre las tediosas filas en el banco. Sin más por el momento, le deseamos una buen día.\nSaludos.";
                negocios.SendMail(asunto, mensaje, email);
                response.sendRedirect("index.html");
            }
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
