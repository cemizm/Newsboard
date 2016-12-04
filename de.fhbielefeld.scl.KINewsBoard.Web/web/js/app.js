var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'slickCarousel',
    'ngPopover',
    'monospaced.qrcode',
    'LocalStorageModule',
    'nwb.frontend',
    'nwb.public'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/frontend/1");

});