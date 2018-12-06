'use strict'
var loginApp = angular.module('appLogin',[]);
loginApp.controller('loginController', function($scope,$http,$rootScope,$window,$interval){
	$rootScope.linkApi =' http://localhost:8080/';
	if($window.localStorage.getItem("token")!=null){
		alert("Bạn đã đăng nhập từ trước. Vui lòng đăng xuất");
		$window.location.href="../Admin"

	}else{

	// 	function clearLocalStore(){
	// 	$window.localStorage.removeItem('token');
	// 	}
	// var clear = setInterval(clearLocalStore,1000*60*5);
		function setCookie(cname, cvalue, minus) {
	    var d = new Date();
	    d.setTime(d.getTime() + (minus*60*1000));
	    var expires = "expires="+ d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
		}
	$scope.logIn = function(){
		var url = $rootScope.linkApi+'User/checkLogin';
		if( $scope.userName == undefined ||  $scope.userName ==="" ||  $scope.userName ===null ){
			alert("Bạn chưa nhập tên đăng nhập ");
			$('.user').focus();
			return;
			
		}else if($scope.passWord == undefined ||  $scope.passWord ==="" ||  $scope.passWord ===null){
			alert("Bạn Chưa nhập PassWord");
			$('.Password').focus();
			return;
		}else{
			var user = $.param({
			userName:$scope.userName.trim(),
			passWord:$scope.passWord.trim()
			})
			var  config = {
				headers: {
						'Accept': 'text/plain'
						}
			}
		$http.get($rootScope.linkApi+'User/checkLogin?'+user,config).then(function(res){
			if(res.data==='error password'){
				alert("Sai mật khẩu vui lòng thử lại");
				 $scope.passWord="";
				$('.Password').focus();
			}else if(res.data==='error username'){
				alert("Tài khoản không tồn tại");
					$('.user').focus();

			}
			else{

				setCookie('token',res.data,5);

				$window.localStorage.setItem("token",res.data);
				//$http.defaults.headers.common.Authorization = 'Bearer ' +res.data;
				$window.location.href = "../Admin";
			}

		},function(error){

		});	

		}
	}	
	$(document).on("keypress", function(e){
    if(e.which == 13){
        $('#login').click();
    }
});	
	}
	
});
