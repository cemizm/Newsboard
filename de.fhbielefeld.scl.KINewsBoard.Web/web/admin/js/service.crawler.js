angular.module('nwbadmin')
    .factory('CrawlerService', ['$http', '$q', function ($http, $q) {
        var api = "/WebService/backend/"
        var service = {};

        service.getAll = function () {
            return $http.get(api + "crawler").then(function (response) {
                return response.data;
            });
        };

        service.get = function (id) {
            return $http.get(api + "crawler/" + id).then(function (response) {
                return response.data;
            });
        };

        service.create = function (crawler) {
            return $http.post(api + "crawler", crawler).then(function (response) {
                return true;
            });
        }

        service.update = function (crawler) {
            return $http.put(api + "crawler", crawler).then(function (response) {
                return true;
            });
        }

        service.delete = function (crawlerId) {
            return $http.delete(api + "crawler/" + crawlerId).then(function (response) {
                return true;
            });
        }

        return service;
    }]);