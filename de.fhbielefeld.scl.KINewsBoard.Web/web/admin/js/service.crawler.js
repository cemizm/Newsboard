/**
 * @class nwbadmin.CrawlerService
 * @description Schnittstelle für die CRUD Operationen der Crawler.
 */
angular.module('nwbadmin')
    .factory('CrawlerService', ['$http', '$q', function ($http, $q) {
        var api = "../WebService/backend/"
        var service = {};

        /**
         * @name service.getAll
         * @function getAll
         * @memberOf nwbadmin.CrawlerService
         * @instance
         * @description Ruft alle Crawler ab.
         */
        service.getAll = function () {
            return $http.get(api + "crawler").then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.get
         * @function get
         * @memberOf nwbadmin.CrawlerService
         * @instance
         * @description Ruft den Crawler mit der angegebenen Id ab.
         * @param {Number} id - Die Id des Crawlers der abgerufen werden soll.
         */
        service.get = function (id) {
            return $http.get(api + "crawler/" + id).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.create
         * @function create
         * @memberOf nwbadmin.CrawlerService
         * @instance
         * @description Legt einen neuen Crawler an.
         * @param {object} crawler - Der Crawler der angelegt werden soll.
         */
        service.create = function (crawler) {
            return $http.post(api + "crawler", crawler).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.update
         * @function update
         * @memberOf nwbadmin.CrawlerService
         * @instance
         * @description Aktualisiert den angegeben Crawler.
         * @param {object} crawler - Der Crawler der aktualisiert werden soll.
         */
        service.update = function (crawler) {
            return $http.put(api + "crawler", crawler).then(function (response) {
                return response.status == 200;
            });
        }

        /**
         * @name service.delete
         * @function delete
         * @memberOf nwbadmin.CrawlerService
         * @instance
         * @description Löscht den Crawler mit der angegebenen Id.
         * @param {Number} crawlerId - Die Id des Crawlers der gelöscht werden soll.
         */
        service.delete = function (crawlerId) {
            return $http.delete(api + "crawler/" + crawlerId).then(function (response) {
                return response.status == 200;
            });
        }

        return service;
    }]);