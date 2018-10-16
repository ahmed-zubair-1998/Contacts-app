package com.example.ahmad.contacts;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Contact contact;
    private String name;
    private ArrayList<String> emails;
    private ArrayList<Integer> numbers;
    private ArrayList<EditText> emails_v;
    private ArrayList<EditText> numbers_v;
    private ArrayList<String> emails_id;
    private ArrayList<String> numbers_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        emails = new ArrayList<>();
        numbers=new ArrayList<>();
        emails_v = new ArrayList<>();
        numbers_v = new ArrayList<>();
        EditText email, number;
        email = (EditText) findViewById(R.id.email_input);
        number = (EditText) findViewById(R.id.phone_input);
        emails_v.add(email);
        numbers_v.add(number);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        numbers_id = new ArrayList<>();
        emails_id = new ArrayList<>();

        for (EditText e : emails_v) {
            emails_id.add(e.getText().toString());
        }
        for (EditText e : numbers_v) {
            numbers_id.add(e.getText().toString());
        }
        EditText name_v = (EditText) findViewById(R.id.name_input);
        outState.putStringArrayList("emails_id", emails_id);
        outState.putStringArrayList("numbers_id", numbers_id);
        outState.putString("name", name_v.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EditText name_v = (EditText) findViewById(R.id.name_input);
        EditText email, number;
        email = (EditText) findViewById(R.id.email_input);
        number = (EditText) findViewById(R.id.phone_input);


        name_v.setText(savedInstanceState.getString("name",""));
        emails_id = savedInstanceState.getStringArrayList("emails_id");
        numbers_id=savedInstanceState.getStringArrayList("numbers_id");

        email.setText(emails_id.get(0));
        number.setText(numbers_id.get(0));

        for (int i = 1; i < emails_id.size(); i++) {
            addEmail_helper();
            emails_v.get(i).setText(emails_id.get(i));
        }
        for (int i = 1; i < numbers_id.size(); i++) {
            addPhone_helper();
            numbers_v.get(i).setText(numbers_id.get(i));
        }

    }

    public void addEmail(View view) {
        addEmail_helper();
    }

    private void addEmail_helper() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        EditText text = new EditText(context);
        text.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 19));
        Button x = new Button(context);
        x.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        x.setText("Remove");
        layout.addView(text);
        layout.addView(x);
        LinearLayout parent = (LinearLayout) findViewById(R.id.additional_emails);
        parent.addView(layout);
        emails_v.add(text);
        final EditText text_cpy = text;
        final LinearLayout layout_cpy = layout;
        final LinearLayout parent_cpy = parent;
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emails_v.remove(text_cpy);
                parent_cpy.removeView(layout_cpy);
            }
        });
    }

    public void addPhone(View view) {
        addPhone_helper();
    }

    private void addPhone_helper() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        EditText text = new EditText(context);
        text.setInputType(InputType.TYPE_CLASS_PHONE);
        text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 19));
        Button x = new Button(context);
        x.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        x.setText("Remove");
        layout.addView(text);
        layout.addView(x);
        LinearLayout parent = (LinearLayout) findViewById(R.id.additional_phones);
        parent.addView(layout);
        numbers_v.add(text);
        final EditText text_cpy = text;
        final LinearLayout layout_cpy = layout;
        final LinearLayout parent_cpy = parent;
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers_v.remove(text_cpy);
                parent_cpy.removeView(layout_cpy);
            }
        });
    }


    public void save(View view) {
        EditText name_v = (EditText) findViewById(R.id.name_input);
        name = name_v.getText().toString();
        if("".equals(name)) {
            Toast t = Toast.makeText(context, "Name Missing", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        for (EditText email_v : emails_v) {
            String s = email_v.getText().toString();
            if(! "".equals(s))
                emails.add(s);
        }
        for(EditText number_v : numbers_v){
            String s = number_v.getText().toString();
            if(! "".equals(s)){
                numbers.add(Integer.parseInt(s));
            }
        }
        if(emails.size() == 0){
            Toast t = Toast.makeText(context, "Email Missing", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        if(numbers.size() == 0){
            Toast t = Toast.makeText(context, "Number Missing", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        contact = new Contact(name, emails, numbers);
        Log.i("MyActivity", String.valueOf(contact));
        clear();
        Toast t = Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT);
        t.show();
    }

    private void clear() {
        name = "";
        emails.clear();
        numbers.clear();
        numbers_v.clear();
        emails_v.clear();
        EditText name_v = (EditText) findViewById(R.id.name_input);
        EditText email, number;
        email = (EditText) findViewById(R.id.email_input);
        number = (EditText) findViewById(R.id.phone_input);
        name_v.setText("");
        email.setText("");
        number.setText("");
        emails_v.add(email);
        numbers_v.add(number);
        LinearLayout add_emails = (LinearLayout) findViewById(R.id.additional_emails);
        LinearLayout add_numbers = (LinearLayout) findViewById(R.id.additional_phones);
        add_emails.removeAllViews();
        add_numbers.removeAllViews();
    }

    public void cancel(View view) {
        clear();
    }
}
