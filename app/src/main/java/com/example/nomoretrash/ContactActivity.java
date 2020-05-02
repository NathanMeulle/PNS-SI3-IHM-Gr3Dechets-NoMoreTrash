package com.example.nomoretrash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView mEditTextTo;
    private EditText mEditTextMessage;
    String subject;
    Spinner mSpinnerSubject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mEditTextTo = findViewById(R.id.edit_text_to);
        mSpinnerSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);

        mSpinnerSubject.setOnItemSelectedListener(this);
        mSpinnerSubject = findViewById(R.id.edit_text_subject);
        List<String> list = new ArrayList<>();
        list.add("Problème de signalement");
        list.add("Problème avec mon historique");
        list.add("Suggestion");
        list.add("Autre");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSubject.setAdapter(dataAdapter);


        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });


        final Button buttonStat = findViewById(R.id.boutonRetour);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactActivity.this.finish();
            }
        });

        if (savedInstanceState != null) {
            mSpinnerSubject.setSelection(savedInstanceState.getInt("mSpinnerSubject", 0));
        }
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        Log.d("email ", "A : " + recipients[0] + ", objet : " + subject + ", message : " + message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choisissez votre application email"));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence charSequence = (CharSequence) parent.getItemAtPosition(position);
        subject = charSequence.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mSpinnerSubject", mSpinnerSubject.getSelectedItemPosition());
        // do this for each or your Spinner
        // You might consider using Bundle.putStringArray() instead
    }

}
