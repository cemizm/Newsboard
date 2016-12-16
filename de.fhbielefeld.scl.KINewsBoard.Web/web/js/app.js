var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'slickCarousel',
    'ngPopover',
    'monospaced.qrcode',
    'LocalStorageModule',
    'angular-inview',
    'nwb.frontend',
    'nwb.public',
    'nwb.placeholder'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
});


