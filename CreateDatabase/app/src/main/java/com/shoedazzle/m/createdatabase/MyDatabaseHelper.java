package com.shoedazzle.m.createdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //Create Table;
    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "Age";

    //If upadate table colume;
    private static final String GENDER = "Gender";
    private static final int VERSION_NUMBER = 2;
    //Create table;
    //private  static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(25),"+AGE+" INTEGER );";
    //OnUpdate;
    private  static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(25),"+AGE+" INTEGER,"+GENDER+" VARCHAR(15));";
    Context context;
    //Drop Table;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Show Database;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;


    //1.Super: Constructor;
    public MyDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    //2.Oncreate;
    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }


    }

    //3.OnUpgrade;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"OnUpgate is called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }


    }

    //InsertData;
    public long insertData(String Name,String Age,String Gender){
        //Return Data;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //All Data Store;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,Name);
        contentValues.put(AGE,Age);
        contentValues.put(GENDER,Gender);
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    //ShowDatabase;
     public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
       return cursor;

    }


    //Update data;
    public boolean updateData(String id, String Name, String Age, String Gender){
        //Return Data;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //All Data Store;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,Name);
        contentValues.put(AGE,Age);
        contentValues.put(GENDER,Gender);

        sqLiteDatabase.update(TABLE_NAME,contentValues ,ID+" = ?",new String[]{id});
        return true;
    }

    //Delete Data;
    public int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }


}
