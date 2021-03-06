var app=angular.module("alluserDetailsApp",[]);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller("userInfoController",function($scope,$http,$window)
{
  $scope.getUserDetails=function()
  {
    $http({
        method:'POST',
        url:'http://localhost:8080/api/checkSession',
        dataType:'json',
        data:{
            email:'deafualt@domain.com',
            status:'browsing'
        }
    }).then(function(response)
    {
        if(response.data)
        {
            $http({

                method:'GET',
                url:'http://localhost:8080/api/getAlluserdetails'
            }).then(function(response)
            {
                if(response.data)
                {
                    $scope.IsVisible=false;
                    $scope.hidebutton=true;
                    $scope.showbutton=false;
                    $scope.userDetails=response.data;
                    $scope.IsVisible=true;
                     
                
                    $scope.hideDetails=function()
                    {
                        $scope.IsVisible=false;
                        $scope.hidebutton=false;
                        $scope.showbutton=true;
                    }

                    $scope.showDetails=function()
                    {
                        $scope.IsVisible=true;
                        $scope.hidebutton=true;
                        $scope.showbutton=false;
                    }
                }

            })

        }
        else
        {
            $window.location.href='/allUserDetails';
        }
    });
  }

  $scope.getFullDetails = function(phone){
    $window.location.href='/editUser?phone='+phone;
  }

});