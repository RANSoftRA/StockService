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
angular.module("StockServiceClient").controller("MainController", ['Auth', '$scope', '$http',
		function(Auth, $scope, $http) {
	
			$scope.loggedIn = false;
			
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

			$scope.login = function(username, password) {
				Auth.setCredentials(username, password);
				$http.get("/secured/finance/transactions").success(
						function(data, status, headers, config) {
							$scope.loggedIn = true;
							$scope.portfolio = angular.fromJson(data);
						});
			}

			$scope.register = function(username, password) {
				$http.post("/users?un=" + username + "&pw=" + password).success(
						function(data, status, headers, config) {
							$scope.login(username, password);
						}).error(function() {
							alert("Registration failed! Username probably already exists!")
						});
			}

			$scope.logout = function() {
					Auth.clearCredentials();
					$scope.loggedIn = false;
			}
			
			$scope.buyStock = function(stock, quantity) {
							
				$http.post("/secured/finance/transactions?stocksymbol=" + stock.symbol + "&amount=" + quantity + "&type=false").success(function(data) {
					$scope.portfolio = angular.fromJson(data);
				}).error(function(data, status, headers, config) {
					alert(angular.fromJson(data).message);
				});
			}
			
			$scope.sellStock = function(symbol, quantity) {
				$http.post("/secured/finance/transactions?stocksymbol=" + symbol + "&amount=" + quantity + "&type=true").success(function(data){
					$scope.portfolio = angular.fromJson(data);
				}).error(function(data, status, headers, config) {
					alert(angular.fromJson(data).message);
				});
			}
			
			$scope.updatePassword = function(username, password) {
				$http.put("/secured/users/" + username + "?pw=" + password).success(function(data){
					alert("Passwort successfully changed!")
					$scope.portfolio = angular.fromJson(data);
				});
			}
			
		} ]);