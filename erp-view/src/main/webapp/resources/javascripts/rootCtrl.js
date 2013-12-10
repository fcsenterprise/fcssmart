$app = angular.module('rootCtrl', [ 'ngResource', 'ngTable', 'ui.mask' ]);

$app.config(function($routeProvider) {
	$routeProvider.when('/home', {
		controller : listCtrl,
		templateUrl : 'list.html'
	}).when('/clientList', {
		controller : 'clientListCtrl',
		templateUrl : 'clientList.html'
	}).when('/produtos', {
		controller : produtosCtrl,
		templateUrl : 'produtos.html'
	}).when('/empresas', {
		controller : 'EmpresaListCtrl',
		templateUrl : 'empresaList.html'
	}).when('/empresa/:operacao/:cnpjCei', {
		controller : 'EmpresaCtrl',
		templateUrl : 'empresa.html'
	}).when('/departamentos', {
		controller : 'DepartamentoListCtrl',
		templateUrl : 'departamentoList.html'
	}).when('/departamento/:operacao/:nome', {
		controller : 'DepartamentoCtrl',
		templateUrl : 'departamento.html'
	}).otherwise({
		redirectTo : '/'
	});

});
$app.run(function($rootScope, $resource) {
	$rootScope.config = {
		appName : "AngularJS",
		logado : true,
		mainRoute : [ {
			link : "#",
			name : "Home"
		}, {
			link : "#",
			name : "Settings"
		}, {
			link : "#",
			name : "Help"
		} ],
		userProfile : {
			name : "Fabrício Santos",
			avatar : "../../images/avatars/avatar1.jpg"
		},
		menus : [ {
			id : "menu1",
			name : "Menu Principal",
			link : "/",
			icon : "icon-hand-up",
			subMenus : [ {
				name : "Empresas",
				link : "#/empresas",
				icon : "icon-hand-up"
			}, {
				name : "Departamentos",
				link : "#/departamentos",
				icon : "icon-hand-up"
			} ]
		} ]
	};

	$rootScope.fruits = [ "banana", "apple", "orange" ];
	Config = $resource("/config");
	$rootScope.loadConfig = function() {
		Config.query(function(data) {
			$rootScope.config = data;
		});
	};
	// $rootScope.loadConfig();

	$rootScope.page = {
		title : "Home",
		subTitle : "home",
		icon : "icon-home"
	};
});

function listCtrl($scope, $location) {
	if (!$scope.config.logado) {
		$location.path('/');
	}
	$scope.page.title = "list";
	$scope.page.subTitle = "list";
	$scope.page.icon = "icon-home";
	$scope.client = {
		name : "fabricio",
		email : "fabricio@com.br",
		getAddressById : function(id) {
			for ( var i = 0; i < this.addresses.length; i++) {
				if (this.addresses[i].id == id) {
					return i;
				}
			}
			;
			return -1;
		},
		addresses : []
	};

	$scope.initAddresses = function() {
		$scope.client.addresses.unshift({
			id : $scope.client.addresses.length + 1,
			description : "Novo Endereço"
		});
	};
	$scope.saveAddress = function(address) {
		var addressId = $scope.client.getAddressById(address.id);
		if (addressId != -1) {
			$scope.client.addresses[addressId] = address;
		} else {
			$scope.client.addresses.push(address);
		}

		alert("Endereço: " + address.id + " salvo com sucesso!");
	};
	$scope.removeAddress = function(address) {
		var addressId = $scope.client.getAddressById(address.id);
		if (addressId != -1) {
			$scope.client.addresses[addressId] = null;
			$scope.client.addresses.length--;
			alert("Endereço: " + address.id + " removido com sucesso!");
		} else {
			alert("Endereço com o id: " + address.id + " não foi encontrado!");
		}
	};
}

function produtosCtrl($scope, $http) {
	$scope.page.title = "produto";
	$scope.page.subTitle = "fruits to edit";
	$scope.fruits = Array();
	$scope.getData = function() {
		$http.get("../../data/produto/db.db").success(function(data) {
			$scope.fruits = data.fruits;
			console.log($scope.fruits);
		}).error(function(data) {
			alert("Error...");
			console.log(data);
		});
	};
}
$app
		.controller(
				'EmpresaListCtrl',
				function($scope, $filter, ngTableParams, $routeParams,
						$resource) {
					var Empresas = $resource("/erp/empresa/:pageSize/:pageNumber/:orderFields");

					$scope.pageTemplate = [ '05', '10', '15', '25', '50', '100' ];
					$scope.pageSize = '10';
					$scope.load = function(pageSize, pageNumber, orderFields) {
						$scope.empresasTableParams = new ngTableParams({
							page : pageNumber,
							count : pageSize,
							orderBy : orderFields
						}, {
							total : function() {
								return getData().data.length;
							},
							getData : function($defer, params) {
								Empresas.get({
									'pageSize' : params.count(),
									'pageNumber' : params.page(),
									'orderFields' : 'cnpjCei'
								}, function(data) {
									if (data.data !== undefined
											&& data.data.length !== undefined) {
										params.total(data.total);
										params.pageCount = data.pageCount;
										$defer.resolve(data.data);
									} else {
										params.total(0);
										$defer.resolve([]);
									}
									return data;
								});
							}
						});
					};

					$scope.load(10, 1, "razaoSocial asc");
				});
$app.controller('EmpresaCtrl', function($scope, $filter, ngTableParams,
		$routeParams, $resource, $location) {
	var Empresa = $resource("/erp/empresa/:cnpjCeiParam", {
		cnpjCeiParam : '@cnpjCeiParam'
	});

	$scope.salvar = ($routeParams.operacao == 0);
	$scope.visualizar = ($routeParams.operacao == 1);
	$scope.editar = ($routeParams.operacao == 2);
	$scope.excluir = ($routeParams.operacao == 3);

	$scope.operacao = function() {
		Empresa.save($scope.empresa, function(data) {
			$location.path("/empresas");
		});
	};
	var cnpjCei = $routeParams.cnpjCei;
	if ($scope.excluir) {
		$scope.operacao = function() {
			Empresa.remove({
				cnpjCeiParam : cnpjCei
			}, function(data) {
				$location.path("/empresas");
			});
		};
	}
	if (cnpjCei !== undefined) {
		Empresa.get({
			cnpjCeiParam : cnpjCei
		}, function(data) {
			$scope.empresa = data;
		});
	}
});
$app.controller('DepartamentoCtrl', function($scope, $filter, ngTableParams,
		$routeParams, $resource, $location) {
	var Departamento = $resource("/erp/departamento/:nomeParam", {
		nomeParam : '@nomeParam'
	});

	$scope.salvar = ($routeParams.operacao == 0);
	$scope.visualizar = ($routeParams.operacao == 1);
	$scope.editar = ($routeParams.operacao == 2);
	$scope.excluir = ($routeParams.operacao == 3);

	$scope.operacao = function() {
		Departamento.save($scope.departamento, function(data) {
			$location.path("/departamentos");
		});
	};
	var nome = $routeParams.nome;
	if ($scope.excluir) {
		$scope.operacao = function() {
			Departamento.remove({
				nomeParam : nome
			}, function(data) {
				$location.path("/departamentos");
			});
		};
	}
	if (nome !== undefined) {
		Departamento.get({
			nomeParam : nome
		}, function(data) {
			$scope.departamento = data;
		});
	}
});

$app
		.controller(
				'DepartamentoListCtrl',
				function($scope, $filter, ngTableParams, $routeParams,
						$resource) {
					var Departamentos = $resource("/erp/departamento/:pageSize/:pageNumber/:orderFields");

					$scope.pageTemplate = [ '05', '10', '15', '25', '50', '100' ];
					$scope.pageSize = '10';
					$scope.load = function(pageSize, pageNumber, orderFields) {
						$scope.departamentoTableParams = new ngTableParams({
							page : pageNumber,
							count : pageSize,
							orderBy : orderFields
						}, {
							total : function() {
								return getData().data.length;
							},
							getData : function($defer, params) {
								Departamentos.get({
									'pageSize' : params.count(),
									'pageNumber' : params.page(),
									'orderFields' : 'nome'
								}, function(data) {
									if (data.data !== undefined
											&& data.data.length !== undefined) {
										params.total(data.total);
										params.pageCount = data.pageCount;
										$defer.resolve(data.data);
									} else {
										params.total(0);
										$defer.resolve([]);
									}
									return data;
								});
							}
						});
					};

					$scope.load(10, 1, "razaoSocial asc");
				});

$app.filter('makeRange', function() {
	return function(input) {
		var lowBound, highBound;
		switch (input.length) {
		case 1:
			lowBound = 0;
			highBound = parseInt(input[0]) - 1;
			break;
		case 2:
			lowBound = parseInt(input[0]);
			highBound = parseInt(input[1]);
			break;
		default:
			return input;
		}
		var result = [];
		for ( var i = lowBound; i <= highBound; i++)
			result.push(i);
		return result;
	};
});