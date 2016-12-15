/**
 * Created by Veronika on 12/1/2016.
 */
angular.module('nwbadmin')
    .factory('ViewService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/view/"
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
