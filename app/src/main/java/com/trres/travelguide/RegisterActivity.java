package com.trres.travelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void RegisterOnClick(View view) {
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String email = this.email.getText().toString();
        Log.e("email ",email);
        String password = this.password.getText().toString();
        switch (validate(email,lastName,password,firstName)){
            case 0:
                firebaseRegister(firstName,lastName,email,password);
                break;
            case 5:
                this.firstName.setError(getString(R.string.first_name_error));
                break;
            case 1:
                this.email.setError(getString(R.string.not_email_error));
                break;
            case 2:
                this.email.setError(getString(R.string.email_error));
                break;
            case 3:
                this.password.setError(getString(R.string.password_error));
                break;
            case 4:
                this.lastName.setError(getString(R.string.last_name_error));
                break;

        }


    }

    private void firebaseRegister(final String firstName, final String lastName, String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                            .setDisplayName(firstName + " " + lastName)
                            .build();
                    user.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        }
                    });
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                } else {
                    Log.e("error", task.getException().getMessage());
                    Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int validate(String email,String lastName,String password,String firstName ){
        if(email.isEmpty()){
            return 2;
        }
        if(password.isEmpty()){
            return 3;
        }
        if(lastName.isEmpty()){
            return 4;
        }
        if(firstName.isEmpty()){
            return 5;
        }
        return 0;
    }
}