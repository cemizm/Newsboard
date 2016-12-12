'use strict';

angular.module('nwbadmin.views', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('views', {
            url: '/views',
            templateUrl: 'views/views/index.html',
            controller: 'ViewsViewController',
            data: {
                authenticate: true,
            }
        });
        $stateProvider.state('editview', {
            url: '/editviews',
            templateUrl: 'views/views/edit.html',
            controller: 'ViewsEditController'
        });

    }])
    /*.controller('ViewsViewController',
        ['$scope', '$location',
            function ($scope, $location) {

            }]);*/
.controller('ViewsViewController',
    ['$scope', '$location', 'ViewService', 'GroupSetService',
        function ($scope, $location, ViewService, GroupSetService) {

            $scope.inProgress = false;

            $scope.update = function () {
                GroupSetService.getAll().then(function (data) {
                    $scope.groups = data;
                });
                ViewService.getAll().then(function (data) {
                    $scope.views = data;
                    $scope.active = null;
                });
            };

            $scope.select = function (view) {
                $scope.active = angular.copy(view);
            };

            $scope.delete = function () {
                if (!$scope.active) return;

                ViewService.delete($scope.active.id).then(function (result) {
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
                    ViewService.create($scope.active).then(done);
                else
                    ViewService.update($scope.active).then(done);

            };



            /* $scope.toggle = function(group) {
             var idx = $scope.active.groups.indexOf(group);

             if (idx > -1) {
             $scope.active.groups.splice(idx, 1);
             }
             else {
             $scope.active.groups.push(group);
             }
             }

             $scope.update();
             }*/
        }])
.controller('ViewsEditController',
    ['$scope', '$location','ViewService', 'GroupSetService', 'CrawlerService',
        function ($scope, $location, ViewService, GroupSetService, CrawlerService) {
            $scope.update = function () {
                GroupSetService.getAll().then(function (data) {
                    $scope.groups = data;
                });
                ViewService.getAll().then(function (data) {
                    $scope.views = data;
                    $scope.active = null;
                });
                CrawlerService.getAll().then(function (data) {
                    $scope.crawlers = data;
                    $scope.active = null;
                })
            };
            $scope.create = function () {
                $scope.active = {
                    disabled: false,
                    name: "",
                    groups: [],
                    crawlers: []
                };
            }
            $scope.toggle = function (group) {
                var idx = $scope.active.groups.indexOf(group);

                if (idx > -1) {
                    $scope.active.groups.splice(idx, 1);
                }
                else {
                    $scope.active.groups.push(group);
                }

                $scope.update();
            };
            $scope.togglecrawlers = function (crawler) {
                var idx = $scope.active.crawlers.indexOf(group);

                if (idx > -1) {
                    $scope.active.crawlers.splice(idx, 1);
                }
                else {
                    $scope.active.crawlers.push(group);
                }
            }

            $scope.update();

        }]);
