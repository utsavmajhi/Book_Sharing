package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class register_page extends AppCompatActivity {
    public CircularProgressButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        btn= (CircularProgressButton) findViewById(R.id.btn_id);
       // btn.startAnimation();




        //btn.revertAnimation();
    }

    public void submitclick(View view) {
        btn.startAnimation();
        //btn.revertAnimation();


    }
}
