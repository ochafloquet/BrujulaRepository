angular
		.module('app.util.alerts', [])
		.factory(
				'alertService',
				function($rootScope) {
					var alertService = {};

					$rootScope.alerts = [];

					alertService.reset = function() {
						$rootScope.alerts = [];
					};

					alertService.setPermanent = function() {
						if ($rootScope.alerts.length) {
							$rootScope.alerts[0].autoclose = false;
						}
					};

					alertService.show = function(type, message, isAutoclose) {

						alertService.reset();

						$rootScope.alerts.push({
							"type" : type,
							"message" : message,
							"autoclose" : isAutoclose
						})
					};

					alertService.showDanger = function(message) {
						
						alertService.reset();

						if (message == undefined) {
							message = 'Ocurrió un error; vuelve a intentarlo más tarde.';
						}

						$rootScope.alerts.push({
							"type" : 'danger',
							"message" : message,
							"autoclose" : true
						})
					};

					alertService.showInfo = function(message) {

						alertService.reset();

						$rootScope.alerts.push({
							"type" : 'info',
							"message" : message,
							"autoclose" : true
						})
					};

					alertService.showWarning = function(message) {

						alertService.reset();

						$rootScope.alerts.push({
							"type" : 'warning',
							"message" : message,
							"autoclose" : true
						})
					};

					alertService.showSuccess = function(message) {

						alertService.reset();

						if (message == undefined) {
							message = 'Operación exitosa.';
						}

						$rootScope.alerts.push({
							"type" : 'success',
							"message" : message,
							"autoclose" : true
						})
					};

					return alertService;
				});