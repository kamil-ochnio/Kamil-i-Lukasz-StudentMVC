var app = angular.module('student', []);
var appName = "/Student-MVC.Winiarczyk-Ochnio";
app.controller('MainCtrl', function($scope,$http) {
  $scope.name = "Felipe";
  $scope.pokazStudentow=function(){
	$http.get(appName+'/studenci/lista').
                success(function(data) {
                $scope.studenci = data;
                console.log(data);
            });
        }
  $scope.usunStudenta=function(index){
	  $http.get(appName+'/studenci/usunStudenta/'+$scope.studenci[index].id).
      success(function() {
    	  $scope.pokazStudentow();
      });
  }
});
