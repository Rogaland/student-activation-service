'use strict';

describe('Service: ActivateStudent', function () {

  // load the service's module
  beforeEach(module('studentActivationFrontendApp'));

  // instantiate service
  var ActivateStudent;
  beforeEach(inject(function (_ActivateStudent_) {
    ActivateStudent = _ActivateStudent_;
  }));

  it('should do something', function () {
    expect(!!ActivateStudent).toBe(true);
  });

});
