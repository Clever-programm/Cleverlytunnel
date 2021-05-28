package com.cleverest.cleverlytunnel;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class GameLevels extends AppCompatActivity {

    private SQLiteDatabase mDb;
    String rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);
        initDB();
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        TextView tv3 = (TextView)findViewById(R.id.textView3);
        TextView tv4 = (TextView)findViewById(R.id.textView4);
        TextView tv5 = (TextView)findViewById(R.id.textView5);
        TextView tv6 = (TextView)findViewById(R.id.textView6);
        TextView tv7 = (TextView)findViewById(R.id.textView7);
        TextView tv8 = (TextView)findViewById(R.id.textView8);
        TextView tv9 = (TextView)findViewById(R.id.textView9);
        TextView tv10 = (TextView)findViewById(R.id.textView10);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        tv5.setVisibility(View.GONE);
        tv6.setVisibility(View.GONE);
        tv7.setVisibility(View.GONE);
        tv8.setVisibility(View.GONE);
        tv9.setVisibility(View.GONE);
        tv10.setVisibility(View.GONE);

            try{
                String q = "SELECT Rating FROM `Records` WHERE id = " + (EnableSave.id);
                Cursor cursor = mDb.rawQuery(q,null);
                cursor.moveToFirst();
                rate = cursor.getString(0);
                cursor.close();

                int rating = Integer.parseInt(rate);

                if(rating >= 1){
                    tv1.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv2.setVisibility(View.VISIBLE);
                }
                if(rating >= 2){
                    tv2.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv3.setVisibility(View.VISIBLE);
                }
                if(rating >= 3){
                    tv3.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv4.setVisibility(View.VISIBLE);
                }
                if(rating >= 4){
                    tv4.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv5.setVisibility(View.VISIBLE);
                }
                if(rating >= 5){
                    tv5.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv6.setVisibility(View.VISIBLE);
                }
                if(rating >= 6){
                    tv6.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv7.setVisibility(View.VISIBLE);
                }
                if(rating >= 7){
                    tv7.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv8.setVisibility(View.VISIBLE);
                }
                if(rating >= 8){
                    tv8.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv9.setVisibility(View.VISIBLE);
                }
                if(rating >= 9){
                    tv9.setBackgroundResource(R.drawable.button_level_style_complite);
                    tv10.setVisibility(View.VISIBLE);
                }
                if(rating >= 10){
                    tv10.setBackgroundResource(R.drawable.button_level_style_complite);
                }
            }catch (Exception e){
                Log.d(e.getMessage(),"bd");
            }



        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Button btnsave = (Button)findViewById(R.id.button_save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,EnableSave.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,Level1.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,Level2.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,Level3.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this,Level4.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    Log.d(e.getMessage(),"intent");
                    Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(GameLevels.this,MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){
            Log.d(e.getMessage(),"intent");
            Toast toast = Toast.makeText(getBaseContext(),"IntentError:3", Toast.LENGTH_SHORT);
            toast.show();
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