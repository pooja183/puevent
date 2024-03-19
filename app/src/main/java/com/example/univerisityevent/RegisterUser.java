package com.example.univerisityevent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirm_password);
        buttonRegister = findViewById(R.id.register);

        buttonRegister.setOnClickListener(view -> {
            String email = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            if (password.equals(confirmPassword)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Registration success
                                Toast.makeText(RegisterUser.this, "Registration successful.",
                                        Toast.LENGTH_SHORT).show();
                                // You can add further actions here, such as navigating to the login activity
                                finish(); // Finish the registration activity
                            } else {
                                // Registration failed
                                Toast.makeText(RegisterUser.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // Show error message if passwords do not match
                Toast.makeText(RegisterUser.this, "Passwords do not match.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
