$app = angular.module('loginCtrl', [ 'ngResource' ]);

$app.run(function($rootScope, $resource, $http, $location) {
});

function loginCtrl($scope, $resource, $location, $route) {
	$scope.error = location.search.split("error=")[1];
}
