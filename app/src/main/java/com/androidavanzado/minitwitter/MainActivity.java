package com.androidavanzado.minitwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView textviewGoSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btnLogin = findViewById(R.id.buttonLogin);
        textviewGoSignUp = findViewById(R.id.textViewGoSignUp);

        btnLogin.setOnClickListener(this);
        textviewGoSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.buttonLogin:
                break;
            case R.id.textViewGoSignUp:
                goToSignUp();
                break;
        }
    }

    private void goToSignUp() {
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }
}