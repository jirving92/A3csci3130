package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField,
            provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            businessNumberField.setText(String.valueOf(receivedPersonInfo.businessNumber));
            nameField.setText(receivedPersonInfo.name);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        String name = nameField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        appState.firebaseReference.child(receivedPersonInfo.uid).child("businessNumber").
                setValue(businessNumber);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("name").setValue(name);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("primaryBusiness").
                setValue(primaryBusiness);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("address").setValue(address);
        appState.firebaseReference.child(receivedPersonInfo.uid).child("province").
                setValue(province);



    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
