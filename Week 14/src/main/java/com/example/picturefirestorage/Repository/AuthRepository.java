package com.example.picturefirestorage.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.picturefirestorage.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository {

    FirebaseAuth firebaseAuth;
    private MainActivity mainActivity;

    public AuthRepository(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        firebaseAuth = FirebaseAuth.getInstance();
        checkCredentials();
    }

    public AuthRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void checkCredentials() {
        firebaseAuth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    mainActivity.onLoginFail();
                } else {
                    Log.d("success", firebaseAuth.getCurrentUser().getEmail());
                    mainActivity.onLoginSuccess(firebaseAuth.getCurrentUser());
                }
            }
        });
    }

    public void signUserIn(String email, String password, final MainActivity mainActivity) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mainActivity.onLoginSuccess(task.getResult().getUser());
                        } else {
                            System.out.println("Login failed!");
                        }
                    }
                });
    }

    public void signUserOut() {
        firebaseAuth.signOut();
    }
}
