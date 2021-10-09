package org.o7planning.yiji2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    String DATABASE_NAME ="yiji2.db";
    TextView txtRegister;
    EditText edtEmail, edtPass;
    Button btnLogin;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtRegister = findViewById(R.id.txt_sign_in);
        edtEmail = findViewById(R.id.edittext_email);
        edtPass = findViewById(R.id.edittext_pass);
        btnLogin = findViewById(R.id.button_login);

        //Su kien chuyen man hinh dang ky
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPerson();
            }
        });
    }
    void checkPerson(){
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT * FROM person WHERE email=? AND pass = ? ",new String[]{edtEmail.getText().toString().trim(),edtPass.getText().toString().trim()});
            if (cursor.getCount() >0 ) {
                cursor.moveToFirst();
                SharedPreferences.Editor editor = getSharedPreferences("preference_login_key", MODE_PRIVATE).edit();
                editor.putString("preference_login_status", Integer.toString(cursor.getInt(0)));
                editor.apply();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            } else {
                Toast.makeText(this,"Email và mật khẩu sai",Toast.LENGTH_LONG).show();
            }
    }
}