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

    $urlRouterProvider.otherwise("/dashboard");

}).run(function ($rootScope, AuthService, $state) {

    $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
        if (toState.data && toState.data.authenticate && !AuthService.isAuthenticated()) {
            event.preventDefault();
            $state.go("auth");
        }
    });

    $rootScope.$on("unauthorized", function () {
        $state.go("auth");
    });

    if (AuthService.isAuthenticated()) {
        AuthService.getUser();
    }
});