package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.example.t1.UnusedPackages.barcodescanner;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Homepage extends AppCompatActivity /*implements ZXingScannerView.ResultHandler*/ {
    private Barcode barcodeResult;
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    //Barcode Scanner
    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(Homepage.this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withText("Scanning...")
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {

                    @Override
                    public void onResult(Barcode barcode) {
                        barcodeResult = barcode;
                       // result.setText(barcode.rawValue);
                        Toast.makeText(Homepage.this, barcode.rawValue, Toast.LENGTH_SHORT).show();
                    }

                })
                .build();
        materialBarcodeScanner.startScan();
    }

    //barcode scanner ends

    public void addbkclick(View view) {

        startActivity(new Intent(Homepage.this,add_bookspage.class));
       // startScan();


    }


}
