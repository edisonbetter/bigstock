'use strict';

angular.module('bigstockApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
