'use strict';

angular.module('nwb.frontend', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('frontend', {
                url: '/?keyword&view',
                templateUrl: 'views/frontend/index.html',
                controller: 'FrontendViewController'
            })
            .state("frontend.detail", {
                url: "detail/{news}/",
                onEnter: ['$stateParams', '$state', '$uibModal', 'FrontendService', function ($stateParams, $state, $uibModal, FrontendService) {
                    $uibModal.open({
                        templateUrl: "views/frontend/detail.html",
                        resolve: {
                            news: function () {
                                return FrontendService.getNewsEntryDetails($stateParams.news, $stateParams.view)
                            }
                        },
                        controller: 'DetailViewController'
                    }).result.finally(function () {
                        $state.go('^');
                    });
                }]
            });

    }])
    .controller('FrontendViewController',
        ['$scope', '$location', '$stateParams', 'FrontendService', 'localStorageService', '$state',
            function ($scope, $location, $stateParams, FrontendService, localStorageService, $state) {

                $scope.page = 1;
                $scope.keyword = $stateParams.keyword;
                $scope.contentsize = 200;
                $scope.isLoading = true;
                $scope.moreEntries = true;
                $scope.entries = [];
                $scope.busy = false;

                $scope.updateView = function () {
                    $scope.isLoading = true;

                    FrontendService.getNewsEntries($scope.page, $stateParams.keyword, $stateParams.view).then(function (entries) {
                        $scope.entries = $scope.entries.concat(entries);
                        $scope.isLoading = false;
                        $scope.moreEntries = entries != null && entries.length == 20;
                    });
                };

                $scope.search = function () {
                    $state.go('frontend', {keyword: $scope.keyword, view: $stateParams.view})
                };

                $scope.loadMoreNews = function () {
                    if (!$scope.moreEntries || $scope.isLoading)
                        return;

                    $scope.page += 1;
                    $scope.updateView();

                    $scope.busy = false;
                };

                $scope.rate = function (entry, up) {
                    if ($scope.isRated(entry)) return;

                    localStorageService.set(entry.id, up);
                    FrontendService.rateNewsEntry(entry, up).then(function (updated) {
                        entry.rating = updated.rating;
                    });
                };

                $scope.isRated = function (entry) {
                    return localStorageService.get(entry.id) !== null;
                };

                $scope.isCurrentVote = function (entry, up) {
                    if (!$scope.isRated(entry))
                        return false;

                    return localStorageService.get(entry.id) === up;
                };

                $scope.updateView();

            }])
    .controller('DetailViewController', ['$scope', 'news',
        function ($scope, news) {

            $scope.entry = news;
            $scope.selectedResult = null;

            $scope.dismiss = function () {
                $scope.$dismiss();
            };

            $scope.updateText = function () {
                $scope.colored = $scope.entry.content;

                if ($scope.selectedResult != null) {
                    String.prototype.replaceBetween = function (start, end, what) {
                        return this.substring(0, start) + what + this.substring(end);
                    };

                    $scope.selectedResult.sentenceResults.forEach(function (sentenceResult) {
                        var cssAttr = sentenceResult.value >= 0 ? "analyzerTextPos" : "analyzerTextNeg";
                        var subStr = $scope.colored.substring(sentenceResult.charStart, sentenceResult.charEnd);
                        $scope.colored = $scope.colored.replaceBetween(sentenceResult.charStart,
                            sentenceResult.charEnd, '<span class="' + cssAttr + '">' + subStr + '</span>');
                    });
                }
            };

            $scope.updateText()
        }]);