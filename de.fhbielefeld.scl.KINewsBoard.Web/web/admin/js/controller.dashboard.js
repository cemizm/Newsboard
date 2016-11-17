'use strict';

angular.module('nwbadmin.dashboard', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('dashboard', {
            url: '/dashboard',
            templateUrl: 'views/dashboard/index.html',
            controller: 'DashboardViewController'
        });

    }])
    .controller('DashboardViewController',
        ['$scope', '$location',
            function ($scope, $location) {

            }]);