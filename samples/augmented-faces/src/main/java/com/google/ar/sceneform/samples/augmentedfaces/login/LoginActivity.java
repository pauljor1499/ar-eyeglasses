package com.google.ar.sceneform.samples.augmentedfaces.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.ar.sceneform.samples.augmentedfaces.R;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextInputLayout username, password;

    private String getUsername, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.submit);

        login.setOnClickListener(view -> {
            getUsername = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
            getPassword = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

            if(Objects.equals(getUsername, "admin2022") && getPassword.equals("admin2022")){
                Intent intent = new Intent(LoginActivity.this, ProductCatalog.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_SHORT).show();
            }
        });

    }
}