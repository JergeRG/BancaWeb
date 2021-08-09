window.onload = iniciar();

function iniciar() {
    document.getElementById("enviar").addEventListener('click', validar(), false);
}

function validaNombre() {
    var elemento = document.getElementById("nombre");
    if (!elemento.checkValidity()) {
        if (elemento.validity.valueMissing) {
            error2(elemento, "Debe introducir un nombre")
        }
        if (elemento.validity.patternMismatch) {
            error2(elemento, "El nombre debe tener entre 2 y 15 caracteres");
        }
        //error(elemento);
        return false;
    }
    return true;
}

function validar(e) {
    if (validaNombre() && confirm("Pulsa aceptar si deseas enviar el formulario")) {
        return true
    } else {
        e.preventDefault();
        return false;
    }
}


function error2(elemento, mensaje) {
    document.getElementById("divnombre").innerHTML +="data-validate=\"Debe ingresar su nombre iniaciando con mayúsculas\"";
    elemento.focus();
}