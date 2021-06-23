var app=angular.module("groupPolicyApp",[]);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);
app.controller("groupPolicy",function($scope,$window,$http)
{
	$scope.policy={};
    $scope.savePolicy=function(policy){
        $http({
          method: 'POST',
          url: 'http://localhost:8080/policy/add',
          dataType: 'json',
          data: {description:policy.description, execSeq:policy.execSeq, name:policy.name, status:policy.status
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