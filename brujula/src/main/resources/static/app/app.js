'use strict';
angular
		.module(
				'app',
						[ 	'ngRoute', 
 							'ngTable',
							'ngResource',
							'ngStorage',
							'blockUI',
							'app.services',
							'app.directives',
							'app.util.alerts',
							'ngTouch'

						])

		
		// definicion de Opciones de Menu
		
		
		.config(function($routeProvider, $locationProvider) {
		  $routeProvider
		   
		  
		  .when('/login', {
				templateUrl : 'app/views/login.html',
				controller : LoginController
		  })
		  .when('/logout', {
				templateUrl : 'app/views/nologin.html',
				controller : LogoutController
		  })
		  .when(
				'/panel',{
						templateUrl : 'app/views/paginaPrincipal.html',
						controller : paginaPrincipalController
		  })
		  .when('/tipo02',{
						templateUrl : 'app/views/tipo02/masterTabTipo02.html',
						controller : MasterTipo02Controller
		  })
		  
		  .otherwise({
				redirectTo : '/login'
		  });
		  // configure html5 to get links working on jsfiddle
		  //$locationProvider.html5Mode(true);
		})
		

		

		.run(function($rootScope) {

		})
		// funciones generales
		.controller(
				'funcGenerales',
				function(
						$log, 
						$location, 
						$scope,
						$rootScope,
						$localStorage, 
						$http, 
						alertService
						) {

					$scope.urlBaseLocation = "http://" + $location.host() + ":"
							+ $location.port();
					$scope.urlBase = String($location.absUrl()).replace('!#',
							'').replace(String($location.path()), "");
					console.log("$scope.urlBase:" + $scope.urlBase);
					$scope.urlName = $scope.urlBase.replace(
							$scope.urlBaseLocation, '');
					$scope.urlRest = $scope.urlBaseLocation;
							//+ "/APPSunat/rest";
					$scope.urlReporte = $scope.urlBaseLocation
							+ "/api";

					$scope.nombreUsuarioAplicacion = $localStorage.NOMBRECOMPELTO;
					$scope.perfilusuarioAplicacion = $localStorage.PERFIL;

					$scope.permisos = $localStorage.permisosL;

					console.log("$scope.permisos:" + $scope.permisos);

					$scope.logout = function() {
						// Limpiando variables locales
						$localStorage.NOMBRE_USUARIO = "";
						$localStorage.Nombre_Usuario = "";						

						console.log("Cerrando session");

						$location.path("/login");
						alertService.showSuccess('Sesión terminada.');
					}

					$scope.validarLogin = function() {
						console.log("Validando session ");
						var usuarioName = $localStorage.Nombre_Usuario;
						if ((String(usuarioName).localeCompare("") == 0)
								|| (String(usuarioName).localeCompare(
										"undefined") == 0)
								|| (String(usuarioName).localeCompare("null") == 0)) {
							$location.path("/login");
						}
					};

					var urlSession = $scope.urlRest
							+ "/rest/session/revisarSession";
					$scope.leerSession = function() {

						$http(
								{
									method : 'POST',
									url : urlSession,
									data : $.param({}),
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
									},
								}).success(function(data) {
							if (data) {
								console.log("Tiene sesion  abierta");
								$scope.validarConexiones();
							} else {

								console.log("No hay datos del usuario");
								$location.path("/nologin");
							}
						}).error(function(status) {
							$scope.status = status.mensaje;
						});
					};

					

				})
