'use strict';

angular.module('bigstockApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('stockTradingProfit', {
                parent: 'entity',
                url: '/stockTradingProfits',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.home.title.all'
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
            .state('stockTradingProfitHKD', {
                parent: 'entity',
                url: '/stockTradingProfits/hkd',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.home.title.hkd'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/stockTradingProfit/stockTradingProfits.html',
                        controller: 'StockTradingProfitHKDController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('stockTradingProfit');
                        return $translate.refresh();
                    }]
                }
            })
            .state('stockTradingProfitCNY', {
                parent: 'entity',
                url: '/stockTradingProfits/cny',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.home.title.cny'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/stockTradingProfit/stockTradingProfits.html',
                        controller: 'StockTradingProfitCNYController'
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
                url: '/stockTradingProfits/:id',
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
