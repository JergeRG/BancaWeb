/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global elemennto */

var o;
var elemento;
    
$(document).ready(function() {
    elemento = document.getElementById("status").innerHTML;
  $.ajax({
     url:'..\\'+ elemento+'cuentas.json',
     success: function(data){
       o = JSON.parse(data); 
       $.each(o.data, function(i, item) {
           $('#miTabla').append('<tr><td>\n\
           <p><a href="inicio.jsp?cuenta='+item.numerodecuenta+'">Cuenta :'+item.numerodecuenta+'</a></p></td></tr>');
       });
     }      
  });
  

});

