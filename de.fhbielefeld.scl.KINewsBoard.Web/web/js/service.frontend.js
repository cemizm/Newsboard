angular.module('nwb')
    .factory('FrontendService', ['$http', '$q', function ($http, $q) {
        var api = "../WebService/frontend/";
        var service = {};

        service.getNewsEntries = function (page, keyword, view) {

            var params = {};

            if (page) params.page = page;
            if (keyword) params.keyword = keyword;
            if (view) params.view = view;

            return $http.get(api + "news", {params: params})
                .then(function (response) {
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

        service.getNewsEntryDetails = function (newsId, viewId) {

            var params = {};

            if (viewId) params.view = viewId;

            return $http.get(api + "news/" + newsId, {params: params}).then(function (response) {
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