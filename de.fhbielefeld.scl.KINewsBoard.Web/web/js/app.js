var app = angular.module('nwb', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'slickCarousel',
    'ngPopover',
    'nwb.frontend',
    'nwb.public'
]).config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise("/frontend/1");

});