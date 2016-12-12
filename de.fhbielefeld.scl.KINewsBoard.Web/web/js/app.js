var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'slickCarousel',
    'ngPopover',
    'monospaced.qrcode',
    'LocalStorageModule',
    'infinite-scroll',
    'nwb.frontend',
    'nwb.public',
    'nwb.placeholder'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/frontend");
});


