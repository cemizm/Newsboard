'use strict';

angular.module('nwbadmin.groups', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('groups', {
            url: '/groups',
            templateUrl: 'views/groups/index.html',
            controller: 'GroupViewController',
            data: {
                authenticate: true,
            }
        });


    }])
    .controller('GroupViewController',
        ['$scope', '$location', 'GroupSetService',
            function ($scope, $location, GroupSetService) {

                $scope.inProgress = false;

                $scope.update = function () {
                    GroupSetService.getAll().then(function (data) {
                        $scope.groups = data;
                        $scope.active = null;
                    });
                };

                $scope.select = function (group) {
                    $scope.active = angular.copy(group);
                };

                $scope.delete = function () {
                    if (!$scope.active) return;

                    GroupSetService.delete($scope.active.id).then(function (result) {
                        $scope.update();
                    });
                };

                $scope.save = function () {
                    if (!$scope.active) return;

                    $scope.inProgress = true;

                    var done = function (result) {
                        if (result)
                            $scope.update();

                        $scope.inProgress = false;
                    }

                    if (!$scope.active.id)
                        GroupSetService.create($scope.active).then(done);
                    else
                        GroupSetService.update($scope.active).then(done);
                };

                $scope.create = function () {
                    $scope.active = {
                        name: ""
                    };
                }

                $scope.update();
            }
        ]);