'use strict';

/**
 * @ngdoc service
 * @name studentActivationFrontendApp.Student
 * @description
 * # Student
 * Factory in the studentActivationFrontendApp.
 */
angular.module('studentActivationFrontendApp')
  .factory('Student', function ($resource) {
    return $resource("/account/activation/student", {t: '@t'});
  });
