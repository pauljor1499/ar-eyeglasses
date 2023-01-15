package com.google.ar.sceneform.samples.augmentedfaces.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.ar.sceneform.samples.augmentedfaces.R;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;

public class UserLoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(UserLoginActivity.this, "Please Enter all the Fields!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(UserLoginActivity.this, "Sign in Successfully!", Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                        Intent intent = new Intent(getApplicationContext(), ProductCatalog.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(UserLoginActivity.this, "Invalid Credentials ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}