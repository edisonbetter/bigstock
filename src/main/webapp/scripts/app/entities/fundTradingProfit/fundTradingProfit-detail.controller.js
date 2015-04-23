'use strict';

angular.module('bigstockApp')
    .controller('FundTradingProfitDetailController', function ($scope, $stateParams, $window, FundTradingProfit, User) {
        $scope.fundTradingProfit = {};
        $scope.load = function (id) {
            FundTradingProfit.all.get({id: id}, function(result) {
              $scope.fundTradingProfit = result;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.delete = function (id) {
            FundTradingProfit.all.get({id: id}, function(result) {
                $scope.fundTradingProfit = result;
                $('#deleteFundTradingProfitConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            FundTradingProfit.all.delete({id: id},
                function () {
                    $('#deleteFundTradingProfitConfirmation').modal('hide');
                });
        };
        
        $scope.goBack = function(){
        	$window.history.back();
        };
    });
