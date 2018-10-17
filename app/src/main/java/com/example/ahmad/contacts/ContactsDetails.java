package com.example.ahmad.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        TextView name = (TextView) findViewById(R.id.details_name);
        TextView title = (TextView) findViewById(R.id.title);
        LinearLayout emails = (LinearLayout) findViewById(R.id.emails_layout);
        LinearLayout phones = (LinearLayout) findViewById(R.id.phones_layout);
        Log.d("aa", "emails\n" + contact.emails.toString());
        Log.d("aa", "phones\n" + contact.numbers.toString());

        name.setText(contact.name);
        title.setText(contact.title);
        for (String email : contact.emails) {
            Log.d("aa", email);
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setText(email);
            emails.addView(tv);
        }
        for (Long number : contact.numbers) {
            Log.d("aaa", number.toString());
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setText(number.toString());
            phones.addView(tv);
        }

    }
}
