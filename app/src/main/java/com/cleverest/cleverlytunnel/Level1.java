package com.cleverest.cleverlytunnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Level1 extends AppCompatActivity {

    private long BackPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
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
}