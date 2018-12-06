'use strict'
angular.module('dashboard',[]).factory('dashbroadServices', ['$http', '$rootScope','$window',function($http,$rootScope,$window){
	var services ={
		showTopTenStaffs : showTopTenStaffs

		};
	return services;
	var token = 'Bearer ' +$window.localStorage.getItem("token");
	 $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
	function showTopTenStaffs(min ,max){
		// var config = {
		// 	headers :{
		// 		'Authorization':'Bearer '+$window.localStorage.getItem("token")
		// 	}

		// }
		var data = $.param({
								First:min,
								End:max
							})
	return $http.get($rootScope.linkApi+'staffs/thanhtich?'+data,{'Authorization':token});		
	};
}])