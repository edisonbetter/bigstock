'use strict';

angular.module('bigstockApp')
    .controller('StockTradingProfitCNYController', function ($scope, StockTradingProfit, Principal, ParseLinks) {
        $scope.stockTradingProfits = [];
        $scope.tableTitle="bigstockApp.stockTradingProfit.home.title.cny";
        $scope.page = 1;
        $scope.loadAll = function() {
            StockTradingProfit.cny.query({currency:'CNY', page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.stockTradingProfits = result;
            });
        };
        $scope.loadAll();

    });
