'use strict';

angular.module('nwbadmin.auth', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('auth', {
            url: '/auth/:logout',
            templateUrl: 'views/auth/index.html',
            controller: 'AuthController'
        });


    }])
    .controller('AuthController',
        ['$scope', '$location', 'AuthService', '$state', '$stateParams',
            function ($scope, $location, AuthService, $state, $stateParams) {

                $scope.username = "";
                $scope.password = "";
                $scope.error = "";
                $scope.isLoading = false;

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