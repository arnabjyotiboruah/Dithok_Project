var app = angular.module('searchUsersApp', []);

app.factory('myFactory', function () {
    var obj = {};
    obj.message = '';
    obj.userData = {};
    obj.setMessage = function (newMessage) {
        obj.message = newMessage;
    };
    return obj;
});


app.controller('searchUsersCtrl', function ($scope, $http, myFactory) {
    $scope.userData = {}; //TODO: it will contain nested object
    $scope.editUserRespData = {};

    $scope.noUserData = function () {
        return Object.keys($scope.userData).length === 0;
    }
    $scope.searchByEmail = function () {

        //checking if the session is live
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/checkSession',
            dataType: 'json',
            data: {
                email: 'deafualt@domain.com',
                status: 'browsing'
            }
        }).then(function (response) {

            if (response.data) {
                $http.post(
                    'http://localhost:8080/api/searchuser',
                    {
                        "userId": $scope.email
                    }
                ).then(function (response) {
                    $scope.userData = response.data;
                    console.log(response);
                    if (response.data != ""){
                        $scope.userFound = true;
                    }
                    else{
                        $scope.userFound = false;
                        window.alert("Not Found");
                    }
                },
                    function (response) {
                        $scope.msg = response.statusText;
                        console.log(response.statusText);
                        $scope.userFound = false;
                    }
                );

            }
            else { $window.location.href = '/searchEditUser'; }
        })
    };





    $scope.editUser = function () {

        //checking if the session is live
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/checkSession',
            dataType: 'json',
            data: {
                email: 'deafualt@domain.com',
                status: 'browsing'
            }
        }).then(function (response) {
            if (response.data) {

                // tranforming User Data to encodedURI format for http request
                var arr = [];
                for (item in $scope.userData) {
                    arr.push(encodeURIComponent(item) + "=" + encodeURIComponent($scope.userData[item]));
                    if (angular.isObject($scope.userData[item])) {
                        arr.pop();
                        for (subitem in $scope.userData[item]) {
                            var key = subitem;
                            if (subitem == 'addressLine1') key = 'house_number';
                            if (subitem == 'addressLine2') key = 'street';
                            if (subitem == 'zipCode') key = 'zip_code';

                            arr.push(encodeURIComponent(key) + "=" + encodeURIComponent($scope.userData[item][subitem]));
                        }
                    }
                }
                //
                var strData = arr.join("&");
                console.log(strData);

                // making the request to uodate the user details in the DB
                $http({
                    method: 'POST',
                    url: 'http://localhost:8080/api/edituser',
                    headers: {
                        "Accept": "application/json",
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    data: strData,
                }).then(sendEmail);


            } else {
                $window.location.href = '/searchEditUser';
            }


        });

    }

    function sendEmail(editUserResponse){

        // console.log(editUserResponse);
        // console.log($scope.userData.email);
        console.log(editUserResponse.data.status);
        if (editUserResponse.data.status == 1) {
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/sendemail',
                headers: {
                    "Accept": "application/json"
                },
                data: {
                    email: $scope.userData.email,
                    subject: "User details changed!",
                    body: "Hello User! Your Profile information has been updated."
                }
            }).then(sendSms);
        }
    }




    function sendSms(emailResponse) {
        // console.log(emailResponse);
        //window.alert(editUserResponse.data.message);
        //Sending SMS to the user about there updates using api
        console.log(emailResponse.data.status);

        if (emailResponse.data.status == 1) {
            $http({
                method: 'POST',
                url: 'http://localhost:8080/api/sendsms',
                headers: {
                    "Accept": "application/json"
                },
                data: $scope.userData.phoneNumber
            }).then(getSmsHistory);
        }

    }

    function getSmsHistory(sendSmsResponse) {
        // console.log(sendSmsResponse);
        // getting the sms report history from the service provider
        console.log(sendSmsResponse.data.status);
        if (sendSmsResponse.data.status == "success"){	
            $http({	
                method: 'POST',	
                url: 'http://localhost:8080/api/smshistory',	
                headers: {	
                    "Accept": "application/json"	
                },	
            }).then(updateSmsTable);	
        }	
        else{	
            window.alert("SMS not sent to that Number")	
        }
    }

    function updateSmsTable(smsHistoryResponse) {
        console.log(smsHistoryResponse);

        // Updating the sms table with delivered sms report
        if (smsHistoryResponse.data.status == "success") {
            for (msgKey in smsHistoryResponse.data.messages) {
                var saveSmsTempData = {
                    smsId: smsHistoryResponse.data.messages[msgKey].id,
                    phoneNumber: smsHistoryResponse.data.messages[msgKey].number,
                    deliveredAt: smsHistoryResponse.data.messages[msgKey].datetime,
                    sender: smsHistoryResponse.data.messages[msgKey].sender,
                    status: smsHistoryResponse.data.messages[msgKey].status,
                    content: smsHistoryResponse.data.messages[msgKey].content
                };
                var str = [];
                for (var key in saveSmsTempData)
                    str.push(encodeURIComponent(key) + "=" + encodeURIComponent(saveSmsTempData[key]));
                var saveSmsDataStr = str.join("&");
                $http({
                    method: 'POST',
                    url: 'http://localhost:8080/api/savesmshistory',
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Accept": "application/json"
                    },
                    data: saveSmsDataStr

                }).then(function (saveSmsHistoryResponse) {
                    console.log(saveSmsHistoryResponse);

                });

            }
            window.alert("Successfully updated! An email and an SMS is sent to the user's email and mobile number");
        }

    }



});//controller

