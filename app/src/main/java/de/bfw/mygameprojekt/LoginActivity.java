package de.bfw.mygameprojekt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton switchToSignupButton;

    SharedPreferences sharedPreferences;
    boolean hasSignedUp;

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

        if (hasSignedUp) {
            switchToSignupButton.setVisibility(View.GONE);
        }
    }
}