$app = angular.module('rootCtrl', [ 'ngResource', 'ui.mask',
		'$strap.directives', 'ui.select2' ]);

$app.config(function($routeProvider, $httpProvider) {
	$routeProvider.when('/', {
		controller : 'IndexCtrl',
		templateUrl : 'institucional.html'
	}).when('/empresa', {
		controller : 'EmpresaCtrl',
		templateUrl : 'empresa.html'
	}).when('/natureza', {
		controller : 'NaturezaCtrl',
		templateUrl : 'natureza.html'
	}).when('/departamento', {
		controller : 'DepartamentoCtrl',
		templateUrl : 'departamento.html'
	}).when('/usuario', {
		controller : 'UsuarioCtrl',
		templateUrl : 'usuario.html'
	}).when('/alterarSenha', {
		controller : 'AlterarSenhaCtrl',
		templateUrl : 'alterarSenha.html'
	}).when('/email', {
		controller : 'EmailCtrl',
		templateUrl : 'email.html'
	}).when('/menu', {
		controller : 'MenuCtrl',
		templateUrl : 'menu.html'
	}).otherwise({
		redirectTo : '/'
	});
});
$app.run(function($rootScope, $resource, $timeout) {

	$rootScope.entrar = function() {
		$resource('/erp/usuario/selecionarEmpresa').save({},
				$rootScope.empresa, function(data) {
					window.location = "/resources/pages/erp/index.html";
				}, function(data) {
					alert(data);
				});
	};
	$rootScope.config = {
		appName : "AngularJS",
		logado : true,
		userProfile : {
			name : "Fabrício Santos",
		}
	};
	// Config = $resource("/config");
	// $rootScope.loadConfig = function() {
	// Config.query(function(data) {
	// $rootScope.config = data;
	// });
	// };

	$rootScope.page = {
		title : "Home",
		subTitle : "home",
		icon : "icon-home"
	};
});
function NaturezaCtrl($scope, $resource, $location) {
	$scope.natureza = {};
}
function loginCtrl($scope, $resource, $location) {
	$scope.login = function() {
		$resource('/j_spring_security_check').save($.param($scope.user),
				function(data) {
					console.log(data);
					$location.path('/');
				}, function(data) {
					console.log(data);
					$scope.error = data;
				});

	};
}

function IndexCtrl($scope, $resource, $location, $route) {
	$scope.msg = "Sistema em Construção";
	$scope.imglink = "/resources/images/Construcao.png";
}
function EmpresaCtrl($scope, $resource, $location, $route) {
	$scope.$root.page = {
		title : "Empresas",
		subTitle : "Cadastro de Empresas",
		icon : "fa fa-building-o"
	};
}
function DepartamentoCtrl($scope, $filter, $routeParams, $resource, $location,
		$route) {
	$scope.$root.page = {
		title : "Departamentos",
		subTitle : "Cadastro de Departamentos",
		icon : "fa fa-group"
	};
}

function AlterarSenhaCtrl($scope, $resource) {
	$scope.alterarSenha = function() {
		$resource('/erp/usuario/alterarSenha').save($scope.usuario,
				function(data) {
					alert("Senha Alterada Com Sucesso!");
				}, function(data) {
					alert("Erro ao Alterar Senha!");
				});
	};
}
function UsuarioCtrl($scope, $resource) {
	$scope.columns = [ {
		'mData' : 'id',
		'sTitle' : 'ID'
	}, {
		'mData' : 'menu.nome',
		'sTitle' : 'menu'
	}, {
		'mData' : 'empresa.razaoSocial',
		'sTitle' : 'empresa'
	}, {
		'mData' : 'credencial.login',
		'sTitle' : 'Login'
	}, {
		'mData' : 'ler',
		'sTitle' : 'Listar'
	}, {
		'mData' : 'criar',
		'sTitle' : 'Salvar'
	}, {
		'mData' : 'editar',
		'sTitle' : 'Editar'
	}, {
		'mData' : 'remover',
		'sTitle' : 'Remover'
	} ];
	$scope.tableFunc = function(scope) {
		alert("value " + value + ", newValue " + newValue);
	};
	$scope.permissaoLink = '/erp/permissao/table/';
	$scope
			.$watch(
					'usuario',
					function(value, newValue) {
						$scope.permissaoLink = '/erp/permissao/table/'
								+ ($scope.usuario != null
										&& $scope.usuario.credencial != null
										&& $scope.usuario.credencial.id != null ? $scope.usuario.credencial.id
										: '');
						console.log("permissao", $scope.permissaoLink);
					});
	$scope.reenviarSenha = function() {
		var usr = $resource('/erp/usuario/novaSenha');
		usr.save($scope.usuario, function(data) {
			alert("Senha Reenviada Com Sucesso!");
		}, function(data) {
			alert(data);
		});
	};

	$scope.permissoes = [];
	$scope.$root.page = {
		title : "Usuários",
		subTitle : "Cadastro de Usuários",
		icon : "fa fa-user"
	};

	$scope.addToList = function() {
		if ($scope.permissao != null) {
			$scope.permissoes.push($scope.permissao);
		}
	};

	$scope.removeFromList = function() {
		var index = $scope.permissoes.indexOf($scope.permissao);
		if (index > -1) {
			$scope.permissoes.splice(index, 1);
		}
	};

	$scope.usuario = {
		bloqueado : false,
		admin : false
	};
}

function EmailCtrl($scope, $resource) {
	var Email = $resource('/erp/email/load').query({}, function(data) {
		console.log(data[0]);
		$scope.email = {
			config : data[0]
		};
	}, function(data) {
		console.log(data);
		alert(data);
	});
}

function MenuCtrl($scope, $resource) {
	$scope.$root.page = {
		title : "Menus",
		subTitle : "Cadastro de Menus",
		icon : "fa fa-th-list"
	};
}
$app
		.directive(
				'appMenu',
				function($compile, $resource, $q) {
					return {
						restrict : 'E',
						scope : {
							menus : '=',
							clickable : "=?",
							selected : '=?'
						},
						link : function(scope, element, attrs) {
							var Menus = $resource('/erp/menu/all/:paramId', {
								paramId : '@paramId'
							});

							var getSuperMenu = function() {
								return '<ul class="nav navbar-collapse collapse navbar-collapse-primary"> </ul>';
							};
							var getMenu = function(id, nome, glow, icone) {
								return '<li class="dark-nav"> '
										+ (glow == true ? '<span class="glow"></span>'
												: '')
										+ '<a class="accordion-toggle" data-toggle="collapse" ng-href="#'
										+ id
										+ '"> <i class="'
										+ icone
										+ ' icon-2x icon-red"></i> <span class="ng-binding">'
										+ nome
										+ '<i class="icon-caret-down '
										+ (glow == true ? 'icon-2x' : '')
										+ '"></i>'
										+ '</span></a><ul id="'
										+ id
										+ '" class="collapse" style="height: auto;"></ul></li>';
							};
							var getSubmenu = function(id, url, icone, nome) {
								return '<li style="background-color:black;"><a style="padding-left: 40px;"'
										+ (url == null || !scope.clickable ? ' ng-click="onClick('
												+ id + ')" '
												: 'ng-href="' + url + '"')
										+ '> <i class="icon-muted pull-left'
										+ icone + '"></i>' + nome + '</a></li>';
							};

							scope.onClick = function(id) {
								$resource('/erp/menu/:paramId', {
									paramId : '@paramId'
								}).get({
									paramId : id
								}, {}, function(data) {
									console.log(data);
									scope.selected = data;
								}, function(data) {
									alert("Erro ao buscar o menu selecionado");
								});
							};
							var i = 0;
							var create = function(menus, element) {
								menus.forEach(function(menu) {
									var newEl;
									if (menu.children !== undefined
											&& menu.children.length > 0) {
										var menuId = scope.$id + (++i);
										newEl = angular.element(getMenu(menuId,
												menu.object.nome,
												menu.object.menu == null,
												menu.object.icone));
										element.append(newEl);
										create(menu.children, newEl.find('#'
												+ menuId));
									} else {
										newEl = angular.element(getSubmenu(
												menu.object.id,
												menu.object.url,
												menu.object.icone,
												menu.object.nome));
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
					};
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
											+ 'app-table-selected="menu" recriate="false" app-table-action="#/menu/"'
											+ 'app-table-link="\'/erp/menu\'"></table></div></div></div>';
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
										'mData' : 'menu.nome',
										'sTitle' : 'Menu Pai',
										'sDefaultContent' : ''
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
				'usuarioTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							usuario : '=',
							columns : '=?',
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Usu&aacute;rios</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="usuario" recriate="false" app-table-action="#/usuario/"'
											+ 'app-table-link="\'/erp/usuario\'"></table></div></div></div>';
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
										'mData' : 'credencial.login',
										'sTitle' : 'Login'
									}, {
										'mData' : 'credencial.email',
										'sTitle' : 'Email'
									}, {
										'mData' : 'credencial.bloqueado',
										'sTitle' : 'bloqueado'
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
				'permissaoTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							permissao : '=',
							columns : '=?',
							credencialId : '@'
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Permiss&otilde;es</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="permissao"  recriate="false" app-table-action="#/permissao/'
											+ scope.credencialId
											+ '"'
											+ 'app-table-link="\'/erp/permissao\'"></table></div></div></div>';
									scope.columns = [ {
										'mData' : 'id',
										'sTitle' : 'ID'
									}, {
										'mData' : 'menu.nome',
										'sTitle' : 'menu'
									}, {
										'mData' : 'empresa.razaoSocial',
										'sTitle' : 'empresa'
									}, {
										'mData' : 'credencial.login',
										'sTitle' : 'Login'
									}, {
										'mData' : 'ler',
										'sTitle' : 'Listar'
									}, {
										'mData' : 'criar',
										'sTitle' : 'Salvar'
									}, {
										'mData' : 'editar',
										'sTitle' : 'Editar'
									}, {
										'mData' : 'remover',
										'sTitle' : 'Remover'
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
			scope.create = function(destroy) {
				if (destroy) {
					element.dataTable().fnDestroy();
				}
				element.addClass("dTable table responsive table-responsive");
				scope.dataTable = element.dataTable({
					aoColumns : scope.appTableColumns,
					bJQueryUI : false,
					bAutoWidth : false,
					sPaginationType : "full_numbers",
					sDom : "<\"table-header\"flr>t<\"table-footer\"ip>",
					bServerSide : true,
					bDestroy : destroy,
					aLengthMenu : [ [ 5, 10, 25, 50 ], [ '05', 10, 25, 50 ] ],
					iDisplayLength : 5,
					sAjaxSource : scope.appTableLink,
					fnRowCallback : function(nRow, aData, iDisplayIndex) {
						var el = angular.element(nRow);
						el.on('click', function(obj) {
							el.parent().children().removeClass('danger');
							var pos = obj.currentTarget._DT_RowIndex;
							var selected = scope.dataTable.fnGetData()[pos];
							console.log(selected);
							if (scope.appTableSelected == selected) {
								scope.appTableSelected = {};
							} else {
								el.addClass('danger');
								scope.appTableSelected = selected;
							}
							scope.$apply();
						});
					}
				});
			};
			scope.create(false);
			if (scope.recriate == 'true') {
				scope.$watch('appTableLink', function(value, newValue) {
					if (scope.appTableLink != null) {
						console.log('table link', scope.appTableLink);
						scope.create(true);
					}
				});
			}
		},
		scope : {
			appTableColumns : "=",
			appTableLink : "=",
			appTableSelected : "=",
			appEditLink : "@",
			recriate : '@?'
		}
	};
});

$app.directive('appEndereco', function() {
	return {
		restrict : 'EA',
		templateUrl : '/resources/components/enderecoModal.html',
		transclude : true,
		scope : {
			modalId : '@',
			modalTitle : '@',
		}
	};
});
$app.directive('appModal', function() {
	return {
		restrict : 'EA',
		templateUrl : '/resources/components/enderecoModal.html',
		transclude : true,
		scope : {
			modalId : '@',
			modalTitle : '@',
		}
	};
});
$app.directive('appPage', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appPage.html',
		transclude : true,
		scope : {}
	};
});
$app.directive('appTab', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appTab.html',
		transclude : true,
		scope : {}
	};
});

$app.directive('appForm', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appForm.html',
		transclude : true,
		scope : {}
	};
});

$app.directive('appInput', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appInput.html',
		transclude : true,
		scope : {label:'='}
	};
});

$app.directive('appTabHeader', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appTabHeader.html',
		transclude : true,
		scope : {}
	};
});

$app.directive('appTabContent', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/appTabContent.html',
		transclude : true,
		scope : {}
	};
});
$app.directive('contentOption', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/contentOption.html',
		transclude : true,
		scope : {contentId:'@',contentActive:'@?'}
	};
});
$app.directive('headerOption', function() {
	return {
		restrict : 'EA',
		replace: 'true',
		templateUrl : '/resources/components/headerOption.html',
		scope : {
			headerActive : '@?',
			contentId : '@',
			headerIcon : '@',
			headerTitle : '='
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
											+ 'app-table-selected="empresa" recriate="false" app-table-action="#/empresa/"'
											+ 'app-table-link="\'/erp/empresa\'" app-edit-link="/empresa"></table></div></div></div>';
									scope.columns = [
											{
												'mData' : 'id',
												'sTitle' : 'ID'
											},
											{
												'mData' : 'cnpj',
												'sTitle' : 'CNPJ'
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
				'empresaPermissionTable',
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
											+ 'app-table-selected="empresa" recriate="false" app-table-action="#/empresa/"'
											+ 'app-table-link="\'/erp/empresa/permission\'" app-edit-link="/empresa"></table></div></div></div>';
									scope.columns = [
											{
												'mData' : 'id',
												'sTitle' : 'ID'
											},
											{
												'mData' : 'cnpj',
												'sTitle' : 'CNPJ'
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
$app.directive('appFormValidate', function() {
	return {
		restrict : 'A',
		require : '^form',
		link : function(scope, element, attrs) {
			angular.element(element).validationEngine('detach', {
				promptPosition : "topLeft",
				scroll : false,
				autoHidePrompt : true,
				autoHideDelay : 5000
			});
			$('a[data-toggle="tab"]').on('click', function() {
				angular.element(element).validationEngine('hideAll');
			});
		}
	};
});

$app
		.directive(
				'permissaoActions',
				function($resource, $route, $window) {
					return {
						restrict : 'E',
						template : '<div class="form-actions ">'
								+ '<button type="button" id="btnSalvar{{$id}}" class="btn btn-blue" ng-click="salvar()">Salvar</button>'
								+ '<button type="button" class="btn btn-blue" ng-show="value && value.id" ng-click="excluir()">Excluir</button>'
								+ '<button type="button" class="btn btn-blue" ng-click="value = {}">Limpar</button>'
								+ '</div>',
						scope : {
							value : '=',
							credencial : '='
						},
						link : function(scope, element, attrs) {
							var Resource = $resource(
									'/erp/permissao/:credencialId', {
										credencialId : '@id'
									});

							scope.isValid = function() {
								var el = angular.element('#' + scope.formId);
								var ret = el.validationEngine('validate');
								if (!ret) {
									$('#btnSalvar' + scope.$id + '')
											.validationEngine(
													'showPrompt',
													'Existem erros não tratados verifique as outras abas',
													'load');
								}
								return ret;
							};
							scope.finishEvent = function() {
								$route.reload();
							};
							scope.salvar = function() {
								if (scope.isValid()) {
									scope.value.credencial = scope.credencial;
									Resource.save({
										credencialId : scope.credencial.id
									}, scope.value, function(data) {
										alert("Salvo Com Sucesso");
										scope.finishEvent();
									}, function(data) {
										alert(data.data);
									});
								}
							};
							scope.excluir = function() {
								Resource.remove({
									'credencialId' : scope.value.id
								}, function(data) {
									alert("Excluído Com Sucesso");
									scope.finishEvent();
								}, function(data) {
									alert(data.data);
								});
							};
						}
					};
				});
$app
		.directive(
				'appActions',
				function($resource, $route, $window) {
					return {
						restrict : 'E',
						template : '<div class="form-actions ">'
								+ '<button type="button" id="btnSalvar{{$id}}" class="btn btn-blue" ng-click="salvar()">Salvar</button>'
								+ '<button type="button" class="btn btn-blue" ng-show="value && value.id" ng-click="excluir()">Excluir</button>'
								+ '<button type="button" class="btn btn-blue" ng-click="value = {}">Limpar</button>'
								+ '</div>',
						scope : {
							resource : '@',
							value : '=',
							fullReload : '@',
							formId : '@'
						},
						link : function(scope, element, attrs) {
							var Resource = $resource(scope.resource, {
								paramId : '@paramId'
							});

							scope.isValid = function() {
								var el = angular.element('#' + scope.formId);
								var ret = el.validationEngine('validate');
								if (!ret) {
									$('#btnSalvar' + scope.$id + '')
											.validationEngine(
													'showPrompt',
													'Existem erros não tratados verifique as outras abas',
													'load');
								}
								return ret;
							};
							scope.finishEvent = function() {
								if (scope.fullReload == 'true') {
									$window.location.reload();
								} else {
									$route.reload();
								}
							};
							scope.salvar = function() {
								if (scope.isValid()) {
									Resource.save({}, scope.value, function(
											data) {
										alert("Salvo Com Sucesso");
										scope.finishEvent();
									}, function(data) {
										alert(data.data);
									});
								}
							};
							scope.excluir = function() {
								Resource.remove({
									'paramId' : scope.value.id
								}, function(data) {
									alert("Excluído Com Sucesso");
									scope.finishEvent();
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
											+ 'app-table-selected="departamento" recriate="false" app-table-action="#/departamento/"'
											+ 'app-table-link="\'/erp/departamento\'" ></table></div></div></div>';
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
$app.directive('appCheckbox', [
		'$timeout',
		'$parse',
		function($timeout, $parse) {
			return {
				compile : function(element, $attrs) {
					var icheckOptions = {
						checkboxClass : 'icheckbox_flat-aero',
						radioClass : 'iradio_flat-aero'
					};

					var modelAccessor = $parse($attrs['ngModel']);
					return function($scope, element, $attrs, controller) {

						var modelChanged = function(event) {
							$scope.$apply(function() {
								modelAccessor.assign($scope,
										event.target.checked);
							});
						};

						$scope.$watch(modelAccessor, function(val) {
							var action = val ? 'check' : 'uncheck';
							element.iCheck(icheckOptions, action).on(
									'ifChanged', modelChanged);
						});
					};
				}
			};
		} ]);
$app
		.directive(
				'enderecoTable',
				function($compile) {
					return {
						restrict : 'E',
						scope : {
							endereco : '=',
							columns : '=?',
							entidade : '@?'
						},
						compile : function compile(tElement, tAttrs, transclude) {
							return {
								pre : function preLink(scope, element, attrs,
										controller) {
									template = '<div class="box">'
											+ '<div class="box-header">'
											+ '	<span class="title">Endere&ccedil;os</span>'
											+ '</div><div class="box-content">'
											+ '<div id="dataTable">'
											+ '<table id="'
											+ scope.$id
											+ '" app-table app-table-columns="columns"'
											+ 'app-table-selected="departamento" recriate="false" app-table-action="#/endereco/"'
											+ 'app-table-link="\'/erp/endereco/\''
											+ scope.entidade
											+ '" ></table></div></div></div>';
									scope.columns = [ {
										'mData' : 'id',
										'sTitle' : 'ID'
									}, {
										'mData' : 'descricao',
										'sTitle' : 'Descri&ccedil;&atilde;o'
									}, {
										'mData' : 'rua',
										'sTitle' : 'Rua'
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