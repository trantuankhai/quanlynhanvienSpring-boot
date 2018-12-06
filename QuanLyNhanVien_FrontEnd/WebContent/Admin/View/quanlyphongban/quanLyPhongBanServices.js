'use strict'
angular.module('managerDepartsServices', []).factory('quanLyPhongBanServices', ["$http", "$rootScope", function($http, $rootScope) {
    var service = {
            getTenDeparts : getTenDeparts,
            addDeparts:addDeparts,
            sumPage:sumPage,
            updateDeparts:updateDeparts,
            deleteDeparts:deleteDeparts,
            redirectPage:redirectPage,
            countStaffsInDeparts : countStaffsInDeparts
            };
        return service;
    function getTenDeparts(soluong) {
            var link = $.param({
                    First : 0,
                    End : soluong
                });
          var url = $rootScope.linkApi+"departs/page?"+link;
        return $http.get(url);
    }
     function addDeparts(departs) {
        return $http.post($rootScope.linkApi+'departs',departs,{});
    }
    function sumPage(showOnePage){
        var url = $rootScope.linkApi+"departs/sumPage/"+showOnePage;
        return $http.get(url);
    }

    function updateDeparts(id_departs,name_departs,dataUpdate){
        var req = {
                method: 'PUT',
                url:$rootScope.linkApi+'departs/'+id_departs+"?name="+name_departs,
                data: {
                    user: dataUpdate
                },
               headers: {
                'Accept': 'text/plain',
                'Content-Type': 'application/json'
            }
            }
        return $http(req)
    }
    function deleteDeparts(id_departs,dataDelete){
                var req = {
                method: 'DELETE',
                url:$rootScope.linkApi+'departs/'+id_departs,
                data: {
                    user: dataDelete
                },
               headers: {
                'Accept': 'text/plain',
                'Content-Type': 'application/json'
            }
            }
        return $http(req);
    }
    function redirectPage(page,showOnePage){
                var link = $.param({
                    First : page,
                    End : showOnePage
                });
          var url = $rootScope.linkApi+"departs/page/?"+link;
        return $http.get(url);

    }
    function countStaffsInDeparts(id){
        var url  = $rootScope.linkApi+"departs/countStaffs/"+id;
        return $http.get(url);

    }

}]);