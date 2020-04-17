var app = angular.module('vacApp', []);
app.controller('loginCtrl', function ($scope, $http) {
    $scope.userName = "";
    $scope.password = "";

    $scope.login = function () {
        $http.post(
            "/login",
            {
                "userName": $scope.userName,
                "password": $scope.password
            }
        ).then(function (response) {
            if (response.data == true) {
                window.location = "/index.html";
            }
        })
    }

});