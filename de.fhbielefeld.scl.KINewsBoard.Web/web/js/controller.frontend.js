'use strict';

angular.module('nwb.frontend', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('frontend', {
                url: '/?keyword&view',
                templateUrl: 'views/frontend/index.html',
                controller: 'FrontendViewController'
            });

    }])
    .controller('FrontendViewController',
        ['$scope', '$location', '$stateParams', 'FrontendService', 'localStorageService', '$state',
            function ($scope, $location, $stateParams, FrontendService, localStorageService, $state) {

                $scope.page = 1;
                $scope.keyword = $stateParams.keyword;
                $scope.contentsize = 200;
                $scope.isLoading = true;
                $scope.moreEntries = true;
                $scope.entries = [];
                $scope.busy = false;

                $scope.updateView = function () {
                    $scope.isLoading = true;

                    FrontendService.getNewsEntries($scope.page, $stateParams.keyword, $stateParams.view).then(function (entries) {
                        $scope.entries = $scope.entries.concat(entries);
                        $scope.isLoading = false;
                        $scope.moreEntries = entries != null && entries.length == 20;
                    });
                };

                $scope.search = function () {
                    $state.go('frontend', {keyword: $scope.keyword, view: $stateParams.view})
                };

                $scope.loadMoreNews = function () {
                    if (!$scope.moreEntries || $scope.isLoading)
                        return;

                    $scope.page += 1;
                    $scope.updateView();

                    $scope.busy = false;
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

angular.module('nwb.frontend').controller('ModalDemoCtrl', function ($uibModal, $log, $document, $sce, FrontendService) {
    var $ctrl = this;
    $ctrl.items = ['item1', 'item2', 'item3'];

    $ctrl.animationsEnabled = true;
    $ctrl.colored = "";

    $ctrl.getAnalyzerList = function () {
        var analyzerArr = [];
        $ctrl.entry.analyzerResults.forEach(function (analyzerResult) {
            analyzerArr.push({
                "id" : analyzerResult.analyzer.id,
                "name" : analyzerResult.analyzer.name
            });
        });
        return analyzerArr;
    };

    $ctrl.colorText = function (entry, analyzerIndex) {
        var colored = "";

        String.prototype.replaceBetween = function(start, end, what) {
            return this.substring(0, start) + what + this.substring(end);
        };

        var currAnalyzer = -1;
        var contentArr = entry.content.split('');
        var contentArrIndex = 0;

        contentArr.forEach(function (charIndex) {
            if (currAnalyzer < 0) {

            }
            else {

            }
                contentArrIndex++;
        });

        entry.analyzerResults[analyzerIndex].sentenceResults.forEach(function (sentenceResult) {
            var cssAttr = sentenceResult.value >= 0 ? "analyzerTextPos" : "analyzerTextNeg";
            colored = entry.content.replaceBetween(sentenceResult.charStart, sentenceResult.charEnd, '<span class="' + cssAttr + '">' + entry.content.substring(sentenceResult.charStart, sentenceResult.charEnd) + '</span>');
        });

        return colored;

    };


    $ctrl.open = function (size, entry, parentSelector) {
        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;

        FrontendService.getNewsEntryDetails(entry.id).then(function (details) {
            $ctrl.entry = details;
            $ctrl.analyzerIndex = 0;
            $ctrl.analyzerArr = $ctrl.getAnalyzerList();

            $ctrl.colored = $ctrl.colorText(details, $ctrl.analyzerIndex);



            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myModalContent',
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

                    },
                    colored: function () {
                        return $ctrl.colored;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $ctrl.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        });



    };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('nwb.frontend').controller('ModalInstanceCtrl', function ($uibModalInstance, items, entry, colored) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.entry = entry;
    $ctrl.colored = colored;
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
    templateUrl: 'myModalContent',
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
            $ctrl.colored = $ctrl.resolve.colored;
        };

        $ctrl.ok = function () {
            $ctrl.close({$value: $ctrl.selected.item});
        };

        $ctrl.cancel = function () {
            $ctrl.dismiss({$value: 'cancel'});
        };
    }
});