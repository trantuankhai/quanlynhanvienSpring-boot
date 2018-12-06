'use strict'
angular.module('managerStaffServices',[]).factory('quanlynhanvienservices',['$http','$rootScope', function($http,$rootScope){
	var services = {
					getListStaffs : getListStaffs,
					addStaffs1 : addStaffs1,
					updateStaff : updateStaff,
					deleteStaffs : deleteStaffs,
					sumpage : sumpage,
			        redirectPage:redirectPage,
			        getAboutStaffs : getAboutStaffs,
			        getAllDeparts : getAllDeparts,
			        uploadAvatar : uploadAvatar,
			        staffsNote:staffsNote
    				}
    return services;

		function getListStaffs(){
			var url = $rootScope.linkApi+"staffs";
			return $http.get(url);
				
			};
		function getAllDeparts(){
        var url = $rootScope.linkApi+"departs";
        return $http.get(url);
    	};

		function getAboutStaffs(sl){
			var url = $rootScope.linkApi + "staffs/About/"+sl
			return $http.get(url);
		};
		function addStaffs1(dataStaffs){
			var req = {
                method: 'POST',
                url :$rootScope.linkApi+"staffs",
            	data : dataStaffs	
            }
			
			return $http(req);
				
			};
			function staffsNote(id,type,reason,date){
				var data =  $.param({
					manv:id,
					type:type,
					reason:reason,
					date:date

				});
			var url =$rootScope.linkApi+"records?"+data;	
			           	return  $http.post(url,data,{});
				}

			function uploadAvatar(dataAvatar){
			var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
            	'Accept': 'text/plain',
                'Content-Type': undefined
            }
      		 }
        	var url =$rootScope.linkApi+"avatar";
         	var data = new FormData();
          	data.append("multipartFile", dataAvatar);
			return $http.post(url,data,config);

			}

		function updateStaff(id_staffs,dataStaffs){
				 var req = {
                method: 'PUT',
                url:$rootScope.linkApi+'staffs/'+id_staffs,
                data: dataStaffs,
               headers: {
                'Accept': 'text/html',
                'Content-Type': 'application/json'
          	  }
            }
			 return $http(req);	
			};

		function deleteStaffs(id_staffs){
			    var req = {
                method: 'DELETE',
                url:$rootScope.linkApi+'staffs/'+id_staffs,
                data:null,
               headers: {
                'Accept': 'text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8',
                'Content-Type': 'application/json'
            }
            }
        return $http(req);

				
			};

		function sumpage(showOnePage){
			return $http.get($rootScope.linkApi+"staffs/sumPage/"+showOnePage)
				
			};


		function redirectPage(page,show){
			return $http.get($rootScope.linkApi+"staffs/navigationPage/"+page+"/"+show);
				
			};
}])