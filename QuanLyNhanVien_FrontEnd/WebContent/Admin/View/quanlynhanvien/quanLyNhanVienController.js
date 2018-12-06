'use strict'
appAdmin.controller('quanlynhanviencontroller', function($scope ,quanlynhanvienservices, $rootScope, $window, $http){
	// if($window.localStorage.getItem('token')==null)
	// {
	// $window.location.href='../Login';
	// }else{
	 $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
	$scope.checkEdit = false;
	$scope.checkAdd = false;
	$scope.checkView = false;
	$scope.checkNote = false;
	$scope.changeStaffs = 10;
	$scope.showOnePage = 10;

	//LẤy Dữ Liệu Nhân Viên
	quanlynhanvienservices.getAboutStaffs($scope.showOnePage).then(function(res){
		$scope.listStaffs = res.data;
	},function(error){

	});
	quanlynhanvienservices.getAllDeparts().then(function(res){
		$scope.listDeparts = res.data;

	},function(error){

	});
	quanlynhanvienservices.sumpage($scope.showOnePage).then(function(res){
		$scope.sumPage = res.data;
	},function(error){

	});
	$scope.showChange = function(){
		quanlynhanvienservices.getAboutStaffs($scope.changeStaffs).then(function(res){
			$scope.listStaffs = res.data;
		quanlynhanvienservices.sumpage($scope.changeStaffs).then(function(res){
		$scope.sumPage = res.data;
	},function(error){

	});

		},function(error){

		})
	}
	$scope.redirectPage = function(index){
		var page = index*$scope.showOnePage;
		quanlynhanvienservices.redirectPage(page,$scope.showOnePage).then(function(res){
			$scope.listStaffs = res.data;
		})


	}
		$scope.boQua = function(){
				clear();
		}
	function clear(){
				$scope.tempStaffs = {
					    "id_staffs":null,
					    "departs": {
					      "id_departs": 1,
					      "name_departs":null
					    },
					    "name": null,
					    "gender": 1,
					    "birthday": null,
					    "photo": null,
					    "email": null,
					    "phone": null,
					    "salary": null,
					    "rank_staff": 1,
					    "note": null
					 	 }

			}
		$scope.showAddStaffs = function(){
			$scope.checkAdd = true;
			$scope.checkView = false;
			$scope.checkEdit = false;
			$scope.checkNote = false;
			clear();
		};
		$scope.showNote = function(list){
			$scope.checkNote = true;
			$scope.checkAdd = false;
			$scope.checkView = false;
			$scope.checkEdit = false;
			$scope.records.hinhthuc = 1;
			$scope.idNote = list.id_staffs;

	};
		$scope.clearForm =function(){
			$scope.tempStaffs.name = "";
			$scope.tempStaffs.gender=1;
			$scope.tempStaffs.birthday ="";
			$scope.tempStaffs.salary="";
			$scope.tempStaffs.departs.id_departs="";
			$scope.tempStaffs.rank_staffs = 1;
			$scope.tempStaffs.email = "";
			$scope.tempStaffs.phone = "";
			$scope.tempStaffs.note="";

		};
			$scope.showEditStaffs = function(list){
			$scope.checkEdit = true;
			$scope.checkAdd = false;
			$scope.checkView = false;
			$scope.checkNote = false;
			$scope.listTemp = list;
			$scope.avatar = list.photo;
			$scope.tempStaffs = {
								    "id_staffs": list.id_staffs,
								    "departs": {
								      "id_departs": list.departs.id_departs,
								      "name_departs":list.departs.name_departs
								    },
								    "name": list.name,
								    "gender": list.gender,
								    "birthday": list.birthday,
								    "photo": list.photo,
								    "email": list.email,
								    "phone": list.phone,
								    "salary": list.salary,
								    "rank_staff": list.rank_staff,
								    "note": list.note
					 			 }
		};
		$scope.showViewStaffs = function(list){
			$scope.checkView = true;
			$scope.checkEdit = false;
			$scope.checkAdd = false;
			$scope.checkNote = false;
			$scope.tempStaffs = {
									    "id_staffs": list.id_staffs,
									    "departs": {
									      "id_departs": list.departs.id_departs,
									      "name_departs":list.departs.name_departs
									    			},
									    "name": list.name,
									    "gender": list.gender,
									    "birthday": list.birthday,
									    "photo": list.photo,
									    "email": list.email,
									    "phone": list.phone,
									    "salary": list.salary,
									    "rank_staff": list.rank_staff,
									    "note": list.note
					  			}
		};
		$scope.deleteStaffs = function(list,index){
			if(confirm("Bạn có muốn xóa nhân viên không ?")){
				quanlynhanvienservices.deleteStaffs(list.id_staffs).then(function(res){
					console.log(res.data);
					$scope.listStaffs.splice(index,1);
					quanlynhanvienservices.sumpage($scope.showOnePage).then(function(res){
						$scope.sumPage = res.data;
					},function(error){

					});
				},function(error){
					console.log(error);

				})
			}else{

			}
		};
		$scope.noteStaffs = function(){
			var date = new Date();
			var dateStr = date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear()+" "+date.getHours()+":"+date.getMinutes();
			quanlynhanvienservices.staffsNote($scope.idNote,$scope.records.hinhthuc,$scope.records.reason,dateStr).then(function(res){
				$scope.records.reason ='';
				$("#nhanvien").modal("toggle");

			},function(error){
				console.log(error);
			});
			
		}
		$scope.addStaffs = function(){
				  $scope.Staffs ={
							  "birthday": $scope.tempStaffs.birthday,
							  "departs": {
							    "id_departs": $scope.tempStaffs.departs.id_departs,
							    "name_departs": $scope.tempStaffs.departs.name_departs
							  },
							  "email": $scope.tempStaffs.email,
							  "gender":$scope.tempStaffs.gender,
							  "id_staffs": 0,
							  "name":  $scope.tempStaffs.name,
							  "note":  $scope.tempStaffs.note,
							  "phone":  $scope.tempStaffs.phone,
							  "photo": $scope.tempStaffs.photo.name,
							  "rank_staff":$scope.tempStaffs.rank_staffs,
							  "salary": $scope.tempStaffs.salary
							}

		               var config =  {
			                'Accept': 'text/html',
			           		'Content-Type': 'application/json'
		            		}
					quanlynhanvienservices.addStaffs1($scope.Staffs).then(function(res){
					console.log(res.data);
					if(res.data === -1){
						alert("Thêm Nhân Viên Thất Bại");
					}else{
							$scope.Staffs.id_staffs=res.data;
							quanlynhanvienservices.uploadAvatar($scope.tempStaffs.photo).then(function(res){
									$scope.listStaffs.push($scope.Staffs);
									$('#nhanvien').modal('toggle');

						},function(error){
							console.log(error)

						});
					}

				},function(error){
					console.log(error);
				})


		};
		$scope.updateStaffs = function(){
			if($scope.tempStaffs.photo.name == null || $scope.tempStaffs.photo.name ==='' || $scope.tempStaffs.photo.name == undefined){
							var dataStaffs = {
										"id_staffs": $scope.tempStaffs.id_staffs,
									    "departs": {
									      "id_departs":$scope.tempStaffs.departs.id_departs,
									      "name_departs":$scope.tempStaffs.departs.name_departs
									    			},
									    "name":$scope.tempStaffs.name,
									    "gender": $scope.tempStaffs.gender,
									    "birthday": $scope.tempStaffs.birthday,
									    "photo": $scope.avatar,
									    "email": $scope.tempStaffs.email,
									    "phone": $scope.tempStaffs.phone,
									    "salary": $scope.tempStaffs.salary,
									    "rank_staff": $scope.tempStaffs.rank_staff,
									    "note": $scope.tempStaffs.note
						};
			quanlynhanvienservices.updateStaff($scope.tempStaffs.id_staffs,dataStaffs).then(function(res){
				$scope.listTemp.id_staffs = $scope.tempStaffs.id_staffs;
				$scope.listTemp.departs.id_departs = $scope.tempStaffs.departs.id_departs;
				$scope.listTemp.departs.name_departs = $scope.tempStaffs.departs.name_departs;
				$scope.listTemp.name = $scope.tempStaffs.name;
				$scope.listTemp.gender = $scope.tempStaffs.gender;
				$scope.listTemp.birthday = $scope.tempStaffs.birthday;
				$scope.listTemp.photo = $scope.tempStaffs.photo;
				$scope.listTemp.email = $scope.tempStaffs.email;
				$scope.listTemp.phone = $scope.tempStaffs.phone;
				$scope.listTemp.salary = $scope.tempStaffs.salary;
				$scope.listTemp.rank_staff = $scope.tempStaffs.rank_staff;
				$scope.listTemp.note = $scope.tempStaffs.note;
				$scope.tempStaffs = $scope.listTemp;
				$("#nhanvien").modal("toggle");
//				quanlynhanvienservices.getListStaffs();
			},function(error){
				console.log(error);
			});

			}else{
					var dataStaffs = {
							"id_staffs": $scope.tempStaffs.id_staffs,
							"departs": {
						  	"id_departs":$scope.tempStaffs.departs.id_departs,
					   		"name_departs":$scope.tempStaffs.departs.name_departs
					 			},
							"name":$scope.tempStaffs.name,
							"gender": $scope.tempStaffs.gender,
							"birthday": $scope.tempStaffs.birthday,
							"photo": $scope.tempStaffs.photo.name,
							"email": $scope.tempStaffs.email,
							 "phone": $scope.tempStaffs.phone,
							"salary": $scope.tempStaffs.salary,
						 	"rank_staff": $scope.tempStaffs.rank_staff,
							"note": $scope.tempStaffs.note
							};
			quanlynhanvienservices.updateStaff($scope.tempStaffs.id_staffs,dataStaffs).then(function(res){
				quanlynhanvienservices.uploadAvatar($scope.tempStaffs.photo).then(function(res){
					$scope.listTemp.id_staffs = $scope.tempStaffs.id_staffs;
					$scope.listTemp.departs.id_departs = $scope.tempStaffs.departs.id_departs;
					$scope.listTemp.departs.name_departs = $scope.tempStaffs.departs.name_departs;
					$scope.listTemp.name = $scope.tempStaffs.name;
					$scope.listTemp.gender = $scope.tempStaffs.gender;
					$scope.listTemp.birthday = $scope.tempStaffs.birthday;
					$scope.listTemp.photo = res.data;
					$scope.listTemp.email = $scope.tempStaffs.email;
					$scope.listTemp.phone = $scope.tempStaffs.phone;
					$scope.listTemp.salary = $scope.tempStaffs.salary;
					$scope.listTemp.rank_staff = $scope.tempStaffs.rank_staff;
					$scope.listTemp.note = $scope.tempStaffs.note;
					$scope.tempStaffs = $scope.listTemp;
				$("#nhanvien").modal("toggle");
//				quanlynhanvienservices.getListStaffs()
				},function(error){

				})
				;
			},function(error){
				console.log(error);
			});

			}

		};

	// }
});
