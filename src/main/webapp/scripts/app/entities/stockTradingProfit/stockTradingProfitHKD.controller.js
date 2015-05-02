'use strict';

angular.module('bigstockApp')
    .controller('StockTradingProfitHKDController', function ($scope, StockTradingProfit, Principal, ParseLinks) {
        $scope.stockTradingProfits = [];
        $scope.tableTitle="bigstockApp.stockTradingProfit.home.title.hkd";
        $scope.page = 1;
        $scope.perPage = 10;
        $scope.loadAll = function() {
            StockTradingProfit.hkd.query({currency:'HKD', page: $scope.page, per_page: $scope.perPage}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.stockTradingProfits = result;
            });
        };
        $scope.loadAll();

    });
