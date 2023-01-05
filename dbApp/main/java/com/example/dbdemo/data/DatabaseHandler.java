package com.example.dbdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dbdemo.model.Contact;
import com.example.dbdemo.params.Params;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context){
        super(context , Params.DB_NAME , null , Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "(" +
                Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("dbronit", "Query is running ..." + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        db.insertOrThrow(Params.TABLE_NAME, null, values);
//        Log.d("dbronit", "Successfully inserted");
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //create query to read
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);


        if (cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        //lets update now
        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?" ,
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }

}
