'use strict';

angular.module('nwbadmin.views', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state('views', {
            url: '/views',
            abstract: true,
            templateUrl: 'views/views/index.html',
            data: {
                authenticate: true,
            }
        }).state('views.list', {
            url: '/',
            templateUrl: 'views/views/list.html',
            controller: 'ViewsViewController'
        }).state('views.edit', {
            url: '/edit',
            params: {
                view: undefined
            },
            templateUrl: 'views/views/edit.html',
            controller: 'ViewsEditController'
        });

    }])
    .controller('ViewsViewController',
        ['$scope', '$location', 'ViewService', '$state',
            function ($scope, $location, ViewService, $state) {

                $scope.update = function () {
                    ViewService.getAll().then(function (data) {
                        $scope.views = data;
                        $scope.active = null;
                    });
                };

                $scope.select = function (view) {
                    $scope.active = view;
                };

                $scope.create = function () {
                    $state.go("views.edit");
                };

                $scope.edit = function () {
                    if (!$scope.active) return;

                    $state.go("views.edit", {view: $scope.active});
                };

                $scope.delete = function () {
                    if (!$scope.active) return;

                    ViewService.delete($scope.active.id).then(function (result) {
                        $scope.update();
                    });
                };

                $scope.update();
            }])
    .controller('ViewsEditController',
        ['$scope', '$location', '$stateParams', 'ViewService', 'GroupSetService', 'CrawlerService', '$state',
            function ($scope, $location, $stateParams, ViewService, GroupSetService, CrawlerService, $state) {

                $scope.view = $stateParams.view;
                $scope.inProgress = false;

                GroupSetService.getAll().then(function (data) {
                    $scope.groups = data;
                });

                CrawlerService.getAll().then(function (data) {
                    $scope.crawlers = data;
                });

                if ($scope.view == null) {
                    $scope.view = {
                        disabled: false,
                        name: "",
                        description: "",
                        groups: [],
                        crawlers: []
                    }
                }

                $scope.toggle = function (list, item) {
                    var idx = list.indexOf(item);

                    if (idx > -1) {
                        list.splice(idx, 1);
                    }
                    else {
                        list.push(item);
                    }
                };

                $scope.cancel = function () {
                    $state.go("views.list");
                };

                $scope.save = function () {
                    $scope.inProgress = true;

                    var done = function (result) {
                        if (result)
                            $state.go("views.list");

                        $scope.inProgress = false;
                    };

                    if (!$scope.view.id)
                        ViewService.create($scope.view).then(done);
                    else
                        ViewService.update($scope.view).then(done);
                };
            }]);