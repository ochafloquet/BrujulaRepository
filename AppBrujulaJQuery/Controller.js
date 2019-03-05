var transaccion = [];
$(document).ready(function() {
    $( "#result" ).hide();
    $.getJSON( "/paises", { paises: "SudAmerica" },function( data ) {
        var options_html = ['<option value="">Seleccione</option>'];
        $.each(data, function (index, pais) {
            options_html.push("<option value='" + pais.code + "'>" + pais.name + "</option>");
        });
        $('select#paises').html(options_html.join('')).prop('disabled', false);

    });

    $("#btn_remove").click(function() {
        var code  = $("#paises option:selected").val();
         var name = $("#paises option:selected").text();
         console.log(code+'-'+name);
        if(name!=""){
            $("#mi-modal").modal('show');

            $("#modal-btn-si").on("click", function(){
                $("#paises option:selected").remove();
                transaccion = [];
                transaccion.push ('remove',code,name);
                $("#mi-modal").modal('hide');
            });

            $("#modal-btn-no").on("click", function(){

                $("#mi-modal").modal('hide');
            });
        }
    });

    $("#btn_add").click(function() {

        var code = $('#txt_code').val();
        var name = $('#txt_name').val();

        if(txt_code!=""&&txt_name!=""){
            $("#paises").append("<option value='" + code + "'>" + name + "</option>");
            $('#myModal').modal('toggle');
            transaccion = [];
            transaccion.push ('add', code, name);

        }else{
            alert("Ingresar datos solicitados");
            return;
        }
    });



    $("#btn_back").click(function() {

        for(var i=0; i<transaccion.length; i++){

            if(transaccion[i] == "add"){
                console.log("value1"+transaccion[1]);
                var code= transaccion[1];
                $("#paises option[value= '" + code + "']").remove();
                $( "#result" ).show();
                $("#result").html("Se elimino el pais agregado");
                setTimeout(function() {
                    $( "#result" ).hide();
                }, 2000);
            }
            if(transaccion[i] == "remove"){
                var code= transaccion[1];
                var name= transaccion[2];
                $("#paises").append("<option value='" + code + "'>" + name + "</option>");
                $( "#result" ).show();
                $("#result").html("Se agrego el pais eliminado");
                setTimeout(function() {
                    $( "#result" ).hide();
                }, 2000);
            }



        }

        //


    });






});