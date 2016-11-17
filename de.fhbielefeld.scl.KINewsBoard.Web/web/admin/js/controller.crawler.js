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
        ['$scope', '$location',
            function ($scope, $location) {

            }]);