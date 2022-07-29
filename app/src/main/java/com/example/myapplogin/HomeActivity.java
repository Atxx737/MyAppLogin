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
import com.example.myapplogin.Models.ResponeUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private final static String TAG = "MyAppLogin/";

    TextView tvUsername;
    CardView btnLogout;
    private ResponeUser usr;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUsername = findViewById(R.id.a_home_tv_usename);

        Bundle bundleRcvUser= getIntent().getExtras();
        if( bundleRcvUser != null)
        {
            usr = (ResponeUser) bundleRcvUser.get("object_user");
            if(usr != null)
            {
                mUsername = usr.getUsername().trim();
            }
        }
        tvUsername.setText(mUsername);

        btnLogout = findViewById(R.id.a_home_btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogout();

            }
        });
    }

    private void clickLogout() {
        APIService.apiService.Logout(mUsername)
                .enqueue(new Callback<ResponeAPI>() {
                    @Override
                    public void onResponse(Call<ResponeAPI> call, Response<ResponeAPI> response) {
                        ResponeAPI responeAPI = response.body();
                        if (responeAPI != null && response.isSuccessful())
                        {
                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Log.e(TAG, "Logout:" + responeAPI.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeAPI> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Logout failure!", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}