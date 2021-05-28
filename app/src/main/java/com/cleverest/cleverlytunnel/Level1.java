package com.cleverest.cleverlytunnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Level1 extends AppCompatActivity {

    private long BackPressedTime;
    private Toast backToast;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        initDB();

        TextView level = (TextView)findViewById(R.id.text_levels);
        level.setText(R.string.level1);

        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Cursor cursor = mDb.rawQuery("SELECT rating FROM Records WHERE id = " + EnableSave.id,null);
                    cursor.moveToFirst();
                    int rate = Integer.parseInt(cursor.getString(0));
                    cursor.close();
                    if(rate==0){mDb.execSQL("update records set rating = 1 where id = "+ EnableSave.id);}
                    else if(rate<0){
                        Toast toast = Toast.makeText(getBaseContext(),"How you do it???", Toast.LENGTH_SHORT);
                        toast.show();}
                }catch (Exception e){
                    Log.d(e.getMessage(),"bd");
                    Toast toast = Toast.makeText(getBaseContext(),"DataBaseError:1", Toast.LENGTH_SHORT);
                    toast.show();
                }

                try{
                    Intent intent = new Intent(Level1.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });

    }
    @Override
    public void onBackPressed(){

        if(BackPressedTime + 2000 > System.currentTimeMillis()){
            try{
                Intent intent = new Intent(Level1.this,GameLevels.class);
                startActivity(intent);
                finish();
            }catch(Exception e){

            }
        }else {
            backToast = Toast.makeText(getBaseContext(),"Click again to left this level", Toast.LENGTH_SHORT);
            backToast.show();
        }
        BackPressedTime = System.currentTimeMillis();
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