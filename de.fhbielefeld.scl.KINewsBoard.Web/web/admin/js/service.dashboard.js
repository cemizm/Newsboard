angular.module('nwbadmin')
    .factory('DashboardService', ['$http', '$q', function ($http, $q) {
        var api = "../../WebService/backend/dashboard/"
        var service = {};

        service.get = function (id) {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        return service;
    }]);