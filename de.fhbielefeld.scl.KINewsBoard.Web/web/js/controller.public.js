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
        ['$scope', '$location', '$stateParams', 'FrontendService',
            function ($scope, $location, $stateParams, FrontendService) {

                FrontendService.getNewsEntriesByViewId($stateParams.viewId, 0).then(function (entries) {
                    $scope.entries = entries;

                    $scope.slickConfig = {
                        autoplay: true,
                        vertical: true,
                        slidesToShow: 5,
                        draggable: false,
                        infinite: true,
                        centerMode: true,
                        autoplaySpeed: 3000,
                        method: {},
                        event: {
                            beforeChange: function (event, slick, previousSlide, currentSlide, nextSlide) {
                                $(slick.$slides.get(previousSlide)).removeClass("selected");
                            },
                            afterChange: function (event, slick, slide, nextSlide) {
                                $scope.currentIndex = slide;
                                $(slick.$slides.get(slide)).addClass("selected");
                            },
                            init: function (event, slick) {
                                slick.slickGoTo($scope.currentIndex);
                            }
                        }
                    };

                    $scope.currentIndex = 0;
                });

                $scope.currentIndex = 0;

            }]);