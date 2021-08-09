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
public class realizarTransferencia extends HttpServlet {

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
        String usuario = (String)sesion.getAttribute("usuario");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        String url =  request.getServletContext().getRealPath("/")+"\\"+usuario+"cuentas.json";
        String cuenta = (String)sesion.getAttribute("cuenta");
        String cuentaT = request.getParameter("cuenta");
        String cantidad = request.getParameter("cantidad");
        String nip = request.getParameter("nip");
        if(!(cuentaT.equals(cuenta)) && negocios.verificarNumero(cuentaT) && cuentaT.length() >= 6   ){
            if(negocios.verificarNumero(cantidad)){
                float monto1 = Float.parseFloat(cantidad);
                if(monto1<=0){
                    System.out.println("Ingrese un monto valido");
                    response.sendRedirect("inicio/transferenciasv.jsp");
                }
                else{
                    float saldo = negocios.obtieneSaldo(Integer.valueOf(cuenta), url);
                    if(saldo<monto1){
                        System.out.println("No cuentas con saldo suficiente");
                        response.sendRedirect("inicio/transferenciasi.jsp");
                    }
                    else{
                        if(negocios.verificaCuentaNip(Integer.parseInt(cuenta), nip)){
                            String des = "Transferencia a cuenta: " + cuentaT;
                            insertaMovimiento insert = new insertaMovimiento() {};
                            insert.insertTopic(Integer.parseInt(cuenta), date, des, monto1);
                            float nuevosaldo = saldo-monto1;
                            negocios.actualizaSaldo(Integer.parseInt(cuenta), nuevosaldo);
                            negocios.convertirJSONCuenta(usuario,url);
                            System.out.println(dateFormat.format(date));
                            String url1 =  request.getServletContext().getRealPath("/")+"\\usuarios.json";
                            String asunto = "Transferencia";
                            String mensaje = " A fecha de hoy: "+ dateFormat.format(date) +" se realizo una transferencia a la cuenta " + cuentaT + " por la "
                                     + "cantidad de $" + monto1;
                            String email = negocios.buscaEmail1(usuario, url1);
                            negocios.SendMail(asunto, mensaje, email);
                            if(negocios.verificaCuenta(Integer.parseInt(cuentaT))){
                                saldo = negocios.encuentraCuentaSaldo(Integer.parseInt(cuentaT));
                                des = "Deposito de cuenta: " + cuenta;
                                insert.insertTopic(Integer.parseInt(cuentaT), date, des, monto1);
                                nuevosaldo = saldo+monto1;
                                negocios.actualizaSaldo(Integer.parseInt(cuentaT), nuevosaldo);
                                asunto = "Deposito";
                                mensaje = " A fecha de hoy: "+ dateFormat.format(date) +" se realizo un deposito de la cuenta " + cuentaT + " por la "
                                        + "cantidad de $" + monto1;
                                usuario = negocios.encuentraCuentaUsuario(Integer.parseInt(cuentaT));
                                System.out.println(usuario);
                                email = negocios.buscaEmail1(usuario, url1);
                                negocios.SendMail(asunto, mensaje, email);
                            }
                            response.sendRedirect("inicio/transferenciase.jsp");
                        }
                        else{
                            System.out.println("Su nip es erroneo");
                            response.sendRedirect("inicio/transferenciasp.jsp");
                        }
                    }
                }
            }
            else{
                System.out.println("Ingrese una cantidad valida");
                response.sendRedirect("inicio/transferenciasc.jsp");
            }
        }
        else{
            System.out.println("Ingrese un numero de cuenta valido");
            response.sendRedirect("inicio/transferenciasn.jsp");
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
