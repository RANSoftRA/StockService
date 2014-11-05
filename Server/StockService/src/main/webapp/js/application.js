stockServiceClient = angular.module("StockServiceClient", [])

stockServiceClient.sessionId = "asdfsdf";

stockServiceClient.directive('unregistered', function() {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : '/templates/unregistered.html'
	}
});

stockServiceClient.directive('registered', function() {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : '/templates/registered.html'
	}
});

function TopController($scope, $http) {
	
	$scope.loggedIn = false;
	
	$scope.login = function(username, password) {

		$http.post("/users/" + username + "/login?pw=" + password).success(
				function(data, status, headers, config) {
					$scope.loggedIn = true;
					// $scope.userinformation = angular.fromJson(data);
				});
	}

	$scope.register = function(username, password) {
		$http.put("/users/" + username + "?pw=" + password).success(
				function(data, status, headers, config) {
					$scope.loggedIn = true;
					// $scope.userinformation = angular.fromJson(data);
				});
	}

	$scope.logout = function() {
		$http.post("/users/logout?sessionID=" + stockServiceClient.sessionId)
				.success(function(data, status, headers, config) {
					$scope.loggedIn = true;
				});
	}
}

function MiddleController($scope, $http) {

	// Calling to get all Stocks
	$http.get("/finance/stocks").success(
			function(data, status, headers, config) {
				$scope.stocks = angular.fromJson(data);
			});

	$scope.getStockhistory = function(stock) {

		$scope.currentStock = stock;

		$http.get("/finance/stocks/" + stock.symbol + "/history").success(
				function(data, status, headers, config) {
					$scope.stockHistory = angular.fromJson(data);
				});

	};
}