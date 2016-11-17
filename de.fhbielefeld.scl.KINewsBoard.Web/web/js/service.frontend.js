angular.module('nwb')
    .factory('FrontendService', ['$http', '$q', function ($http, $q) {
        var api = "http://localhost:8080/WebService/frontend/"
        var service = {};

        service.getNewsEntries = function (page, keyword) {
            // return test JSON for dev without DB
            return $http.get("res/response.json")
                .then(function (response) {
                return response.data;
            });

            return $http.get(api + "news", {
                params: {
                    page: page,
                    keyword: keyword
                }
            }).then(function (response) {
                return response.data;
            });
        };

        service.getNewsEntriesByViewId = function (viewId, page) {
            return $http.get(api + "news/findByViewId/" + viewId, {
                params: {
                    page: page
                }
            }).then(function (response) {
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