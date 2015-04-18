'use strict';

angular.module('bigstockApp')
    .factory('FundTradingProfit', function ($resource) {
        return $resource('api/fundTradingProfits/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
//                    var creationDateFrom = data.creationDate.split("-");
//                    data.creationDate = new Date(new Date(creationDateFrom[0], creationDateFrom[1] - 1, creationDateFrom[2]));
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });