/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import servlets.procesarLogin;


/**
 *
 * @author Jorge
 */
public class negocios {
    public static String Username = "bancawebupiita@gmail.com";
    public static String  PassWord = "pass 123$%";
    public static boolean validarDatos(String username, String password, String url) {
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getUsername().equals(username)  && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public static boolean buscaUsuario(String username, String url) {
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(username.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    public static boolean buscaEmail(String email, String url) {
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getEmail().toUpperCase().equals(email.toUpperCase())){
                return true;
            }
        }
        return false;
    }
    public static String buscaEmail1(String usuario, String url) {
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(usuario.toUpperCase())){
                return user.getEmail();
            }
        }
        return null;
    }
     public static String buscaPassword(String usuario, String url) {
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(usuario.toUpperCase())){
                return user.getPassword();
            }
        }
        return null;
    }
    public static boolean buscaRegistro(int numerodecuenta){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       RegistroJpaController registroX = new RegistroJpaController(emf); 
       return registroX.findByNumerodecuenta(numerodecuenta);
    }
    public static boolean buscaRegistroNip(int numerodecuenta, String nip){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       RegistroJpaController registroX = new RegistroJpaController(emf); 
       return registroX.findByNipNumerodecuenta(numerodecuenta, nip);
    }
    public static boolean verificaCuentaNip(int numerodecuenta, String nip){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       CuentaJpaController cuentaX = new CuentaJpaController(emf); 
       return cuentaX.findByNipNumerodecuenta(numerodecuenta, nip);
    }
    
    public static boolean verificaCuenta(int numerodecuenta){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       CuentaJpaController cuentaX = new CuentaJpaController(emf); 
       return cuentaX.findByNumerodecuenta(numerodecuenta);
    }
    public static String encuentraCuentaUsuario(int numerodecuenta){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       CuentaJpaController cuentaX = new CuentaJpaController(emf); 
       Cuenta c =  cuentaX.findByNumerodecuenta1(numerodecuenta);
       return c.getUsername();
    }
     public static float encuentraCuentaSaldo(int numerodecuenta){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       CuentaJpaController cuentaX = new CuentaJpaController(emf); 
       Cuenta c =  cuentaX.findByNumerodecuenta1(numerodecuenta);
       return c.getSaldo();
    }
    
    public static boolean verificaSesion(String username, String url){
        List<Usuario> users = listaUsuarios(url);
        Usuario userX = null;
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(username.toUpperCase())){
                userX = user;
                break;
            }
        }
        return userX.getSesion();
    }
    

    public static boolean verificaBloqueo(String username, String url){
        List<Usuario> users = listaUsuarios(url);
        Usuario userX = null;
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(username.toUpperCase())){
                userX = user;
                break;
            }
        }
        return userX.getBlock();
    }
    
    public static Usuario compruebaUsuario(String username, String password){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        UsuarioJpaController usuarioX = new UsuarioJpaController(emf);
        Usuario user;
        user = usuarioX.findByPasswordUsername(username, password);
        if(user !=null){
            System.out.println("Nombre: " + user.getNombre());
            System.out.println("E-mail: " + user.getEmail());
        }
        else
            System.out.println("Contraseña incorrectos");
        return user;
    }
    public static void convertirJSON(String ruta) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        UsuarioJpaController usuarioX = new UsuarioJpaController(emf);
        List<Usuario> ejemplo = usuarioX.findAll();
        JSONObject objeto = new JSONObject();
        JSONObject iterador;
        JSONArray a = new JSONArray();
        System.out.println("Result:" + ejemplo);
        for(Usuario opcion : ejemplo){
            iterador = new JSONObject();
            iterador.put("username", opcion.getUsername());
            iterador.put("password", opcion.getPassword());
            iterador.put("nombre", opcion.getNombre());
            iterador.put("apellidos", opcion.getApellidos());
            iterador.put("telefono", opcion.getTelefono());
            iterador.put("cuentas", opcion.getCuentas());
            iterador.put("email", opcion.getEmail());
            iterador.put("direccion", opcion.getDireccion());
            iterador.put("sesion", opcion.getSesion());
            iterador.put("block", opcion.getBlock());
            a.add(iterador); 
        }
        objeto.put("data", a);
        System.out.println(objeto);
        try (FileWriter archivo = new FileWriter(ruta)) {
            archivo.write(objeto.toJSONString());
            archivo.flush();
            archivo.close();
        }
    }
    public static void convertirJSONCuenta(String usuario, String ruta) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        CuentaJpaController cuentaX = new CuentaJpaController(emf);
        List<Cuenta> result = cuentaX.findByUsername(usuario);
        JSONObject objeto = new JSONObject();
        JSONObject iterador;
        JSONArray a = new JSONArray();
        for(Cuenta opcion : result){
            iterador = new JSONObject();
            iterador.put("numerodecuenta", opcion.getNumerodecuenta());
            iterador.put("username", opcion.getUsername());
            iterador.put("nip", opcion.getNip());
            iterador.put("saldo", opcion.getSaldo());
            a.add(iterador);
        }
        objeto.put("data", a);
        System.out.println(objeto);
        try (FileWriter archivo = new FileWriter(ruta)) {
            archivo.write(objeto.toJSONString());
            archivo.flush();
            archivo.close();
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void convertirJSONMovimientos(int cuenta, int mes, String ruta) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        MovimientosJpaController movimientosX = new MovimientosJpaController(emf);
        List<Movimientos> lista = movimientosX.findByNumerodecuenta(cuenta);
        List<Movimientos> result = seleccionarMes(mes, lista);
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        JSONObject objeto = new JSONObject();
        JSONObject iterador;
        JSONArray a = new JSONArray();
        for(Movimientos opcion : result){
            iterador = new JSONObject();
            iterador.put("fecha", dateFormat.format(opcion.getFecha()));
            iterador.put("descripcion", opcion.getDescripcion());
            iterador.put("monto", opcion.getMonto());
            a.add(iterador);
        }
        objeto.put("data", a);
        System.out.println(objeto);
        try (FileWriter archivo = new FileWriter(ruta)) {
            archivo.write(objeto.toJSONString());
            archivo.flush();
            archivo.close();
        } catch(IOException e){
            System.out.println(e);
        }
    }
    
    private static List<Movimientos> seleccionarMes(int mes, List<Movimientos> lista){
        int comp;
        List<Movimientos> result = new ArrayList<Movimientos>();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date1;
        for(Movimientos movimiento :  lista){
            date1 = movimiento.getFecha();
            comp = Integer.parseInt(dateFormat.format(date1));
            if(mes == comp)
                result.add(movimiento);
        }
        return result;
    }
    
   public static void actualizaSesion(String usuario, boolean val){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        UsuarioJpaController usuarioX = new UsuarioJpaController(emf);
        usuarioX.updateSesion(usuario, val);
   }
   public static void actualizaSaldo(int cuenta, float saldo){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       CuentaJpaController cuentaX = new CuentaJpaController(emf);
       RegistroJpaController registroX = new RegistroJpaController(emf);
       cuentaX.updateSaldo(cuenta, saldo);
      registroX.updateSaldo(cuenta, saldo);
   }
    public static void bloqueaUsuario(String usuario){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       UsuarioJpaController usuarioX = new UsuarioJpaController(emf);
       usuarioX.bloquearUsuario(usuario);
   }
    
    public static void agregarCuenta(String usuario, int numerodecuenta, String url){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
       UsuarioJpaController usuarioX = new UsuarioJpaController(emf);
       RegistroJpaController registroX = new RegistroJpaController(emf);
       int cuentas = 0;
        List<Usuario> users = listaUsuarios(url);
        for (Usuario user : users) {
            if(user.getUsername().toUpperCase().equals(usuario.toUpperCase())){
                cuentas = user.getCuentas();
                break;
            }
        }
        usuarioX.updateCuentas(usuario, cuentas+1);
        registroX.updateUso(numerodecuenta);
    }
    
    public static float saldoRegistro(int numerodecuenta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginPU");
        RegistroJpaController registroX = new RegistroJpaController(emf);
        Registro reg = registroX.findByNumerodecuenta1(numerodecuenta);
        return reg.getSaldo();
    }
   
   public static List<Usuario> listaUsuarios(String archivo) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try{
            FileReader fr = new FileReader(archivo);
            object = parser.parse(fr);
        } catch(ParseException ex) {
                    Logger.getLogger(procesarLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
        }
        JSONObject jsonObject = (JSONObject)object;
        JSONArray array = (JSONArray) jsonObject.get("data");
        List<Usuario> users = new ArrayList<Usuario>();
         for(Object usua :  array){
            JSONObject obj = (JSONObject)usua;
            String usuario = (String)obj.get("username");
            String password = (String)obj.get("password");
            String nombre = (String)obj.get("nombre");
            String apellidos = (String) obj.get("apellidos");
            String telefono = (String) obj.get("telefono");
            String email = (String) obj.get("email");
            String direccion = (String) obj.get("direccion");
            String sesion = String.valueOf(obj.get("sesion"));
            String cuentas = String.valueOf(obj.get("cuentas"));
            String block = String.valueOf(obj.get("block"));
            Usuario aux = new Usuario(usuario, password , nombre, apellidos, telefono, Integer.valueOf(cuentas), email, direccion, Boolean.valueOf(sesion), Boolean.valueOf(block));
            users.add(aux);
         }
        return users;
    }
   
   public static List<Cuenta> listaCuentas(String archivo) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try{
            FileReader fr = new FileReader(archivo);
            object = parser.parse(fr);
        } catch(ParseException ex) {
                    Logger.getLogger(procesarLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
        }
        JSONObject jsonObject = (JSONObject)object;
        JSONArray array = (JSONArray) jsonObject.get("data");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
         for(Object usua :  array){
            JSONObject obj = (JSONObject)usua;
            String numerodecuenta = String.valueOf(obj.get("numerodecuenta"));
            String username = (String)obj.get("username");
            String nip = (String)obj.get("nip");
            String saldo = String.valueOf(obj.get("saldo"));
            Cuenta aux = new Cuenta(Integer.valueOf(numerodecuenta), username, nip, Float.valueOf(saldo));
            cuentas.add(aux);
         }
        return cuentas;
    }
    
    public static float obtieneSaldo(int cuenta,String archivo) {
        List<Cuenta> cuentas = listaCuentas(archivo);
        Cuenta cuentaX = null;
        for (Cuenta cuen : cuentas) {
            if(cuen.getNumerodecuenta().equals(cuenta)){
                cuentaX = cuen;
                break;
            }
        }
        return cuentaX.getSaldo();
    }
       public static boolean  verificarNumero(String cadena){
        try {
		Float.parseFloat(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
       public static void SendMail(String Subject, String Mensage, String To) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);
 
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
