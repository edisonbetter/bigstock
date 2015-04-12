/* globals $ */
'use strict';

angular.module('bigstockApp')
    .directive('bigstockAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
