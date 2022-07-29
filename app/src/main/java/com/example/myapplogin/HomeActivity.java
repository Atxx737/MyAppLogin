package com.example.myapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplogin.API.APIService;
import com.example.myapplogin.Models.ResponeAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private final static String TAG = "MyAppLogin";

    TextView tvUsername;
    CardView btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUsername = findViewById(R.id.a_home_tv_usename);

        btnLogout = findViewById(R.id.a_home_btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogout();

            }
        });
    }

    private void clickLogout() {
        APIService.apiService.Logout("test")
                .enqueue(new Callback<ResponeAPI>() {
                    @Override
                    public void onResponse(Call<ResponeAPI> call, Response<ResponeAPI> response) {
                        ResponeAPI responeAPI = response.body();
                        if (responeAPI != null && response.isSuccessful())
                        {
                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Log.e(TAG, responeAPI.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeAPI> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Logout failure!", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}