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
        ['$scope', '$location', 'CrawlerService',
            function ($scope, $location, CrawlerSerivce) {
                CrawlerService.get().then(function (data) {
                    $scope.crawlers = data;
                });
            }]);