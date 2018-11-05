package com.shoedazzle.m.createdatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    MyDatabaseHelper myDatabaseHelper;

    //Insert From;
    EditText name,age,gender;
    Button addData;

    //Show Data;
    Button showData;

    //Update Data;
    Button updateData;
    EditText id;

    //Delete data;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database Return;
        myDatabaseHelper = new MyDatabaseHelper(this);
       SQLiteDatabase sqLiteDatabase =  myDatabaseHelper.getWritableDatabase();


        //Insert From;
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        addData = findViewById(R.id.button);
        addData.setOnClickListener(this);

        //ShowData;
        showData = findViewById(R.id.show);
        showData.setOnClickListener(this);

        //UpdateData;
        updateData = findViewById(R.id.update);
        id = findViewById(R.id.id);
        updateData.setOnClickListener(this);

        //Delete Data;
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String Name = name.getText().toString();
        String Age = age.getText().toString();
        String Gender = gender.getText().toString();
        //Update data;
        String Id = id.getText().toString();


        //Insert Data;
        if (v.getId() == R.id.button){

            //Call insertData;
          long rowId = myDatabaseHelper.insertData(Name,Age,Gender);
          if (rowId==-1){
              Toast.makeText(getApplicationContext(),"Not Successfull Insert",Toast.LENGTH_LONG).show();
          }
          else {
              Toast.makeText(getApplicationContext(),"Successfull Insert",Toast.LENGTH_LONG).show();
          }

        }

        //Show All Data Display;
        if (v.getId()== R.id.show) {

            Cursor cursor = myDatabaseHelper.displayAllData();

            if (cursor.getCount() == 0) {

                //There is no data so will displayAllData;
                ShowData("Error","No Data Found");
                return;
            }

            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {

                stringBuffer.append("ID :" + cursor.getString(0) + "\n");
                stringBuffer.append("NAME :" + cursor.getString(1) + "\n");
                stringBuffer.append("AGE :" + cursor.getString(2) + "\n");
                stringBuffer.append("GENDER :" + cursor.getString(3) + "\n\n\n");

            }
            ShowData("ResultSet", stringBuffer.toString());
        }

        //Update Data;
        if (v.getId()== R.id.update){

        boolean isUpdated =  myDatabaseHelper.updateData(Id,Name,Age,Gender);
        if(isUpdated==true){
            Toast.makeText(getApplicationContext(),"Successful UpDate",Toast.LENGTH_LONG).show();
            }
         else {
            Toast.makeText(getApplicationContext(), "UnSuccessful UpDate", Toast.LENGTH_LONG).show();
        }
     }

     //Delete Data;
     if (v.getId()==R.id.delete){

          int value =  myDatabaseHelper.deleteData(Id);
          if(value>0){
              Toast.makeText(getApplicationContext(),"Successful Delete",Toast.LENGTH_LONG).show();
          }
          else{

              Toast.makeText(getApplicationContext(),"UnSuccessful Delete",Toast.LENGTH_LONG).show();
          }
     }

 }

    //Show Data;
    public void ShowData(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }



}
