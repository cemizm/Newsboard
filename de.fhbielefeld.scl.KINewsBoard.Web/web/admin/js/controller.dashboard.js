'use strict';

angular.module('nwbadmin.dashboard', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('dashboard', {
            url: '/dashboard',
            templateUrl: 'views/dashboard/index.html',
            controller: 'DashboardViewController',
            data: {
                authenticate: true,
            }
        });

    }])
    /**
     * @class nwbadmin.DashboardViewController
     * @description Controller für die Übersicht des Newsboards.
     */
    .controller('DashboardViewController', ['$scope', '$location', 'DashboardService',
        function ($scope, $location, DashboardService) {
            DashboardService.get().then(function (dashboard) {
                $scope.dashboard = dashboard;
            })
        }]);