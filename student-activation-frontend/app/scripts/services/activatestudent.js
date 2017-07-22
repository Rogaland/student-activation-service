'use strict';

/**
 * @ngdoc service
 * @name studentActivationFrontendApp.ActivateStudent
 * @description
 * # ActivateStudent
 * Factory in the studentActivationFrontendApp.
 */
angular.module('studentActivationFrontendApp')
  .factory('ActivateStudent', function ($resource) {
    return $resource('/account/activation/student', {t: '@t'}, {
      'activate': {
        method: 'POST'
      }
    })
  });
