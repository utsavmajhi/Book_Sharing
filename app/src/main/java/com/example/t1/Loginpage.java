package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
    }

    public void regisclick(View view) {


        startActivity(new Intent(this,register_page.class));

    }

    public void lgclick(View view) {
        startActivity(new Intent(this,Homepage.class));
    }
}
