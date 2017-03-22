package com.example.luke.phonebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.R.attr.button;
import static android.R.attr.onClick;

public class ContactDetailActivity extends AppCompatActivity {
    private Context mContext;
    Contact contact;
    int position;
    EditText nameEditText;
    EditText companyEditText;
    EditText workEditText;
    EditText homeEditText;
    EditText mobileEditText;
    EditText streetEditText;
    EditText cityEditText;
    EditText countryEditText;
    EditText zipEditText;
    Button editButton;
    Boolean editFrozen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editFrozen = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonTouched();
            }
        });

        //Receive parcel from main activity
        Intent i = getIntent();
        contact = (Contact) i.getParcelableExtra("contact");
        position = i.getIntExtra("position", -1);

        //Set Image with Picasso
        ImageView ivAvatar = (ImageView) findViewById(R.id.imgAvatar);
        Picasso.with(this)
                .load(contact.getmLargeImageURL())
                .placeholder(R.drawable.droidbug)
                .into(ivAvatar);

        nameEditText= (EditText) findViewById(R.id.et_name);
        nameEditText.setText(contact.getmName(), TextView.BufferType.EDITABLE );

        companyEditText= (EditText)findViewById(R.id.et_company);
        companyEditText.setText(contact.getmCompany(), TextView.BufferType.EDITABLE);

        workEditText= (EditText) findViewById(R.id.et_work);
        workEditText.setText(contact.getmWork(), TextView.BufferType.EDITABLE);

        homeEditText= (EditText) findViewById(R.id.et_home);
        homeEditText.setText(contact.getmHome(), TextView.BufferType.EDITABLE);

        mobileEditText= (EditText) findViewById(R.id.et_mobile);
        mobileEditText.setText(contact.getmMobile(), TextView.BufferType.EDITABLE);

        streetEditText= (EditText) findViewById(R.id.et_street);
        streetEditText.setText(contact.getmStreet(), TextView.BufferType.EDITABLE);

        cityEditText= (EditText) findViewById(R.id.et_city);
        cityEditText.setText(contact.getmCity(), TextView.BufferType.EDITABLE);

        countryEditText= (EditText) findViewById(R.id.et_country);
        countryEditText.setText(contact.getmCountry(), TextView.BufferType.EDITABLE);

        zipEditText= (EditText) findViewById(R.id.et_zip);
        zipEditText.setText(contact.getmZip(), TextView.BufferType.EDITABLE);
    }

    private void toEditMode(){
        editFrozen = false;
        //change editing capabilities to true
        nameEditText.setFocusableInTouchMode(true);
        nameEditText.setFocusable(true);
        nameEditText.setClickable(true);
        nameEditText.setSelectAllOnFocus(true);

        companyEditText.setFocusableInTouchMode(true);
        companyEditText.setFocusable(true);
        companyEditText.setClickable(true);

        workEditText.setFocusableInTouchMode(true);
        workEditText.setFocusable(true);
        workEditText.setClickable(true);

        homeEditText.setFocusableInTouchMode(true);
        homeEditText.setFocusable(true);
        homeEditText.setClickable(true);

        mobileEditText.setFocusableInTouchMode(true);
        mobileEditText.setFocusable(true);
        mobileEditText.setClickable(true);

        streetEditText.setFocusableInTouchMode(true);
        streetEditText.setFocusable(true);
        streetEditText.setClickable(true);

        cityEditText.setFocusableInTouchMode(true);
        cityEditText.setFocusable(true);
        cityEditText.setClickable(true);

        countryEditText.setFocusableInTouchMode(true);
        countryEditText.setFocusable(true);
        countryEditText.setClickable(true);

        zipEditText.setFocusableInTouchMode(true);
        zipEditText.setFocusable(true);
        zipEditText.setClickable(true);
        //change edit name to save
        editButton.setText("Save Contact");
    }

    private void toViewMode(){
        editFrozen = true;
        //disable editing capabilities
        nameEditText.setFocusableInTouchMode(false);
        nameEditText.setFocusable(false);
        nameEditText.setClickable(false);

        companyEditText.setFocusableInTouchMode(false);
        companyEditText.setFocusable(false);
        companyEditText.setClickable(false);

        workEditText.setFocusableInTouchMode(false);
        workEditText.setFocusable(false);
        workEditText.setClickable(false);

        homeEditText.setFocusableInTouchMode(false);
        homeEditText.setFocusable(false);
        homeEditText.setClickable(false);

        mobileEditText.setFocusableInTouchMode(false);
        mobileEditText.setFocusable(false);
        mobileEditText.setClickable(false);

        streetEditText.setFocusableInTouchMode(false);
        streetEditText.setFocusable(false);
        streetEditText.setClickable(false);

        countryEditText.setFocusableInTouchMode(false);
        countryEditText.setFocusable(false);
        countryEditText.setClickable(false);

        cityEditText.setFocusableInTouchMode(false);
        cityEditText.setFocusable(false);
        cityEditText.setClickable(false);

        zipEditText.setFocusableInTouchMode(false);
        zipEditText.setFocusable(false);
        zipEditText.setClickable(false);
        //change edit name to edit
        editButton.setText("Edit Contact");

        //update Contact in Contact List
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("editName", nameEditText.getText().toString());
        intent.putExtra("editCompany", companyEditText.getText().toString());
        intent.putExtra("editWork", workEditText.getText().toString());
        intent.putExtra("editHome", homeEditText.getText().toString());
        intent.putExtra("editMobile", mobileEditText.getText().toString());
        intent.putExtra("editStreet", streetEditText.getText().toString());
        intent.putExtra("editCity", cityEditText.getText().toString());
        intent.putExtra("editCountry", countryEditText.getText().toString());
        intent.putExtra("editZip", zipEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void onEditButtonTouched(){
        if(editFrozen){
            toEditMode();
        }else{
            toViewMode();
        }
    }



}
