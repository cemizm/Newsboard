var app = angular.module('nwbadmin', [
    'ui.router',
    'ui.bootstrap',
    'nwbadmin.dashboard',
    'nwbadmin.crawler',
    'nwbadmin.analyzer',
    'nwbadmin.groups',
    'nwbadmin.views'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/dashboard");
});