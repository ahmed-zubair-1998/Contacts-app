package com.example.ahmad.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<String>{

    private ArrayList<Character> arr;

    public ContactsAdapter(@NonNull Context context, @NonNull String[] objects) {
        super(context, 0, objects);
        arr = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String contact = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.contact_name);
        tv.setText(contact);
        Character c = contact.split(" ")[1].charAt(0);
        TextView tv2 = (TextView) convertView.findViewById(R.id.contact_desc);
        tv2.setText("Contact#  " + position + c.toString());
        TextView header = (TextView) convertView.findViewById(R.id.head);

        if(!arr.contains(c)){
            arr.add(c);
            header.setText(c.toString());
        }

        return convertView;
    }
}
