/**
 * @class nwbadmin.AnalyzerService
 * @description Schnittstelle für die CRUD Operationen der Analyzer.
 */
angular.module('nwbadmin')
    .factory('AnalyzerService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/analyzer/"
        var service = {};

        /**
         * @name service.getAll
         * @function getAll
         * @memberOf nwbadmin.AnalyzerService
         * @instance
         * @description Ruft alle Analyzer ab.
         */
        service.getAll = function () {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.get
         * @function get
         * @memberOf nwbadmin.AnalyzerService
         * @instance
         * @description Ruft den Analyzer mit der angegebenen Id ab.
         * @param {Number} id - Die Id des Analyzers der abgerufen werden soll.
         */
        service.get = function (id) {
            return $http.get(api + id).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.create
         * @function create
         * @memberOf nwbadmin.AnalyzerService
         * @instance
         * @description Legt einen neuen Analyzer an.
         * @param {object} entity - Der Analyzer der angelegt werden soll.
         */
        service.create = function (entity) {
            return $http.post(api, entity).then(function (response) {
                return response.status == 200;
            });
        }


        /**
         * @name service.update
         * @function update
         * @memberOf nwbadmin.AnalyzerService
         * @instance
         * @description Aktualisiert den angegeben Analyzer.
         * @param {object} entity - Der Analyzer der aktualisiert werden soll.
         */
        service.update = function (entity) {
            return $http.put(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.delete
         * @function delete
         * @memberOf nwbadmin.AnalyzerService
         * @instance
         * @description Löscht den Analyzer mit der angegebenen Id.
         * @param {Number} id - Die Id des Analyzers der gelöscht werden soll.
         */
        service.delete = function (id) {
            return $http.delete(api + id).then(function (response) {
                return response.status == 200;
            });
        }

        return service;
    }]);