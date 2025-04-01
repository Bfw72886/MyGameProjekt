package de.bfw.mygameprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Sign up Screen that only shows, when user hasn't signed up yet
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameInput;
    EditText passwordInput;
    EditText confirmPasswordInput;
    AppCompatButton submitButton;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        editor = getSharedPreferences("userPrefs", Context.MODE_PRIVATE).edit();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == submitButton.getId()) {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show();
            } else {
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putBoolean("hasSignedUp", true);
                editor.apply();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }

    }
}