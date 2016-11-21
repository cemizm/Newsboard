angular.module('nwbadmin')
    .factory('TokenService', function () {
        var service = {};

        service.rand = function() {
            return Math.random().toString(36).substr(2); // remove `0.`
        };

        service.generate = function () {
            return service.rand() + service.rand(); // to make it longer
        }

        return service;
    });