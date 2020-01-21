package com.example.courseeggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    TextView textView;
    SeekBar seekBar;
    Button button; ImageView imageView;
    int n = 0, timeSet;
    long min;
    int sec;
    String str;

    public void tap(View view) {
        imageView=findViewById(R.id.imageView);
        mediaPlayer = MediaPlayer.create(this, R.raw.horn);
        button = findViewById(R.id.button);
        if (seekBar.getProgress( ) != 0) {
            if (n == 0) {
                button.setText("STOP");
                n = 1;
                countDownTimer = new CountDownTimer(timeSet * 1000, 1000) {
                    public void onTick(long totalTime) {
                        min = totalTime / 60000;
                        sec = (int) ((totalTime % 60000) / 1000);
                        if ((sec / 10) == 0) {
                            str = "0";
                        } else {
                            str = "";
                        }
                        textView.setText(String.format(min + " : " + str + sec));
                        seekBar.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        mediaPlayer.start( );
                        seekBar.setEnabled(true);

                    }
                }.start( );

            } else {
                button.setText("GO");
                mediaPlayer.stop( );
                countDownTimer.cancel( );
                textView.setText("0 : 00");
                seekBar.setEnabled(true);
                seekBar.setProgress(0);
                n = 0;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.horn);
        final int x = mediaPlayer.getDuration( );
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.setMax(600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener( ) {
            @Override
            public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {

                timeSet = progress;
                if (fromUser) {
                    min = progress / 60;
                    sec = progress % 60;
                    if ((sec / 10) == 0) {
                        str = "0";
                    } else {
                        str = "";
                    }
                    textView.setText(String.format(min + " : " +str+sec));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
