'use strict';

angular.module('nwb.public', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('public', {
            url: '/public',
            templateUrl: 'views/public/index.html',
            controller: 'PublicViewController'
        });

    }])
    .controller('PublicViewController',
        ['$scope', '$location', 'FrontendService',
            function ($scope, $location, FrontendService) {
                FrontendService.getNewsEntries(1, "").then(function(entries) {
                    $scope.entries = entries;
                });

                $scope.myInterval = 2000;
                $scope.noWrapSlides = false;
                $scope.active = 0;

            }]);