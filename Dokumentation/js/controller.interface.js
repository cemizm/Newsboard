'use strict';

angular.module('nwbdoku.interface', ['ui.router'])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.when('/interface', '/interface/analyzer');

        $stateProvider.state('interface', {
            url: '/interface',
            templateUrl: 'views/index.html',
        }).state('interface.analyzer', {
            url: '/analyzer',
            templateUrl: 'views/interface/analyzer.html'
        }).state('interface.crawler', {
            url: '/crawler',
            templateUrl: 'views/interface/crawler.html'
        });
    }]);