package com.nihon.basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private PaintView paintView;
    ImageView clean,sound,img,btnpre,btnnext;
    TextView show;
    int num=0;
    public MediaPlayer mediaplayer;
       int[] songfile=new int[] {R.raw.a, R.raw.i, R.raw.u, R.raw.e, R.raw.o,R.raw.ka, R.raw.ki, R.raw.ku, R.raw.ke, R.raw.ko,R.raw.sa, R.raw.shi, R.raw.su, R.raw.se, R.raw.so,
            R.raw.ta, R.raw.chi, R.raw.tsu, R.raw.te, R.raw.to,R.raw.na, R.raw.ni, R.raw.nu, R.raw.ne, R.raw.no,R.raw.ha, R.raw.hi, R.raw.hu, R.raw.he, R.raw.ho,R.raw.ma, R.raw.mi, R.raw.mu,
            R.raw.me, R.raw.mo ,R.raw.ya, R.raw.yu, R.raw.yo,R.raw.ra, R.raw.ri, R.raw.ru, R.raw.re, R.raw.ro,R.raw.wa,R.raw.n,R.raw.wo};

    int[] pic=new int[] {R.drawable.a, R.drawable.i, R.drawable.u, R.drawable.e, R.drawable.o,R.drawable.ka, R.drawable.ki, R.drawable.ku, R.drawable.ke, R.drawable.ko,R.drawable.sa, R.drawable.shi, R.drawable.su, R.drawable.se, R.drawable.so,
            R.drawable.ta, R.drawable.chi, R.drawable.tsu, R.drawable.te, R.drawable.to,R.drawable.na, R.drawable.ni, R.drawable.nu, R.drawable.ne, R.drawable.no,R.drawable.ha, R.drawable.hi, R.drawable.hu, R.drawable.he, R.drawable.ho,R.drawable.ma, R.drawable.mi, R.drawable.mu,
            R.drawable.me, R.drawable.mo ,R.drawable.ya, R.drawable.yu, R.drawable.yo,R.drawable.ra, R.drawable.ri, R.drawable.ru, R.drawable.re, R.drawable.ro,R.drawable.wa,R.drawable.n,R.drawable.wo};

    int[] pic2=new int[] {R.drawable.a_1, R.drawable.i_1, R.drawable.u_1, R.drawable.e_1, R.drawable.o_1,R.drawable.ka_1, R.drawable.ki_1, R.drawable.ku_1, R.drawable.ke_1, R.drawable.ko_1,R.drawable.sa_1, R.drawable.shi_1, R.drawable.su_1, R.drawable.se_1, R.drawable.so_1,
            R.drawable.ta_1, R.drawable.chi_1, R.drawable.tsu_1, R.drawable.te_1, R.drawable.to_1,R.drawable.na_1, R.drawable.ni_1, R.drawable.nu_1, R.drawable.ne_1, R.drawable.no_1,R.drawable.ha_1, R.drawable.hi_1, R.drawable.fu_1, R.drawable.he_1, R.drawable.ho_1,R.drawable.ma_1, R.drawable.mi_1, R.drawable.mu_1,
            R.drawable.me_1, R.drawable.mo_1,R.drawable.ya_1, R.drawable.yu_1, R.drawable.yo_1,R.drawable.ra_1, R.drawable.ri_1, R.drawable.ru_1, R.drawable.re_1, R.drawable.ro_1,R.drawable.wa_1,R.drawable.n_1,R.drawable.n_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        paintView = (PaintView) findViewById(R.id.paintView);
        clean=(ImageView)findViewById(R.id.clean);
        sound=(ImageView)findViewById(R.id.sound);
        img=(ImageView)findViewById(R.id.img);
        show=(TextView) findViewById(R.id.show);
        btnpre=(ImageView)findViewById(R.id.btnpre);
        btnnext=(ImageView)findViewById(R.id.btnnext);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        paintView.setBackground(getResources().getDrawable(pic[0]));
        mediaplayer=new MediaPlayer();
        clean.setOnClickListener(cbtn);
        sound.setOnClickListener(sbtn);
        pre();
        //show.setText("あい（愛）"+"\n[a-i]\n"+"愛：愛慕");
        btnpre.setOnTouchListener(btpre);
        btnnext.setOnTouchListener(btnext);

    }
private ImageView.OnClickListener cbtn=new ImageView.OnClickListener(){
    @Override
    public void onClick(View v) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        paintView.clear();
    }
};
    private ImageView.OnClickListener sbtn=new ImageView.OnClickListener(){
        @Override
        public void onClick(View v) {
                    playSong(songfile[num]);

        }
    };
public void count(){
    if(num<0){num=0;}
    if(num>=pic.length){num=pic.length-1;mytoast("最後一題");}
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    paintView.init(metrics);
    paintView.clear();
pre();

}
    public void pre(){
    int s=num+1;
        String result = dbbasic.executeQuery(s+"");

        try{
            JSONArray jsonArray = new JSONArray(result);

            for(int i = 0; i < jsonArray.length(); i++) //代理或主管有工號者顯示
            {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                String nihon=jsonData.getString("nihon");
                String pinyin=jsonData.getString("pinyin");
                String ch=jsonData.getString("ch");
                show.setText(nihon+"\n\n"+pinyin+"\n\n"+ch);
            }

        }

        catch(Exception e){}

    }
    private ImageView.OnTouchListener btnext=new ImageView.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    btnnext.setImageResource(R.drawable.aki_nexth);
                    num++;
                    count();
                    paintView.setBackground(getResources().getDrawable(pic[num]));
                    img.setImageResource(pic2[num]);
                    break;
                case MotionEvent.ACTION_UP:
                    btnnext.setImageResource(R.drawable.aki_next);

                    break;
            }
            return true;
        }


    };
    private ImageView.OnTouchListener btpre=new ImageView.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    btnpre.setImageResource(R.drawable.aki_preh);
                    num--;
                    count();
                    paintView.setBackground(getResources().getDrawable(pic[num]));
                    img.setImageResource(pic2[num]);
                    break;
                case MotionEvent.ACTION_UP:

                    btnpre.setImageResource(R.drawable.aki_pre);
                    break;
            }
            return true;
        }


    };
    private void playSong(int song) {
        mediaplayer.reset();
        mediaplayer= MediaPlayer.create(MainActivity.this, song); //播放歌曲源
        try {
            mediaplayer.prepare();
        } catch (IllegalStateException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //  e.printStackTrace();
        }
        mediaplayer.start(); //開始播放


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void mytoast(String str)
    {
        Toast toast=Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
