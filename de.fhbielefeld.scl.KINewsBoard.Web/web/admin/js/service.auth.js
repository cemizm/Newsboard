/**
 * @class nwbadmin.AuthService
 * @description Schnittstelle für die Anmeldung.
 */
angular.module('nwbadmin')
    .factory('AuthService', ['$http', '$rootScope', '$window', function ($http, $rootScope, $window) {
        var api = "../../WebService/backend/auth/"
        var service = {};

        /**
         * @name service.login
         * @function login
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Meldet den Benutzer mit angegeben Benutzernamen und Password an.
         * @param {String} username - Benutzername für Anmledung
         * @param {String} password - Passwort für Anmeldung
         */
        service.login = function (username, password) {
            var config = {
                headers: {
                    "username": username,
                    "password": password
                }
            };

            return $http.post(api + "login", {}, config).then(function (response) {
                if (response.status == 401) return null;

                service.setUser(response.data)

                return response.data;
            });
        };

        /**
         * @name service.getUser
         * @function getUser
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Ruft die Benutzerdaten des angmeldeten Benutzers ab.
         */
        service.getUser = function () {
            return $http.get(api + "user").then(function (response) {
                if (response.status == 401) return null;
                service.setUser(response.data);
                return response.data;
            });
        }

        /**
         * @name service.logout
         * @function logout
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Meldet den angemledeten Benutzer ab.
         */
        service.logout = function () {
            return $http.post(api + "logout", null).then(function (response) {
                service.setUser(null);
                return response;
            });
        };

        /**
         * @name service.setUser
         * @function setUser
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Setzen den Benutzer Applikationsweit und speichert den Token im SessionStorage.
         * @param {?Object} user - Der Benutzer der gesetzt werden soll. Null um den Benutzer abzumelden.
         */
        service.setUser = function (user) {
            $rootScope.user = user;

            if (user)
                $window.sessionStorage.token = user.authtoken;
            else
                delete $window.sessionStorage.token;

            $rootScope.$broadcast(user == null ? 'unauthorized' : 'authorized');
        }

        /**
         * @name service.expired
         * @function expired
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Entfernt den Benutzer aus dem SessionStorage und veranlasst die Navigation zum Anmeldebildschirm.
         */
        service.expired = function () {
            service.setUser(null);
        };

        /**
         * @name service.isAuthenticated
         * @function isAuthenticated
         * @memberOf nwbadmin.AuthService
         * @instance
         * @description Prüft ob der Benutzer authentifiziert ist.
         */
        service.isAuthenticated = function () {
            return $window.sessionStorage.token != null;
        };

        return service;
    }]);