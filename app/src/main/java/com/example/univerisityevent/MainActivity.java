package com.example.univerisityevent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextUsername, editTextPassword;
    private TextView buttonSign_in_with, textViewForget_password, textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        EditText editTextUsername = findViewById(R.id.username);
        EditText editTextPassword = findViewById(R.id.password);
        Button buttonLogin = findViewById(R.id.login);
        TextView registerTextView = findViewById(R.id.register); // Changed to TextView since it's used for navigation
        TextView forgetPasswordTextView = findViewById(R.id.forget_password);

        buttonLogin.setOnClickListener(view -> {
            String email = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Login successful, show success popup
                                showPopup("Login Successful", "You have successfully logged in.");
                            } else {
                                // Login failed, show error popup
                                showPopup("Login Error", "Authentication failed. Please try again.");
                            }
                        });
            }
        });

        registerTextView.setOnClickListener(view -> {
            // Navigate to RegisterUser activity
            startActivity(new Intent(MainActivity.this, RegisterUser.class));
        });


        forgetPasswordTextView.setOnClickListener(view -> {
            // Handle forgot password button click here (show message for reset link)
            showPopup("Forgot Password", "Reset link has been sent to your email.");
        });
    }

    private void showPopup(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with the app flow or handle other actions
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
