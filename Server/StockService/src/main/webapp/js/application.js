stockServiceClient = angular.module("StockServiceClient", ['ngCookies']);

/**
 * Base 64 encryption and decryption
 */
stockServiceClient.factory('Base64', function() {
    var keyStr = 'ABCDEFGHIJKLMNOP' +
            'QRSTUVWXYZabcdef' +
            'ghijklmnopqrstuv' +
            'wxyz0123456789+/' +
            '=';
    return {
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                        keyStr.charAt(enc1) +
                        keyStr.charAt(enc2) +
                        keyStr.charAt(enc3) +
                        keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                alert("There were invalid base64 characters in the input text.\n" +
                        "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                        "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };
});

/**
 * Authorization Service - Set Credentials and clearCredentials
 */
stockServiceClient.factory('Auth', [
		'Base64',
		'$cookieStore',
		'$http',
		function(Base64, $cookieStore, $http) {

			return {
				setCredentials : function(username, password) {
					var encoded = Base64.encode(username + ':' + password);
					$http.defaults.headers.common.Authorization = 'Basic '
							+ encoded;
					$cookieStore.put('authdata', encoded);
				},
				clearCredentials : function() {
					document.execCommand("ClearAuthenticationCache");
					$cookieStore.remove('authdata');
					$http.defaults.headers.common.Authorization = 'Basic ';
				}
			};
		} ]);

// Directives
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


// Controllers
stockServiceClient.controller("TopController", ['Auth', '$scope', '$http',
		function(Auth, $scope, $http) {
			$scope.loggedIn = false;

			$scope.login = function(username, password) {
				Auth.setCredentials(username, password);
				$http.get("secured/users/" + username + "/transactions").success(
						function(data, status, headers, config) {
							$scope.loggedIn = true;
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
		} ]);

stockServiceClient.controller("MiddleController", [
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