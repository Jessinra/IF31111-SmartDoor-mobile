package com.knockknock.dragonra.smartdoor.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

@SuppressWarnings("ALL")
public class SmartDoorUser implements Parcelable {
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
        setFullName(account.getDisplayName());
        setGivenName(account.getGivenName());
        setFamilyName(account.getFamilyName());
        setEmail(account.getEmail());
        setUserID(account.getId());
        setPhotoUri(account.getPhotoUrl());
    }

    @NonNull
    public String toString() {
        return (getFullName() + "\n" +
                getFamilyName() + "\n" +
                getGivenName() + "\n" +
                getEmail() + "\n" +
                getUserID() + "\n" +
                getPhotoUri());
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

    public void setPhotoUri(String photoUri) {
        this.photoUri = Uri.parse(photoUri);
    }


    /* Parcelling */
    public SmartDoorUser(Parcel in) {
        String[] data = new String[6];

        in.readStringArray(data);

        setFullName(data[0]);
        setGivenName(data[1]);
        setFamilyName(data[2]);
        setEmail(data[3]);
        setUserID(data[4]);
        setPhotoUri(data[5]);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.fullName,
                this.givenName,
                this.familyName,
                this.email,
                this.userID,
                this.photoUri.toString()});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SmartDoorUser createFromParcel(Parcel in) {
            return new SmartDoorUser(in);
        }

        public SmartDoorUser[] newArray(int size) {
            return new SmartDoorUser[size];
        }
    };
}
