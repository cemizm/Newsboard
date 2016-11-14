'use strict';

angular.module('nwb.frontend', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('frontend', {
            url: '/frontend',
            templateUrl: 'views/frontend/index.html',
            controller: 'FrontendViewController'
        });

    }])
    .controller('FrontendViewController',
        ['$scope', '$location', 'FrontendService',
            function ($scope, $location, FrontendService) {
                FrontendService.getNewsEntries(1, "").then(function(entries) {
                    $scope.entries = entries;
                });
            }]);