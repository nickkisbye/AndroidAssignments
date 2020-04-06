package com.example.picturefirestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.picturefirestorage.Repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        authRepository = new AuthRepository(this);
    }

    public void onLoginSuccess(FirebaseUser user) {
        Intent intent = new Intent(this, PictureUploadActivity.class);
        intent.putExtra("firebaseUser", user);
        startActivity(intent);
    }

    public void onLoginFail() {
        System.out.println("Logged out");
    }

    public void signIn(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.length() > 0 && password.length() > 0) {
            authRepository.signUserIn(email, password, this);
        }
    }

}
