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
        //Dependency injection, so we can use the methods from this class in the AuthRepository.
    }
    
    /**
    When Main activity starts, the AuthRepository will be instanciated and check if the user is logged in. 
    If user login is successful, onLoginSuccess will be called and go to the PictureUploadActivity.
    */

    public void onLoginSuccess(FirebaseUser user) {
        Intent intent = new Intent(this, PictureUploadActivity.class);
        intent.putExtra("firebaseUser", user);
        startActivity(intent);
    }

    /**
    Just for printing out the message, can be used to show an error message to the user in the future.
    */
    public void onLoginFail() {
        System.out.println("Logged out");
    }

    /**
    When the Login button is pressed, this method will be called. The signUserIn method from authRepository
    will take this activity as the third parameter so it can call the OnLoginSuccess method if user authentication is succesful.
    */
    public void signIn(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.length() > 0 && password.length() > 0) {
            authRepository.signUserIn(email, password, this);
        }
    }

}
