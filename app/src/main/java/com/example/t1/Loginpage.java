package com.example.t1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Loginpage extends AppCompatActivity {

    private EditText lgem,lgpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        lgem=findViewById(R.id.lgemail);
        lgpassword=findViewById(R.id.lgpass);
    }

    public void regisclick(View view) {

        startActivity(new Intent(this,register_page.class));

    }

    public void lgclick(View view) {

        String email=lgem.getText().toString().trim();
        String pass=lgpassword.getText().toString();
        if(email.isEmpty()||pass.isEmpty())
        {
          /*
            Toasty.Config.getInstance()
                    .tintIcon(boolean tintIcon), // optional (apply textColor also to the icon)
                    .setToastTypeface(@NonNull Typeface typeface), // optional
                    .setTextSize(int sizeInSp), // optional
                    .allowQueue(boolean allowQueue), // optional (prevents several Toastys from queuing)
                    .apply(); // required
            */

            //DynamicToast.make(this, "All Credentials are Mandatory","#A4303F", "#F0F3F5").show();
            Toasty.error(this, "All Credentials are Mandatory", Toast.LENGTH_SHORT, true).show();
        }
        else
        {

            ApiInterface apiInterface=RetrofitClient.getClient().create(ApiInterface.class);
            sendlgformat lgcred=new sendlgformat(email,pass);
            Call<getlgformat> call=apiInterface.getlogindata(lgcred);
            call.enqueue(new Callback<getlgformat>() {
                @Override
                public void onResponse(Call<getlgformat> call, Response<getlgformat> response) {
                    if(response.isSuccessful())
                    {
                        String token=response.body().getToken();

                        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("token",token);
                        editor.apply();
                      //  Toast.makeText(Loginpage.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        Toasty.success(Loginpage.this, "Successfully Logged In", Toast.LENGTH_SHORT, true).show();
                        startActivity(new Intent(Loginpage.this,Homepage.class));
                        finish();
                    }
                    else
                    {
                        if(response.code()==400)
                        {
                            Toast.makeText(Loginpage.this,"Wrong Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<getlgformat> call, Throwable t) {
                    Toast.makeText(Loginpage.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        //verification of data with the server








    }
}
