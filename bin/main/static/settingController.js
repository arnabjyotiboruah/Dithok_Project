var app=angular.module("settingApp",[]);

app.config(['$qProvider', function($qProvider){
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller("settingController",function($scope,$http,$timeout,$window)
{
    $scope.setSessionTimeout=function(timeOutMinute)
    {
        $http({
            method:'POST',
            url: 'http://localhost:8080/api/checkSession',
            dataType: 'json',
            data:{
                email:"deafult@domain.com",
                sessionstatus:"browsing"
            }   

        }).then(function(response)
        {
             if(response.data)
             {
                $http({
                    method:'POST',
                    url:'http://localhost:8080/api/setSessionTimeout',
                    dataType:'application/json',
                    data:{
                        sessionInactiveInterval:timeOutMinute
                        
                    }
                }).then(function(response)
                {
                    if(response.data)
                    {
                        if(response.data.sessionInactiveInterval==0)
                        {
                            $scope.responseMessage=response.data.message + " to";
                            $scope.responseTime=response.data.sessionInactiveInterval + " minute";
                            $scope.msg="You'll be log out After 5 seconds!"                         
                            $timeout(function(){ $window.location.href = '/'; }, 5000);
                        }
                        else
                        {
                            $scope.responseMessage=response.data.message + " to";
                            $scope.responseTime=response.data.sessionInactiveInterval + " minute";
                        }
                        
                    }                   
                    else
                    {
                        $scope.responseMessage="Session Timeout setting failed!!";
                        $scope.responseTime=null;              
                    }
                    
                    
                });
            }
            else
            {
                $window.location.href='/settings';
            }
        });
    }
             
});