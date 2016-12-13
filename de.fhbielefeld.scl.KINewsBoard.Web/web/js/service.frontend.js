angular.module('nwb')
    .factory('FrontendService', ['$http', '$q', function ($http, $q) {
        var api = "/WebService/frontend/";
        var service = {};

        service.getNewsEntries = function (page, keyword, view) {

            return $http.get(api + "news", {
                params: {
                    page: page,
                    keyword: keyword,
                    view: view
                }
            }).then(function (response) {
                return response.data;
            });
        };

        service.getNewsEntriesByViewId = function (viewId) {
            return $http.get(api + "news/findByView/" + viewId).then(function (response) {
                return response.data;
            }, function error(response) {
                return null;
            });
        };

        service.getNewsEntryDetails = function (newsId) {
            return $http.get(api + "news/" + newsId).then(function (response) {
                return response.data;
            });
        };

        service.rateNewsEntry = function (newsEntry, rate) {
            return $http.post(api + "news/" + newsEntry.id + "/rate?up=" + rate, null).then(function (response) {
                return response.data;
            });
        }

        return service;
    }]);