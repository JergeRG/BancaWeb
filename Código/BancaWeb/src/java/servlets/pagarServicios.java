/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocios.negocios;
import persistencia.insertaMovimiento;

/**
 *
 * @author Jorge
 */
public class pagarServicios extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        String servicio = request.getParameter("servicio");
        String des;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        String monto = request.getParameter("monto");
        String usuario = (String) sesion.getAttribute("usuario");
        String cuenta = (String) sesion.getAttribute("cuenta");
        String url =  request.getServletContext().getRealPath("/")+"\\"+usuario+"cuentas.json";
        if(negocios.verificarNumero(monto)){
            float monto1 = Float.parseFloat(monto);
            if(monto1<=0){
                System.out.println("Ingrese un monto valido");
                response.sendRedirect("inicio/serviciosm.jsp");
            }
            else{
                float saldo = negocios.obtieneSaldo(Integer.valueOf(cuenta), url);
                if(saldo<monto1){
                    System.out.println("No cuentas con saldo suficiente");
                    response.sendRedirect("inicio/servicioss.jsp");
                }
                else{
                    des = "Pago de " + servicio;
                    insertaMovimiento insert = new insertaMovimiento() {};
                    insert.insertTopic(Integer.parseInt(cuenta), date, des, monto1);
                    float nuevosaldo = saldo-monto1;
                    negocios.actualizaSaldo(Integer.parseInt(cuenta), nuevosaldo);
                    negocios.convertirJSONCuenta(usuario,url);
                    System.out.println(dateFormat.format(date));
                    url =  request.getServletContext().getRealPath("/")+"\\usuarios.json";
                    String asunto = "Pago de servicio";
                    String mensaje = " A fecha de hoy: "+ dateFormat.format(date) +" se realizo un pago de " + servicio + " por la "
                            + "cantidad de $" + monto1;
                    String email = negocios.buscaEmail1(usuario, url);
                    negocios.SendMail(asunto, mensaje, email);
                    response.sendRedirect("inicio/serviciose.jsp");
            }
            }
        }
        else{
            System.out.println("Ingrese un numero");
            response.sendRedirect("inicio/serviciosn.jsp");
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
