$app = angular.module('rootCtrl', [ 'ngResource', 'ui.mask',
		'$strap.directives' ]);

$app.config(function($routeProvider, $httpProvider) {
	$routeProvider.when('/empresa', {
		controller : 'EmpresaCtrl',
		templateUrl : 'empresa.html'
	}).when('/departamento', {
		controller : 'DepartamentoCtrl',
		templateUrl : 'departamento.html'
	}).when('/usuario', {
		controller : 'UsuarioCtrl',
		templateUrl : 'usuario.html'
	}).when('/email', {
		controller : 'EmailCtrl',
		templateUrl : 'email.html'
	}).when('/menu', {
		controller : 'MenuCtrl',
		templateUrl : 'menu.html'
	}).otherwise({
		redirectTo : '/empresa'
	});
});
$app.run(function($rootScope, $resource, $timeout) {
	angular.element("form.validatable").validationEngine({
		promptPosition : "topLeft"
	});
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
function UsuarioCtrl($scope) {
	$scope.$root.page = {
		title : "Usuários",
		subTitle : "Cadastro de Usuários",
		icon : "fa fa-user"
	};
}

function EmailCtrl($scope) {
	$scope.email = { config : {}
	};
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
						},
						link : function compile(scope, element, attrs) {
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
							var getSubmenu = function(url, icone, nome) {
								return '<li><a '
										+ (url == null ? '' : 'ng-href="' + url
												+ '"')
										+ '> <i class="icon-muted pull-left'
										+ icone + '"></i>' + nome + '</a></li>';
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
											+ 'app-table-selected="usuario" app-table-action="#/usuario/"'
											+ 'app-table-link="/erp/usuario"></table></div></div></div>';
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
						var pos = obj.currentTarget._DT_RowIndex;
						var selected = dataTable.fnGetData()[pos];
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
		},
		scope : {
			appTableColumns : "=",
			appTableLink : "@",
			appTableSelected : "=",
			appEditLink : "@"
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
			angular.element(element).validationEngine({
				validateNonVisibleFields : true,
				updatePromptsPosition : true,
				focusFirstField : true,
				scroll : false,
				promptPosition : "topLeft"
			});
			$('a[data-toggle="tab"]').on('click', function() {
				angular.element(element).validationEngine('hideAll');
			});
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
								if (scope.fullReload == 'false') {
									$route.reload();
								} else {
									$window.location.reload();
								}
							};
							scope.salvar = function() {
								if (scope.isValid()) {
									Resource.save(scope.value, function(data) {
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
											+ 'app-table-selected="departamento" app-table-action="#/endereco/"'
											+ 'app-table-link="/erp/endereco/'
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