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
                onExit: ['$uibModalStack', function ($uibModalStack) {
                    $uibModalStack.dismissAll();
                }],
            });

    }])
    /**
     * @class nwb.FrontendViewController
     * @description Controller für die Web/Mobile Ansicht des Newsboards.
     */
    .controller('FrontendViewController', ['$scope', '$location', '$stateParams', 'FrontendService', 'localStorageService', '$state',
            function ($scope, $location, $stateParams, FrontendService, localStorageService, $state) {

                $scope.page = 1;
                $scope.keyword = $stateParams.keyword;
                $scope.contentsize = 200;
                $scope.isLoading = true;
                $scope.moreEntries = true;
                $scope.entries = [];
                $scope.busy = false;

                /**
                 * @name $scope.updateView
                 * @function updateView
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Aktualisiert die Nachrichteneinträge mit neuen Inhalten.
                 */
                $scope.updateView = function () {
                    $scope.isLoading = true;

                    FrontendService.getNewsEntries($scope.page, $stateParams.keyword, $stateParams.view).then(function (entries) {
                        $scope.entries = $scope.entries.concat(entries);
                        $scope.isLoading = false;
                        $scope.moreEntries = entries != null && entries.length == 20;
                    });
                };

                /**
                 * @name $scope.search
                 * @function search
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Führt eine Suche mit dem angegeben Keyword durch.
                 */
                $scope.search = function () {
                    $state.go('frontend', {keyword: $scope.keyword, view: $stateParams.view})
                };

                /**
                 * @name $scope.loadMoreNews
                 * @instance
                 * @function loadMoreNews
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Lädt Nachrichteneinträge der aktuellen View nach.
                 */
                $scope.loadMoreNews = function () {
                    if (!$scope.moreEntries || $scope.isLoading)
                        return;

                    $scope.page += 1;
                    $scope.updateView();

                    $scope.busy = false;
                };

                /**
                 * @name $scope.rate
                 * @instance
                 * @function rate
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Bewertet einen Nachrichteneintrag und fügt die Id des Nachrichteneintrags in das LocalStorage hinzu.
                 * @param {object} entry - Der Nachrichteneintrag der bewertet werden soll.
                 * @param {boolean} up - True, wenn der Beitrag positiv bewertet werden soll.
                 */
                $scope.rate = function (entry, up) {
                    if ($scope.isRated(entry)) return;

                    localStorageService.set(entry.id, up);
                    FrontendService.rateNewsEntry(entry, up).then(function (updated) {
                        entry.rating = updated.rating;
                    });
                };

                /**
                 * @name $scope.isRated
                 * @instance
                 * @function isRated
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Prüft ob ein Nachrichteneintrag bereits bewertet wurde und damit die Id im LocalStorage vorhanden ist.
                 * @param {object} entry - Der Nachrichteneintrag der geprüft werden soll.
                 * @return {boolean} True, wenn der Nachrichteneintrag bereits bewertet wurde.
                 */
                $scope.isRated = function (entry) {
                    return localStorageService.get(entry.id) !== null;
                };

                /**
                 * @name $scope.isCurrentVote
                 * @instance
                 * @function isCurrentVote
                 * @memberOf nwb.FrontendViewController
                 * @instance
                 * @description Ermitellt wie der Benutzer einen Nachrichteneintrag bewertet hat, sofern dieser bereits bewertet wurde.
                 * @param {object} entry - Der Nachrichteneintrag der geprüft werden soll.
                 * @param {boolean} up - True, wenn geprüft werden soll, ob der Benutzer den Beitrag positiv bewertet hat.
                 * @return {boolean} True, wenn Nachrichteneintrag bewertet mit dem angefraten Ergebnis bewertet wurde.
                 */
                $scope.isCurrentVote = function (entry, up) {
                    if (!$scope.isRated(entry))
                        return false;

                    return localStorageService.get(entry.id) === up;
                };

                $scope.updateView();

            }])
    /**
     * @class nwb.DetailViewController
     * @description Controller für die Detailansicht eines Nachrichteneintrags.
     */
    .controller('DetailViewController', ['$scope', 'news',
        function ($scope, news) {

            $scope.entry = news;
            $scope.selectedResult = null;

            /**
             * @name $scope.dismiss
             * @function dismiss
             * @memberOf nwb.DetailViewController
             * @instance
             * @description Schliesst die Popup Ansicht.
             */
            $scope.dismiss = function () {
                $scope.$dismiss();
            };

            /**
             * @name $scope.updateText
             * @function updateText
             * @memberOf nwb.DetailViewController
             * @instance
             * @description markiert den Text nach dem übergebenen Analyseergebnis.
             * @param {object} result - das Analyseergebnis mit dem der Text markiert werden soll.
             */
            $scope.updateText = function (result) {
                if (result == $scope.selectedResult)
                    return;

                $scope.selectedResult = result;
                $scope.colored = $scope.entry.content;

                if (result != null) {
                    $scope.colored = "";

                    var copy = $scope.entry.content;
                    var lastResult = null;

                    for (var pos = 0; pos < copy.length; pos++) {
                        var c = copy.charAt(pos);

                        if (lastResult && pos > lastResult.charEnd) {
                            $scope.colored += '</span>';
                            lastResult = null;
                        }

                        if (!lastResult) {
                            lastResult = result.sentenceResults.find(function (sr) {
                                return (pos >= sr.charStart && pos <= sr.charEnd);
                            });

                            if (lastResult) {
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