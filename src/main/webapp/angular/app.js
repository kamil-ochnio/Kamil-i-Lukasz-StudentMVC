var app = angular.module('student', []);
var appName = "/Student-MVC.Winiarczyk-Ochnio";
app.controller('MainCtrl', function($scope,$http) {
	$scope.active="home";
	$scope.aktywnaListaPrzedmiotow=0;
	$scope.name = "Felipe";
	$scope.edytowanyStudent;
	$scope.ocenaDoDodania="";
	$scope.studentForm = {};
	$scope.ocenyStudenta = {};
	$scope.pokazStudentow=function(){
		$http.get(appName+'/studenci/lista').
                success(function(data) {
                $scope.studenci = data;
                console.log(data);
            });
        }
	
	$scope.pokazPrzedmioty=function(){
		//$scope.aktywnaListaPrzedmiotow=1;
		$http.get(appName+'/studenci/przedmioty').
                success(function(data) {
                	
                $scope.przedmioty = data;
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
		$scope.pobierzOcenyStudenta();
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
	
	$scope.wystawOcene=function(){
		$scope.pokazPrzedmioty();
		$scope.active="wystawOcene";
	}
	
	$scope.dodajOcene=function(index){
		var przedmiot = $scope.przedmioty[index].nazwa;
		$http({
			method: "PUT",
			url: appName+"/studenci/dodajOcene/student/"+$scope.edytowanyStudent.id+"/przedmiot/"+przedmiot,
			data: $scope.ocenaDoDodania,
		}).success(function(data){
			alert(data);
			$scope.ocenaDoDodania="";
		});
	}
	
	$scope.wrocDoEdycji=function(){
		$scope.pobierzOcenyStudenta();
		$scope.active="edycja";
	}
	
	$scope.pobierzOcenyStudenta=function(){
		$scope.ocenyStudenta = {};
		$http({
			method: "GET",
			url: appName+"/studenci/pobierzOcenyStudenta/"+$scope.edytowanyStudent.id,
		}).success(function(data){
			$scope.ocenyStudenta = data;
		});
	}	
});
