angular.module('nwbadmin').factory('AuthInterceptor', ['$rootScope', '$window', function ($rootScope, $window) {
    return {
        'request': function (config) {
            if ($window.sessionStorage.token != null) {
                config.headers = config.headers || {};
                config.headers.Authorization = "Basic " + $window.sessionStorage.token;
            }
            return config;
        },
        'responseError': function (response) {
            if (response.status === 401)
            {
                $rootScope.user = null;
                delete $window.sessionStorage.token;
                $rootScope.$broadcast('unauthorized');
            }

            return response;
        }
    };
}]);