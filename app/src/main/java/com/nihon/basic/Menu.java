package com.nihon.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
ImageView imgh,imgk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgh=(ImageView)findViewById(R.id.imgh);//平假
        imgk=(ImageView)findViewById(R.id.imgk);//片假
        imgh.setOnTouchListener(hbtn);
        imgk.setOnTouchListener(kbtn);

    }
    private ImageView.OnTouchListener hbtn=new ImageView.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    imgh.setImageResource(R.drawable.aki_hirah);
                    break;
                case MotionEvent.ACTION_UP:
                    imgh.setImageResource(R.drawable.aki_hira);
                    Intent intent=new Intent();
                    intent.setClass(Menu.this,MainActivity.class);
                    startActivity(intent);
                    break;

            }
            return true;
        }
    };
    private ImageView.OnTouchListener kbtn=new ImageView.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    imgk.setImageResource(R.drawable.aki_katah);
                    break;
                case MotionEvent.ACTION_UP:
                    imgk.setImageResource(R.drawable.aki_kata);
                    Intent intent=new Intent();
                    intent.setClass(Menu.this,Basich.class);
                    startActivity(intent);
                    break;

            }
            return true;
        }
    };
}
