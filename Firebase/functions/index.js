const escapeHtml = require('escape-html');
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();


exports.testNotification = functions.https.onRequest((req, res) => {

    // This registration token comes from the client FCM SDKs.
    var adminToken = [
        "edf7Q39Bggw:APA91bFv8WgABi-cMXgqGOPbxhVF_oPkGQJsD3Cc9vDbWind-84RrDqqv-U_AKRmLXw4Smdi9Hd6qJE6DmnXD0qNPN4mjM8_9vBukLa7MIUN38zGj4psINJH3Uqi1R0NJxr-N-Dw1XKt",
        "dRahuTn6Xjk:APA91bF9oAMzPol_GENMtsP8JQh9hpahCfu-1MeRYFOrqC7QCREuSq9RSU39DtdFH4e9THb81BzoWX38T_hGO_mKQ1lYmxX4VePSe1f_8CPmHO4fDR6DRRuZnWEKYveB3YWOXZ0LrHZO",
        "fiyO8hOTOkU:APA91bGs-DH-KbDwKCH0GhlAfcvlb6tvXQXcM-YJPnEV2OHzdBwlUSaaKCM0ZZZVXA11FEXfXUD8VuxSl6X6IxP0yeZA-y84MeWWSxW_wJbxHm2ZcQR6XKN5IqcJ3Fufv1UHs-A1GzeC",
        "e8L_9UiMyx4:APA91bGJkbHw8FW_KDjzPFCh97Kveoqr9z6IwL4Xc4ujPR9yPhWmK56ZU6hjBKsWZC5mv4aPHT9k6Ri37CLojxxPbCIErNDmZc8Uxo1C9U35Lmlj51PkqBvfmZxTCa55E_YNttYeUho4"
    ];

    adminToken.forEach(function (userToken) {

        var message = {
            data: {
                building: 'Whitehouse',
                time: '19:45'
            },
            notification: {
                title: "Hello !",
                body: "Somebody is in front of your house",
            },
            token: userToken
        };

        admin.messaging().send(message);
    });

    res.send("Message sent!");
});

exports.dashboard = functions.https.onRequest((req, res) => {
    return dashboard(req, res);
});

exports.history = functions.https.onRequest((req, res) => {
    return history(req, res);
});

exports.historyLogger = functions.https.onRequest((req, res) => {
    return historyLogger(req, res);
});

exports.dashboardHandler = functions.https.onRequest((req, res) => {
    return dashboardHandler(req, res);
});

async function dashboard(request, response) {
    var maxBuilding = 10

    var userToken = request.body.userToken;
    var message = {
        buildings: await getBuildings(userToken, maxBuilding)
    };
    response.send(message);
}

async function history(request, response) {
    let maxHistory = 50

    var userToken = request.body.userToken;
    let message = {
        userToken: userToken,
        history: await getHistory(userToken, maxHistory)
    };
    response.send(message)
}

async function historyLogger(request, response) {

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
