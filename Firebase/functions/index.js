const escapeHtml = require('escape-html');
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.newPushNotification = functions.https.onRequest((req, res) => {

    // This registration token comes from the client FCM SDKs.
    var adminToken = [
        "eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O",
        "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5",
    ];

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

async function tokenValidReport(){

    var adminToken = [
        "eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O",
        "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5",
    ];

    adminToken.forEach(function (userToken) {

        try {
            var message = {
                notification: {
                    title: "Hi, token still valid :) !",
                },
                token: userToken
            };

            admin.messaging().send(message);

        } catch (error) {
            console.log("testNotification error : " + error + "\n");
            console.log("Message not sent! Update the key I think");
        }
    });
}

async function checkForSpy(request){

    try{
        if(request.body.secretCode !== "seara"){
            spyReport();
        }
    }
    catch(error){
        spyReport();
    }
}

async function spyReport(){

    var adminToken = [
        "eUZWH1Z4pmU:APA91bEl73Gx9JableWtesfq1Da4BSHREqZZu96Wp_gPZ58gy_0gfRhHhKYyRZ814cvLTz5nIHWjhVvqua-vjyM-U6C6HpiONLULqWX8DEH4--T03SEAm942MDmJIgHkKvG726Rz9F8O",
        "cscJ-BNoAU4:APA91bHxiVaUtu9f6WHU5MrLgbeLsxtsCiv-TAEK3_g2uKRwW0ypcqpWkzdLrBDtfOdC-EmH_W3VLhYOavU5s3W_OyY-RJMblIu1RBkC4DXKXr5Q5Gf-4NmGrJnLZuG3mSnkKM64Ysm5",
    ];

    adminToken.forEach(function (userToken) {

        try {
            var message = {
                notification: {
                    title: "Noticed someone !",
                },
                token: userToken
            };

            admin.messaging().send(message);

        } catch (error) {
            console.log("testNotification error : " + error + "\n");
            console.log("Message not sent! Update the key I think");
        }
    });
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

async function dashboard(request, response) {
    checkForSpy(request);
    var maxBuilding = 10

    var userToken = request.body.userToken;
    var message = {
        buildings: await getBuildings(userToken, maxBuilding)
    };
    response.send(message);
}

async function history(request, response) {
    checkForSpy(request);
    let maxHistory = 50

    var userToken = request.body.userToken;
    let message = {
        userToken: userToken,
        history: await getHistory(userToken, maxHistory)
    };
    response.send(message)
}

async function historyLogger(request, response) {
    checkForSpy(request);

    let userToken = request.body.userToken;
    let buildingId = request.body.buildingId;
    let buildingLockState = request.body.buildingLockState;

    let buildingName = await getBuildingName(userToken, buildingId);
    let timestamp = await getTimestamp()

    let historyRecord = {
        "buildingLockState": buildingLockState,
        "buildingName": buildingName,
        "timeStamp": timestamp
    };

    let DbReference = "/SmartDoorUser/" + userToken + "/History"
    admin.database().ref(DbReference).push(historyRecord);

    var message = {
        "userToken": userToken,
        "buildingId": buildingId,
        "buildingLockState": buildingLockState
    };

    response.send(message);
}

async function dashboardHandler(request, response) {
    checkForSpy(request);

    let userToken = request.body.userToken;
    let buildingId = request.body.buildingId;
    let buildingLockState = request.body.buildingLockState;

    if (buildingLockState === "locked" || buildingLockState === "unlocked") {
        await setBuildingLockState(userToken, buildingId, buildingLockState);
    }

    var message = {
        "userToken": userToken,
        "buildingId": buildingId,
        "buildingLockState": buildingLockState
    };
    response.send(message);
}

function getTimestamp() {
    return new Promise(resolve => {

        var date = new Date().toLocaleString("en-US", { timeZone: "Asia/Jakarta" });
        resolve(date);

    })
        .catch((error) => {
            console.log("getTimestamp error :" + error + "\n");
        });
}

function getBuildings(userToken, maxBuilding) {
    return new Promise(resolve => {

        var DbReference = "/SmartDoorUser/" + userToken + "/Building"

        var buildingRef = admin.database().ref(DbReference);
        buildingRef.once("value", function (snapshot) {

            let result = []
            snapshot.forEach(function (childSnapshot) {
                result.push(childSnapshot.val())
            });

            resolve(result.slice(0, maxBuilding))
        });
    })
        .catch((error) => {
            console.log("getBuildings error : " + error + "\n");
        })
}

function getBuildingName(userToken, buildingId) {
    return new Promise(resolve => {

        var DbReference = "/SmartDoorUser/" + userToken + "/Building"

        var buildingRef = admin.database().ref(DbReference);
        buildingRef.once('value', function (snapshot) {

            snapshot.forEach(function (childSnapshot) {

                var building = childSnapshot.val();
                if (building.buildingID === buildingId) {
                    resolve(building.buildingName);
                }
            });
        });
    })
        .catch((error) => {
            console.log("getBuildingName error : " + error + "\n");
        })
}

function setBuildingLockState(userToken, buildingId, buildingLockState) {
    return new Promise(resolve => {

        var DbReference = "/SmartDoorUser/" + userToken + "/Building";
        var buildingRef = admin.database().ref(DbReference);

        buildingRef.once('value', function (snapshot) {

            snapshot.forEach(function (childSnapshot) {

                var building = childSnapshot.val();
                if (building.buildingID === buildingId) {

                    childSnapshot.getRef().update({ buildingLockState: buildingLockState });
                    resolve();
                }
            });
        });
    })
        .catch((error) => {
            console.log("setBuildingLockState error : " + error + "\n");
        })
}

function getHistory(userToken, maxHistory) {
    return new Promise(resolve => {

        var DbReference = "/SmartDoorUser/" + userToken + "/History"
        var historyRef = admin.database().ref(DbReference);

        historyRef.once("value", function (snapshot) {

            let result = []
            snapshot.forEach(function (childSnapshot) {
                result.push(childSnapshot.val())
            });

            resolve(result.reverse().slice(0, maxHistory))
        });
    })
        .catch((error) => {
            console.log("getHistory error : " + error + "\n");
        })
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

async function arduinoDashboard(request, response) {

    var userToken = "1234512345";
    var buildingId = "0";

    var message = await getArduinoState(userToken, buildingId);
    response.send(message);
}

function getArduinoState(userToken, buildingId) {
    return new Promise(resolve => {

        var DbReference = "/SmartDoorUser/" + userToken + "/Building/" + buildingId;
        var buildingRef = admin.database().ref(DbReference);

        buildingRef.once('value', function (snapshot) {
            resolve(snapshot.val()["buildingLockState"]);
        });
    })
        .catch((error) => {
            console.log("getBuildings error : " + error + "\n");
        })
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
    return arduinoChangeLockState(req, res,"unlocked");
});

async function arduinoHistoryLogger(request, response, lockState) {

    let userToken = "1234512345";
    let buildingId = "1";
    let buildingLockState = lockState;

    let buildingName = await getBuildingName(userToken, buildingId);
    let timestamp = await getTimestamp()

    let historyRecord = {
        "buildingLockState": buildingLockState,
        "buildingName": buildingName,
        "timeStamp": timestamp
    };

    let DbReference = "/SmartDoorUser/" + userToken + "/History"
    admin.database().ref(DbReference).push(historyRecord);

    response.send("ok");
}

async function arduinoChangeLockState(request, response, lockState) {

    let userToken = "1234512345";
    let buildingId = "1";
    let buildingLockState = lockState;

    if (buildingLockState === "locked" || buildingLockState === "unlocked") {
        await setBuildingLockState(userToken, buildingId, buildingLockState);
    }
    response.send("ok");
}
