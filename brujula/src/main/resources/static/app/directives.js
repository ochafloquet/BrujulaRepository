angular.module("app.directives", [])
.directive('notification',function($timeout){
	  return {
		  restrict: 'E',
		  replace: true,
		  scope: {
			  ngModel: '='
		  },
		  template:'<div class="alert alert-{{ngModel.type}}" bs-alert="ngModel" ng-hide="ngModel.isClosed"><button class="close missing" ng-click="ngModel.isClosed=true"><i class="icn icn-remove"></i></button>{{ngModel.message}}</div>',
		  link: function(scope, element, attrs) {
		      $timeout(function(){
		    	  if (scope.ngModel.autoclose){
		    		  element.remove();
		    		  scope.ngModel.isClosed=true;
		    	  }
		      }, 5000);
		  }
	  }
	  })   


.directive('onlyDigits', function() {
	return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, element, attr, ctrl) {
			function inputValue(val) {
				if (val) {
					var digits = val.replace(/[^0-9]/g, '');

					if (digits !== val) {
						ctrl.$setViewValue(digits);
						ctrl.$render();
					}
					return parseInt(digits, 10);
				}
				return undefined;
			}
			ctrl.$parsers.push(inputValue);
		}
	};
})

.directive(
		'onlyLettersInput',
		function() {
			return {
				require : 'ngModel',
				link : function(scope, element, attr, ngModelCtrl) {
					function fromUser(text) {
						//var transformedInput = text.replace(/[^a-zA-Z]/g, '');
						var transformedInput = text.replace(
								/[^a-\w\sáéíóúÁÉÍÓÚÑñ]/g, '');
						// var transformedInput = text.replace(/^[ñA-Za-z _]*[ñA-Za-z][ñA-Za-z _]*$/);

						//console.log(transformedInput);
						if (transformedInput !== text) {
							ngModelCtrl.$setViewValue(transformedInput);
							ngModelCtrl.$render();
						}
						return transformedInput;
					}
					ngModelCtrl.$parsers.push(fromUser);
				}
			};
})

