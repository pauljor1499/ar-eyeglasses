package com.google.ar.sceneform.samples.augmentedfaces.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.ar.sceneform.samples.augmentedfaces.R;

public class UserRegisterActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(UserRegisterActivity.this,"Please Enter all the fields!",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(UserRegisterActivity.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);*/
                                username.setText("");
                                password.setText("");
                                repassword.setText("");

                            }else{
                                Toast.makeText(UserRegisterActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UserRegisterActivity.this,"User Already Exist! Please Log in...", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserRegisterActivity.this,"Password not Match!   ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                repassword.setText("");

                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);

            }
        });

    }
}