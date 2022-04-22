package com.androidavanzado.minitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidavanzado.minitwitter.R;
import com.androidavanzado.minitwitter.retrofit.MiniTwitterClient;
import com.androidavanzado.minitwitter.retrofit.MiniTwitterService;
import com.androidavanzado.minitwitter.retrofit.request.RequestSignUp;
import com.androidavanzado.minitwitter.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewBackLogin;
    Button buttonSignUp;
    EditText editTextUser, editTextEmail, editTextPassword;

    MiniTwitterClient miniTwitterClient;
    MiniTwitterService miniTwitterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        retrofitInit();
        findViews();
        events();


    }

    private void retrofitInit() {
        miniTwitterClient = MiniTwitterClient.getInstance();
        miniTwitterService = miniTwitterClient.getMiniTwitterService();
    }

    private void findViews() {
        textViewBackLogin = findViewById(R.id.textViewBackLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextUser = findViewById(R.id.editTextUsuario);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    private void events() {
        textViewBackLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.buttonSignUp:
                doSingUp();
                break;
            case R.id.textViewBackLogin:
                goToLogin();
                break;
        }
    }

    private void doSingUp() {
        String email = editTextEmail.getText().toString();
        String username = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        if(username.isEmpty()) {
            editTextUser.setError("El campo es obligatorio");
        } else if(email.isEmpty()){
            editTextEmail.setError("El campo es obligatorio");
        } else if(password.isEmpty() || password.length() < 4) {
            editTextPassword.setError("La contraseña es requerida y debe tener al menos 4 caracteres");
        } else {
            RequestSignUp requestSignUp = new RequestSignUp(username, email, password, "UDEMYANDROID");
            miniTwitterService.doSigUp(requestSignUp).enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if(response.isSuccessful()) {
                        Intent i = new Intent(SignUpActivity.this, DashboardActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Algo no salio bien, revisa la informacion ingresada", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void goToLogin() {
        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}