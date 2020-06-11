package com.example.musicplayerbb;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    DBHelper db;
    public static String veremail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);
        e1 = (EditText)findViewById(R.id.logEmail);
        e2 = (EditText)findViewById(R.id.logPass);
        b1 = (Button)findViewById(R.id.button);
       // b2 = (Button)findViewById(R.id.backlogbut);



     /*   b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean checkmailpass = db.emailpassword(email,password);
                if (checkmailpass == true) {
                    Toast.makeText(getApplicationContext(), "successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, SongActivity.class);
                    i.putExtra("email",email);
                    startActivity(i);
                    veremail = email;
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
            }


        });
  return ;


    }
}