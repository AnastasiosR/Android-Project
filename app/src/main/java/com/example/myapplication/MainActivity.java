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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.*;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="" ;
    //Initialize firebaseAuth
     public FirebaseAuth mAuth;
    private View rootView;


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

        rootView = findViewById(android.R.id.content).getRootView();

        email = "tasoscat@gmail.com";
        password = "123456";
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

     //i choose the email cause it's easier
    private void createUserWithEmail(){
        final TextView status = findViewById(R.id.status);
        final String status1 = status.getText().toString();
        final EditText emailtxtBox = findViewById(R.id.emailTextBox);
        final String email = emailtxtBox.getText().toString();
        EditText passwordtxtBox = findViewById(R.id.passwordTextBox);
        final String password = passwordtxtBox.getText().toString();
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
                            if(email.isEmpty() || password.isEmpty())
                            {
                                status.setText("Email or password cant be null");
                            }
                            else
                            status.setText("Something went wrong");
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }
 
 private void signIn(){
     EditText emailtxtBox = findViewById(R.id.emailTextBox);
     emailtxtBox.setText("tasoscat@gmail.com");
     String email = emailtxtBox.getText().toString();
     EditText passwordtxtBox = findViewById(R.id.passwordTextBox);
     passwordtxtBox.setText("123456");
     final TextView status = findViewById(R.id.status);
     String status2 = status.getText().toString();
     String password = passwordtxtBox.getText().toString();
     String message;


     if(emailtxtBox.getText().toString().isEmpty()){
        message = "Email is mandatory";
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
        emailtxtBox.requestFocus();
        return;
     }

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
        status.setText("Loging in..");
    }

    public void registerButtonClicked(View view) {
        TextView status = findViewById(R.id.status);
        status.setText("Registered!");
        createUserWithEmail();

    }

}//end of Activity
