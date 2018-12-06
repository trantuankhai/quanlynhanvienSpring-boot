 'use strict'
appAdmin.controller('thongketheophongbancontroller', function($scope ,thongketheophongbanservices, $rootScope, $window, $http){
    // if($window.localStorage.getItem('token')==null) {
    // $window.location.href='../Login';
    // }else{
         $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
        $scope.changeRecords = 10;
        $scope.showOnePage =10;
    	thongketheophongbanservices.thongkephongban(0,$scope.showOnePage).then(
    	function (res){
    	$scope.listThanhTichPhongBan = res.data;
    	},
    	function (error){
    		console.log(error);
    	});
        $scope.showChange = function(){
            thongketheophongbanservices.changeRC(0,$scope.changeRecords).then(function(res){
                $scope.listThanhTichPhongBan = res.data;
                thongketheophongbanservices.countDeparts($scope.changeRecords).then(function(res){
                    $scope.sumPage = res.data;
                },function(error){
                });
            },function(error){

            });
        }
        $scope.redirectPage = function(index){
            $scope.first = (index)*showOnePage;
            thongketheophongbanservices.pagination($scope.first,$scope.showOnePage).then(function(res){
                $scope.listThanhTichPhongBan = res.data;
            },function(error){

            })

        }
        thongketheophongbanservices.countDeparts($scope.showOnePage).then(function(res){
            $scope.sumPage = res.data;
        },function(error){

        });

    // }
});
