angular.module('nwbadmin')
    .factory('AnalyzerService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/analyzer/"
        var service = {};

        service.getAll = function () {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        service.get = function (id) {
            return $http.get(api + id).then(function (response) {
                return response.data;
            });
        };

        service.create = function (entity) {
            return $http.post(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        service.update = function (entity) {
            return $http.put(api, entity).then(function (response) {
                return response.status == 200;
            });
        }

        service.delete = function (id) {
            return $http.delete(api + id).then(function (response) {
                return response.status == 200;
            });
        }

        return service;
    }]);