'use strict';

angular.module('bigstockApp')
    .controller('StockTradingProfitController', function ($scope, StockTradingProfit, Principal, ParseLinks) {
        $scope.stockTradingProfits = [];
        $scope.tableTitle="bigstockApp.stockTradingProfit.home.title.all";
        $scope.page = 1;
        $scope.loadAll = function() {
            StockTradingProfit.all.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.stockTradingProfits = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
        	Principal.identity().then(function(account) {
        		$scope.stockTradingProfit.username=account.login;
        	});
        	
            StockTradingProfit.all.update($scope.stockTradingProfit,
                function () {
                    $scope.loadAll();
                    $('#saveStockTradingProfitModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            StockTradingProfit.all.get({id: id}, function(result) {
                $scope.stockTradingProfit = result;
                $('#saveStockTradingProfitModal').modal('show');
            });
        };

        

        $scope.clear = function () {
            $scope.stockTradingProfit = {creationDate: null, code: null, name: null, quantity: null, buyPrice: null, sellPrice: null, buyTransactionFee: null, sellTransactionFee: null, buyConsideration: null, sellConsideration: null, profit: null, currency: null, market: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
