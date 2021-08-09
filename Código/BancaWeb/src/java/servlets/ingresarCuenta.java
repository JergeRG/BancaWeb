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
import javax.servlet.http.HttpSession;
import persistencia.insertarCuenta;

/**
 *
 * @author Jorge
 */
public class ingresarCuenta extends HttpServlet {

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
        String  usuario, nip;
        int numerodecuenta;
        String url = request.getServletContext().getRealPath("/")+"\\usuarios.json";
        HttpSession session = request.getSession();
        usuario = (String) session.getAttribute("usuario");
        nip = (String) request.getParameter("NIP");
        numerodecuenta = Integer.parseInt(request.getParameter("numerodecuenta"));
        if(!negocios.buscaRegistro(numerodecuenta)){
            System.out.println("No existe la cuenta bancaria en el sistema");
            response.sendRedirect("registro\\registrocuentan.jsp");
        }
        else{
            if(negocios.verificaCuenta(numerodecuenta)){
                System.out.println("La cuenta de banco ya esta ligada a otro usuario");
                response.sendRedirect("registro\\registrocuentad.jsp");
            }
            else{
                if(!negocios.buscaRegistroNip(numerodecuenta, nip)){
                     System.out.println("NIP erróneo");
                     response.sendRedirect("registro\\registrocuentae.jsp");
                }
                else{
                    insertarCuenta insert = new insertarCuenta() {};
                    float saldo =  negocios.saldoRegistro(numerodecuenta);
                    insert.insertTopic(numerodecuenta, usuario, nip, saldo);
                    negocios.agregarCuenta(usuario, numerodecuenta, url);
                    String url1 =  request.getServletContext().getRealPath("/")+"\\" + usuario + "cuentas.json";
                    negocios.convertirJSONCuenta(usuario,url1);
                    String asunto = "Alta de cuenta bancaria";
                    String mensaje = "Se ha ligado a su cuenta en el sistema la siguiente cuenta bancaria:\n\n"
                            + "Cuenta: " + numerodecuenta + " \n\nA partir de ahora podrá realizar operaciones con ella desde nuestro portal.";
                    String email = negocios.buscaEmail1(usuario, url);
                    negocios.SendMail(asunto, mensaje, email);
                    response.sendRedirect("inicio\\Cuentas.jsp");
                }
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
