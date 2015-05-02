'use strict';

angular.module('bigstockApp')
    .controller('FundTradingProfitHKDController', function ($scope, FundTradingProfit, Principal, ParseLinks) {
        $scope.fundTradingProfits = [];
        $scope.tableTitle="bigstockApp.fundTradingProfit.home.title.hkd";
        $scope.page = 1;
        $scope.perPage = 10;
        $scope.loadAll = function() {
            FundTradingProfit.hkd.query({currency:'HKD', page: $scope.page, per_page: $scope.perPage}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.fundTradingProfits = result;
            });
        };
        $scope.loadAll();

    });
