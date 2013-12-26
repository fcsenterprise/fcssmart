$app = angular.module('empresaCtrl', [ 'ngResource' ]);

$app.run(function($rootScope, $resource, $http, $location) {
});

function empresaCtrl($scope, $resource, $location, $route) {
	$scope.error = location.search.split("error=")[1];
}
