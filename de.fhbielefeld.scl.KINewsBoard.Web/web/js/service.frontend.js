/**
 * @class nwb.FrontendService
 * @description Bietet die Schnittstellen Operationen zum Frontend Service.
 */
angular.module('nwb')
    .factory('FrontendService', ['$http', '$q', function ($http, $q) {
        var api = "../WebService/frontend/";
        var service = {};

        /**
         * @name service.getNewsEntries
         * @instance
         * @function getNewsEntries
         * @memberOf nwb.FrontendService
         * @instance
         * @description Holt alle die Nachrichteneinträge.
         * @param {Number} [page] - Die Seitenzahl ab der die Nachrichtenbeiträge abgerufen werden sollen.
         * @param {String} [keyword] - Der Suchbegriff nach dem die Nachrichtenbeiträge gefiltert werden sollen.
         * @param {Number} [view] - Die Ansicht dessen Nachrichtenbeiträge abgerufen werden sollen.
         */
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

        /**
         * @name service.getNewsEntriesByViewId
         * @instance
         * @function getNewsEntriesByViewId
         * @memberOf nwb.FrontendService
         * @instance
         * @description Ruf die Ansicht mit den Nachrichteneinträgen ab.
         * @param {Number} viewId - Die Id der Ansicht die abgerufen werden soll.
         */
        service.getNewsEntriesByViewId = function (viewId) {
            return $http.get(api + "news/findByView/" + viewId).then(function (response) {
                return response.data;
            }, function error(response) {
                return null;
            });
        };

        /**
         * @name service.getNewsEntryDetails
         * @instance
         * @function getNewsEntryDetails
         * @memberOf nwb.FrontendService
         * @instance
         * @description Ruft die Details eines Nachrichteneintrags ab.
         * @param {Number} newsId - Die Id des Nachrichteneintrags das abgerufen werden soll.
         * @param {Number} [viewId] - Die Id der Ansicht für das die Details des Nachrichteneintrags abgerufen werden soll.
         */
        service.getNewsEntryDetails = function (newsId, viewId) {

            var params = {};

            if (viewId) params.view = viewId;

            return $http.get(api + "news/" + newsId, {params: params}).then(function (response) {
                return response.data;
            });
        };

        /**
         * @name service.rateNewsEntry
         * @instance
         * @function rateNewsEntry
         * @memberOf nwb.FrontendService
         * @instance
         * @description Bewertet einen Nachrichteneintrag.
         * @param {object} newsId - Der Nachrichteneintrag der bewertet werden soll.
         * @param {boolean} rate - True, wenn der Nachrichteneintrag positiv bewertet werden soll.
         */
        service.rateNewsEntry = function (newsEntry, rate) {
            return $http.post(api + "news/" + newsEntry.id + "/rate?up=" + rate, null).then(function (response) {
                return response.data;
            });
        };

        return service;
    }]);