package com.cleverest.cleverlytunnel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog lose;
    Dialog win;
    public int count = 0;
    Array array = new Array();
    private long BackPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
        TextView level = (TextView)findViewById(R.id.text_levels);
        level.setText(R.string.level2);

        final ImageView image_left = (ImageView)findViewById(R.id.image_left);
        image_left.setClipToOutline(true);
        final ImageView image_right = (ImageView)findViewById(R.id.image_right);
        image_right.setClipToOutline(true);

        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        TextView closebtn = (TextView)dialog.findViewById(R.id.close_btn);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialog.dismiss();
            }
        });
        Button continuebtn = (Button) dialog.findViewById(R.id.button_continue);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        lose = new Dialog(this);
        lose.requestWindowFeature(Window.FEATURE_NO_TITLE);
        lose.setContentView(R.layout.lose_dialog);
        lose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lose.setCancelable(false);
        TextView closebtnlose = (TextView)lose.findViewById(R.id.close_btn);
        closebtnlose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level2.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                lose.dismiss();
            }
        });
        Button againbtn = (Button) lose.findViewById(R.id.button_to_again);
        againbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lose.dismiss();
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
                    Intent intent = new Intent(Level2.this,GameLevels.class);
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
                    Intent intent = new Intent(Level2.this,Level3.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                win.dismiss();
            }
        });

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
        };

        TextView txtleft = (TextView)findViewById(R.id.textleft);
        TextView txtright = (TextView)findViewById(R.id.textright);

        txtleft.setText(array.texts1[0]);
        txtright.setText(array.texts1[10]);
        ImageView imgleft = (ImageView)findViewById(R.id.image_left);
        ImageView imgright = (ImageView)findViewById(R.id.image_right);


        imgleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    imgright.setEnabled(false);
                    if(count==0||count==1||count==3||count==6||count==7||count==9){
                        imgleft.setImageResource(R.drawable.img_true);
                    }else{
                        imgleft.setImageResource(R.drawable.img_false);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    imgright.setEnabled(true);
                    if(count==2||count==4||count==5||count==8){
                        while (count!=-1){
                            TextView tv = (TextView)findViewById(progress[count]);
                            tv.setBackgroundResource(R.drawable.stile_points);
                            count--;
                        }
                        count++;
                        imgleft.setImageResource(R.drawable.left_tunnel);
                        txtleft.setText(array.texts1[0]);
                        txtright.setText(array.texts1[10]);
                        lose.show();
                    }else if(count==0||count==1||count==3||count==6||count==7){
                        TextView tv = (TextView)findViewById(progress[count]);
                        tv.setBackgroundResource(R.drawable.stile_pointson);
                        count++;
                        imgleft.setImageResource(R.drawable.left_tunnel);
                        txtleft.setText(array.texts1[count]);
                        txtright.setText(array.texts1[count + 10]);
                    }else if(count==9){
                        TextView tv = (TextView)findViewById(progress[count]);
                        tv.setBackgroundResource(R.drawable.stile_pointson);
                        count++;
                        imgleft.setImageResource(R.drawable.left_tunnel);
                        win.show();
                    }
                }
                return true;
            }
        });


        imgright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    imgleft.setEnabled(false);
                    if(count==0||count==1||count==3||count==6||count==7||count==9){
                        imgright.setImageResource(R.drawable.img_false);
                    }else{
                        imgright.setImageResource(R.drawable.img_true);
                    }
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    imgleft.setEnabled(true);
                    if(count==0||count==1||count==3||count==6||count==7||count==9){
                        while (count!=-1){
                            TextView tv = (TextView)findViewById(progress[count]);
                            tv.setBackgroundResource(R.drawable.stile_points);
                            count--;
                        }
                        count++;
                        imgright.setImageResource(R.drawable.right_tunnel);
                        txtleft.setText(array.texts1[0]);
                        txtright.setText(array.texts1[10]);
                        lose.show();
                    }else if(count==2||count==4||count==5||count==8){
                        TextView tv = (TextView)findViewById(progress[count]);
                        tv.setBackgroundResource(R.drawable.stile_pointson);
                        count++;
                        imgright.setImageResource(R.drawable.right_tunnel);
                        txtleft.setText(array.texts1[count]);
                        txtright.setText(array.texts1[count + 10]);
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed(){

        if(BackPressedTime + 2000 > System.currentTimeMillis()){
            try{
                Intent intent = new Intent(Level2.this,GameLevels.class);
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