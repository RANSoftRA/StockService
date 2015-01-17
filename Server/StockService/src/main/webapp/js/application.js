angular.module("StockServiceClient", ['ngCookies']);

angular.module('StockServiceClient').config(['$httpProvider', function($httpPovider) {
	$httpPovider.defaults.headers.common.Accept = '*/*';
	$httpPovider.interceptors.push('AuthInterceptor');
} ]);


// Directives
angular.module("StockServiceClient").directive('unregistered', function() {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : '/templates/unregistered.html'
	}
});

angular.module("StockServiceClient").directive('registered', function() {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : '/templates/registered.html'
	}
});

// Controllers
angular.module("StockServiceClient").controller("BodyController", ['Auth', '$scope', '$http',
		function(Auth, $scope, $http) {
	
			$scope.loggedIn = false;

			$scope.login = function(username, password) {
				Auth.setCredentials(username, password);
				$http.get("secured/finance/transactions").success(
						function(data, status, headers, config) {
							$scope.loggedIn = true;
						}).error(function() {
							alert("Login not successful!")
						});
			}

			$scope.register = function(username, password) {
				Auth.setCredentials(username, password);
				$http.put("users?un=" + username + "&pw=" + password).success(
						function(data, status, headers, config) {
							$scope.loggedIn = true;
							// $scope.userinformation = angular.fromJson(data);
						});
			}

			$scope.logout = function() {
					Auth.clearCredentials();
					$scope.loggedIn = false;
			}
			
			$scope.buyStock = function(stock, quantity) {
				
				alert("you want to buy " + stock.symbol + " / " + quantity);
				
				$http.post("/secured/finance/transactions?stocksymbol=" + stock.symbol + "&amount=" + quantity + "&type=false").success(function(data) {
					$scope.portfolio = angular.fromJson(data);
				});
			}
		} ]);

angular.module("StockServiceClient").controller("MiddleController", [
		'$scope',
		'$http',
		function($scope, $http) {
			var canvasGraph = new CanvasGraph();

			// Calling to get all Stocks
			$http.get("/finance/stocks").success(
					function(data, status, headers, config) {
						$scope.stocks = angular.fromJson(data);
					});

			$scope.getStockhistory = function(stock) {

				$scope.currentStock = stock;

				$http.get("/finance/stocks/" + stock.symbol + "/history")
						.success(function(data, status, headers, config) {
							canvasGraph.draw(angular.fromJson(data));
						});

			};
			
		} ]);