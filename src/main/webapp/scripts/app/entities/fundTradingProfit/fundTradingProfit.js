'use strict';

angular.module('bigstockApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('fundTradingProfit', {
                parent: 'entity',
                url: '/fundTradingProfit',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fundTradingProfit/fundTradingProfits.html',
                        controller: 'FundTradingProfitController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('fundTradingProfit');
                        return $translate.refresh();
                    }]
                }
            })
            .state('fundTradingProfitDetail', {
                parent: 'entity',
                url: '/fundTradingProfit/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.stockTradingProfit.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fundTradingProfit/fundTradingProfit-detail.html',
                        controller: 'FundTradingProfitDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('fundTradingProfit');
                        return $translate.refresh();
                    }]
                }
            });
    });
