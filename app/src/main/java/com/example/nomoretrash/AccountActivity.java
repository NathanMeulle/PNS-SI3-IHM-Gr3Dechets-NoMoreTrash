package com.example.nomoretrash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends Activity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText userName, passWord;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }


}


