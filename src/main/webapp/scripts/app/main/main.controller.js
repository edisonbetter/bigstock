'use strict';

angular.module('bigstockApp')
    .controller('MainController', function ($scope, TradingProfit, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        
        $scope.tradingProfit = {};
        $scope.loadProfit=function(){
        	TradingProfit.all.get(function(result){
        		$scope.tradingProfit = result;
        	});
        };
        $scope.loadProfit();
    });
