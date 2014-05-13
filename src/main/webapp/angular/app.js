var app = angular.module('student', []);

app.controller('MainCtrl', function($scope,$http) {
  $scope.name = "Felipe";
  $scope.pokazStudentow=function(){
	$http.get('/AngularSpringApp/studenci/lista').
                success(function(data) {
                $scope.studenci = data;
                console.log(data);
            });
        }
  $scope.usunStudenta=function(index){
	  alert($scope.studenci[index].id);
	  $http.get('/AngularSpringApp/studenci/usunStudenta/'+$scope.studenci[index].id).
      success(function() {
    	  $scope.pokazStudentow();
      });
  }
});
