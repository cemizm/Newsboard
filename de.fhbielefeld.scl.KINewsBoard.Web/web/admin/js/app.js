/**
 * @namespace nwbadmin
 * @description Newsboard Administration
 */
var app = angular.module('nwbadmin', [
    'ui.router',
    'ui.bootstrap',
    'nwbadmin.auth',
    'nwbadmin.dashboard',
    'nwbadmin.crawler',
    'nwbadmin.analyzer',
    'nwbadmin.groups',
    'nwbadmin.views'
]).config(function ($urlRouterProvider, $httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
    $httpProvider.interceptors.push('ErrorInterceptor');

    $urlRouterProvider.otherwise("/dashboard");

}).run(function ($rootScope, AuthService, $state) {

    $rootScope.alerts = [];


    $rootScope.closeAlert = function(index) {
        $rootScope.alerts.splice(index, 1);
    };

    $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
        if (toState.data && toState.data.authenticate && !AuthService.isAuthenticated()) {
            event.preventDefault();
            $state.go("auth");
        }
    });

    $rootScope.$on("unauthorized", function () {
        $state.go("auth");
    });

    $rootScope.$on("error", function (event, msg) {
        $rootScope.alerts.push(msg);
    });

    if (AuthService.isAuthenticated()) {
        AuthService.getUser();
    }
});