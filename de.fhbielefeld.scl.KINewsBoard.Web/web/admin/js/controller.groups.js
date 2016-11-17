'use strict';

angular.module('nwbadmin.groups', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('groups', {
            url: '/groups',
            templateUrl: 'views/groups/index.html',
            controller: 'GroupsViewController'
        });

    }])
    .controller('GroupsViewController',
        ['$scope', '$location',
            function ($scope, $location) {

            }]);