'use strict';

angular.module('nwbadmin.crawler', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('crawler', {
            url: '/crawler',
            templateUrl: 'views/crawler/index.html',
            controller: 'CrawlerViewController',
            data: {
                authenticate: true,
            }
        });


    }])
    /**
     * @class nwbadmin.CrawlerViewController
     * @description Controller für die Administration der Crawler.
     */
    .controller('CrawlerViewController', ['$scope', '$location', 'CrawlerService', 'TokenService',
        function ($scope, $location, CrawlerService, TokenService) {

            $scope.inProgress = false;

            /**
             * @name $scope.update
             * @function update
             * @memberOf nwbadmin.CrawlerViewController
             * @instance
             * @description Aktualisiert die Auflistung der Crawler.
             */
            $scope.update = function () {
                CrawlerService.getAll().then(function (data) {
                    $scope.crawlers = data;
                    $scope.active = null;
                });
            };

            /**
             * @name $scope.select
             * @function select
             * @memberOf nwbadmin.CrawlerViewController
             * @instance
             * @description Selektiert den angegeben Crawler.
             * @param {object} crawler - Der Crawler der zur Bearbeitung selektiert werden soll.
             */
            $scope.select = function (crawler) {
                $scope.active = angular.copy(crawler);
            };

            /**
             * @name $scope.delete
             * @function delete
             * @memberOf nwbadmin.CrawlerViewController
             * @instance
             * @description Löscht den selektierten Crawler.
             */
            $scope.delete = function () {
                if (!$scope.active) return;

                CrawlerService.delete($scope.active.id).then(function (result) {
                    $scope.update();
                });
            };

            /**
             * @name $scope.save
             * @function save
             * @memberOf nwbadmin.CrawlerViewController
             * @instance
             * @description Speichert den aktuell in Bearbeitung befindlichen Crawler.
             */
            $scope.save = function () {
                if (!$scope.active) return;

                $scope.inProgress = true;

                var done = function (result) {
                    if (result)
                        $scope.update();

                    $scope.inProgress = false;
                }

                if (!$scope.active.id)
                    CrawlerService.create($scope.active).then(done);
                else
                    CrawlerService.update($scope.active).then(done);
            };

            /**
             * @name $scope.create
             * @function create
             * @memberOf nwbadmin.CrawlerViewController
             * @instance
             * @description Erzeugt einen neuen Crawler zur Bearbeitung.
             */
            $scope.create = function () {
                $scope.active = {
                    disabled: false,
                    ignoreAnalyzer: false,
                    name: "",
                    token: TokenService.generate()
                };
            }

            $scope.update();
        }
    ]);