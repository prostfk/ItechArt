angular.module("myApp", [])
    .controller('MainController', function ($scope) {
        $scope.current = '';

        $scope.tasks = [
            "first",
            "second"
        ];

        $scope.addNewTask = function () {
            $scope.tasks.push($scope.current);
            $scope.current = '';
        }

        $scope.deleteTask = function (item) {
            let index = $scope.tasks.indexOf(item);
            $scope.tasks.splice(index, 1)
        }

    })
;