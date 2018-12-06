 'use strict'
appAdmin.controller('thongketheonhanviencontroller', function($scope ,thongketheonhanvienServices, $rootScope, $window, $http){
    // if($window.localStorage.getItem('token')==null) {
    // $window.location.href='../Login';
    // }else{
    $http.defaults.headers.common.Authorization = 'Bearer '+$window.localStorage.getItem("token");
    $scope.changeRecodrs = 10;
    $scope.showOnePage  = 10;
    thongketheonhanvienServices.thongketheonhanvien(0,$scope.showOnePage).then(
        function(res){
         $scope.listThongkeNhanVien = res.data;
            },function(error){
                  console.log(error);
        });
    $scope.showRecords = function(){
         thongketheonhanvienServices.showReocrds($scope.changeRecodrs).then(function(res){
            $scope.listThongkeNhanVien = res.data;
        thongketheonhanvienServices.countStaffs($scope.changeRecodrs).then(function(res){
             $scope.sumPage = res.data;
            },function(error){

            });
         },function(error){
              console.log(error);
         });


        }
        $scope.redirectPage = function(index){
            $scope.first = (index)* $scope.showOnePage;
            thongketheonhanvienServices.pagination($scope.first,$scope.showOnePage).then(
                function(res){
                    $scope.listThongkeNhanVien = res.data;
                }
                , function(error){
                    console.log(error);
                });
        }
        // lấy tổng số trang
    thongketheonhanvienServices.countStaffs($scope.showOnePage).then(function(res){
             $scope.sumPage = res.data;
        },function(error){

        });

 //   }
    $scope.showDetail = function(nv){
        $scope.emailNv = nv[6];
        $scope.id_staffs = nv[0];
        thongketheonhanvienServices.showReocrdsById(nv[0]).then(function(res){
            $scope.recordsDetail = res.data;
        },function(error){

        });
    };
    $scope.senMailToStaffs = function(){
        if(confirm('Ban có muốn gửi mail thông báo thành tích cho nhân viên ?')){
                    thongketheonhanvienServices.sendMail($scope.emailNv,$scope.id_staffs).then(function(res){
                         if(res.data ==='Thành Công'){
                            alert('Gửi thành công');
                            $('#recordsDetail').modal('toggle');
                         }else{
                            alert('Gửi thất bại');
                         }
        },function(error){
            console.log(error)

        });
        }

    }
});





    // function sampleData() {
    //                 var data = [];
    //             $http.get("http://localhost:8080/departs").then(function(res){
    //                 data=res.data;

    //             },function(error){

    //             })
    //             // for (var i = 0; i < 50; i++) {
    //             //     var today = new Date();
    //             //     today.setDate(today.getDate() + i);

    //             //     data.push({
    //             //         OrderID: i,
    //             //         Freight: i * 10,
    //             //         OrderDate: today,
    //             //         ShipName: "ShipName " + i,
    //             //         ShipCity: "ShipCity " + i
    //             //     });
    //             // }

    //             return data;
    //         }

        
    //             // $("#responsive-panel").kendoResponsivePanel({
    //             //     breakpoint: 768,
    //             //     autoClose: false,
    //             //     orientation: "top"
    //             // });

    //             // $("#menu").kendoMenu();
    //             // $(".textButton").kendoButton();

    //             $("#grid").kendoGrid({
    //                 columns: [{
    //                     title: "ID Phòng Ban",
    //                     field: "idDeparts",
    //                     filterable: true,
    //                     encoded:true
    //                 }, {
    //                     title: "Tên Phòng Ban",
    //                     field: "nameDeparts",
    //                     encoded:true
    //                  }
    //                 // , {
    //                 //     title: "Order Date",
    //                 //     field: "OrderDate",
    //                 //     format: "{0:MM/dd/yyyy}",
    //                 //     encoded:true
    //                 // }, {
    //                 //     title: "Ship Name",
    //                 //     field: "ShipName",
    //                 //     encoded:true
    //                 // }, {
    //                 //     title: "Ship City",
    //                 //     field: "ShipCity",
    //                 //     encoded:true
    //                 // }
    //                 ],
    //                 pageable: {
    //                     buttonCount: 10
    //                 },
    //                 sortable: true,
    //                 filterable:true,
    //                 messages: {
    //                     noRecords: "No records available."
    //                 },
    //                 dataSource:{
    //                     data: sampleData(),
    //                     pageSize: 20,
    //                     schema: {
    //                         model: {
    //                             fields: {
    //                                 idDeparts: {
    //                                     type: "number"
    //                                 },
    //                                 nameDeparts: {
    //                                     type: "string"
    //                                 },
    //                                 // ContactName: {
    //                                 //     type: "string"
    //                                 // },
    //                                 // Freight: {
    //                                 //     type: "number",
    //                                 //     defaultValue: null
    //                                 // },
    //                                 // ShipAddress: {
    //                                 //     type: "string"
    //                                 // },
    //                                 // OrderDate: {
    //                                 //     type: "date",
    //                                 //     defaultValue: null
    //                                 // },
    //                                 // ShippedDate: {
    //                                 //     type: "date",
    //                                 //     defaultValue: null
    //                                 // },
    //                                 // ShipCountry: {
    //                                 //     type: "string"
    //                                 // },
    //                                 // ShipCity: {
    //                                 //     type: "string"
    //                                 // },
    //                                 // ShipName: {
    //                                 //     type: "string"
    //                                 // },
    //                                 // EmployeeID: {
    //                                 //     type: "number",
    //                                 //     defaultValue: null
    //                                 // }
    //                             }
    //                         }
    //                     }
    //                 }
    //             });


  