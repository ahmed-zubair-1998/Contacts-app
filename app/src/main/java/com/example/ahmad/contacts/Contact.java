package com.example.ahmad.contacts;

import java.util.ArrayList;

public class Contact {
    String name;
    ArrayList<String> emails;
    ArrayList<Integer> numbers;

    public Contact(String name, ArrayList<String> emails, ArrayList<Integer> numbers)
    {
        this.name=name;
        this.emails=emails;
        this.numbers=numbers;
    }

    @Override
    public String toString() {
        String s = name + ":-\n";
        for(String email : emails){
            s += email + "\n";
        }
        for(int number : numbers){
            s += number + "\n";
        }
        return s;
    }
}
