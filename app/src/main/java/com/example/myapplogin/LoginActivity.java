package com.example.myapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplogin.API.APIService;
import com.example.myapplogin.Models.ResponeAPI;
import com.example.myapplogin.Models.ResponeUser;
import com.example.myapplogin.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "MyAppLogin/";
    CardView btnLogin;

    private EditText etUsername, etPassword;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername= findViewById(R.id.a_login_et_user);
        etPassword = findViewById(R.id.a_login_et_password);

        btnLogin = findViewById(R.id.a_login_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String username = etUsername.getText().toString().trim();
//        Log.e(TAG, "Login: username" + username);

        String password = etPassword.getText().toString().trim();
        mUser = new User(username, password);
//        Log.e(TAG, "Login:" + mUser.getUsername()+ "|" + mUser.getPassword());

        APIService.apiService.Login(mUser).enqueue(new Callback<ResponeUser>() {
            @Override
            public void onResponse(Call<ResponeUser> call, Response<ResponeUser> response) {
                ResponeUser responeAPI = response.body();

                if (responeAPI != null && response.isSuccessful())
                {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("object_user", responeAPI);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Log.e(TAG, "Login:" + responeAPI.getUsername());
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failure!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponeUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "API:Login failure!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}