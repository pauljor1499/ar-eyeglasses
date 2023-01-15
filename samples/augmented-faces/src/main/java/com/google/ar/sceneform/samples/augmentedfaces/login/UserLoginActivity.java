package com.google.ar.sceneform.samples.augmentedfaces.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.sceneform.samples.augmentedfaces.R;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog2.ProductCatalog2;


public class UserLoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin, btnregistration;
    DBHelper DB;
    ImageView login_logo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        login_logo = (ImageView) findViewById(R.id.my_login_logo);
        btnregistration = (Button) findViewById(R.id.bttn_to_register);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(UserLoginActivity.this, "Please Enter all the Fields!", Toast.LENGTH_SHORT).show();
                }else if(user.equals("admin2023") || pass.equals("admin2023")){
                    username.setText("");
                    password.setText("");
                    Intent intent = new Intent(getApplicationContext(), ProductCatalog2.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Admin successfully logged in",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        username.setText("");
                        password.setText("");
                        Intent intent = new Intent(getApplicationContext(), ProductCatalog.class);
                        startActivity(intent);
                        Toast.makeText(UserLoginActivity.this, "Sign in Successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UserLoginActivity.this, "Invalid Credentials ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}