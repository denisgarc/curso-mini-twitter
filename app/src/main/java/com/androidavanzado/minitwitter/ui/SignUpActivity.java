package com.androidavanzado.minitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidavanzado.minitwitter.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewBackLogin;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        textViewBackLogin = findViewById(R.id.textViewBackLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        textViewBackLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.buttonSignUp:
                break;
            case R.id.textViewBackLogin:
                goToLogin();
                break;
        }
    }

    private void goToLogin() {
        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}