var app = angular.module('student', []);
var appName = "/Student-MVC.Winiarczyk-Ochnio";
app.controller('MainCtrl', function($scope,$http) {
	$scope.active="home";
	$scope.name = "Felipe";
	$scope.edytowanyStudent;
	$scope.studentForm = {};
	$scope.pokazStudentow=function(){
		$http.get(appName+'/studenci/lista').
                success(function(data) {
                $scope.studenci = data;
                console.log(data);
            });
        }
	$scope.usunStudenta=function(){
		$http.delete(appName+'/studenci/usunStudenta/'+$scope.edytowanyStudent.id).
		success(function(data) {
    	  alert(data);
    	  $scope.active="lista";
    	  $scope.pokazStudentow();
      });
	}
  
	$scope.edytujStudenta=function(index){
		$scope.active="edycja";
		$scope.edytowanyStudent = $scope.studenci[index];
	}
	
	$scope.dodajStudenta=function(){
		$http({
			method: "PUT",
			url: appName+"/studenci/dodajStudenta",
			data: $scope.studentForm,
		}).success(function(data){
			alert(data);
			$scope.studentForm = {};
		});
		
	}
});
