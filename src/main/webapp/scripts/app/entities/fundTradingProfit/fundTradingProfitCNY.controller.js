'use strict';

angular.module('bigstockApp')
    .controller('FundTradingProfitCNYController', function ($scope, FundTradingProfit, Principal, ParseLinks) {
        $scope.fundTradingProfits = [];
        $scope.tableTitle="bigstockApp.fundTradingProfit.home.title.cny";
        $scope.page = 1;
        $scope.loadAll = function() {
            FundTradingProfit.cny.query({currency:'CNY', page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.fundTradingProfits = result;
            });
        };
        $scope.loadAll();

    });
