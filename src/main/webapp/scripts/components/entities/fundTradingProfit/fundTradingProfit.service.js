'use strict';

angular.module('bigstockApp')
    .factory('FundTradingProfit', function ($resource) {
        return $resource('api/fundTradingProfits/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });