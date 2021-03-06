package com.cleverest.cleverlytunnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class EnableSave extends AppCompatActivity {

    private SQLiteDatabase mDb;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_save);
        initDB();
        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(EnableSave.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });
        EditText editName = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText editPass = (EditText)findViewById(R.id.editTextTextPassword);
        Button button_entry = (Button)findViewById(R.id.button_entry);
        button_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Cursor cursor = mDb.rawQuery("SELECT id FROM `Records` WHERE User = '" + editName.getText() + "' AND Password = '" + editPass.getText() + "'",null);
                    cursor.moveToFirst();
                    id = cursor.getString(0);
                    Toast toast = Toast.makeText(getBaseContext(),"OK Your id = " + id, Toast.LENGTH_SHORT);
                    toast.show();
                    cursor.close();
                }catch (Exception e){
                    Log.d(e.getMessage(),"bd");
                    Toast toast = Toast.makeText(getBaseContext(),"DataBaseError:1", Toast.LENGTH_SHORT);
                    toast.show();
                }
                Intent intent = new Intent(EnableSave.this,GameLevels.class);
                startActivity(intent);
                finish();
            }
        });
        Button button_regist = (Button)findViewById(R.id.button_register);
        button_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if ((editName.getText()).length()==0 && (editPass.getText()).length()==0){
                        Toast toast = Toast.makeText(getBaseContext(),"Add name and password",Toast.LENGTH_SHORT);
                        toast.show();
                    }else if ((editName.getText()).length()==0){
                        Toast toast = Toast.makeText(getBaseContext(),"Add name",Toast.LENGTH_SHORT);
                        toast.show();
                    }else if ((editPass.getText()).length()==0){
                        Toast toast = Toast.makeText(getBaseContext(),"Add password",Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        mDb.execSQL("insert into records (user, password, rating) values ('" + editName.getText() + "', '" + editPass.getText()  + "', 0)");
                        Cursor cursor = mDb.rawQuery("SELECT id FROM `Records` WHERE User = '" + editName.getText() + "' AND Password = '" + editPass.getText() + "'",null);
                        cursor.moveToFirst();
                        id = cursor.getString(0);
                        Toast toast = Toast.makeText(getBaseContext(),"OK Your id = " + id, Toast.LENGTH_SHORT);
                        toast.show();
                        cursor.close();
                    }

                }catch (Exception e){
                    Log.d(e.getMessage(),"bd");
                    Toast toast = Toast.makeText(getBaseContext(),"DataBaseError:1", Toast.LENGTH_SHORT);
                    toast.show();
                }
                Intent intent = new Intent(EnableSave.this,GameLevels.class);
                startActivity(intent);
                finish();
            }
        });
    }

    
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(EnableSave.this,GameLevels.class);
            startActivity(intent);
            finish();
        }catch(Exception e){

        }
    }
    private void initDB() {
        //???????????????????? ?????? ???????????? ?? ????
        DatabaseHelper mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
    }
}