'use strict';

angular.module('nwb.public', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('public', {
            url: '/public',
            templateUrl: 'views/public/index.html',
            controller: 'PublicViewController'
        });

    }])
    .controller('PublicViewController',
        ['$scope', '$location', 'FrontendService',
            function ($scope, $location, FrontendService) {
                FrontendService.getNewsEntries(1, "").then(function(entries) {
                    $scope.entries = entries;
                });

                $scope.currentIndex = 0;
                $scope.slickConfig = {
                    enabled: true,
                    autoplay: true,
                    vertical: true,
                    slidesToShow: 4,
                    draggable: false,
                    infinite: true,
                    centerMode: true,
                    autoplaySpeed: 3000,
                    method: {},
                    event: {

                        beforeChange: function (event, slick, previousSlide, currentSlide, nextSlide) {
                            $(slick.$slides.get(currentSlide)).css("border-color", "red");
                            $(slick.$slides.get(previousSlide)).css("border-color", "");
                        },
                        afterChange: function (event, slick, currentSlide, nextSlide) {
                            $scope.currentIndex = currentSlide; // save current index each time
                        },
                        init: function (event, slick) {
                            slick.slickGoTo($scope.currentIndex); // slide to correct index when init
                        }
                    }
                };

            }]);