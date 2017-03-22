package com.example.luke.phonebook;

import android.graphics.Bitmap;
/**
 * Created by Luke on 3/20/2017.
 */

public class PhoneBook {

    //private Bitmap mAvatar;
    private String mName;
    private String mCompany;
    //private Boolean mFavorite;
    private String mSmallImageURL;
    private String mLargeImageURL;
    private String mEmail;
    private String mWebsite;
    //private String mBirthDate;
    private String mWork;
    private String mHome;
    private String mMobile;
    private String mStreet;
    private String mCity;
    private String mState;
    private String mCountry;
    private String mZip;
    //private String mLat;
    //private String myLong;


    public PhoneBook(String mName, String mCompany, String mSmallImageURL, String mLargeImageURL, String mEmail, String mWebsite, String mWork, String mHome, String mMobile, String mStreet, String mCity, String mState, String mCountry, String mZip) {
        this.mName = mName;
        this.mCompany = mCompany;
        this.mSmallImageURL = mSmallImageURL;
        this.mLargeImageURL = mLargeImageURL;
        this.mEmail = mEmail;
        this.mWebsite = mWebsite;
        this.mWork = mWork;
        this.mHome = mHome;
        this.mMobile = mMobile;
        this.mStreet = mStreet;
        this.mCity = mCity;
        this.mState = mState;
        this.mCountry = mCountry;
        this.mZip = mZip;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmSmallImageURL() {
        return mSmallImageURL;
    }

    public void setmSmallImageURL(String mSmallImageURL) {
        this.mSmallImageURL = mSmallImageURL;
    }

    public String getmLargeImageURL() {
        return mLargeImageURL;
    }

    public void setmLargeImageURL(String mLargeImageURL) {
        this.mLargeImageURL = mLargeImageURL;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public String getmWork() {
        return mWork;
    }

    public void setmWork(String mWork) {
        this.mWork = mWork;
    }

    public String getmHome() {
        return mHome;
    }

    public void setmHome(String mHome) {
        this.mHome = mHome;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }
    public String getmStreet() {
        return mStreet;
    }

    public void setmStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmZip() {
        return mZip;
    }

    public void setmZip(String mZip) {
        this.mZip = mZip;
    }
}


