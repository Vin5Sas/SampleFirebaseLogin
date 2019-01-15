package com.admin.samplefblogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    Button loginButton;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton = (Button) findViewById(R.id.LoginButton);
        emailEditText = (EditText) findViewById(R.id.EmailEditText);
        passwordEditText = (EditText) findViewById(R.id.PasswordEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    public void loginUser(){
        String emailid = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(emailid)){
            Toast.makeText(getApplicationContext(),"Please enter E-mail ID!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter Password!",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(emailid,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent mainMenuIntent = new Intent(getApplicationContext(),ProfileActivity.class);
                    finish();
                    Toast.makeText(getApplicationContext(),"Successfully Logged in!",Toast.LENGTH_SHORT).show();
                    startActivity(mainMenuIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Problem Logging in. Please try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
