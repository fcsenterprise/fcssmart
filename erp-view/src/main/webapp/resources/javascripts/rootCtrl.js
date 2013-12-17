$app = angular.module('rootCtrl', [ 'ngResource', 'ui.mask',
		'$strap.directives' ]);

$app.config(function($routeProvider, $httpProvider) {
	$routeProvider.when('/empresa', {
		controller : 'EmpresaCtrl',
		templateUrl : 'empresa.html'
	}).when('/departamento', {
		controller : 'DepartamentoCtrl',
		templateUrl : 'departamento.html'
	}).when('/funcionario', {
		controller : 'FuncionarioCtrl',
		templateUrl : 'funcionario.html'
	}).when('/menu', {
		controller : 'MenuCtrl',
		templateUrl : 'menu.html'
	}).otherwise({
		redirectTo : '/empresa'
	});
});
$app.run(function($rootScope, $resource, $timeout) {
	$rootScope.config = {
		appName : "AngularJS",
		logado : true,
		userProfile : {
			name : "Fabrício Santos",
			avatar : "../../images/avatars/avatar1.jpg"
		}
	};

	Config = $resource("/config");
	$rootScope.loadConfig = function() {
		Config.query(function(data) {
			$rootScope.config = data;
		});
	};

	$rootScope.page = {
		title : "Home",
		subTitle : "home",
		icon : "icon-home"
	};
});
function EmpresaCtrl($scope, $resource, $location, $route) {
	var Empresa = $resource("/erp/empresa/:paramId", {
		paramId : '@paramId'
	});

	$scope.$root.breadCumbs = [ {
		name : 'Cadastros Basicos',
		icon : 'fa fa-book'
	}, {
		name : 'Empresa',
		icon : 'fa fa-building-o'
	} ];

	$scope.salvar = function() {
		Empresa.save($scope.empresa, function(data) {
			alert("Salvo Com Sucesso");
			$route.reload();
		}, function(data) {
			alert(data.data);
		});
	};
	$scope.excluir = function() {
		Empresa.remove({
			'paramId' : $scope.empresa.id
		}, function(data) {
			$location.path("/empresa");
		});
	};

}
function DepartamentoCtrl($scope, $filter, $routeParams, $resource, $location,
		$route) {
	var Departamento = $resource("/erp/departamento/:paramId", {
		paramId : '@paramId'
	});

	$scope.$root.breadCumbs = [ {
		name : 'Cadastros Basicos',
		icon : 'fa fa-book'
	}, {
		name : 'Departamento',
		icon : 'fa fa-group'
	} ];
	$scope.salvar = function() {

		Departamento.save($scope.departamento, function(data) {
			$location.path("/departamentos");
		});
	};
	var nome = $routeParams.nome;
	$scope.excluir = function() {
		Departamento.remove({
			paramId : $scope.departamento.id
		}, function(data) {
			$location.path("/departamentos");
		});
	};
}
function FuncionarioCtrl($scope) {
	$scope.$root.breadCumbs = [ {
		name : 'Cadastros Basicos',
		icon : 'fa fa-book'
	}, {
		name : 'Funcionario',
		icon : 'fa fa-user'
	} ];
}

function MenuCtrl($scope, $resource) {
	$scope.$root.breadCumbs = [ {
		name : 'Cadastros Basicos',
		icon : 'fa fa-book'
	}, {
		name : 'Menus',
		icon : 'fa fa-list-ul'
	} ];

}
$app
		.directive(
				'appMenu',
				function($compile, $resource, $q) {
					return {
						restrict : 'E',
						scope : {
							menus : '=',
						},
						link : function compile(scope, element, attrs) {
							var Menus = $resource('/erp/menu/all/:paramId', {
								paramId : '@paramId'
							});
							var getSuperMenu = function() {
								return '<ul class="nav navbar-collapse collapse navbar-collapse-primary"> </ul>';
							};
							var getMenu = function(id, nome) {
								return '<li class="dark-nav"> '
										+ '<span class="glow"></span> <a class="accordion-toggle" data-toggle="collapse" ng-href="#'
										+ id
										+ '"> <i class="fa fa-folder icon-2x"></i> <span class="ng-binding">'
										+ nome
										+ '<i class="icon-caret-down"></i>'
										+ '</span></a><ul id="' + id
										+ '" class="collapse" style="height: auto;"></ul></li>';
							};
							var getSubmenu = function(url, icone, nome) {
								return '<li><a '
										+ (url == null ? '' : 'ng-href="' + url
												+ '"')
										+ '> <i class="'
										+ icone + '"></i>' + nome + '</a></li>';
							};
							var i = 0;
							var create = function(menus, element) {
								menus
										.forEach(function(menu) {
											var newEl;
											if (menu.subMenus !== undefined
													&& menu.subMenus.length > 0) {
												var menuId = scope.$id + (++i);
												newEl = angular
														.element(getMenu(
																menuId,
																menu.nome));
												element.append(newEl);
												create(menu.subMenus, newEl.find('#'+menuId));
											} else {
												newEl = angular
														.element(getSubmenu(
																menu.url,
																menu.icone,
																menu.nome));
												element.append(newEl);
											}
										});
							};
							Menus.query(function(menus) {
								var sMenuEl = angular.element(getSuperMenu());
								create(menus, sMenuEl);
								element.replaceWith(sMenuEl);
								$compile(sMenuEl)(scope);
							}, function(data) {
								console.log(data);
								alert(data);
							});
						}
					}
				});

$app
		.directive(
				'menuTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							menu : '=',
							columns : '=?',
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Menus</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="menu" app-table-action="#/menu/"'
											+ 'app-table-link="/erp/menu"></table></div></div></div>';
									scope.columns = [ {
										'mData' : 'id',
										'sTitle' : 'ID'
									}, {
										'mData' : 'nome',
										'sTitle' : 'Nome'
									}, {
										'mData' : 'url',
										'sTitle' : 'Url'
									}, {
										'mData' : 'icone',
										'sTitle' : '&Iacute;cone'
									}, {
										'mData' : 'nomeMenuPai',
										'sTitle' : 'Menu Pai'
									} ];
									var currentElement = angular
											.element(template);
									element.replaceWith(currentElement);
									$compile(currentElement)(scope);
								}
							};
						}
					};
				});
$app
		.directive(
				'funcionarioTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							funcionario : '=',
							columns : '=?',
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Funcionarios</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="funcionario" app-table-action="#/funcionario/"'
											+ 'app-table-link="/erp/funcionario"></table></div></div></div>';
									scope.columns = [ {
										'mData' : 'id',
										'sTitle' : 'ID'
									}, {
										'mData' : 'nome',
										'sTitle' : 'Nome'
									}, {
										'mData' : 'cpf',
										'sTitle' : 'cpf'
									}, {
										'mData' : 'dataNascimento',
										'sTitle' : 'Data Nascimento'
									}, {
										'mData' : 'credencial.login',
										'sTitle' : 'Login'
									}, {
										'mData' : 'credencial.email',
										'sTitle' : 'Email'
									} ];
									var currentElement = angular
											.element(template);
									element.replaceWith(currentElement);
									$compile(currentElement)(scope);
								}
							};
						}
					};
				});
$app.directive('appTable', function($location, $modal) {

	return {
		restrict : 'A, C',
		requires : '^table',
		link : function(scope, element, attrs, controller) {
			element.addClass("dTable table responsive table-responsive");
			var dataTable = element.dataTable({
				aoColumns : scope.appTableColumns,
				bJQueryUI : false,
				bAutoWidth : false,
				sPaginationType : "full_numbers",
				sDom : "<\"table-header\"flr>t<\"table-footer\"ip>",
				bServerSide : true,
				aLengthMenu : [ [ 5, 10, 25, 50 ], [ '05', 10, 25, 50 ] ],
				iDisplayLength : 5,
				sAjaxSource : scope.appTableLink,
				fnRowCallback : function(nRow, aData, iDisplayIndex) {
					var el = angular.element(nRow);
					el.on('click', function(obj) {
						el.parent().children().removeClass('danger');
						el.addClass('danger');
						var pos = obj.currentTarget._DT_RowIndex;
						scope.appTableSelected = dataTable.fnGetData()[pos];
						scope.$apply();
					});
				}
			});
		},
		scope : {
			appTableColumns : "=",
			appTableLink : "@",
			appTableSelected : "=",
			appEditLink : "@"
		}
	};
});

$app.directive('appModal', function() {
	return {
		restrict : 'EA',
		templateUrl : '/resources/components/modal.html',
		transclude : true,
		scope : {
			modalId : '@',
			modalTitle : '@',
		}
	};
});

$app.directive('modalShower', function() {
	return {
		restrict : 'A',
		transclude : false,
		scope : {
			modalId : '@'
		},
		link : function(scope, element, attrs) {
			element.on('click', function() {
				$('#' + scope.modalId).modal('show');
			});
		}
	};
});

$app
		.directive(
				'empresaTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							empresa : '=',
							columns : '=?',
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Empresas</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="empresa" app-table-action="#/empresa/"'
											+ 'app-table-link="/erp/empresa" app-edit-link="/empresa"></table></div></div></div>';
									scope.columns = [
											{
												'mData' : 'id',
												'sTitle' : 'ID'
											},
											{
												'mData' : 'cnpjCei',
												'sTitle' : 'CNPJ/CEI'
											},
											{
												'mData' : 'razaoSocial',
												'sTitle' : 'Raz&atilde;o Social'
											},
											{
												'mData' : 'nomeFantasia',
												'sTitle' : 'Nome Fantasia'
											},
											{
												'mData' : 'inscricaoEstadual',
												'sTitle' : 'Inscri&ccedil;&atilde;o Estadual'
											} ];
									var currentElement = angular
											.element(template);
									element.replaceWith(currentElement);
									$compile(currentElement)(scope);
								}
							};
						}
					};
				});
$app
		.directive(
				'appFormGroup',
				function() {
					return {
						restrict : 'E',
						replace : true,
						compile : function() {
						},
						scope : {
							label : '@',
							type : '@?',
							class : '=?',
							dataPromptPosition : '@?',
							uiMask : '@?',
							value : '='
						},
						template : '<div class="form-group">'
								+ '<label class="control-label col-lg-2">{{label}}</label>'
								+ '<div class="col-lg-10">'
								+ '<input type="{{type || "text"}}" ng-class="class"'
								+ ' data-prompt-position="{{dataPromptPosition || "topLeft"}}" ui-mask="{{uiMask || "*" }}"'
								+ ' ng-model="value" /></div></div>',
						link : function(scope, element, attrs) {
							scope.$watch('value', function(newValue) {
								scope.value = newValue;
								scope.$apply();
							});
						}
					};
				});
$app
		.directive(
				'appActions',
				function($resource, $route) {
					return {
						restrict : 'E',
						template : '<div class="form-actions ">'
								+ '<button type="button" class="btn btn-blue" ng-click="salvar()">Salvar</button>'
								+ '<button type="button" class="btn btn-blue" ng-show="value && value.id" ng-click="excluir()">Excluir</button>'
								+ '<button type="button" class="btn btn-blue" ng-click="value = {}">Limpar</button>'
								+ '</div>',
						scope : {
							resource : '@',
							value : '='
						},
						link : function(scope, element, attrs) {
							var Resource = $resource(scope.resource, {
								paramId : '@paramId'
							});

							scope.salvar = function() {
								Resource.save(scope.value, function(data) {
									alert("Salvo Com Sucesso");
									$route.reload();
								}, function(data) {
									alert(data.data);
								});
							};
							scope.excluir = function() {
								Resource.remove({
									'paramId' : scope.value.id
								}, function(data) {
									alert("Excluído Com Sucesso");
									$route.reload();
								}, function(data) {
									alert(data.data);
								});
							};
						}
					};
				});
$app
		.directive(
				'departamentoTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							departamento : '=',
							columns : '=?',
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Departamentos</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="departamento" app-table-action="#/departamento/"'
											+ 'app-table-link="/erp/departamento" ></table></div></div></div>';
									scope.columns = [ {
										'mData' : 'id',
										'sTitle' : 'ID'
									}, {
										'mData' : 'nome',
										'sTitle' : 'Nome'
									}, {
										'mData' : 'descricao',
										'sTitle' : 'Descri&ccedil;&atilde;o'
									}, {
										'mData' : 'empresa.razaoSocial',
										'sTitle' : 'Empresa'
									} ];
									var currentElement = angular
											.element(template);
									element.replaceWith(currentElement);
									$compile(currentElement)(scope);
								}
							};
						}
					};
				});