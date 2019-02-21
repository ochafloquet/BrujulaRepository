function MasterTipo02Controller(
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
		$window,
		NgTableParams,
		blockUI,
		ngTableParams,
		$timeout
		) {

	$controller('funcGenerales', {
		$scope : $scope
	});
	
	
	
	$(function() {
		$('#idContenedor').tabs();
		$('.ui-widget-header').css("background-color", "#eeeeee");
		$('.ui-tabs .ui-tabs-nav .ui-tabs-anchor').css("float", "none");
		$('.ui-widget-header').css("border", "0");
	});

	$('#lista').click(function(e) {
		var id = e.target.id;
		console.log("id tab >> " + id);
		switch (id) {
		case "idSeleccion":
			$scope.cargalistadepartamento();
			break;
		case "idEvaluacion":
			$scope.cargalistapersona();
			
			break;
		case "idResultado":
			$scope.cargalistapersonaProfesor();
			break;
		
		}

	});
	
console.log($scope.urlRest);
	//urls
var addDepartment	=$scope.urlRest + "/department/addDepartment";
var departmentall	=$scope.urlRest + "/department/all";
var addperson		=$scope.urlRest + "/persona/addperson";
var fullpersonaName =$scope.urlRest + "/persona/completeName";
var addprofesor    	=$scope.urlRest + "/professor/add";
var fullPerona 		=$scope.urlRest + "/persona/all";
	
	//Declaramos los objetos
	//Departamento
	$scope.departamento={};
	$scope.listadepartamento = [];
	
	$scope.nuevo = function() {		
		$scope.departamento.nombre="";
		$('#modalDepartamento').modal();
		$('#divAlert').hide();
	}

	$scope.save = function() {	
		
		console.log("+++++++++++++++++***********"+$scope.departamento.nombre.length);
		
		if($scope.departamento.nombre.length<=20){
			var body = {
					"departmentName" : $scope.departamento.nombre
				}
				console.log(JSON.stringify(body));
				$http({
					method : 'POST',
					url : addDepartment,
					data : JSON.stringify(body),
					headers : {
						'Content-Type' : 'application/json'
					}
				}).success(function(data) {
					console.log("LstIndInfra : " + JSON.stringify(data));
					$scope.tblDepartamento.reload();
					 var count = Object.keys(data).length;
					  console.log(count);
					if (count > 0) {
						alertService.showSuccess("Registro exitoso con ID"+data.id_Department);

					} else {
						alertService.showDanger("No se realizó el registro.");
					}
				}).error(function(status) {

				});
				$('#modalDepartamento').modal('hide');
		}else{
			
			$('#divAlert').attr('style','display: block !important');		
			$('#divAlert').show();
			$('#idAlert').html("Excede el tamaño permitido de 20 caracteres");
			 $timeout( function(){				 	
					$('#divAlert').hide();
			        }, 3000 );
			return;
			
			
		}
		
		
	}
	
	$scope.cargalistadepartamento=function(){
		
		$scope.tblDepartamento = new NgTableParams(
				{
					page : 1,
					count : 10,
					filter : {},
					sorting : {

					}
				},
				{
					counts : [ 10, 15, 20, 25 ],
					getData : function($defer, params) {
						
						$http(
								{
									method : 'GET',
									url : departmentall,
									data :"",
									headers : {
										'Content-Type' : 'application/json',
										'Access-Control-Allow-Origin' : '*',
										'Access-Control-Allow-Methods' :'DELETE, POST, GET, OPTIONS',
										'Access-Control-Allow-Headers' : 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
									}
								})
								.success(
										function(response) {
											blockUI.start();
											var filtro = {};
											var NewData = [];											
											var nData = response;
											$scope.listadepartamento= response; 
											console.log(JSON.stringify($scope.listadepartamento));
											params.total(nData.length);
											$scope.orderedData = params
													.sorting() ? $filter(
													'orderBy')(nData,
													params.orderBy()) : nData;
											var filterData = filtro ? $filter(
													'filter')(
													$scope.orderedData, filtro)
													: $scope.orderedData;
											params.total(filterData.length);
											$defer.resolve(filterData.slice(
													(params.page() - 1)
															* params.count(),
													params.page()
															* params.count()));

											blockUI.stop();
										}).error(function(status) {
									$scope.status = status;
								});
					}
				});
		
	}
	
	//PERSONA
	$scope.persona={};
	
	
	$scope.nuevoPersona = function() {		
		$scope.persona={};
		$('#modalPersona').modal();
		
	}
	
	$scope.savePersona = function() {	
		var yearOfBirth = parseInt($scope.persona.anioNac);
		var body = {
				 "name":$scope.persona.name,
		         "surname":$scope.persona.surname,
		         "yearOfBirth":yearOfBirth
			}
			console.log(JSON.stringify(body));
			$http({
				method : 'POST',
				url : addperson,
				data : JSON.stringify(body),
				headers : {
					'Content-Type' : 'application/json'
				}
			}).success(function(data) {
				console.log("LstIndInfra : " + JSON.stringify(data));
				$scope.tblPersona.reload();
				 var count = Object.keys(data).length;
				  console.log(count);
				if (count > 0) {
					alertService.showSuccess("Registro exitoso con ID"+data.id_Person);

				} else {
					alertService.showDanger("No se realizó el registro.");
				}
			}).error(function(status) {
				console.log("status : " + JSON.stringify(status));
				alertService.showDanger("DUPLICIDAD DE NOMBRES "+status.message);
				
			});
			$('#modalPersona').modal('hide');
		
	}
	
$scope.cargalistapersona=function(){
		
		$scope.tblPersona = new NgTableParams(
				{
					page : 1,
					count : 10,
					filter : {},
					sorting : {

					}
				},
				{
					counts : [ 10, 15, 20, 25 ],
					getData : function($defer, params) {
						
						$http(
								{
									method : 'GET',
									url : fullpersonaName,
									data :"",
									headers : {
										'Content-Type' : 'application/json',
										'Access-Control-Allow-Origin' : '*',
										'Access-Control-Allow-Methods' :'DELETE, POST, GET, OPTIONS',
										'Access-Control-Allow-Headers' : 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
									}
								})
								.success(
										function(response) {
											blockUI.start();
											var filtro = {};
											var NewData = [];											
											var nData = response;
											params.total(nData.length);
											$scope.orderedData = params
													.sorting() ? $filter(
													'orderBy')(nData,
													params.orderBy()) : nData;
											var filterData = filtro ? $filter(
													'filter')(
													$scope.orderedData, filtro)
													: $scope.orderedData;
											params.total(filterData.length);
											$defer.resolve(filterData.slice(
													(params.page() - 1)
															* params.count(),
													params.page()
															* params.count()));

											blockUI.stop();
										}).error(function(status) {
									$scope.status = status;
								});
					}
				});
		
	}
	
	//Profesor
$scope.professor={};
$scope.editarProfesor = function(item) {		
	$scope.professor={};
	$scope.professor.id_Person=item.id_Person;
	$('#modalProfesor').modal();
	
}

$scope.saveProfesor = function() {
	
	console.log(JSON.stringify($scope.professor));
	
	
	
	var body = {
			"id_Person":$scope.professor.id_Person,
			 "asociateStartDate":$scope.professor.asociateStartDate,
	         "actingEndDate":$scope.professor.actingEndDate,
	         "department":{
	        	    "id_Department": $scope.professor.dpto_selected.id_Department
	        	}
		}
		console.log(JSON.stringify(body));
		$http({
			method : 'POST',
			url : addprofesor,
			data : JSON.stringify(body),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			console.log("LstIndInfra : " + JSON.stringify(data));
			$scope.tblPersonaProfesor.reload();
			 var count = Object.keys(data).length;
			  console.log(count);
			if (count > 0) {
				alertService.showSuccess("Registro exitoso con ID"+data.id_Person);
				
			} else {
				alertService.showDanger("Fecha actingEndDate debe ser mayor a la actual...!!!");
			}
		}).error(function(status) {
			console.log("status : " + JSON.stringify(status));
			alertService.showDanger("Fecha actingEndDate debe ser mayor a la actual...!!!");
			
		});
		$('#modalProfesor').modal('hide');
	
}
$scope.cargalistapersonaProfesor=function(){
	blockUI.start();
	$scope.tblPersonaProfesor = new NgTableParams(
			
			{
				page : 1,
				count : 10,
				filter : {},
				sorting : {

				}
			},
			{
				counts : [ 10, 15, 20, 25 ],
				getData : function($defer, params) {
					
					$http(
							{
								method : 'GET',
								url : fullPerona,
								data :"",
								headers : {
									'Content-Type' : 'application/json',
									'Access-Control-Allow-Origin' : '*',
									'Access-Control-Allow-Methods' :'DELETE, POST, GET, OPTIONS',
									'Access-Control-Allow-Headers' : 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
								}
							})
							.success(
									function(response) {
										
										var filtro = {};
										var NewData = [];											
										var nData = response;
										params.total(nData.length);
										$scope.orderedData = params
												.sorting() ? $filter(
												'orderBy')(nData,
												params.orderBy()) : nData;
										var filterData = filtro ? $filter(
												'filter')(
												$scope.orderedData, filtro)
												: $scope.orderedData;
										params.total(filterData.length);
										$defer.resolve(filterData.slice(
												(params.page() - 1)
														* params.count(),
												params.page()
														* params.count()));

										
									}).error(function(status) {
								$scope.status = status;
							});
				}
			});
	blockUI.stop();
}
	/////fin
	$scope.cargalistapersona();
	$scope.cargalistadepartamento();
	$scope.cargalistapersonaProfesor();
}