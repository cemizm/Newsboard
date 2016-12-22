'use strict';

angular.module('nwbadmin.auth', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('auth', {
            url: '/auth/:logout',
            templateUrl: 'views/auth/index.html',
            controller: 'AuthController'
        });


    }])
    /**
     * @class nwbadmin.AuthController
     * @description Controller für die Anmeldung an der Adminstrationsoberfläche.
     */
    .controller('AuthController',
        ['$scope', '$location', 'AuthService', '$state', '$stateParams',
            function ($scope, $location, AuthService, $state, $stateParams) {

                $scope.username = "";
                $scope.password = "";
                $scope.error = "";
                $scope.isLoading = false;

                /**
                 * @name $scope.login
                 * @function login
                 * @memberOf nwbadmin.AuthController
                 * @instance
                 * @description Führt die Anmeldung aus.
                 */
                $scope.login = function () {
                    $scope.isLoading = true;
                    AuthService.login($scope.username, $scope.password).then(function (user) {
                        $scope.isLoading = false;

                        if (user == null)
                            return $scope.error = "Anmeldung fehlgeschlagen!";

                        $state.go('dashboard');
                    });

                    $scope.error = "";
                    $scope.password = "";
                };

                if ($stateParams.logout) {
                    if (AuthService.isAuthenticated()) {
                        AuthService.logout().then(function () {
                            $state.go('auth');
                        });
                    }
                    else
                        $state.go('auth');
                }
                else if (AuthService.isAuthenticated())
                    $state.go('dashboard');
            }
        ]);