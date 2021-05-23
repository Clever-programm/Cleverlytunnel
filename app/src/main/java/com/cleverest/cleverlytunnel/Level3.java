package com.cleverest.cleverlytunnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Level3 extends AppCompatActivity {

    Array array = new Array();
    private long BackPressedTime;
    private Toast backToast;
    Dialog win;
    static public boolean winner = false;
    static public boolean door_key = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level3.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });



        win = new Dialog(this);
        win.requestWindowFeature(Window.FEATURE_NO_TITLE);
        win.setContentView(R.layout.win_dialog);
        win.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        win.setCancelable(false);
        TextView closebtnwin = (TextView)win.findViewById(R.id.close_btn);
        closebtnwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level3.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                win.dismiss();
            }
        });
        Button continuebtnwin = (Button) win.findViewById(R.id.button_continue);
        continuebtnwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                }catch (Exception e){

                }
                win.dismiss();
            }
        });

        Button btnpow = (Button)findViewById(R.id.btn_pow);
        btnpow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(winner){
                    win.show();
                }
            }
        });



    }

    @Override
    public void onBackPressed(){

        if(BackPressedTime + 2000 > System.currentTimeMillis()){
            try{
                Intent intent = new Intent(Level3.this,GameLevels.class);
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
}
