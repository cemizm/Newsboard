'use strict';

angular.module('nwb.public', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('public', {
            url: '/public/{viewId}',
            templateUrl: 'views/public/index.html',
            controller: 'PublicViewController'
        });

    }])
    .controller('PublicViewController',
        ['$scope', '$location', '$stateParams', 'FrontendService', '$interval',
            function ($scope, $location, $stateParams, FrontendService, $interval) {

                var counter = 10000;
                var stop;

                FrontendService.getNewsEntriesByViewId($stateParams.viewId, 0).then(function (view) {
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

                $scope.getAnalyzerResult = function () {
                    var tmp = $scope.view.newsEntries[$scope.currentIndex].analyzerResult;

                    if (tmp < 0)
                        tmp = tmp * -1;

                    return tmp;
                }

                $scope.getAnalyzerResultType = function() {
                    return $scope.getAnalyzerResult() < 0 ? "danger" : "success"
                }

                $scope.startTimer = function () {
                    if (angular.isDefined(stop)) return;

                    stop = $interval(function () {
                        $scope.remaining = $scope.remaining - 1;
                    }, counter / 100);
                };

                $scope.stopTimer = function () {
                    if (angular.isDefined(stop)) {
                        $interval.cancel(stop);
                        stop = undefined;
                    }
                };

                $scope.resetTimer = function () {
                    $scope.remaining = 100;
                };

                $scope.$on('$destroy', function () {
                    $scope.stopTimer();
                });

                $scope.currentIndex = 0;
                $scope.resetTimer();
            }]);