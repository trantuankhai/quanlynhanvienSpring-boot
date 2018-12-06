'use  strict'
angular.module('thongketheophongban',[]).factory('thongketheophongbanservices', ['$http','$rootScope', function($http,$rootScope){
	var services  = {
		thongkephongban : thongkephongban,
		changeRC : changeRC,
		pagination:pagination,
		countDeparts :countDeparts
	};
	return services;
	 function thongkephongban(first , end){
	 	var data = $.param(
	 	{
	 		min :first,
	 		max : end
	 	}
	 		);
	 	return $http.get($rootScope.linkApi+'departs/thanhtich?'+data,{});
		
	};
	function changeRC(first , end){
			var data = $.param(
	 	{
	 		min :0,
	 		max : end
	 	}
	 		);
	 	return $http.get($rootScope.linkApi+'departs/thanhtich?'+data,{});
	};
	function pagination(first , end){
		var data = $.param(
	 	{
	 		min :first,
	 		max : end
	 	}
	 		);
	 	return $http.get($rootScope.linkApi+'departs/thanhtich?'+data,{});	
	};
	function countDeparts(showOnePage){
		return $http.get($rootScope.linkApi+'departs/sumPage/'+showOnePage,{});	
	};
}])
