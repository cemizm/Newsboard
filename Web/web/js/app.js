/**
 * @namespace nwb
 * @description Newsboard Frontend
 */
var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'slickCarousel',
    'monospaced.qrcode',
    'LocalStorageModule',
    'angular-inview',
    'nwb.frontend',
    'nwb.public',
    'nwb.placeholder',
    'ngSanitize'
]).config(function ($urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise("/");
    $locationProvider.html5Mode(true);
});




