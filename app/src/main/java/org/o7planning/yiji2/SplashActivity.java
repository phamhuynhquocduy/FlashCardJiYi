package org.o7planning.yiji2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.o7planning.yiji2.database.Database;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private Button buttonStart;
    SQLiteDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        buttonStart = findViewById(R.id.btn_start);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kiem tra co login chua
                SharedPreferences sharedPref = getSharedPreferences("preference_login_key", Context.MODE_PRIVATE);
                if (sharedPref.getString("preference_login_status",null)==null) {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
        //Them data lan dau
        this.deleteDatabase("yiji2.db");
        this.data = Database.initDatabase(this, "yiji2.db");
    }
}


