'use strict';

angular.module('nwbadmin.analyzer', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('analyzer', {
            url: '/analyzer',
            templateUrl: 'views/analyzer/index.html',
            controller: 'AnalyzerViewController'
        });


    }])
    .controller('AnalyzerViewController',
        ['$scope', '$location', 'AnalyzerService', 'GroupSetService', 'TokenService',
            function ($scope, $location, AnalyzerService, GroupSetService, TokenService) {

                $scope.update = function () {
                    GroupSetService.getAll().then(function(data){
                        $scope.groups = data;
                    });
                    AnalyzerService.getAll().then(function (data) {
                        $scope.analyzers = data;
                        $scope.active = null;
                    });
                };

                $scope.select = function (analyzer) {
                    $scope.active = angular.copy(analyzer);
                };

                $scope.delete = function () {
                    if (!$scope.active) return;

                    AnalyzerService.delete($scope.active.id).then(function (result) {
                        $scope.update();
                    });
                };

                $scope.save = function () {
                    if (!$scope.active) return;

                    if (!$scope.active.id) {
                        AnalyzerService.create($scope.active).then(function (result) {
                            $scope.update();
                        });

                    }
                    else {
                        AnalyzerService.update($scope.active).then(function (result) {
                            $scope.update();
                        });
                    }
                };

                $scope.create = function () {
                    $scope.active = {
                        disabled: false,
                        groups: [],
                        name: "",
                        token: TokenService.generate()
                    };
                };

                $scope.toggle = function(group) {
                    var idx = $scope.active.groups.indexOf(group);

                    if (idx > -1) {
                        $scope.active.groups.splice(idx, 1);
                    }
                    else {
                        $scope.active.groups.push(group);
                    }
                }

                $scope.update();
            }
        ]);