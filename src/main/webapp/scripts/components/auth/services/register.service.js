'use strict';

angular.module('bigstockApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


