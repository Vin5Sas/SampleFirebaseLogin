package com.admin.samplefblogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    Button logoutButton;
    TextView userIDLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        logoutButton = (Button) findViewById(R.id.LogoutButton);
        userIDLabel = (TextView) findViewById(R.id.UserIDLabel);

        userIDLabel.setText(user.getEmail());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(getApplicationContext(),"Logged Out Successfully!",Toast.LENGTH_SHORT);
                startActivity(mainIntent);
            }
        });
    }
}
