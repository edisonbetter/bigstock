'use strict';

angular.module('bigstockApp')
    .controller('FundTradingProfitController', function ($scope, FundTradingProfit, Principal, ParseLinks) {
        $scope.fundTradingProfits = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            FundTradingProfit.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.fundTradingProfits = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
        	Principal.identity().then(function(account) {
        		$scope.fundTradingProfit.username=account.login;
        	});
            FundTradingProfit.update($scope.fundTradingProfit,
                function () {
                    $scope.loadAll();
                    $('#saveFundTradingProfitModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            FundTradingProfit.get({id: id}, function(result) {
                $scope.fundTradingProfit = result;
                $('#saveFundTradingProfitModal').modal('show');
            });
        };

        

        $scope.clear = function () {
            $scope.fundTradingProfit = {creationDate: null, code: null, name: null, quantity: null, buyPrice: null, sellPrice: null, buyTransactionFee: null, sellTransactionFee: null, buyConsideration: null, sellConsideration: null, profit: null, currency: null, country: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });