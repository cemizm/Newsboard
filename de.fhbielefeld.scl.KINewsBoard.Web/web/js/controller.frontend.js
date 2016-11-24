'use strict';

angular.module('nwb.frontend', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('frontend', {
            url: '/frontend/{viewId}',
            templateUrl: 'views/frontend/index.html',
            controller: 'FrontendViewController'
        });

    }])
    .controller('FrontendViewController',
        ['$scope', '$location', '$stateParams', 'FrontendService',
            function ($scope, $location, $stateParams, FrontendService) {

                $scope.page = 1;
                $scope.keyword = "";

                $scope.updateView = function () {
                    FrontendService.getNewsEntries($scope.page,  $scope.keyword).then(function(entries) {
                        $scope.entries = entries;
                    });
                };

                $scope.updateView();

            }]);