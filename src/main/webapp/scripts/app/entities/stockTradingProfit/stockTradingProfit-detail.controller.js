'use strict';

angular.module('bigstockApp')
    .controller('StockTradingProfitDetailController', function ($scope, $stateParams, StockTradingProfit, User) {
        $scope.stockTradingProfit = {};
        $scope.load = function (id) {
            StockTradingProfit.get({id: id}, function(result) {
              $scope.stockTradingProfit = result;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.delete = function (id) {
            StockTradingProfit.get({id: id}, function(result) {
                $scope.stockTradingProfit = result;
                $('#deleteStockTradingProfitConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            StockTradingProfit.delete({id: id},
                function () {
                    $('#deleteStockTradingProfitConfirmation').modal('hide');
                });
        };
    });
