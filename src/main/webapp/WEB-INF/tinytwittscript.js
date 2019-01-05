var app = angular.module('TinyTwitt', []);
app.controller('TController',['$scope','$window', function($scope,$window) {

    $scope.author = '';
    $scope.log = false;
    $scope.userlogin ='';
    $scope.usermail ='';
    $scope.userpw ='';
    $scope.userfirstname='';
    $scope.userlastname ='';


    $scope.inscription = function(){
        console.log("inscription passe? avant if v3");
        if($scope.log == false){
            console.log("inscription passe? apres if v3");
            console.log(gapi.client);
            gapi.client.tinyTwittAPI.createUser({
                login: $scope.userlogin,
                email: $scope.usermail,
                password: $scope.userpw,
                firstname: $scope.userfirstname,
                lastname: $scope.userlastname
            }).execute(function(resp){
                console.log(resp);
                $scope.author = $scope.userlogin;
                $scope.log = true;
                console.log($scope.author);
                console.log(" is connected");
            });
        }
    }

    $window.init = function() {
        /**console.log("windowinit called");
         gapi.client.init({'apiKey' : 'AIzaSyDp2oR6i7uEZFmTLIxhOBDygL4q_8jjMk0',
             'discoveryDocs': ['https://www.googleapis.com/discovery/v1/apis/translate/v2/rest'],});
         **/
        console.log("windowinit called");
        var rootApi = 'https://tiny-twitt-project-224200.appspot.com/_ah/api/';
        console.log("connexion marche?");
        gapi.client.load('tinytwittAPI', 'v1', function() {
            console.log("twitter api loaded");
            $scope.log = false;
        }, rootApi);
    }
}
]);