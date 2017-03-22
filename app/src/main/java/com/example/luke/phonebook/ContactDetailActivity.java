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
    Button editButton;
    Boolean editFrozen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editFrozen = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);


        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonTouched();
            }
        });
        Intent i = getIntent();
        contact = (Contact) i.getParcelableExtra("contact");
        position = i.getIntExtra("position", -1);
        //Set Image with Picasso
        ImageView ivAvatar = (ImageView) findViewById(R.id.imgAvatar);
        Picasso.with(this)
                .load(contact.getmLargeImageURL())
                .placeholder(this.getDrawable(R.drawable.droidbug))
                .into(ivAvatar);
        //Log.d("Luke",contact.getmLargeImageURL());

        nameEditText= (EditText) findViewById(R.id.et_name);
        nameEditText.setText(contact.getmName(), TextView.BufferType.EDITABLE );

        companyEditText= (EditText)findViewById(R.id.et_company);
        companyEditText.setText(contact.getmCompany(), TextView.BufferType.EDITABLE);
    }

    private void toEditMode(){
        editFrozen = false;
        //change everything to true
        nameEditText.setFocusableInTouchMode(true);
        companyEditText.setFocusableInTouchMode(true);
        //change edit name to save
        editButton.setText("Save Contact");
    }

    private void toViewMode(){
        editFrozen = true;
        //change everthing to false
        nameEditText.setFocusableInTouchMode(false);
        companyEditText.setFocusableInTouchMode(false);
        //change edit name to edit
        editButton.setText("Edit Contact");
        //change values if they are different
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("editName", nameEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();


       /*
        String currentName = nameEditText.getText().toString();
        String storedName = contact.getmName();
        if(!currentName.equals(storedName)){
            contact.setmName(currentName);
        }
        String currentCompany = companyEditText.getText().toString();
        String storedCompany = contact.getmCompany();
        if(!currentCompany.equals(storedCompany)){
            contact.setmCompany(currentCompany);
        }
        */
    }

    private void onEditButtonTouched(){
        if(editFrozen){
            toEditMode();
        }else{
            toViewMode();
        }
    }



}
