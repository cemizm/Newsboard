/**
 * @class nwbadmin.ErrorInterceptor
 * @description Interceptor die jeden HTTP-Response auf Fehlercode prüft und die Fehlmeldung broadcastet.
 */
angular.module('nwbadmin').factory('ErrorInterceptor', ['$rootScope', '$window', function ($rootScope, $window) {
    return {
        'responseError': function (response) {
            if (response.status !== 401)
                $rootScope.$broadcast('error', response.data);

            return response;
        }
    };
}]);