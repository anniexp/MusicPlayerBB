package com.example.musicplayerbb;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    Button playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;
    ArrayList mySongs;
     private Button mainBtn;
    Button regBtn,loginBtn;
   public static boolean auth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        //button to go from playlist activity to main activity
        mainBtn = (Button) findViewById(R.id.mainbtn);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        if(email != null) {
            mainBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    openMainActivity();
                }

            });
        }
        else{
            mainBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Please login!", Toast.LENGTH_SHORT).show();
                }

            });


        }
        regBtn = (Button) findViewById(R.id.mainregbut);
        loginBtn = (Button) findViewById(R.id.mainlogbut);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }



    //button to open the main activity uses this method
    public  void openMainActivity() {


        Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);
            }
}
