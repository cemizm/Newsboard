var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'ngMaterial',
    'ngMessages',
    'nwb.frontend',
    'nwb.public'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/frontend");

});