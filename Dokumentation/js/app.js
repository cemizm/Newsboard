var app = angular.module('nwbdoku', [
    'ui.router',
    'ui.bootstrap',
    'nwbdoku.manual',
    'nwbdoku.interface'
]).config(['$urlRouterProvider', function ($urlRouterProvider) {
    $urlRouterProvider.otherwise('/frontend');
}]);

