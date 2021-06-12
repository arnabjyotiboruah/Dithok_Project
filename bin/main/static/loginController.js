var app=angular.module("loginApp",['ngCookies']);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller("loginController",function($scope,$window,$http)
{
    
    $scope.loginUser=function(user){
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/login',
                dataType: 'json',
                data: {
                    email:user.email,
                    password:user.password
                }
            }).then(function(response){//then1
                if (response.data.status==1){         
                    $http({
                        method: 'POST',
                        url: 'http://localhost:8080/api/checkSession',
                        dataType: 'json',
                        data: {
                            email:user.email,
                            sessionstatus:"login"
                        }   
                }).then(function(response)//then2
                    {
                        if(response.data)
                        {
                        $window.alert("login Successful!");
                        $window.location.href = '/index';              
                        console.log(response.data);
                        }
                        if(!response.data){
                        console.log("Session creation failed");
                        }
                    });//end of then2
                         
             }
             if(response.data.status!=1){

                var message=response.data.message
                $window.alert(message);
             }
            });//end of then1

            };
             
             $scope.logout = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/checkSession',
            dataType: 'json',
            data: {
                email: "deafult@domain.com",
                sessionstatus: "browsing"
            }

        }).then(function (response) {
            if (response.data) {
                $http({
                    method: 'POST',
                    url: 'http://localhost:8080/api/logoutSession',
                }).then(function (response) {
                    if(response.data.sessionInactiveInterval == -1){
                        window.alert("Error in logout");
                    }
                    else{
                        $window.location.href = '/';
                    }
                })
            }
        })
    }

    //logout session
    $scope.logout = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/checkSession',
            dataType: 'json',
            data: {
                email: "deafult@domain.com",
                sessionstatus: "browsing"
            }

        }).then(function (response) {
            if (response.data) {
                $http({
                    method: 'POST',
                    url: 'http://localhost:8080/api/logoutSession',
                }).then(function (response) {
                    if(response.data.sessionInactiveInterval == -1){
                        window.alert("Error in logout");
                    }
                    else{
                        $window.location.href = '/';
                    }
                })
            }
        })
    }

})