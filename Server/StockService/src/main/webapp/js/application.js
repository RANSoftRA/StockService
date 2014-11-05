stockServiceClient = angular.module("StockServiceClient", [])

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
	$scope.name = "Rainer";
}

function MiddleController($scope, $http) {
	
	// Calling to get all Stocks
	$http.get("/finance/stocks").
	success(function(data, status, headers, config) {
		$scope.stocks = angular.fromJson(data);
	});
	
	
	$scope.getStockhistory = function(symbol) {
		$http.get("/finance/stocks/" + symbol + "/history").success(function(data, status, headers,config) {
			$scope.stockHistory = data;
		});
		
	};
}