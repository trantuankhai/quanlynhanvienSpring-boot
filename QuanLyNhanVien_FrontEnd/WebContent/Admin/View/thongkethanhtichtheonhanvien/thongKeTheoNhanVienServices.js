'use strict'
angular.module('thongketheonhanvienmodule',[]).factory('thongketheonhanvienServices', ['$http','$rootScope', function($http,$rootScope){
	var services = 
	{
		thongketheonhanvien : thongketheonhanvien,
		showReocrds : showReocrds,
		pagination : pagination,
		countStaffs : countStaffs,
		showReocrdsById: showReocrdsById,
		sendMail: sendMail
	}
	return services;
		function thongketheonhanvien(first , end){
			var data = $.param(
			{
				First:first,
				End :end
			}
				)
		return $http.get($rootScope.linkApi+'staffs/thanhtich?'+data,{});	
			};
		function showReocrds(end){
					var data = $.param(
			{
				First:0,
				End :end
			}
				)
		return $http.get($rootScope.linkApi+'staffs/thanhtich?'+data,{});	

		};	
		function pagination(first , end){
			var data = $.param(
			{
				First:first,
				End :end
			}
				)
		return $http.get($rootScope.linkApi+'staffs/thanhtich?'+data,{});

		};
		function showReocrdsById(id_staffs ){
			return  $http.get($rootScope.linkApi+"records/"+id_staffs);
		}
		function countStaffs(showOnePage){
			return $http.get($rootScope.linkApi+"staffs/sumPage/"+showOnePage, {})
		};
		function sendMail(to , id_staffs){
			var data = $.param({
				to:to,
				id_staffs:id_staffs
			});
			var config = {
				headers :{
					'Accept': 'text/plain',
					'Content-Type':'application/json'
				}
			}
			return $http.get($rootScope.linkApi+"sendMail?"+data,config);
		}

}]);