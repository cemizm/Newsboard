angular.module('nwb')
    .factory('FrontendService', ['$http', '$q', function ($http, $q) {
        var api = "http://localhost:8080/WebService/frontend/"
        var service = {};

        service.getNewsEntries = function (page, keyword) {
            return $http.get(api + "news", {
                params: {
                    page: page,
                    keyword: keyword
                }
            }).then(function (response) {
                return response.data;
            });
        };

        service.getNewsEntriesByViewId = function (viewId) {
            return $http.get(api + "news/findByView/" + viewId).then(function (response) {
                return response.data;
            });
        };

        service.getNewsEntryDetails = function (newsId) {
            return $http.get(api + "news/" + newsId).then(function (response) {
                return response.data;
            });
        }

        return service;
    }]);