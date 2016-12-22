/**
 * @class nwbadmin.ViewService
 * @description Schnittstelle für die CRUD Operationen der Ansichten.
 */
angular.module('nwbadmin')
    .factory('ViewService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/view/"
        var service = {};

        /**
         * @name service.getAll
         * @function getAll
         * @memberOf nwbadmin.ViewService
         * @instance
         * @description Ruft alle Ansichten ab.
         */
        service.getAll = function () {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.get
         * @function get
         * @memberOf nwbadmin.ViewService
         * @instance
         * @description Ruft die Ansicht mit der angegebenen Id ab.
         * @param {Number} id - Die Id der Ansicht die abgerufen werden soll.
         */
        service.get = function (id) {
            return $http.get(api + id).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.create
         * @function create
         * @memberOf nwbadmin.ViewService
         * @instance
         * @description Legt eine neue Ansicht an.
         * @param {object} entity - Die Ansicht die angelegt werden soll.
         */
        service.create = function (entity) {
            return $http.post(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.update
         * @function update
         * @memberOf nwbadmin.ViewService
         * @instance
         * @description Aktualisiert die angegebe Ansicht.
         * @param {object} entity - Die Ansicht die aktualisiert werden soll.
         */
        service.update = function (entity) {
            return $http.put(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.delete
         * @function delete
         * @memberOf nwbadmin.ViewService
         * @instance
         * @description Löscht die Ansicht mit der angegebenen Id.
         * @param {Number} id - Die Id der Ansicht die gelöscht werden soll.
         */
        service.delete = function (id) {
            return $http.delete(api + id).then(function (response) {
                return response.status == 200;
            });
        }

        return service;
    }]);
