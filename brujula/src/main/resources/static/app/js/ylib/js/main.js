//File Upload

/*
document.getElementById("datafile").onchange = function () {
    document.getElementById("fileinput-filename").value = this.value;
};
*/

/*
*
Datepicker
*
*/

$(function () {
  $("#datepicker").datepicker({
        autoclose: true,
        todayHighlight: true
  }).datepicker('update', new Date());;
});


/*
*
Alerts
*
*/

$('.missing').click(function() {
    //console.log($(this).parent());
    $(this).parent().fadeOut();
});

/*
*
Tooltip
*
*/
$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

/*
*
Popover
*
*/
$(function () {
  $('[data-toggle="popover"]').popover()
})


/*
*
Progress bar
*
*/
function progress(maximo, valor, element) {
	var conversor = element.width()/maximo;
	//console.log('conversor: '+conversor);
	//var progressBarWidth = valor * element.width()/100;
	var progressBarWidth = (valor/element.width())*conversor*100;

	var elem = element.find('div');
	//console.log(progressBarWidth + '%');
	elem.width(progressBarWidth + '%');
}

$(document).ready(function() {

	valores = [];
	$('.progressBar').each(function () {
	    var valor = parseFloat($(this).data('num'));
	    valores.push(valor);
	});

	// valores.sort(function(a, b) { return a - b });

	var maximo = Math.max.apply(Math, valores); // 3
	//console.log(Math.max.apply(Math, valores)); // 3

	$('.progressBar').each(function() {
		//alert('Hello');
		var bar = $(this);
		var miValor = $(this).data('num');
		//max = max.substring(3);

		progress(maximo, miValor, bar);

	});
});


/*
*
Number animation
*
*/
$(document).ready(function() {
	$('.count').each(function () {
	    $(this).prop('Counter',0).animate({
	        Counter: $(this).text()
	    }, {
	        duration: 2000,
	        easing: 'swing',
	        step: function (now) {
	            $(this).text(Math.ceil(now));
	        }
	    });
	});
});


/*
Funcion para igualar el alto de columnas
*/

(function($, window) {
    var $ls;
    function autoheight() {
        var max = 0;
        $ls.each(function() {
            $t = $(this);
            $t.css('height','');
            max = Math.max(max, $t.height());
        });
        $ls.height(max);
    }
    $(function() {
        $ls = $('.panel-widget'); // the inline-block selector
        autoheight(); // first time
        $ls.on('load', autoheight); // when images in content finish loading
        $(window).load(autoheight); // when all content finishes loading
        $(window).resize(autoheight); // when the window size changes
    });
})(jQuery, window);


/*
Button collapse animation
*/
$(document).ready(function(){
	$('#btn-collapse').click(function() {
	    //$('#menu-collapse').slideToggle();
	    $('#menu-collapse').toggleClass('active');
	});
});