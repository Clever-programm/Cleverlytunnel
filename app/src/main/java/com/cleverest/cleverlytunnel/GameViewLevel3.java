package com.cleverest.cleverlytunnel;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GameViewLevel3 extends View {

    public int n = 10;
    public int w;
    public int personX = 0;
    public int personY = 0;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    Bitmap b =  BitmapFactory.decodeResource(getResources(), R.drawable.shar);
    Rect br = new Rect(0,0,b.getWidth(),b.getHeight());

    Bitmap tunn =  BitmapFactory.decodeResource(getResources(), R.drawable.tunnel);
    Rect tunnr = new Rect(0,0,tunn.getWidth(),tunn.getHeight());

    Bitmap stena =  BitmapFactory.decodeResource(getResources(), R.drawable.stena);
    Rect stenar = new Rect(0,0,stena.getWidth(),stena.getHeight());

    Bitmap exit =  BitmapFactory.decodeResource(getResources(), R.drawable.exit);
    Rect exitr = new Rect(0,0,exit.getWidth(),exit.getHeight());

    private float canvasSize;
    int[][] board = {{9,0,0,99,99,99,9,9,9,9},{0,0,0,0,99,0,99,99,99,99},{9,9,9,0,9,9,9,9,9,99},{9,9,9,9,9,9,0,9,9,99},{9,9,9,9,9,9,9,9,9,99},{9,9,9,9,9,9,9,9,9,99},{9,9,0,9,9,9,9,9,9,99},{9,9,9,9,9,9,9,9,9,99},{9,9,9,9,9,9,9,9,100,99},{9,9,9,9,9,9,9,9,9,9},};

    public GameViewLevel3(Context context, AttributeSet attrs) {
        super(context, attrs);
        canvasSize = (int) convertDpToPixel(355, context);
        w = (int) (canvasSize / n);
    }


    public float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(250, 0, 0, 0);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        Paint p1 = new Paint();
        board[personX][personY] = 10;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                canvas.drawBitmap(tunn,tunnr, new Rect(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1), p1);
                if (board[i][j] == 10) {
                    canvas.drawBitmap(b,br, new Rect(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1), p1);
                }
                if (board[i][j] == 100) {
                    canvas.drawBitmap(exit,exitr, new Rect(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1), p1);
                }
                if (board[i][j] == 9) {
                    canvas.drawBitmap(stena,stenar, new Rect(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1), p1);
                }
                if (board[i][j] == 99) {
                    canvas.drawBitmap(stena,stenar, new Rect(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1), p1);
                }
                if (board[i][j] == 1) {
                    p1.setColor(Color.GREEN);
                    canvas.drawOval(i * w + 1, j * w + 1, (i + 1) * w - 1, (j + 1) * w - 1, p1);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int i = (int) (event.getX() / w);
            int j = (int) (event.getY() / w);



            if(personX==0 && personY==0){
                if(board[personX+1][personY]==board[i][j]&&board[personX][personY+1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personX==9&&personY==9){
                if(board[personX-1][personY]==board[i][j]&&board[personX][personY-1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personX==0&&personY==9){
                if(board[personX+1][personY]==board[i][j]&&board[personX][personY-1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personX==9&&personY==0){
                if(board[personX-1][personY]==board[i][j]&&board[personX][personY+1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personX==0){
                if(board[personX+1][personY]==board[i][j]&&board[personX][personY+1]==board[i][j]&&board[personX][personY-1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personX==9){
                if(board[personX-1][personY]==board[i][j]&&board[personX][personY+1]==board[i][j]&&board[personX][personY-1]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personY==0){
                if(board[personX][personY+1]==board[i][j]&&board[personX+1][personY]==board[i][j]&&board[personX-1][personY]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else if(personY==9){
                if(board[personX][personY-1]==board[i][j]&&board[personX+1][personY]==board[i][j]&&board[personX-1][personY]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }else{
                if(board[personX][personY-1]==board[i][j]&&board[personX][personY+1]==board[i][j]&&board[personX+1][personY]==board[i][j]&&board[personX-1][personY]==board[i][j]){
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if(board[x][y]!=10 && board[x][y]!=100 && board[x][y]!=9 && board[x][y]!=99 && board[x][y]!=board[i][j])board[x][y]=0;
                        }
                    }
                    if(board[i][j]!=9&&board[i][j]!=100){
                        board[i][j] = (board[i][j]+1)%3;
                    }

                    if(board[i][j]==2){
                        board[personX][personY]=0;
                        personX=i;
                        personY=j;
                        board[personX][personY]=10;
                    }
                }
            }

            try{
                if (board[personX+1][personY]==100) {
                    Level3.winner = true;
                }
            }catch (Exception e){}
            try{
                if (board[personX-1][personY]==100) {
                    Level3.winner = true;
                }
            }catch (Exception e){}
            try{
                if (board[personX][personY+1]==100) {
                    Level3.winner = true;
                }
            }catch (Exception e){}
            try{
                if (board[personX][personY-1]==100) {
                    Level3.winner = true;
                }
            }catch (Exception e){}




            invalidate();
        }

        return true;
    }
}
