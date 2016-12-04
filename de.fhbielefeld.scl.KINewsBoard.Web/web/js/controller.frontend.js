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
        ['$scope', '$location', '$stateParams', 'FrontendService', 'localStorageService',
            function ($scope, $location, $stateParams, FrontendService, localStorageService) {

                $scope.page = 1;
                $scope.keyword = "";
                $scope.contentsize = 200;

                $scope.updateView = function () {
                    FrontendService.getNewsEntries($scope.page,  $scope.keyword).then(function(entries) {
                        $scope.entries = entries;
                    });
                };

                $scope.rate = function (entry, up) {
                    var value = localStorageService.get(entry.id);
                    if(value) return;

                    localStorageService.set(entry.id, up);
                    FrontendService.rateNewsEntry(entry, up).then(function(updated){
                        entry.rating = updated.rating;
                    });
                };
                $scope.updateView();

            }]);