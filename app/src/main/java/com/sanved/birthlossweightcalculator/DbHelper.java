package com.sanved.birthlossweightcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordsdb";
    private static final String TABLE_NAME = "words";
    private static final String KEY_ID = "id";
    private static final String K_KG = "kilo";
    private static final String K_LBS = "lbs";
    private static final String K_OZ = "oz";
    private static final String K_LBSOZ = "lbsdec";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, kilo REAL, lbs REAL, oz REAL, lbsoz REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // Used to add new words
    public void addWord(WeightEntry wd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(K_KG, wd.getKilo());
        values.put(K_LBS, wd.getPound());
        values.put(K_OZ, wd.getOunces());
        values.put(K_LBSOZ, wd.getLbsdec());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }


    // Get aa ArrayList of all words
    public ArrayList<WeightEntry> allWords() {

        ArrayList<WeightEntry> wordDataList = new ArrayList<WeightEntry>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        WeightEntry word = null;

        if (cursor.moveToFirst()) {
            do {
                word = new WeightEntry(cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4));

                wordDataList.add(word);
            } while (cursor.moveToNext());
        }

        return wordDataList;
    }


    // Written here is a method use to delete every word/row.
    // Use it for testing purposes. Not for release.
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

}