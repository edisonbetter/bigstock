'use strict';

angular.module('bigstockApp').factory('TradingProfit',
		function($resource) {
			return {
				all : $resource('api/tradingProfits/', {}, {
					'query' : {
						method : 'GET',
						isArray : true
					},
					'get' : {
						method : 'GET',
						isArray : false
					}
				}),
				hkd : $resource('api/tradingProfits/hkd', {}, {
					'query' : {
						method : 'GET',
						isArray : true
					},
					'get' : {
						method : 'GET',
						transformResponse : function(data) {
							data = angular.fromJson(data);
							return data;
						}
					}
				}),
				cny : $resource('api/tradingProfits/cny', {}, {
					'query' : {
						method : 'GET',
						isArray : true
					},
					'get' : {
						method : 'GET',
						transformResponse : function(data) {
							data = angular.fromJson(data);
							return data;
						}
					}
				})
			};
		});
