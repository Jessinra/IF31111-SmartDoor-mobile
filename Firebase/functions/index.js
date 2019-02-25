const escapeHtml = require('escape-html');
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();


/*====================================== Dummy & Tutorial function =====================================================*/

exports.helloWorld = functions.https.onRequest((request, response) => {
    response.send("Hello from Firebase!");
});


// Take the text parameter passed to this HTTP endpoint and insert it into the
// Realtime Database under the path /messages/:pushId/original
exports.addMessage = functions.https.onRequest((req, res) => {

    // Grab the text parameter.
    const original = req.query.text;

    // Push the new message into the Realtime Database using the Firebase Admin SDK.
    return admin.database().ref('/messages').push({ original: original }).then((snapshot) => {

        // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
        return res.redirect(303, snapshot.ref.toString());
    });
});

// Listens for new messages added to /messages/:pushId/original and creates an
// uppercase version of the message to /messages/:pushId/uppercase
exports.makeUppercase = functions.database.ref('/messages/{pushId}/original')
    .onCreate((snapshot, context) => {
        // Grab the current value of what was written to the Realtime Database.
        const original = snapshot.val();
        console.log('Uppercasing', context.params.pushId, original);
        const uppercase = original.toUpperCase();
        // You must return a Promise when performing asynchronous tasks inside a Functions such as
        // writing to the Firebase Realtime Database.
        // Setting an "uppercase" sibling in the Realtime Database returns a Promise.
        return snapshot.ref.parent.child('uppercase').set(uppercase);
    });

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

/*===========================================================================================*/
/*===========================================================================================*/
/*===========================================================================================*/

exports.sendNotificationTemplate = functions.https.onRequest((req, res) => {

    // TODO:  (jessin) search for FIREBASE_TOKEN at debug to get this. this one is mine 
    var adminToken = "edf7Q39Bggw:APA91bFv8WgABi-cMXgqGOPbxhVF_oPkGQJsD3Cc9vDbWind-84RrDqqv-U_AKRmLXw4Smdi9Hd6qJE6DmnXD0qNPN4mjM8_9vBukLa7MIUN38zGj4psINJH3Uqi1R0NJxr-N-Dw1XKt";

    // TODO: Modify what data you need to send 
    var message = {
        data: {

            // Notification data
            SystemTitle: "Notification title",
            SistemText: "Notification text content",

            // Other payload data
            score: '850',
            time: '2:45'
        },
        token: adminToken
    };

    // Sending the message
    admin.messaging().send(message);
    res.send("Message sent!");
});

exports.history = functions.https.onRequest((req, res) => {

    var userToken = req.body.userToken;

    // dummy 
    var message = {

        userToken: userToken,
        history: [
            {
                date: "22/03/2019",
                time: "05:39",
                building: "house1",
            },
            {
                date: "23/03/2019",
                time: "05:39",
                building: "house2",
            },
            {
                date: "24/03/2019",
                time: "05:39",
                building: "house3",
            },
            {
                date: "25/03/2019",
                time: "05:39",
                building: "house4",
            },
            {
                date: "26/03/2019",
                time: "05:39",
                building: "house5",
            },
        ]
    };


    // dummy 
    var messageUpdated = {

        userToken: userToken,
        history: [
            {
                date: "22/03/2019",
                time: "05:39",
                building: "office1",
            },
            {
                date: "23/03/2019",
                time: "05:39",
                building: "office2",
            },
            {
                date: "24/03/2019",
                time: "05:39",
                building: "office3",
            },
            {
                date: "25/03/2019",
                time: "05:39",
                building: "office4",
            },
            {
                date: "26/03/2019",
                time: "05:39",
                building: "office5",
            },
        ]
    };

    if (userToken === "update") {
        res.send(messageUpdated);
    }

    res.send(message);
});


exports.dashboard = functions.https.onRequest((req, res) => {

    var userToken = req.body.userToken;

    // dummy 
    var message = {
        buildings: [
            {
                buildingName: "Home",
                buildingLockState: true,
            },
            {
                buildingName: "Office",
                buildingLockState: false,
            },
            {
                buildingName: "Office 2",
                buildingLockState: false,
            },
            {
                buildingName: "Office 3",
                buildingLockState: true,
            },
            {
                buildingName: "Office 4",
                buildingLockState: false,
            },
        ]
    };

    var updateLocked = {
        buildings: [
            {
                buildingName: "Home",
                buildingLockState: true,
            },
            {
                buildingName: "Office",
                buildingLockState: true,
            },
            {
                buildingName: "Office 2",
                buildingLockState: true,
            },
            {
                buildingName: "Office 3",
                buildingLockState: true,
            },
            {
                buildingName: "Office 4",
                buildingLockState: true,
            },
        ]
    };

    var updateUnlocked = {
        buildings: [
            {
                buildingName: "Home",
                buildingLockState: false,
            },
            {
                buildingName: "Office",
                buildingLockState: false,
            },
            {
                buildingName: "Office 2",
                buildingLockState: false,
            },
            {
                buildingName: "Office 3",
                buildingLockState: false,
            },
            {
                buildingName: "Office 4",
                buildingLockState: false,
            },
        ]
    };

    if (userToken === "updateLocked") {
        res.send(updateLocked);
    }

    if (userToken === "updateUnlocked") {
        res.send(updateUnlocked);
    }

    res.send(message);
});