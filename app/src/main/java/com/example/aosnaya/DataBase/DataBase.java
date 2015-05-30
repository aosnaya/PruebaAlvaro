package com.example.aosnaya.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aosnaya.interfaces.DataBaseInterfase;

import java.util.Vector;

/**
 * Created by aosnaya on 19/05/15.
 */
public class DataBase extends SQLiteOpenHelper implements DataBaseInterfase{


    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORES (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, last_name TEXT, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This is to upgrade database version
    }

    @Override
    public boolean storeData(String name, String last_name, String score) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("INSERT INTO SCORES VALUES(null, '" + name + "', '" + last_name + "', " + score + ")");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Vector<String> vecNameList(int size) {
        Vector<String> vecResult = new Vector<>();
        String[] FIELDS = {"name", "last_name", "score"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("SCORES", FIELDS, null, null, null, null, "SCORE", Integer.toString(size));

        while (cursor.moveToNext()) {
            vecResult.add(cursor.getString(0));
            vecResult.add(cursor.getString(1));
            vecResult.add(cursor.getString(2));
        }

        return vecResult;
    }

    @Override
    public Vector<String> vecSearchByID(long id) {
        Vector<String> vecResult = new Vector<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT name, last_name, score FROM SCORES WHERE id = " + id;
        Cursor cursor;
        try {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                vecResult.add(cursor.getString(0));
                vecResult.add(cursor.getString(1));
                vecResult.add(cursor.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vecResult;
    }

    @Override
    public Vector<String> vecSearchByName(String name) {
        Vector<String> vecResult = new Vector<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT name, last_name, score FROM SCORES WHERE name = '" + name + "'";
        Cursor cursor;
        try {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                vecResult.add(cursor.getString(0));
                vecResult.add(cursor.getString(1));
                vecResult.add(cursor.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vecResult;
    }

    @Override
    public Vector<String> vecSearchByLastName(String last_name) {
        Vector<String> vecResult = new Vector<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT name, last_name, score FROM SCORES WHERE last_name = '" + last_name + "'";
        Cursor cursor;
        try {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                vecResult.add(cursor.getString(0));
                vecResult.add(cursor.getString(1));
                vecResult.add(cursor.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vecResult;
    }

    @Override
    public Vector<String> vecSearchByScore(int score) {
        Vector<String> vecResult = new Vector<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT name, last_name, score FROM SCORES WHERE score = '" + score + "'";
        Cursor cursor;
        try {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                vecResult.add(cursor.getString(0));
                vecResult.add(cursor.getString(1));
                vecResult.add(cursor.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vecResult;
    }
}
