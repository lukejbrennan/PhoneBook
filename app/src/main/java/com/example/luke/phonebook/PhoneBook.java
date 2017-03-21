package com.example.luke.phonebook;

import android.graphics.Bitmap;
/**
 * Created by Luke on 3/20/2017.
 */

public class PhoneBook {

    private Bitmap mAvatar;
    private String mName;
    private String mPhone;
    private String mEmail;

    public PhoneBook(String mName, String mPhone, String mEmail) {
       // this.mAvatar = mAvatar; //(include above Bitmap mAvater)
        this.mName = mName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
    }

    /*public Bitmap getmAvatar() {
        return mAvatar;
    }*/

    /*public void setmAvatar(Bitmap mAvatar) {
        this.mAvatar = mAvatar;
    }*/

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
