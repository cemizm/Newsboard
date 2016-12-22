'use strict';

angular.module('nwbadmin.analyzer', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('analyzer', {
            url: '/analyzer',
            templateUrl: 'views/analyzer/index.html',
            controller: 'AnalyzerViewController',
            data: {
                authenticate: true,
            }
        });


    }])
    /**
     * @class nwbadmin.AnalyzerViewController
     * @description Controller für die Administration der Analyzer.
     */
    .controller('AnalyzerViewController', ['$scope', '$location', 'AnalyzerService', 'GroupSetService', 'TokenService',
        function ($scope, $location, AnalyzerService, GroupSetService, TokenService) {

            $scope.inProgress = false;

            /**
             * @name $scope.update
             * @function update
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Aktualisiert die Auflistung der Analyzer.
             */
            $scope.update = function () {
                GroupSetService.getAll().then(function (data) {
                    $scope.groups = data;
                });
                AnalyzerService.getAll().then(function (data) {
                    $scope.analyzers = data;
                    $scope.active = null;
                });
            };

            /**
             * @name $scope.select
             * @function select
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Selektiert den angegeben Analyzer.
             * @param {object} analyzer - Der Analyzer der zur Bearbeitung selektiert werden soll.
             */
            $scope.select = function (analyzer) {
                $scope.active = angular.copy(analyzer);
            };

            /**
             * @name $scope.delete
             * @function delete
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Löscht den selektierten Analyzer.
             */
            $scope.delete = function () {
                if (!$scope.active) return;

                AnalyzerService.delete($scope.active.id).then(function (result) {
                    $scope.update();
                });
            };

            /**
             * @name $scope.save
             * @function save
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Speichert den aktuell in Bearbeitung befindlichen Analyzer.
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
                    AnalyzerService.create($scope.active).then(done);
                else
                    AnalyzerService.update($scope.active).then(done);
            };

            /**
             * @name $scope.create
             * @function create
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Erzeugt einen neuen Analyzer zur Bearbeitung.
             */
            $scope.create = function () {
                $scope.active = {
                    disabled: false,
                    groups: [],
                    name: "",
                    token: TokenService.generate()
                };
            };

            /**
             * @name $scope.create
             * @function create
             * @memberOf nwbadmin.AnalyzerViewController
             * @instance
             * @description Ordnet den Analyzer einer Gruppe zu oder entfernt die Zuordnung falls bereits zugeordnet.
             * @param {object} group - Die Gruppe dem der Analyzer zugeordnet bzw. Zuordnung aufgehoben werden soll.
             */
            $scope.toggle = function (group) {
                var idx = $scope.active.groups.indexOf(group);

                if (idx > -1) {
                    $scope.active.groups.splice(idx, 1);
                }
                else {
                    $scope.active.groups.push(group);
                }
            }

            $scope.update();
        }
    ]);