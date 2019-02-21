package com.knockknock.dragonra.smartdoor.Model;

import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SmartDoorUser {
    private String fullName;
    private String givenName;
    private String familyName;
    private String email;
    private String userID;
    private Uri photoUri;

    public SmartDoorUser(String fullName, String givenName, String familyName, String email, String userID, Uri photoUri) {
        this.fullName = fullName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.userID = userID;
        this.photoUri = photoUri;
    }

    public SmartDoorUser(GoogleSignInAccount account) {
        this.fullName = account.getDisplayName();
        this.givenName = account.getGivenName();
        this.familyName = account.getFamilyName();
        this.email = account.getEmail();
        this.userID = account.getId();
        this.photoUri = account.getPhotoUrl();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }
}
