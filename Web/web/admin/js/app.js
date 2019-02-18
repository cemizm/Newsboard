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

    $urlRouterProvider.otherwise("/dashboard");

}).run(function ($rootScope, $transitions, AuthService, $state) {

    $rootScope.alerts = [];

    $rootScope.closeAlert = function(index) {
        $rootScope.alerts.splice(index, 1);
    };

    $transitions.onBefore({}, function(transition) {
        const stateService = transition.router.stateService;
        const toState = transition.to();
        if (toState.data && toState.data.authenticate && !AuthService.isAuthenticated()) {
            return stateService.target('auth');
        }
    });

    $rootScope.$on("unauthorized", function () {
        $state.go("auth");
    });

    $rootScope.$on("error", function (event, msg) {
        if(msg == null || msg.message == null)
            msg = {message: "Schwerwiegender Fehler!"};
            
        $rootScope.alerts.push(msg);
    });

    if (AuthService.isAuthenticated()) {
        AuthService.getUser();
    }
});