'use strict';

angular.module('nwb.frontend', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('frontend', {
                url: '/frontend',
                templateUrl: 'views/frontend/index.html',
                controller: 'FrontendViewController'
            })
            .state('view', {
                url: '/frontend/{viewId}',
                templateUrl: 'views/frontend/index.html',
                controller: 'FrontendViewController'
            });

    }])
    .controller('FrontendViewController',
        ['$scope', '$location', '$stateParams', 'FrontendService', 'localStorageService',
            function ($scope, $location, $stateParams, FrontendService, localStorageService, spinnerService) {

                $scope.page = 1;
                $scope.keyword = "";
                $scope.contentsize = 200;

                $scope.updateView = function () {
                    FrontendService.getNewsEntries($scope.page, $scope.keyword, $stateParams.viewId).then(function (entries) {
                        if ($scope.page == 1)
                            $scope.entries = entries;
                        else
                            $scope.entries = $scope.entries.concat(entries);
                    });
                };

                $scope.search = function () {
                    $scope.page = 1;
                    $scope.updateView();
                };

                $scope.loadMoreNews = function () {
                    $scope.page += 1;
                    $scope.updateView();
                };

                $scope.rate = function (entry, up) {
                    if ($scope.isRated(entry)) return;

                    localStorageService.set(entry.id, up);
                    FrontendService.rateNewsEntry(entry, up).then(function (updated) {
                        entry.rating = updated.rating;
                    });
                };

                $scope.isRated = function (entry) {
                    return localStorageService.get(entry.id) !== null;
                };

                $scope.isCurrentVote = function (entry, up) {
                    if (!$scope.isRated(entry))
                        return false;

                    return localStorageService.get(entry.id) === up;
                };

                $scope.updateView();

            }]);

angular.module('nwb.frontend').controller('ModalDemoCtrl', function ($uibModal, $log, $document) {
    var $ctrl = this;
    $ctrl.items = ['item1', 'item2', 'item3'];

    $ctrl.animationsEnabled = true;

    $ctrl.open = function (size, entry, parentSelector) {
        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;

        $ctrl.entry = entry;

        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return $ctrl.items;
                },
                entry: function () {
                    return $ctrl.entry;

                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $ctrl.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('nwb.frontend').controller('ModalInstanceCtrl', function ($uibModalInstance, items, entry) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.entry = entry;
    $ctrl.selected = {
        item: $ctrl.items[0]
    };

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.selected.item);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

// Please note that the close and dismiss bindings are from $uibModalInstance.

angular.module('nwb.frontend').component('modalComponent', {
    templateUrl: 'myModalContent.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: function () {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.items = $ctrl.resolve.items;
            $ctrl.selected = {
                item: $ctrl.items[0]
            };
        };

        $ctrl.ok = function () {
            $ctrl.close({$value: $ctrl.selected.item});
        };

        $ctrl.cancel = function () {
            $ctrl.dismiss({$value: 'cancel'});
        };
    }
});