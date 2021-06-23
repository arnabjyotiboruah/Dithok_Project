var app=angular.module("rulesApp",[]);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);
app.controller("rules",function($scope,$window,$http)
{
	$scope.rules={};
    $scope.saverules=function(rules){
        $http({
          method: 'POST',
          url: 'http://localhost:8080/rule/add',
          dataType: 'json',
          data: {execSeq:rules.execSeq
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