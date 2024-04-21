package com.example.drivetomsu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the 'Drive to MSU' button
        Button driveButton = findViewById(R.id.drive_button);

        // Set OnClickListener to the button
        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MapsActivity
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
    }
}
