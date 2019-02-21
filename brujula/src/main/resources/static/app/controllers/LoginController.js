function LoginController(
		$scope, 
		$http, 
		$routeParams,
		$location,
		$rootScope,
		$filter,
		$controller,
		$localStorage, 
		APIservice,
		alertService,
		$window
		) {

	$controller('funcGenerales', {
		$scope : $scope
	});
	//Declaramos el Objeto
	$scope.password = "";	
	$scope.username_cc = "";
	$scope.password_actual = "";
	$scope.password_nuevo = "";
	$scope.repassword_nuevo = "";
	
	//Declaramos Urls
	
	
	var urlLogin = $scope.urlRest + "/login/validarUsuario";
	
	$scope.summit = function() {

		if ((String($scope.username).localeCompare("") == 0) || (String($scope.username).localeCompare("undefined") == 0)
				|| (String($scope.username).localeCompare("null") == 0)

		) {
			// bootbox.alert("Ingrese usuario");
			return;
		}

		if ((String($scope.password).localeCompare("") == 0) || (String($scope.password).localeCompare("null") == 0)
				|| (String($scope.password).localeCompare("undefined") == 0)) {
			// bootbox.alert("Ingrese contrase&nacute;a");
			return;
		}

		$scope.validarlogin();
	};

	$scope.ngEnter = function(keyEvent) {
		if (keyEvent.which == 13)
			$scope.summit();
	};

	$scope.validarlogin = function() {
		$location.path("/panel");
		
		/*APIservice.login(urlLogin, $scope.username, $scope.password).then(function(data) {
			console.log("pasamos el servicio-*****+++++");
			if (data) {
				
				$localStorage.NOMBRECOMPELTO = data.nom_completo;
				$location.path("/panel");
				
				
			} else {
//				alertService.showWarning(data.mensajeUsuario);
				alertService.showWarning('Contraseña incorrecta. Vuelve a intentarlo por favor.');
				alertService.setPermanent();
			}
		}, function error() {
//			alertService.showDanger(data.mensajeUsuario);
			alertService.showDanger('Ocurrió un error; vuelve a intentarlo más tarde.');
		});*/
	}
	
	$scope.abrirCambioClave = function() {
		$('#modalFormCambioClave').modal();
	}
	
//	
//

//
	
//	
//	$scope.abrirCambioClave = function() {
//		$('#modalFormCambioClave').modal();
//	}
//	
//	$scope.cambiarClave = function() {
//		if($scope.username_cc.trim() == ""){
//			$window.alert("Ingrese el usuario");
//		}
//		else if($scope.password_actual.trim() == ""){
//			$window.alert("Ingrese la contraseña actual");
//		}
//		else if($scope.password_nuevo.trim() == ""){
//			$window.alert("Ingrese la contraseña nueva");
//		}
//		else if($scope.repassword_nuevo.trim() == ""){
//			$window.alert("Reingrese la contraseña nueva");
//		}
//		else if($scope.password_nuevo != $scope.repassword_nuevo){
//			$window.alert("Contraseñas deben ser iguales");
//		}
//		else{
//			$http(
//				{
//					method : 'POST',
//					url : urlCambioClave,
//					data : $.param({
//						"username" : $scope.username_cc,
//						"password" : $scope.password_actual,
//						"repassword" : $scope.password_nuevo
//					}),
//					headers : {
//						'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
//					},
//				}
//			)
//			.success(
//				function(data) {
//					console.log(JSON.stringify(data));
//					if(data.exito == true || data.exito == "true"){
//						$("#modalFormCambioClave").modal('hide');
//						alertService.showSuccess(data.mensajeUsuario);
//					}
//					else{
//						$("#modalFormCambioClave").modal('hide');
//						alertService.showDanger(data.mensajeUsuario);
//					}
//					$scope.username_cc = "";
//					$scope.password_actual = "";
//					$scope.password_nuevo = "";
//					$scope.repassword_nuevo = "";
//				}
//				
//			).error(
//				function(status) {
//					$scope.status = status.mensaje;
//				}
//			);
//		}
//	};
//
//	$scope.summit = function() {
//
//		if ((String($scope.username).localeCompare("") == 0) || (String($scope.username).localeCompare("undefined") == 0)
//				|| (String($scope.username).localeCompare("null") == 0)
//
//		) {
//			// bootbox.alert("Ingrese usuario");
//			return;
//		}
//
//		if ((String($scope.password).localeCompare("") == 0) || (String($scope.password).localeCompare("null") == 0)
//				|| (String($scope.password).localeCompare("undefined") == 0)) {
//			// bootbox.alert("Ingrese contrase&nacute;a");
//			return;
//		}
//
//		$scope.validarlogin();
//	};
//
//	$scope.ngEnter = function(keyEvent) {
//		if (keyEvent.which == 13)
//			$scope.summit();
//	};
}