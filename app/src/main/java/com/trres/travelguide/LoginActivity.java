package com.trres.travelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditor;
    private EditText passwordEditor;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditor = findViewById(R.id.email);
        passwordEditor = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
}

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void updateUI(FirebaseUser currentUser) {
    }


    public void LoginOnClick(View view) {
        String email = this.emailEditor.getText().toString();
        String password = this.passwordEditor.getText().toString();
        boolean verification = true;
        if (email.isEmpty()) {
            verification = false;
            emailEditor.setError("Please insert the email");
        }
        if (password.isEmpty()) {
            verification = false;
            passwordEditor.setError("Please insert the password");
        }
        if(verification) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void RegisterOnClick(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
