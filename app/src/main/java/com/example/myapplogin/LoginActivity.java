package com.example.myapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    CardView btnLogin;
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

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//        Bundle bundle= new Bundle();
//        bundle.putSerializable("object_user", mUser);
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}