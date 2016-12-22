/**
 * @class nwbadmin.GroupSetService
 * @description Schnittstelle für die CRUD Operationen der Gruppen.
 */
angular.module('nwbadmin')
    .factory('GroupSetService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/group/"
        var service = {};

        /**
         * @name service.getAll
         * @function getAll
         * @memberOf nwbadmin.GroupSetService
         * @instance
         * @description Ruft alle Gruppen ab.
         */
        service.getAll = function () {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.get
         * @function get
         * @memberOf nwbadmin.GroupSetService
         * @instance
         * @description Ruft die Gruppe mit der angegebenen Id ab.
         * @param {Number} id - Die Id der Gruppe die abgerufen werden soll.
         */
        service.get = function (id) {
            return $http.get(api + id).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.create
         * @function create
         * @memberOf nwbadmin.GroupSetService
         * @instance
         * @description Legt eine neue Gruppe an.
         * @param {object} entity - Die Gruppe die angelegt werden soll.
         */
        service.create = function (entity) {
            return $http.post(api, entity).then(function (response) {
                return response.status == 200;
            });
        };

        /**
         * @name service.update
         * @function update
         * @memberOf nwbadmin.GroupSetService
         * @instance
         * @description Aktualisiert die angegebe Gruppe.
         * @param {object} entity - Die Gruppe die aktualisiert werden soll.
         */
        service.update = function (entity) {
            return $http.put(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.delete
         * @function delete
         * @memberOf nwbadmin.GroupSetService
         * @instance
         * @description Löscht die Gruppe mit der angegebenen Id.
         * @param {Number} id - Die Id der Gruppe die gelöscht werden soll.
         */
        service.delete = function (id) {
            return $http.delete(api + id).then(function (response) {
                return response.status == 200;
            });
        }

        return service;
    }]);