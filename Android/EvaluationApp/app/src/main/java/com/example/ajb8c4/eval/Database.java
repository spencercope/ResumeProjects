package com.example.ajb8c4.eval;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Muhamed on 7/25/2016.
 */


public class Database extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME="Test23.db";
    public static final String TABLE_NAME ="teamTable";

    public static final String TEAMNAME="TEAMNAME";
    public static final String Q1="q1";
    public static final String Q2="q2";
    public static final String Q3="q3";
    public static final String Q4="q4";
    public static final String AVG="Avg";
    public static final String COMMENTS="Comments";
    public static final String TEAMNAMETXT="TEAMNAMETXT";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+ TABLE_NAME +" (TEAMNAME INTEGER PRIMARY KEY AUTOINCREMENT ,Q1 INTEGER,Q2 INTEGER,Q3 INTEGER,Q4 INTEGER,AVG TEXT,COMMENTS TEXT,TEAMNAMETXT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( int q1, int q2, int q3, int q4, String avg, String comments, String teamName)
    {
        //create database and table
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(TEAMNAME,teamName);
        contentValues.put(Q1,q1);
        contentValues.put(Q2,q2);
        contentValues.put(Q3,q3);
        contentValues.put(Q4,q4);
        contentValues.put(AVG,avg);
        contentValues.put(COMMENTS,comments);
        contentValues.put(TEAMNAMETXT,teamName);

        long result=db.insert(TABLE_NAME,null,contentValues);

        if (result==-1)
            return false;
        else
            return true;

    }


    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res= db.rawQuery("select * from teamTable",null);
        return res;

    }

    public void deleteData()
    {
        //Context context = null;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        //context.deleteDatabase(DATABASE_NAME);

    }




}
