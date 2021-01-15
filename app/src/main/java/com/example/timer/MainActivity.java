package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SeekBar sk;
    CountDownTimer cdt;
    boolean allowed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView t=(TextView) findViewById(R.id.clock);

        sk=(SeekBar) findViewById(R.id.seekBar);
        sk.setMax(30);
        cdt=new CountDownTimer(30000,1000) {    //timer
            public void onTick(long milisecondsUntilDone) {
                sk.setProgress((int) milisecondsUntilDone / 1000);
                Log.i("seconds left", String.valueOf(milisecondsUntilDone / 1000));
                t.setText(String.valueOf("00:"+milisecondsUntilDone/1000));


            }

            public void onFinish() {
                Log.i("yes it's finished", "yeah");

            }
        };






        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                t.setText(String.valueOf("00:"+i));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(allowed==true){
                    cdt.cancel();

                }




            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(allowed==true){
                    cdt=new CountDownTimer(seekBar.getProgress()*1000,1000) {    //timer
                        public void onTick(long milisecondsUntilDone) {
                            sk.setProgress((int) milisecondsUntilDone / 1000);
                            Log.i("seconds left", String.valueOf(milisecondsUntilDone / 1000));
                            t.setText(String.valueOf("00:"+milisecondsUntilDone/1000));


                        }

                        public void onFinish() {
                            Log.i("yes it's finished", "yeah");

                        }
                    }.start();


                }

            }
        });


    }
    public void onclick(View view){
        if(allowed==true){
            cdt.cancel();
            allowed=false;
            ((Button)view).setText("START");
        }
        else{
            allowed=true;
            ((Button)view).setText("STOP");
            cdt.start();

        }



    }
}