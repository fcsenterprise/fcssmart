$app = angular.module('loginCtrl', [ 'ngResource' ]);

$app.run(function($rootScope, $resource, $http, $location) {
});

function loginCtrl($scope, $http, $location) {
	$scope.error = $location.search()['error'];
}
