'use strict';

/**
 * @ngdoc function
 * @name studentActivationFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentActivationFrontendApp
 */
angular.module('studentActivationFrontendApp')
  .controller('MainCtrl', function ($location, $scope, Student, ActivateStudent) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    var token = ($location.search()).t;
    if (token === undefined || token === '') {
      $scope.validToken = false;
    } else {
      $scope.validToken = true;
      $scope.activated = false;
      $scope.agree = function () {
        ActivateStudent.activate({t: token}, function (response) {
          console.log(response);
          $scope.activated = true;
        });
      };

      Student.get({t: token}, function (student) {
        $scope.fullname = student.fullName;
        $scope.allowActivation = student.allowActivation;
      });
    }
  });
