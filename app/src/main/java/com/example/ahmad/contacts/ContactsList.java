package com.example.ahmad.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ContactsList extends AppCompatActivity {

    private static ArrayList<Contact> contacts;

    private ContactsAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        contacts = new ArrayList<>();

        createView();
    }

    private void createView() {
        Collections.sort(contacts, Contact.contactComparator);

        adapter = new ContactsAdapter(this, contacts);

        ListView listView = (ListView) findViewById(R.id.contacts_list);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ContactsDetails.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });

        EditText search_str = (EditText) findViewById(R.id.search_bar);
        search_str.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ContactsList.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void addContact(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Contact c = (Contact) data.getSerializableExtra("contact");
            if(c != null) {
                contacts.add(c);
            }
        }
        createView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("contacts", contacts);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contacts = (ArrayList<Contact>) savedInstanceState.getSerializable("contacts");
        createView();
    }
}
