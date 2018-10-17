package com.example.ahmad.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactsAdapter extends ArrayAdapter<Contact> implements Filterable {
    private ArrayList<Character> header_arr;
    private ArrayList<Contact> arr;
    private ArrayList<Contact> filtered_arr;
    private Filter filter;

    public ContactsAdapter(@NonNull Context context, @NonNull ArrayList<Contact> objects) {
        super(context, 0, objects);
        header_arr = new ArrayList<>();
        arr = new ArrayList<>(objects);
        filtered_arr = new ArrayList<>(objects);
    }


    @Nullable
    @Override
    public Contact getItem(int position) {
        return filtered_arr.get(position);
    }

    @Override
    public int getCount() {
        return filtered_arr.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.contact_name);
        tv.setText(contact.name);
        Character c = contact.name.split(" ")[1].charAt(0);
        TextView tv2 = (TextView) convertView.findViewById(R.id.contact_desc);
        tv2.setText(contact.title);
        TextView header = (TextView) convertView.findViewById(R.id.head);
        header.setText(c.toString());

        if(!header_arr.contains(c)){
            header_arr.add(c);
            tv2.setText(c.toString());
            header.setVisibility(View.VISIBLE);
        }
        else{
            header.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new contactFilter();
        }
        return filter;
    }

    private class contactFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null || constraint.toString().trim().length() > 0){
                ArrayList<Contact> filtered_list = new ArrayList<>();
                for (Contact s : arr) {
                    String s2 = s.name.split(" ")[1].toLowerCase();
                    if(s2.startsWith(constraint.toString().toLowerCase())){
                        filtered_list.add(s);
                    }
                }
                results.values=filtered_list;
                results.count=filtered_list.size();
                return results;
            }
            results.count = arr.size();
            results.values = arr;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered_arr = (ArrayList<Contact>) results.values;
            notifyDataSetChanged();
        }
    }
}
