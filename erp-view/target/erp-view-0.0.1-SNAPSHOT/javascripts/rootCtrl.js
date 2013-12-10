$app = angular.module('rootCtrl',['ngResource']);

$app.config(function ($routeProvider) {
	$routeProvider
	.when(
		'/home',{controller:listCtrl, templateUrl:'list.html'}
		)
	.when(
		'/clientList',{controller:clientListCtrl, templateUrl:'clientList.html'}
		)
	.when(
		'/edit/:name',{controller:editCtrl, templateUrl:'form.html'}
		)
	.when(
		'/new',{controller:newCtrl, templateUrl:'form.html'}
		)
	.when(
		'/produtos',{controller:produtosCtrl, templateUrl:'produtos.html'}
		)
	.when(
		'/phones',{controller:phonesCtrl, templateUrl:'phones.html'}
		)
	.when(
		'/',{controller:loginCtrl,templateUrl:'login.html'}
		)
	.otherwise({redirectTo:'/'});
	

});
$app.run(function ($rootScope,$resource) {
	$rootScope.config = {
		appName:"AngularJS",
		logado:false,
		mainRoute :[
			{link:"#",name:"Home"},
			{link:"#",name:"Settings"},
			{link:"#",name:"Help"}
		],
		userProfile:{
			name:"Fabrício Santos",
			avatar:"../../images/avatars/avatar1.jpg"
		},
		menus:[
			{
				id:"menu1",
				name:"Menu Principal",
				link:"/",
				icon:"icon-hand-up",
				subMenus:[
					{name:"Listar Cliientes",link:"#/clientList",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu3",link:"#/menu3",icon:"icon-hand-up"},
					{name:"Teste Menu4",link:"#/menu3",icon:"icon-hand-up"}
				]
			}
		]	
	};


	$rootScope.fruits = ["banana","apple","orange"];
	Config = $resource("/config");
	$rootScope.loadConfig = function () {
		Config.query(function (data) {
			$rootScope.config = data;
		});
	};
	$rootScope.loadConfig();

	$rootScope.page = {title:"Home", subTitle:"home", icon:"icon-home"};
});

function listCtrl ($scope,$location) {
	if(!$scope.config.logado){
		$location.path('/');
	}
	$scope.page.title = "list";
	$scope.page.subTitle = "list";
	$scope.page.icon="icon-home";
	$scope.client={
		name:"fabricio",
		email:"fabricio@com.br",
		getAddressById : function (id) {
			for (var i = 0; i < this.addresses.length; i++) {
				if(this.addresses[i].id == id){
					return i;
				}
			};
			return -1;
		},
		addresses:[]
	};

	$scope.initAddresses = function  () {
		$scope.client.addresses.unshift({id:$scope.client.addresses.length+1,description:"Novo Endereço"});
	};
	$scope.saveAddress = function(address) {
		var addressId = $scope.client.getAddressById(address.id);
		if( addressId != -1){
			$scope.client.addresses[addressId] = address;
		}else{
			$scope.client.addresses.push(address);
		}

		alert("Endereço: "+address.id+" salvo com sucesso!" );
	};
	$scope.removeAddress = function(address){
		var addressId = $scope.client.getAddressById(address.id);
		if( addressId != -1){
			$scope.client.addresses[addressId]= null;
			$scope.client.addresses.length --;
			alert("Endereço: "+address.id+" removido com sucesso!" );
		}else{
			alert("Endereço com o id: "+address.id+" não foi encontrado!");
		}
	};
}

function produtosCtrl ($scope,$http) {
	$scope.page.title = "produto";
	$scope.page.subTitle = "fruits to edit";
	$scope.fruits = Array();
	$scope.getData = function(){
		$http.get("../../data/produto/db.db").success(function(data){
		$scope.fruits = data.fruits;
			console.log($scope.fruits);
			}).error(function(data){
			alert("Error...");
			console.log(data);
		});
	};
}

function clientListCtrl ($scope) {
	angular.element('#myTable').dataTable();
}

function editCtrl ($scope,$routeParams,$location) {
	$scope.page.title = "Edit fruit";
	$scope.page.subTitle = "fruits to edit";
	$scope.fruit = $routeParams.name;
	$scope.fruitIndex = $scope.fruits.indexOf($scope.fruit);
	$scope.save = function(){
		$scope.fruits[$scope.fruitIndex]=$scope.fruit;
		$location.path('/home');
	};
}


function phonesCtrl ($scope,$resource) {
	var Phone = $resource("/phones/:phoneId");

	$scope.getPhoneById = function(){
		Phone.get({phoneId:$scope.idPhone},function(data){
			$scope.phone=data;
		});
	};

	$scope.getPhones = function(){
		Phone.query(function (data){
			scope.phones = data;
		});
	};


	$scope.savePhone = function(){
		p = new Phone();
		p.number="1111-2222";
		p.$save();
	};
	$scope.deletePhone = function(){
		Phone.delete({phoneId:10}, function(data) {
			$scope.phones = data;
		});
	};
}

function loginCtrl ($scope,$location) {
	if($scope.config.logado){
		$location.path('/home');
	}
	$scope.save = function(){
		$scope.config.logado = true;
		$location.path('/home');
	};
}

function newCtrl ($scope,$location){
	$scope.page.title = "new";
	$scope.page.subTitle = "new";
	$scope.page.icon="icon-home";
	$scope.fruit = "";
	$scope.save = function(){
		$scope.fruits.push($scope.fruit);
		$location.path('/home');
	};
};



