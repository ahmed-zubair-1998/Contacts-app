package com.example.ahmad.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Contact implements Serializable{
    String name;
    String title;
    ArrayList<String> emails;
    ArrayList<Long> numbers;

    public Contact(String name, String title, ArrayList<String> emails, ArrayList<Long> numbers)
    {
        this.name=name;
        this.title=title;
        this.emails= new ArrayList<>(emails);
        this.numbers=new ArrayList<>(numbers);
    }

    @Override
    public String toString() {
        String s = name + ":-\n";
        for(String email : emails){
            s += email + "\n";
        }
        for(Long number : numbers){
            s += number + "\n";
        }
        return s;
    }

    public static Comparator<Contact> contactComparator = new Comparator<Contact>() {
        @Override
        public int compare(Contact o1, Contact o2) {
            String s1 = o1.name.split(" ")[1];
            String s2 = o2.name.split(" ")[1];

            return s1.compareTo(s2);

        }
    };
}
