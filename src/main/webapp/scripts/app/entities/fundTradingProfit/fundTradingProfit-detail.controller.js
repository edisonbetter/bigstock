'use strict';

angular.module('bigstockApp')
    .controller('FundTradingProfitDetailController', function ($scope, $stateParams, FundTradingProfit, User) {
        $scope.fundTradingProfit = {};
        $scope.load = function (id) {
            FundTradingProfit.get({id: id}, function(result) {
              $scope.fundTradingProfit = result;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.delete = function (id) {
            FundTradingProfit.get({id: id}, function(result) {
                $scope.fundTradingProfit = result;
                $('#deleteFundTradingProfitConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            FundTradingProfit.delete({id: id},
                function () {
                    $('#deleteFundTradingProfitConfirmation').modal('hide');
                });
        };
    });
