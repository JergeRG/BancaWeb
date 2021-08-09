
(function ($) {
    "use strict";

    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })

    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
		
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
		else if($(input).attr('name') == 'nombre'){
			if($(input).val().trim().match(/^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'apellidos'){
			if($(input).val().trim().match(/^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'username'){
			if($(input).val().trim().match(/[A-Za-z0-9ü_]{3,10}$/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'password'){
			if($(input).val().trim().match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){8,15}$/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'confirm'){
			var pass1 = $('#pass1').val();
			var pass2 = $('#pass2').val();
			if ( pass1 != pass2 )
				return false;
		}
		else if($(input).attr('name') == 'telefono'){
			if($(input).val().trim().match(/[0-9]{10}/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'direccion'){
			if($(input).val().trim().match(/[A-ZÁÉÍÓÚa-zñáéíóú]+[\s]*/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'cuenta'){
			if($(input).val().trim().match(/[0-9]{7}/) == null) {
                return false;
            }
		}
		else if($(input).attr('name') == 'NIP'){
			if($(input).val().trim().match(/[0-9]{4}/) == null) {
                return false;
            }
		}
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    

})(jQuery);