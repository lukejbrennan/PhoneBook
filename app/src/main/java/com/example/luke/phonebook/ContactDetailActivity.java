package com.example.luke.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.button;
import static android.R.attr.onClick;

public class ContactDetailActivity extends AppCompatActivity {
    Contact contact;
    EditText nameEditText;
    EditText companyEditText;
    Button editButton;
    Boolean editFrozen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editFrozen = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonTouched();
            }
        });
        Intent i = getIntent();
        contact = (Contact) i.getParcelableExtra("contact");


        nameEditText= (EditText) findViewById(R.id.et_name);
        nameEditText.setText(contact.getmName(), TextView.BufferType.EDITABLE );

        companyEditText= (EditText)findViewById(R.id.et_company);
        companyEditText.setText(contact.getmCompany(), TextView.BufferType.EDITABLE);
    }


    private void toEditMode(){
        //change everything to true
        nameEditText.setFocusable("true");
        companyEditText.setFocusable("true");
        //change edit name to save
    }

    private void toViewMode(){
        //change everthing to false
        nameEditText.setFocusable("true");
        companyEditText.setFocusable("true");
        //change edit name to edit
        //change values if they are different
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
    }

    private void onEditButtonTouched(){
        if(editFrozen){
            toEditMode();
        }else{
            toViewMode();
        }
    }



}
