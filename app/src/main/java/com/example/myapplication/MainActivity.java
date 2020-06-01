package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="" ;
    //Initialize firebaseAuth
     public FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.status);
        String status1 = status.getText().toString();

        EditText emailtxtBox = findViewById(R.id.emailTextBox);
        EditText passwordtxtBox = findViewById(R.id.passwordTextBox);

        String email = emailtxtBox.getText().toString();
        String password = passwordtxtBox.getText().toString();
    mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    public void writeUserData(FirebaseUser user){

    }

    private void updateUI(FirebaseUser currentUser) {

    }


    private void createUserWithEmail(){
        final TextView status = findViewById(R.id.status);
        final String status1 = status.getText().toString();
        EditText emailtxtBox = findViewById(R.id.emailTextBox);
        String email = emailtxtBox.getText().toString();
        EditText passwordtxtBox = findViewById(R.id.passwordTextBox);
        String password = passwordtxtBox.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG ="" ;

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            status.setText("Created User");

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            status.setText("Something went wrong");
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }
 
 private void signIn(){
     EditText emailtxtBox = findViewById(R.id.emailTextBox);
     String email = emailtxtBox.getText().toString();
     EditText passwordtxtBox = findViewById(R.id.passwordTextBox);
     final TextView status = findViewById(R.id.status);
     String status2 = status.getText().toString();
     String password = passwordtxtBox.getText().toString();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    status.setText("Login Success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(new Intent(MainActivity.this,Homepage.class));

                    updateUI(user);
            }else{
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    status.setText("Failed to login");
                    updateUI(null);
                    // ...
                }
                }
        });
 } //end of signIn method

    private void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

    }


    public void loginButtonClicked(View view) {
        signIn();
        TextView status = findViewById(R.id.status);
        String status1 = status.getText().toString();
        status.setText("Button Clicked!");
    }

    public void registerButtonClicked(View view) {
        createUserWithEmail();

    }
}//end of Activity
