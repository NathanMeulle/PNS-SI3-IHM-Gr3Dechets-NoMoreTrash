package com.example.nomoretrash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ContactActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView mEditTextTo;
    private Spinner mSpinnerSubject;
    private EditText mEditTextMessage;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mEditTextTo = findViewById(R.id.edit_text_to);
        mSpinnerSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.object_contact, R.layout.activity_contact);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinnerSubject.setAdapter(adapter);
        mSpinnerSubject.setOnItemSelectedListener(this);

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
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choisissez votre application email"));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence charSequence = (CharSequence) parent.getItemAtPosition(position);
        System.out.println("Item : " + charSequence.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
