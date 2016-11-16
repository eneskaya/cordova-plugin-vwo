var exec = require('cordova/exec');

exports.start = function(arg0, success, error) {
  exec(success, error, "VWOCordova", "start", [ arg0 ]);
};

exports.getAllObject = function(success, error) {

  exec(
    function( jsonString ) {
      success(JSON.parse(jsonString));
    },
    error,
    "VWOCordova",
    "getAllObject",
    []
  );

};

exports.markConversionForGoal = function(goalIdentifier, value, success, error) {
  exec(
    success,
    error,
    "VWOCordova",
    "markConversionForGoal",
    [
      goalIdentifier,
      value
    ]
  );
};
