'use strict';

angular.module('nwbadmin.groups', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider.state('groups', {
            url: '/groups',
            templateUrl: 'views/groups/index.html',
            controller: 'GroupViewController',
            data: {
                authenticate: true,
            }
        });


    }])
    /**
     * @class nwbadmin.GroupViewController
     * @description Controller für die Administration der Crawler.
     */
    .controller('GroupViewController', ['$scope', '$location', 'GroupSetService',
        function ($scope, $location, GroupSetService) {

            $scope.inProgress = false;

            /**
             * @name $scope.update
             * @function update
             * @memberOf nwbadmin.GroupViewController
             * @instance
             * @description Aktualisiert die Auflistung der Gruppen.
             */
            $scope.update = function () {
                GroupSetService.getAll().then(function (data) {
                    $scope.groups = data;
                    $scope.active = null;
                });
            };

            /**
             * @name $scope.select
             * @function select
             * @memberOf nwbadmin.GroupViewController
             * @instance
             * @description Selektiert die angegebene Gruppe.
             * @param {object} group - Die Gruppe die zur Bearbeitung selektiert werden soll.
             */
            $scope.select = function (group) {
                $scope.active = angular.copy(group);
            };

            /**
             * @name $scope.delete
             * @function delete
             * @memberOf nwbadmin.GroupViewController
             * @instance
             * @description Löscht die selektierte Gruppe.
             */
            $scope.delete = function () {
                if (!$scope.active) return;

                GroupSetService.delete($scope.active.id).then(function (result) {
                    $scope.update();
                });
            };

            /**
             * @name $scope.save
             * @function save
             * @memberOf nwbadmin.GroupViewController
             * @instance
             * @description Speichert die aktuell in Bearbeitung befindliche Gruppe.
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
                    GroupSetService.create($scope.active).then(done);
                else
                    GroupSetService.update($scope.active).then(done);
            };

            /**
             * @name $scope.create
             * @function create
             * @memberOf nwbadmin.GroupViewController
             * @instance
             * @description Erzeugt eine neue Gruppe zur Bearbeitung.
             */
            $scope.create = function () {
                $scope.active = {
                    name: ""
                };
            }

            $scope.update();
        }
    ]);