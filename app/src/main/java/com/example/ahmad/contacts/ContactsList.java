package com.example.ahmad.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class ContactsList extends AppCompatActivity {

    private static String[] contacts = {
            "Ahmed Zubair",
            "Razi Rizvi",
            "Hamza Anjum",
            "Giki Giki",
            "Usama Shahid",
            "Moiz Arshad",
            "Zulqar Abbas",
            "Ali Janjua",
            "Aleem Qasim",
            "Bakar Siddique"
    };

    private ContactsAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        Arrays.sort(contacts, new CustomComparator());

        adapter = new ContactsAdapter(this, contacts);

        ListView listView = (ListView) findViewById(R.id.contacts_list);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);

        EditText search_str = (EditText) findViewById(R.id.search_bar);
        search_str.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ContactsList.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}

class CustomComparator implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        o1 = o1.split(" ")[1];
        o2 = o2.split(" ")[1];
        return o1.compareTo(o2);
    }
}
