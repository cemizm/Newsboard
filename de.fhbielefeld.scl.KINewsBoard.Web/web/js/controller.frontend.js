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
                        controller: 'DetailViewController',
                        size: 'lg'
                    }).result.finally(function () {
                        $state.go('^');
                    });
                }],
                onExit: ['$uibModalStack', function($uibModalStack) {
                    $uibModalStack.dismissAll();
                }],
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
            $scope.colored = $scope.entry.content;

            $scope.dismiss = function () {
                $scope.$dismiss();
            };

            $scope.updateText = function (result) {
                if(result == $scope.selectedResult)
                    return;

                $scope.selectedResult = result;
                $scope.colored = $scope.entry.content;

                if (result != null) {
                    $scope.colored = "";

                    var copy = $scope.entry.content;
                    var lastResult = null;

                    for (var pos = 0; pos < copy.length; pos++)
                    {
                        var c = copy.charAt(pos);

                        if(lastResult && pos > lastResult.charEnd)
                        {
                            $scope.colored += '</span>';
                            lastResult = null;
                        }

                        if(!lastResult)
                        {
                            lastResult = result.sentenceResults.find(function(sr){
                               return (pos >= sr.charStart && pos <= sr.charEnd);
                            });

                            if(lastResult)
                            {
                                var cssAttr = lastResult.value >= 0 ? "analyzerTextPos" : "analyzerTextNeg";
                                $scope.colored += '<span class="' + cssAttr + '">'
                            }
                        }

                        $scope.colored += c;
                    }
                }
            };

            $scope.updateText(news.analyzerResults.length > 0 ? news.analyzerResults[0] : null);
        }]);