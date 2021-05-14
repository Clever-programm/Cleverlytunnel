package com.cleverest.cleverlytunnel;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Table extends ListActivity {

    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();

        ArrayList<String> words = new ArrayList<>();
        words.add("Back");
        try{
            Cursor cursor = mDb.rawQuery("SELECT User FROM `Records`",null);
            cursor.moveToFirst();
            words.add(cursor.getString(0));
            while (cursor.moveToNext()) {
                String u = cursor.getString(0);
                words.add(u);
            }
            cursor.close();
        }catch (Exception e){
            Log.d(e.getMessage(),"bd");
            Toast toast = Toast.makeText(getBaseContext(),"DataBaseError:1", Toast.LENGTH_SHORT);
            toast.show();
        }


        ArrayAdapter<String> monthAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        setListAdapter(monthAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String tap = (String) getListAdapter().getItem(position);
        if(position == 0){
            Intent intent = new Intent(Table.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else Toast.makeText(this, tap, Toast.LENGTH_SHORT).show();
    }

   @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Table.this,MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){

        }
    }
    private void initDB() {
        //Переменная для работы с БД
        DatabaseHelper mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        mDb = mDBHelper.getWritableDatabase();
    }
}


