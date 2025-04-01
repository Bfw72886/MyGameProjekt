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
 * Log in Screen that lets user go to main menu
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton switchToSignupButton;
    AppCompatButton submitButton;
    EditText usernameInput;
    EditText passwordInput;

    SharedPreferences sharedPreferences;
    boolean hasSignedUp;
    MyGameUser registeredUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        hasSignedUp = sharedPreferences.getBoolean("hasSignedUp", false);

        switchToSignupButton = findViewById(R.id.switchToSignupButton);
        switchToSignupButton.setOnClickListener(this);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        if (hasSignedUp) {
            switchToSignupButton.setVisibility(View.GONE);
        }

        registeredUser = new MyGameUser(
                sharedPreferences.getString("username", ""),
                sharedPreferences.getString("password", "")
        );
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == switchToSignupButton.getId()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        if (v.getId() == submitButton.getId()) {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Beide Felder ausfüllen", Toast.LENGTH_SHORT).show();
            } else if (username.equals(registeredUser.getUsername()) && password.equals(registeredUser.getPassword())) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Benutzername oder Passwort falsch oder noch nicht registriert", Toast.LENGTH_SHORT).show();
            }

        }

    }
}