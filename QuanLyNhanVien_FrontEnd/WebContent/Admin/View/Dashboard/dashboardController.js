'use strict'
appAdmin.controller('dashboardController', function($scope ,dashbroadServices, $rootScope, $window, $http , $interval){
	 	 //   if($window.localStorage.getItem('token')){
		  //   $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
		  //   console.log($http.defaults.headers.common.Authorization);
		  // }else{
		  //   $window.location.href='../Login';
		  //   $http.defaults.headers.common.Authorization ='';	
		  // }

			dashbroadServices.showTopTenStaffs(0,10).then(function(res){
				$scope.thanhtich = res.data;
			},function(error){
					console.log(error);

			});

	});
