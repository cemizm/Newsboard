'use strict';

angular.module('nwbadmin.views', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('views', {
            url: '/views',
            templateUrl: 'views/views/index.html',
            controller: 'ViewsViewController'
        });

    }])
    .controller('ViewsViewController',
        ['$scope', '$location',
            function ($scope, $location) {

            }]);