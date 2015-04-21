'use strict';

angular.module('bigstockApp')
    .controller('StockTradingProfitDetailController', function ($scope, $stateParams, $window, StockTradingProfit, User) {
        $scope.stockTradingProfit = {};
        $scope.load = function (id) {
            StockTradingProfit.all.get({id: id}, function(result) {
              $scope.stockTradingProfit = result;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.delete = function (id) {
            StockTradingProfit.all.get({id: id}, function(result) {
                $scope.stockTradingProfit = result;
                $('#deleteStockTradingProfitConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            StockTradingProfit.all.delete({id: id},
                function () {
                    $('#deleteStockTradingProfitConfirmation').modal('hide');
                });
        };
        
        $scope.goBack = function(){
        	$window.history.back();
        }
    });
