package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dbdemo.data.DatabaseHandler;
import com.example.dbdemo.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        //Creating a contact object for db
        Contact ronit = new Contact();
        ronit.setName("Ronit Bhardwaj");
        ronit.setPhoneNumber("8888888333");

        db.addContact(ronit);

        Contact vatsal = new Contact();
        vatsal.setName("Vatsal Purbia");
        vatsal.setPhoneNumber("9999999911");

        db.addContact(vatsal);

        Contact piyush = new Contact();
        piyush.setName("Piyush Tiwari");
        piyush.setPhoneNumber("9999999922");

        db.addContact(piyush);

        Contact udai = new Contact();
        udai.setName("Udai Singh Shekhawat");
        udai.setPhoneNumber("9999999933");

        db.addContact(udai);

//        Log.d("dbronit", "Main activity is doing good");

        db.deleteContact(5);
        db.deleteContact(1);

        //get all contacts
        List<Contact> allContacts = db.getAllContacts();
        for(Contact contact : allContacts){
            Log.d("dbronit", "ID: " + contact.getId() + "\n" + "Name: " + contact.getName() + "\n" + "PhoneNumber: " + contact.getPhoneNumber() + "\n");
        }



    }
}