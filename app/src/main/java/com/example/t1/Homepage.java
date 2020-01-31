package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.t1.UnusedPackages.barcodescanner;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Homepage extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void addbkclick(View view) {

        zXingScannerView=new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(Homepage.this);
        zXingScannerView.startCamera();
        zXingScannerView.setAutoFocus(true);
        // startActivity(new Intent(Homepage.this, barcodescanner.class));

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        String value=result.getText();
       // zXingScannerView.resumeCameraPreview(Homepage.this);


    }
}
