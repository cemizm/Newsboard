'use strict';

angular.module('nwb.public', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('public', {
            url: '/public/{viewId}',
            templateUrl: 'views/public/index.html',
            controller: 'PublicViewController'
        });

    }])
    /**
     * @class nwb.PublicViewController
     * @description Controller für die öffentliche Ansicht des Newsboards.
     */
    .controller('PublicViewController', ['$scope', '$location', '$stateParams', 'FrontendService', '$interval', '$state',
            function ($scope, $location, $stateParams, FrontendService, $interval, $state) {

                var counter = 10000;
                var stop;

                FrontendService.getNewsEntriesByViewId($stateParams.viewId, 0).then(function (view) {
                    if (view == null)
                        $state.go("frontend");

                    $scope.view = view;


                    $scope.slickConfig = {
                        autoplay: true,
                        vertical: true,
                        slidesToShow: 5,
                        draggable: false,
                        infinite: true,
                        centerMode: true,
                        autoplaySpeed: counter,
                        method: {},
                        event: {
                            beforeChange: function (event, slick, previousSlide, currentSlide, nextSlide) {
                                $(slick.$slides.get(previousSlide)).removeClass("selected");
                                $scope.resetTimer();
                            },
                            afterChange: function (event, slick, slide, nextSlide) {
                                $(slick.$slides.get(slide)).addClass("selected");
                                $scope.currentIndex = slide;
                                $scope.startTimer();
                            },
                            init: function (event, slick) {
                                slick.slickGoTo($scope.currentIndex);
                            }
                        }
                    };
                });

                /**
                 * @name $scope.getViewUrl
                 * @instance
                 * @function getViewUrl
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Erzeugt eine Absolute URL zu der aktuellen Ansicht.
                 */
                $scope.getViewUrl = function () {
                    if (!$scope.view)
                        return "";

                    return $state.href('frontend', {view: $scope.view.id}, {absolute: true});
                };

                /**
                 * @name $scope.getAnalyzerResult
                 * @instance
                 * @function getAnalyzerResult
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Ermitellt das durchschnittliche Analyseergebnis.
                 */
                $scope.getAnalyzerResult = function () {
                    var tmp = $scope.view.newsEntries[$scope.currentIndex].analyzerResult;

                    if (tmp < 0)
                        tmp = tmp * -1;

                    return tmp;
                };

                /**
                 * @name $scope.getAnalyzerResultType
                 * @instance
                 * @function getAnalyzerResultType
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Ermitellt ob das Analyseergebnis positiv oder negativ ist.
                 */
                $scope.getAnalyzerResultType = function () {
                    return $scope.getAnalyzerResult() < 0 ? "danger" : "success"
                };

                /**
                 * @name $scope.startTimer
                 * @instance
                 * @function startTimer
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Startet den Timer für das Nachrichteneintrags-Karussel.
                 */
                $scope.startTimer = function () {
                    if (angular.isDefined(stop)) return;

                    stop = $interval(function () {
                        $scope.remaining = $scope.remaining - 1;
                    }, counter / 100);
                };

                /**
                 * @name $scope.stopTimer
                 * @instance
                 * @function stopTimer
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Stopt den Timer für das Nachrichteneintrags-Karussel.
                 */
                $scope.stopTimer = function () {
                    if (angular.isDefined(stop)) {
                        $interval.cancel(stop);
                        stop = undefined;
                    }
                };

                /**
                 * @name $scope.resetTimer
                 * @instance
                 * @function resetTimer
                 * @memberOf nwb.PublicViewController
                 * @instance
                 * @description Setzt den Timer für das Nachrichteneintrags-Karussel zurück.
                 */
                $scope.resetTimer = function () {
                    $scope.remaining = 100;
                };

                $scope.$on('$destroy', function () {
                    $scope.stopTimer();
                });

                $scope.currentIndex = 0;
                $scope.resetTimer();
            }]);