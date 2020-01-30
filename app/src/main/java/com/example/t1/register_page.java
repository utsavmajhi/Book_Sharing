package com.example.t1;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t1.RetrofitApis.ApiInterface;
import com.example.t1.RetrofitApis.RetrofitClient;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register_page extends AppCompatActivity {
    public CircularProgressButton btn;
    private EditText regisname,regispass,regisemail,regisphn,regisroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        btn= (CircularProgressButton) findViewById(R.id.btn_id);
        regisname=findViewById(R.id.rname);
        regisemail=findViewById(R.id.remail);
        regispass=findViewById(R.id.rpass);
        regisroom=findViewById(R.id.rroom);
        regisphn=findViewById(R.id.rphone);
       // btn.startAnimation();




        //btn.revertAnimation();
    }

    public void submitclick(View view) {
        btn.startAnimation();
        String rsname=regisname.getText().toString();
        String rspass=regispass.getText().toString();
        String rsroom=regisroom.getText().toString();
        String rsphn=regisphn.getText().toString();
        String  rsemail=regisemail.getText().toString();

        if(rsname.isEmpty()||rspass.isEmpty()||rsroom.isEmpty()||rsphn.isEmpty()||rsemail.isEmpty())
        {
            btn.revertAnimation();
            //toast
            @ColorInt int tintcolor = Color.parseColor("#A4303F");
            @ColorInt int backgroundcolor = Color.parseColor("#F0F3F5");
            DynamicToast.make(this, "All Credentials are Mandatory",tintcolor, backgroundcolor).show();
        }
        else
        {
            //backends starts

            ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
            Sendregisformat rgcred=new Sendregisformat(rsemail,rsname,rsroom,rsphn,rspass);
            Call c=apiInterface.getregdata(rgcred);
            c.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(register_page.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(register_page.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(register_page.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }


        //btn.revertAnimation();


    }
}
