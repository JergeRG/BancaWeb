var o;
var elemento;

$(document).ready(function() {
	elemento = document.getElementById("status").innerHTML;
               $('#tablaMovimientos').DataTable( {
                   language:{
					   emptyTable:     "No hay movimientos en el mes.",
					   infoEmpty:      "",
					   lengthMenu:     "Movimientos _MENU_ mostrados",
                       info:"Mostrando  _PAGE_ de _PAGE_",
                       search: "Buscar:",
                       paginate:{
                           fist: "Primero",
                           last: "Ultimo", 
                           next: "Siguiente", 
                           previous: "Anterior"
                       }    
                   },  
                     ajax: "../Movimientos"+ elemento +".json",
                     columns: [
                         { "data": "fecha" },
                         { "data": "descripcion" },        
                         { "data": "monto" }
                       ]
                } );
            } );