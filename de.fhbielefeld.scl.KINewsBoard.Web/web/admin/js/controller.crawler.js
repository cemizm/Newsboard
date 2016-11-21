'use strict';

angular.module('nwbadmin.crawler', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('crawler', {
            url: '/crawler',
            templateUrl: 'views/crawler/index.html',
            controller: 'CrawlerViewController'
        });


    }])
    .controller('CrawlerViewController',
        ['$scope', '$location', 'CrawlerService', 'TokenService',
            function ($scope, $location, CrawlerService, TokenService) {


                $scope.update = function () {
                    CrawlerService.getAll().then(function (data) {
                        $scope.crawlers = data;
                        $scope.active = null;
                    });
                };

                $scope.select = function (crawler) {
                    $scope.active = angular.copy(crawler);
                };

                $scope.delete = function () {
                    if (!$scope.active) return;

                    CrawlerService.delete($scope.active.id).then(function (result) {
                        $scope.update();
                    });
                };

                $scope.save = function () {
                    if (!$scope.active) return;

                    if (!$scope.active.id) {
                        CrawlerService.create($scope.active).then(function (result) {
                            $scope.update();
                        });

                    }
                    else {
                        CrawlerService.update($scope.active).then(function (result) {
                            $scope.update();
                        });
                    }
                };

                $scope.create = function () {
                    $scope.active = {
                        disabled: false,
                        ignoreAnalyzer: false,
                        name: "",
                        token: TokenService.generate()
                    };
                }

                $scope.update();
            }
        ]);