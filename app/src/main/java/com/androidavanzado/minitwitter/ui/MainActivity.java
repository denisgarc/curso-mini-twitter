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
import com.androidavanzado.minitwitter.retrofit.request.RequestLogin;
import com.androidavanzado.minitwitter.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView textviewGoSignUp;
    EditText editTextEmail, editTextPassword;

    MiniTwitterClient miniTwitterClient;
    MiniTwitterService miniTwitterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnLogin = findViewById(R.id.buttonLogin);
        textviewGoSignUp = findViewById(R.id.textViewGoSignUp);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    private void events() {
        btnLogin.setOnClickListener(this);
        textviewGoSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.buttonLogin:
                doLogin();
                break;
            case R.id.textViewGoSignUp:
                goToSignUp();
                break;
        }
    }

    private void doLogin() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(email.isEmpty()){
            editTextEmail.setError("El email es requerido");
        } else if(password.isEmpty()) {
            editTextPassword.setError("La contraseña es requerida");
        } else {
            RequestLogin requestLogin = new RequestLogin(email, password);
            Call<ResponseAuth> call = miniTwitterService.doLogin(requestLogin);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sesion iniciada correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(i);

                        // Destruir activity
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this,"Algo fue mal, revise sus datos", Toast.LENGTH_LONG).show();;
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "hola", Toast.LENGTH_LONG).show();;
                }
            });
        }
    }

    private void goToSignUp() {
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }
}