angular.module('nwbadmin')
    .factory('AuthService', ['$http', '$rootScope', '$window', function ($http, $rootScope, $window) {
        var api = "/WebService/backend/auth/"
        var service = {};

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

        service.getUser = function () {
            return $http.get(api + "user").then(function (response) {
                if (response.status == 401) return null;
                service.setUser(response.data);
                return response.data;
            });
        }

        service.logout = function () {
            return $http.post(api + "logout", null).then(function (response) {
                service.setUser(null);
                return response;
            });
        };

        service.setUser = function (user) {
            $rootScope.user = user;

            if (user)
                $window.sessionStorage.token = user.authtoken;
            else
                delete $window.sessionStorage.token;

            $rootScope.$broadcast(user == null ? 'unauthorized' : 'authorized');
        }

        service.expired = function () {
            service.setUser(null);
        };

        service.isAuthenticated = function () {
            return $window.sessionStorage.token != null;
        };

        return service;
    }]);