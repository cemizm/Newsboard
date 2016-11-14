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

            }]);