var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'nwb.frontend',
    'nwb.public'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/frontend");

});