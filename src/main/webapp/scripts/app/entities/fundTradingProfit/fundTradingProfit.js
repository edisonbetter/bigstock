'use strict';

angular.module('bigstockApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('fundTradingProfit', {
                parent: 'entity',
                url: '/fundTradingProfit',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.fundTradingProfit.home.title'
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
            .state('fundTradingProfitHKD', {
                parent: 'entity',
                url: '/fundTradingProfit/hkd',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.fundTradingProfit.home.title.hkd'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fundTradingProfit/fundTradingProfits.html',
                        controller: 'FundTradingProfitHKDController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('fundTradingProfit');
                        return $translate.refresh();
                    }]
                }
            })
            .state('fundTradingProfitCNY', {
                parent: 'entity',
                url: '/fundTradingProfit/cny',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'bigstockApp.fundTradingProfit.home.title.cny'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/fundTradingProfit/fundTradingProfits.html',
                        controller: 'FundTradingProfitCNYController'
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
                    pageTitle: 'bigstockApp.fundTradingProfit.detail.title'
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
