var app=angular.module("createGroupApp",[]);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);
app.controller("createGroup",function($scope,$window,$http)
{
	$scope.group={};
    $scope.saveGroup=function(group){
        $http({
          method: 'POST',
          url: 'http://localhost:8080/group/add',
          dataType: 'json',
          data: {name:group.name, description:group.description, 
            status:group.status, group:group.group
            }   
                }).then(function(response)
                    {
                        if(response.data)
                        {
                        $window.alert("data inserted!");
                         $window.location.href = '/createGroup'              
                        console.log(response.data);
                        }
                        if(!response.data){
                        console.log("group creation failed");
                        }
                    });
         
       
    }
});