/**
 * Created by cem on 10.12.16.
 */
angular.module('nwb.placeholder', []).directive('nwbSrc', function () {
    return {
        restrict: 'A',
        scope: {nwbSrc: '@'},
        link: function ($scope, element, attr) {
            var placeholder = "assets/placeholder.png";
            var update = function (value) {
                attr.$set("src", value != null && value != "" ? value : placeholder);
            };

            element.on("error", function () {
                attr.$set("src", placeholder);
            });

            attr.$observe("nwbSrc", update);

            update(attr.nwbSrc);
        }
    };
});