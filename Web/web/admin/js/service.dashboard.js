/**
 * @class nwbadmin.DashboardService
 * @description Schnittstelle für die Übersicht über das Newsboard.
 */
angular.module('nwbadmin')
    .factory('DashboardService', ['$http', '$q', function ($http, $q) {
        var api = "../WebService/backend/dashboard/"
        var service = {};

        /**
         * @name service.get
         * @function get
         * @memberOf nwbadmin.DashboardService
         * @instance
         * @description Ruft die Informationen zum Newsboard ab.
         */
        service.get = function () {
            return $http.get(api).then(function (response) {
                return response.data;
            });
        };

        return service;
    }]);