function paginaPrincipalController(
		$scope, 
		$http, 
		$routeParams,
		$location,
		$rootScope,
		$filter,
		$controller,
		$localStorage, 
		APIservice,
		//alertService,
		$window
) {
	$controller('funcGenerales', {
		$scope : $scope
	});
	

//	$scope.logout = function() {
//
//		$timeout(function() {
//			$location.path("/login");
//
//		}, 2000);
//	}

// $scope.urlLogout = $scope.urlRest + "/rest/session/logout";
//
//	$scope.logout = function() {
//		$http({
//			method : 'GET',
//			url : $scope.urlLogout,
//			headers : {
//				'Authorization' : ""
//			}
//		})
//				.success(function() {
//					//$localStorage.permisosL = "";
//					$location.path("/login")
//				})
//				.error(
//						function(data, status, headers, config) {
//							console
//									.info("Error intentando llamar al servicio de desloguear:"
//											+ status);
//						});
//	};
//
//	$scope.logout();
}