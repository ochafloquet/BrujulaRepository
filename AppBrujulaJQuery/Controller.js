$(document).ready(function() {

    $.getJSON( "/paises", { paises: "SudAmerica" },function( data ) {
        var options_html = ['<option value="">Seleccione</option>'];
        $.each(data, function (index, pais) {
            options_html.push("<option value='" + pais.id + "'>" + pais.name + "</option>");
        });
        $('select#paises').html(options_html.join('')).prop('disabled', false);

    });

    $("#btn_remove").click(function() {
         var optionText = $("#paises option:selected").text();
        if(optionText!=""){
            if (confirm('Estas Seguro de Eliminar...!!')) {
                $("#paises option:selected").remove();
            }

        }
    });

    $("#btn_add").click(function() {

        var code = $('#txt_code').val();
        var name = $('#txt_name').val();

        if(txt_code!=""&&txt_name!=""){
            $("#paises").append("<option value='item" + code + "'>" + name + "</option>");
            $('#myModal').modal('toggle');
        }else{
            alert("Ingresar datos solicitados");
            return;
        }
    });





});