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
        ['$scope', '$location',
            function ($scope, $location) {

            }]);