'use strict'
var managerDepartsController = angular.module('managerDepartsController', []);
managerDepartsController.controller('quanlyphongbancontroller', function($scope ,quanLyPhongBanServices, $rootScope, $window, $http){
	// 	if($window.localStorage.getItem('token')==null){
	// $window.location.href='../Login';
	// }else{
	 $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
	$scope.add = false;
	$scope.view = false;
	$scope.edit = false;
	$scope.changeValue = 10;
	$scope.showOnePage = 10;
	quanLyPhongBanServices.getTenDeparts($scope.showOnePage).then(function(res){
		$scope.listDeparts = res.data;
	},function(error){
		console.log(error);

	});
	quanLyPhongBanServices.sumPage($scope.showOnePage).then(function(res){
		$scope.countPage = res.data;

	},function(error){

	});	
	$scope.showAdd = function(){
		$scope.add = true;
		$scope.view = false;
		$scope.edit = false;
		clearFrom();
		$('#nameDeparts').focus();
	};
	function clearFrom(){
			$scope.listTemp = {
			'name_departs' : null,
			"id_departs"   : null
		}
	}
	$scope.showEdit = function(list){
		$scope.edit = true;
		$scope.add = false;
		$scope.view = false;
		$scope.departsTemp = list;
		$scope.listTemp = {
			'name_departs' : list.name_departs,
			"id_departs"   : list.id_departs
		}
	};
	$scope.showView = function(list){
		$scope.view = true;
		$scope.edit = false;
		$scope.add = false;
			$scope.listTemp = {
			'name_departs' : list.name_departs,
			"id_departs"   : list.id_departs
		}
	};
	$scope.addDeparts = function(){
			var departs = {
			"id_departs": 0,
			"name_departs": $scope.listTemp.name_departs
			}
		quanLyPhongBanServices.addDeparts(departs).then(function(res){
			$("#phongban").modal("toggle");
			var departs = {
						  "id_departs": res.data,
						  "name_departs": $scope.listTemp.name_departs
							}
			$scope.listDeparts.push(departs);
			$scope.listDeparts.splice($scope.showOnePage-1, 1);
			quanLyPhongBanServices.sumPage($scope.showOnePage).then(
			function(res){
					$scope.countPage = res.data;
				},function(){
					console.log(error);
			});

			clearFrom();
			$scope.nameDeparts = "";
		},function(erorr){
		});
	};
	$scope.updateDeparts = function(){
			quanLyPhongBanServices.updateDeparts($scope.listTemp.id_departs,$scope.listTemp.name_departs).then(function(res){
					$scope.departsTemp.name_departs = $scope.listTemp.name_departs;
					$scope.departsTemp.id_departs = $scope.listTemp.id_departs;
				//	$scope.list = $scope.departsTemp;
					$("#phongban").modal("toggle");
		},function(error){
			console.log(error);

		});
	};
	$scope.deleteDeparts = function deleteDeparts(list,index){
	 var	dataDelete = {
					  "id_departs": list.id_departs,
					  "name_departs": list.name_departs
					}
		if(confirm("Bạn Có Muốn Xóa Phòng Không?")){
			quanLyPhongBanServices.countStaffsInDeparts(list.id_departs).then(function(res){
			if(res.data!=0){
				if(confirm("Tồn tại nhân viên trong phòng ban. Ban có muốn xóa?")){
					quanLyPhongBanServices.deleteDeparts(list.id_departs,dataDelete);
					$scope.listDeparts.splice(index,1);
				    quanLyPhongBanServices.sumPage($scope.showOnePage).then(function(res){
						$scope.countPage = res.data;
				},function(){
				});
				}else{
					// To do code
				}
			}else{
				quanLyPhongBanServices.deleteDeparts(list.id_departs,dataDelete);
				$scope.listDeparts.splice(index,1);
				quanLyPhongBanServices.sumPage($scope.showOnePage).then(function(res){
					console.log(res.data);
					$scope.countPage = res.data;
				},function(){

				});
			}
			},function(error) {
				/* Act on the event */
			});
}
};

	$scope.showAmount =function(){
		$scope.change = $scope.changeValue;
		quanLyPhongBanServices.getTenDeparts($scope.change).then(function(res){
			$scope.listDeparts = res.data;
			quanLyPhongBanServices.sumPage($scope.change).then(function(res){
				$scope.countPage.data;
			},function(error){

			})
		},function(error){

		});
	};
	$scope.redirectPage = function(index){
		$scope.page = (index)*$scope.showOnePage;
		quanLyPhongBanServices.redirectPage($scope.page,$scope.showOnePage).then(function(res){
			$scope.listDeparts = res.data;
			console.log(res.data);
		},function(error){

		});
		
	};

// }
});