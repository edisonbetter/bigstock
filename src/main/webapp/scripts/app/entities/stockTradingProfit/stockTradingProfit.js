'use strict';

angular.module('bigstockApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('stockTradingProfit', {
                parent: 'entity',
                url: '/stockTradingProfit',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/stockTradingProfit/stockTradingProfits.html',
                        controller: 'StockTradingProfitController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('stockTradingProfit');
                        return $translate.refresh();
                    }]
                }
            })
            .state('stockTradingProfitDetail', {
                parent: 'entity',
                url: '/stockTradingProfit/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/stockTradingProfit/stockTradingProfit-detail.html',
                        controller: 'StockTradingProfitDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('stockTradingProfit');
                        return $translate.refresh();
                    }]
                }
            });
    });
