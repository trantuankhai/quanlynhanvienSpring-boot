  
'use strict'
var appAdmin = angular.module('adminApp', ['ngRoute','managerDepartsController','managerDepartsServices','managerStaffServices','dashboard','thongketheophongban','thongketheonhanvienmodule']);
appAdmin.run( function($rootScope, $http, $window, $interval){
	$rootScope.linkApi="http://localhost:8080/";	
  if($window.localStorage.getItem('token')){
    $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
  }else{
    $window.location.href='../Login';
    $http.defaults.headers.common.Authorization ='';
  }
});
appAdmin.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    }; 
}]);
 //  $scope.en  = 
 //  {
 //    'lblShowHot' : 'Dashboard',
 //    'lblQuanLyPhongBan': 'Departs Manager',
 //    'lblQuanLyNhanVien':'Staffs Manager',
 //    'lblThongKe':'Dashboard Records',
 //    'lblThongKeTheoNhanVien':'Dashboard By Staffs',
 //    'lblThongKeTheoPhongBan':' Dashboard By Departs'
 // }

          appAdmin.directive('sideBar',function(){
            return{
              restrict:'AE',
              template:'<div class="left main-sidebar">'
                
                +'<div class="sidebar-inner leftscroll">'
                   +'<div id="sidebar-menu">'
                        
                       +'<ul>'
                            +'<li class="submenu">'
                                +'<a class="active" href="index.html"><i class="fa fa-fw fa-bars"></i><span>{{lblShowHot}} </span> </a>'
                            +'</li>'
                            +'<li class="submenu" >'
                                +'<a href="#!quan-ly-phong-ban"><i class="fa fa-bank"></i><span> {{lblQuanLyPhongBan}} </span> </a>'
                            +'</li>'
                            
                            +'<li class="submenu" >'
                                +'<a href="#!quan-ly-nhan-vien"><i class="fa fa-users"></i><span>{{lblQuanLyNhanVien}} </span> </a>'
                           + '</li>'
                            
                            +'<li class="submenu">'
                                +'<a><i class="fa fa-line-chart"></i> <span> {{lblThongKe}} </span> <span class="menu-arrow"></span></a>'
                                +'<ul class="list-unstyled" >'
                                    +'<li ><a href="#!thong-ke-thanh-tich-nhan-vien">{{lblThongKeTheoNhanVien}}</a></li>'
                                    +'<li ><a href="#!thong-ke-thanh-tich-phong-ban">{{lblThongKeTheoPhongBan}}</a></li>'
                                +'</ul>'
                            +'</li>'
                        +'</ul>'
                        +'<div class="clearfix"></div>'
                    +'</div>'
                    
                    +'<div class="clearfix"></div>'
                +'</div>'
            +'</div>'
            }
                 })
appAdmin.filter('foreach', function () {
    return function (input) {
        var lowBound, highBound;
        switch (input.length) {
            case 1:
                lowBound = 0;
                highBound = parseInt(input[0]) - 1;
                break;
            case 2:
                lowBound = parseInt(input[0]);
                highBound = parseInt(input[1]);
                break;
            default:
                return input;
        }
        var result = [];
        for (var i = lowBound; i <= highBound; i++)
            result.push(i);
        return result;
    };
});

appAdmin.config(function($routeProvider) {
	$routeProvider.when("/", {
		controller : 'dashboardController',
    templateUrl : 'View/Dashboard/dashboard.html'
	}).when("/quan-ly-phong-ban", {
		templateUrl : 'View/quanlyphongban/_quanlyphongban.html',
		controller : 'quanlyphongbancontroller'
	}).when("/quan-ly-nhan-vien", {
		templateUrl : 'View/quanlynhanvien/_quanlynhanvien.html',
		controller : 'quanlynhanviencontroller'
	}).when("/thong-ke-thanh-tich-nhan-vien", {
		templateUrl : 'View/thongkethanhtichtheonhanvien/_thongketheonhanvien.html',
		controller : 'thongketheonhanviencontroller'
	}).when("/thong-ke-thanh-tich-phong-ban", {
		templateUrl : 'View/thongkethanhtichtheophongban/_thongketheophongban.html',
		controller : 'thongketheophongbancontroller'
	}).otherwise({
		templateUrl : "index.html"
	});
});
appAdmin.controller('adminController', function($scope , $rootScope, $window, $http){
  if($window.localStorage.getItem('language')=='vi'){
    $scope.language = 'vi';
    $scope.listLanguage = {
      'lblDeparts':'Quản Lý Phòng Ban',
       'lblStaffs':'Quản Lý Nhân Viên',
       'lblThongKe' :'Thống Kê Thành Tích',
       'lblThongKeByStaffs' :"Theo Nhân Viên",
       'lblThongKeByDeparts' :"Theo Phòng Ban",
       'lblAction' :"Hành Động",
       'lblCodeDeparts' :"Mã Phòng Ban",
       'lblNameDeparts' :"Tên Phòng Ban",
       'lblCodeStaffs' :"Mã Nhân Viên",
       'lblNameStaffs' :"Tên Nhân Viên",
       'lblGenderStaffs' :"Giới Tính",
       'lblAvatarStaffs' :"Hình Ảnh",
       'lblPhoneStaffs' :"Số Điện Thoại",
       'lblNoteStaffs' :"Ghi Chú",
       'lblAddStaffs' :"Thêm Nhân Viên",
       'lblAddDeparts' :"Thêm Phòng Ban",
       'lblDetail' :"Chi Tiết",
       'lblFilter' :"Loc Phòng Ban",
       'lblSeach':'Tìm Kiếm',
       'lblDateOfBirthday':"Ngày Sinh",
       'lblSumScore':'Tổng Điểm',
       'lblRecords':"Ghi Nhận",
       'lblTop10NhanVien':'Tốp 10 Nhân Viên Có Thành Tích Cao Nhất',
       'lblThongKeNhanVien':'Thống Kê Thành Tích Nhân Viên',
       'lblThongKePhongBan':'Thống Kê Thành Tích Theo Phòng Ban'
          }
  }else{
     $scope.language = 'us';
    $scope.listLanguage = {
      'lblDeparts':'Departs Manager',
       'lblStaffs':'Staffs Manager',
       'lblThongKe' :"Statistical Achievement",
       'lblThongKeByStaffs' :"According To Staffs",
       'lblThongKeByDeparts' :"According To Departs",
       'lblAction' :"Action",
       'lblCodeDeparts' :"Code Departs",
       'lblNameDeparts' :"Name Departs",
       'lblCodeStaffs' :"Code Staffs",
       'lblNameStaffs' :"Name Staffs",
       'lblGenderStaffs' :"Gender",
       'lblAvatarStaffs' :"Avatar",
       'lblPhoneStaffs' :"Phone",
       'lblNoteStaffs' :"Note",
       'lblAddStaffs' :"Add Staffs",
       'lblAddDeparts' :"Add Departs",
       'lblDetail' :"Detail",
        'lblFilter' :"Filter Departs",
        'lblSeach':'Search',
        'lblDateOfBirthday':"Date Of Birthday",
        'lblSumScore':'Sum Point',
        'lblRecords':"Records",
        'lblTop10NhanVien':'Top Ten Staffs',
        'lblThongKeNhanVien':'Statistics by Employee',
        'lblThongKePhongBan':'Statistics by Departs'

    }
  }
  
	if($window.localStorage.getItem('token')==null)
	{
	$window.location.href='../Login';
	}else{
		$scope.logOut = function(){
	$window.localStorage.removeItem('token');	
		$window.location.href = "../Login"
	}	
	}
	$scope.changeLanguage =function(){
  $window.localStorage.setItem('language',$scope.language);
  if($scope.language =='vi'){
    $scope.listLanguage = {
      'lblDeparts':'Quản Lý Phòng Ban',
       'lblStaffs':'Quản Lý Nhân Viên',
       'lblThongKe' :"Thống Kê Thành Tích",
       'lblThongKeByStaffs' :"Theo Nhân Viên",
       'lblThongKeByDeparts' :"Theo Phòng Ban",
       'lblAction' :"Hành Động",
       'lblCodeDeparts' :"Mã Phòng Ban",
       'lblNameDeparts' :"Tên Phòng Ban",
       'lblCodeStaffs' :"Mã Nhân Viên",
       'lblNameStaffs' :"Tên Nhân Viên",
       'lblGenderStaffs' :"Giới Tính",
       'lblAvatarStaffs' :"Hình Ảnh",
       'lblPhoneStaffs' :"Số Điện Thoại",
       'lblNoteStaffs' :"Ghi Chú",
       'lblAddStaffs' :"Thêm Nhân Viên",
       'lblAddDeparts' :"Thêm Phòng Ban",
       'lblDetail' :"Chi Tiết",
        'lblFilter' :"Loc Phòng Ban",
        'lblSeach':'Tìm Kiếm',
        'lblDateOfBirthday':"Ngày Sinh",
        'lblSumScore':'Tổng Điểm',
        'lblRecords':"Ghi Nhận",
        'lblTop10NhanVien':'Tốp 10 Nhân Viên Có Thành Tích Cao Nhất',
        'lblThongKeNhanVien':'Thống Kê Thành Tích Nhân Viên',
        'lblThongKePhongBan':'Thống Kê Thành Tích Theo Phòng Ban'

    }
  }else{
       $scope.listLanguage = {
     'lblDeparts':'Departs Manager',
       'lblStaffs':'Staffs Manager',
       'lblThongKe' :"Statistical Achievement",
       'lblThongKeByStaffs' :"According To Staffs",
       'lblThongKeByDeparts' :"According To Departs",
       'lblAction' :"Action",
       'lblCodeDeparts' :"Code Departs",
       'lblNameDeparts' :"Name Departs",
       'lblCodeStaffs' :"Code Staffs",
       'lblNameStaffs' :"Name Staffs",
       'lblGenderStaffs' :"Gender",
       'lblAvatarStaffs' :"Avatar",
       'lblPhoneStaffs' :"Phone",
       'lblNoteStaffs' :"Note",
       'lblAddStaffs' :"Add Staffs",
       'lblAddDeparts' :"Add Departs",
       'lblDetail' :"Detail",
       'lblFilter' :"Filter Departs",
        'lblSeach':'Search',
        'lblDateOfBirthday':"Date Of Birthday",
        'lblSumScore':'Sum Point',
        'lblRecords':"Records",
        'lblTop10NhanVien':'Top Ten Staffs',
        'lblThongKeNhanVien':'Statistics by Employee',
        'lblThongKePhongBan':'Statistics by Departs'

    }
  }

  };
});

