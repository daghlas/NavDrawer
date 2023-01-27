package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.pass);
        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(v -> {
            String username, password;
            username = String.valueOf(textInputEditTextUsername.getText());
            password = String.valueOf(textInputEditTextPassword.getText());

            if(!username.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;
                    PutData putData = new PutData("http://192.168.104.87/LoginRegister/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if(result.equals("Login Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Intent play = new Intent(getApplicationContext(), Login.class);
                        //startActivity(play);
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //finish();
                        System.exit(0);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}