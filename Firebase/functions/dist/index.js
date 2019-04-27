"use strict";

function asyncGeneratorStep(gen, resolve, reject, _next, _throw, key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { Promise.resolve(value).then(_next, _throw); } }

function _asyncToGenerator(fn) { return function () { var self = this, args = arguments; return new Promise(function (resolve, reject) { var gen = fn.apply(self, args); function _next(value) { asyncGeneratorStep(gen, resolve, reject, _next, _throw, "next", value); } function _throw(err) { asyncGeneratorStep(gen, resolve, reject, _next, _throw, "throw", err); } _next(undefined); }); }; }

const escapeHtml = require('escape-html');

const functions = require('firebase-functions');

const admin = require('firebase-admin');

admin.initializeApp();
exports.newPushNotification = functions.https.onRequest((req, res) => {
  // This registration token comes from the client FCM SDKs.
  var adminToken = ["eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O", "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5"];
  adminToken.forEach(function (userToken) {
    try {
      var message = {
        data: {
          building: 'Whitehouse',
          time: '19:45'
        },
        notification: {
          title: "Hello !",
          body: "Somebody is in front of your house"
        },
        token: userToken
      };
      admin.messaging().send(message);
      res.send("Message sent!");
    } catch (error) {
      console.log("testNotification error : " + error + "\n");
      res.send("Message not sent! Update the key I think");
    }
  });
});

function tokenValidReport() {
  return _tokenValidReport.apply(this, arguments);
}

function _tokenValidReport() {
  _tokenValidReport = _asyncToGenerator(function* () {
    var adminToken = ["eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O", "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5"];
    adminToken.forEach(function (userToken) {
      try {
        var message = {
          notification: {
            title: "Hi, token still valid :) !"
          },
          token: userToken
        };
        admin.messaging().send(message);
      } catch (error) {
        console.log("testNotification error : " + error + "\n");
        console.log("Message not sent! Update the key I think");
      }
    });
  });
  return _tokenValidReport.apply(this, arguments);
}

function checkForSpy(_x) {
  return _checkForSpy.apply(this, arguments);
}

function _checkForSpy() {
  _checkForSpy = _asyncToGenerator(function* (request) {
    try {
      if (request.body.secretCode !== "seara") {
        spyReport();
      }
    } catch (error) {
      spyReport();
    }
  });
  return _checkForSpy.apply(this, arguments);
}

function spyReport() {
  return _spyReport.apply(this, arguments);
}
/* ======================================================================= */
// exports.dashboard = functions.https.onRequest((req, res) => {
//     tokenValidReport();
//     return dashboard(req, res);
// });
// exports.history = functions.https.onRequest((req, res) => {
//     return history(req, res);
// });
// exports.historyLogger = functions.https.onRequest((req, res) => {
//     return historyLogger(req, res);
// });
// exports.dashboardHandler = functions.https.onRequest((req, res) => {
//     return dashboardHandler(req, res);
// });


function _spyReport() {
  _spyReport = _asyncToGenerator(function* () {
    var adminToken = ["eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O", "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5"];
    adminToken.forEach(function (userToken) {
      try {
        var message = {
          notification: {
            title: "Noticed someone !"
          },
          token: userToken
        };
        admin.messaging().send(message);
      } catch (error) {
        console.log("testNotification error : " + error + "\n");
        console.log("Message not sent! Update the key I think");
      }
    });
  });
  return _spyReport.apply(this, arguments);
}

exports.newDashboard = functions.https.onRequest((req, res) => {
  tokenValidReport();
  return dashboard(req, res);
});
exports.newHistory = functions.https.onRequest((req, res) => {
  return history(req, res);
});
exports.newHistoryLogger = functions.https.onRequest((req, res) => {
  return historyLogger(req, res);
});
exports.newDashboardHandler = functions.https.onRequest((req, res) => {
  return dashboardHandler(req, res);
});

function dashboard(_x2, _x3) {
  return _dashboard.apply(this, arguments);
}

function _dashboard() {
  _dashboard = _asyncToGenerator(function* (request, response) {
    checkForSpy(request);
    var maxBuilding = 10;
    var userToken = request.body.userToken;
    var message = {
      buildings: yield getBuildings(userToken, maxBuilding)
    };
    response.send(message);
  });
  return _dashboard.apply(this, arguments);
}

function history(_x4, _x5) {
  return _history.apply(this, arguments);
}

function _history() {
  _history = _asyncToGenerator(function* (request, response) {
    checkForSpy(request);
    let maxHistory = 50;
    var userToken = request.body.userToken;
    let message = {
      userToken: userToken,
      history: yield getHistory(userToken, maxHistory)
    };
    response.send(message);
  });
  return _history.apply(this, arguments);
}

function historyLogger(_x6, _x7) {
  return _historyLogger.apply(this, arguments);
}

function _historyLogger() {
  _historyLogger = _asyncToGenerator(function* (request, response) {
    checkForSpy(request);
    let userToken = request.body.userToken;
    let buildingId = request.body.buildingId;
    let buildingLockState = request.body.buildingLockState;
    let buildingName = yield getBuildingName(userToken, buildingId);
    let timestamp = yield getTimestamp();
    let historyRecord = {
      "buildingLockState": buildingLockState,
      "buildingName": buildingName,
      "timeStamp": timestamp
    };
    let DbReference = "/SmartDoorUser/" + userToken + "/History";
    admin.database().ref(DbReference).push(historyRecord);
    var message = {
      "userToken": userToken,
      "buildingId": buildingId,
      "buildingLockState": buildingLockState
    };
    response.send(message);
  });
  return _historyLogger.apply(this, arguments);
}

function dashboardHandler(_x8, _x9) {
  return _dashboardHandler.apply(this, arguments);
}

function _dashboardHandler() {
  _dashboardHandler = _asyncToGenerator(function* (request, response) {
    checkForSpy(request);
    let userToken = request.body.userToken;
    let buildingId = request.body.buildingId;
    let buildingLockState = request.body.buildingLockState;

    if (buildingLockState === "locked" || buildingLockState === "unlocked") {
      yield setBuildingLockState(userToken, buildingId, buildingLockState);
    }

    var message = {
      "userToken": userToken,
      "buildingId": buildingId,
      "buildingLockState": buildingLockState
    };
    response.send(message);
  });
  return _dashboardHandler.apply(this, arguments);
}

function getTimestamp() {
  return new Promise(resolve => {
    var date = new Date().toLocaleString("en-US", {
      timeZone: "Asia/Jakarta"
    });
    resolve(date);
  }).catch(error => {
    console.log("getTimestamp error :" + error + "\n");
  });
}

function getBuildings(userToken, maxBuilding) {
  return new Promise(resolve => {
    var DbReference = "/SmartDoorUser/" + userToken + "/Building";
    var buildingRef = admin.database().ref(DbReference);
    buildingRef.once("value", function (snapshot) {
      let result = [];
      snapshot.forEach(function (childSnapshot) {
        result.push(childSnapshot.val());
      });
      resolve(result.slice(0, maxBuilding));
    });
  }).catch(error => {
    console.log("getBuildings error : " + error + "\n");
  });
}

function getBuildingName(userToken, buildingId) {
  return new Promise(resolve => {
    var DbReference = "/SmartDoorUser/" + userToken + "/Building";
    var buildingRef = admin.database().ref(DbReference);
    buildingRef.once('value', function (snapshot) {
      snapshot.forEach(function (childSnapshot) {
        var building = childSnapshot.val();

        if (building.buildingID === buildingId) {
          resolve(building.buildingName);
        }
      });
    });
  }).catch(error => {
    console.log("getBuildingName error : " + error + "\n");
  });
}

function setBuildingLockState(userToken, buildingId, buildingLockState) {
  return new Promise(resolve => {
    var DbReference = "/SmartDoorUser/" + userToken + "/Building";
    var buildingRef = admin.database().ref(DbReference);
    buildingRef.once('value', function (snapshot) {
      snapshot.forEach(function (childSnapshot) {
        var building = childSnapshot.val();

        if (building.buildingID === buildingId) {
          childSnapshot.getRef().update({
            buildingLockState: buildingLockState
          });
          resolve();
        }
      });
    });
  }).catch(error => {
    console.log("setBuildingLockState error : " + error + "\n");
  });
}

function getHistory(userToken, maxHistory) {
  return new Promise(resolve => {
    var DbReference = "/SmartDoorUser/" + userToken + "/History";
    var historyRef = admin.database().ref(DbReference);
    historyRef.once("value", function (snapshot) {
      let result = [];
      snapshot.forEach(function (childSnapshot) {
        result.push(childSnapshot.val());
      });
      resolve(result.reverse().slice(0, maxHistory));
    });
  }).catch(error => {
    console.log("getHistory error : " + error + "\n");
  });
}
/* ============================================================================ */
// exports.bugLegendHistoryLogger = functions.https.onRequest((req, res) => {
//     return bugLegendHistoryLogger(req, res);
// });
// exports.bugLegendHistory = functions.https.onRequest((req, res) => {
//     return bugLegendHistory(req, res);
// });
// async function bugLegendHistoryLogger(request, response) {
//     let name = request.query.name;
//     let score = request.query.score;
//     let timestamp = await getTimestamp();
//     let historyRecord = {
//         "name": name,
//         "score": score,
//         "timeStamp": timestamp
//     };
//     let DbReference = "/BugLegend/History"
//     admin.database().ref(DbReference).push(historyRecord);
//     var message = {
//         "name": name,
//         "score": score,
//         "timeStamp": timestamp
//     };
//     response.send(message);
// }
// async function bugLegendHistory(request, response) {
//     let maxHistory = 30
//     let message = {
//         history: await getBugLegendHistory(maxHistory)
//     };
//     response.send(message)
// }
// function getBugLegendHistory(maxHistory) {
//     return new Promise(resolve => {
//         var DbReference = "/BugLegend/History"
//         var historyRef = admin.database().ref(DbReference);
//         historyRef.once("value", function (snapshot) {
//             let result = []
//             snapshot.forEach(function (childSnapshot) {
//                 result.push(childSnapshot.val())
//             });
//             resolve(result.reverse().slice(0, maxHistory))
//         });
//     })
//         .catch((error) => {
//             console.log("getBugLegendHistory error : " + error + "\n");
//         })
// }

/* ============================================================================ */


exports.arduinoHandler = functions.https.onRequest((req, res) => {
  return arduinoDashboard(req, res);
});

function arduinoDashboard(_x10, _x11) {
  return _arduinoDashboard.apply(this, arguments);
}

function _arduinoDashboard() {
  _arduinoDashboard = _asyncToGenerator(function* (request, response) {
    var userToken = "1234512345";
    var buildingId = "0";
    var message = yield getArduinoState(userToken, buildingId);
    response.send(message);
  });
  return _arduinoDashboard.apply(this, arguments);
}

function getArduinoState(userToken, buildingId) {
  return new Promise(resolve => {
    var DbReference = "/SmartDoorUser/" + userToken + "/Building/" + buildingId;
    var buildingRef = admin.database().ref(DbReference);
    buildingRef.once('value', function (snapshot) {
      resolve(snapshot.val()["buildingLockState"]);
    });
  }).catch(error => {
    console.log("getBuildings error : " + error + "\n");
  });
}
/* hardcoded GET request to change state */


exports.arduinoHistoryLogLock = functions.https.onRequest((req, res) => {
  return arduinoHistoryLogger(req, res, "locked");
});
exports.arduinoHistoryLogUnlock = functions.https.onRequest((req, res) => {
  return arduinoHistoryLogger(req, res, "unlocked");
});
exports.arduinoLock = functions.https.onRequest((req, res) => {
  return arduinoChangeLockState(req, res, "locked");
});
exports.arduinoUnlock = functions.https.onRequest((req, res) => {
  return arduinoChangeLockState(req, res, "unlocked");
});

function arduinoHistoryLogger(_x12, _x13, _x14) {
  return _arduinoHistoryLogger.apply(this, arguments);
}

function _arduinoHistoryLogger() {
  _arduinoHistoryLogger = _asyncToGenerator(function* (request, response, lockState) {
    let userToken = "1234512345";
    let buildingId = "1";
    let buildingLockState = lockState;
    let buildingName = yield getBuildingName(userToken, buildingId);
    let timestamp = yield getTimestamp();
    let historyRecord = {
      "buildingLockState": buildingLockState,
      "buildingName": buildingName,
      "timeStamp": timestamp
    };
    let DbReference = "/SmartDoorUser/" + userToken + "/History";
    admin.database().ref(DbReference).push(historyRecord);
    response.send("ok");
  });
  return _arduinoHistoryLogger.apply(this, arguments);
}

function arduinoChangeLockState(_x15, _x16, _x17) {
  return _arduinoChangeLockState.apply(this, arguments);
}

function _arduinoChangeLockState() {
  _arduinoChangeLockState = _asyncToGenerator(function* (request, response, lockState) {
    let userToken = "1234512345";
    let buildingId = "1";
    let buildingLockState = lockState;

    if (buildingLockState === "locked" || buildingLockState === "unlocked") {
      yield setBuildingLockState(userToken, buildingId, buildingLockState);
    }

    response.send("ok");
  });
  return _arduinoChangeLockState.apply(this, arguments);
}