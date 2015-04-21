'use strict';

angular.module('bigstockApp').factory('StockTradingProfit',
		function($resource) {
			return {
				all : $resource('api/stockTradingProfits/:id', {}, {
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
					},
					'update' : {
						method : 'PUT'
					}
				}),
				hkd : $resource('api/stockTradingProfits/hkd', {}, {
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
					},
					'update' : {
						method : 'PUT'
					}
				}),
				cny : $resource('api/stockTradingProfits/cny', {}, {
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
					},
					'update' : {
						method : 'PUT'
					}
				})
			};
		});
