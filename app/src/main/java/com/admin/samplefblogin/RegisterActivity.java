package com.admin.samplefblogin;

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

public class RegisterActivity extends AppCompatActivity {

    EditText emailIDEditText;
    EditText passwordEditText;
    Button registerButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        emailIDEditText = (EditText) findViewById(R.id.EmailEditText);
        passwordEditText = (EditText) findViewById(R.id.PasswordEditText);
        registerButton = (Button) findViewById(R.id.RegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser()
    {
        String emailID = emailIDEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(emailID)){
            Toast.makeText(getApplicationContext(),"Please enter E-mail ID!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter Password!",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(emailID,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered Successfully!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Couldn't Register User! Please try again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
