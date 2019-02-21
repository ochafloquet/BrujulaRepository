angular.module('app.services', [])
    .factory('APIservice', function ($http) {

        return{
        	/*----Login-----*/
        	login : function(url, username, password){
        		return $http({
                    method: 'POST',
                    url: url,
                    data : 	{
	                    		"username":username,
	                    		"password":password
                    		},
                    headers : {
    					'Content-Type' : 'application/json',
    					'Access-Control-Allow-Credentials': true
    				}
                }).then(function(response){
                	console.log("data del Api service: " + JSON.stringify(response.data));
                	return response.data;
                }); 
        	},
        	
        	
        	/*----------------*/
        	
        };
    });
