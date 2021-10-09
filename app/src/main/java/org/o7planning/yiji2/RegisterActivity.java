package org.o7planning.yiji2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.o7planning.yiji2.database.Database;

public class RegisterActivity extends AppCompatActivity {
    String DATABASE_NAME = "yiji2.db";
    TextView txtLogin;
    EditText editTextPass, editTextEmail, editTextName, editTextRePass;
    SQLiteDatabase database;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin = findViewById(R.id.txt_sign_in);
        editTextEmail = findViewById(R.id.edittext_email);
        editTextName = findViewById(R.id.edittext_name);
        editTextPass = findViewById(R.id.edittext_pass);
        editTextRePass = findViewById(R.id.edittext_repass);
        btnRegister = findViewById(R.id.button_signin);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUser();
            }
        });
    }
    //Them nguoi dung
    void insertUser(){
        ContentValues row = new ContentValues();
        row.put("name",editTextName.getText().toString());
        row.put("email",editTextEmail.getText().toString());
        row.put("pass",editTextPass.getText().toString());
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        database.insert("person",null,row);
        Toast.makeText(this,"Đăng ký tài khoản thành công",Toast.LENGTH_LONG).show();
        //Them lai co so du lieu
        //this.deleteDatabase("yiji2.db");
        //database = Database.initDatabase(this, "yiji2.db");
        //Chuyen sang man hinh dang nhap
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}