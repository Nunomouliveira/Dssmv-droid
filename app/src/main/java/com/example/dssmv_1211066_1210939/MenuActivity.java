package com.example.dssmv_1211066_1210939;

import android.os.Vibrator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements SensorEventListener {

    private Button buttonLibrary;
    private Button settingsButton;

    private SensorManager sensorManager;
    private long lastUpdate;
    private float lastX, lastY, lastZ;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuactivity);

        buttonLibrary = findViewById(R.id.buttonLibrary);
        buttonLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLibraryActivity();
            }
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastUpdate) > 100) {
                long timeDifference = currentTime - lastUpdate;
                lastUpdate = currentTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                if (lastX == 0 && lastY == 0 && lastZ == 0) {
                    // erro no inicio
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    return;
                }

                float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / timeDifference * 10000;


                if (speed > SHAKE_THRESHOLD) {
                    // The device has been shaken, open BooksActivity
                    openLibraryActivity();
                }

                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "Accelerometer not available on this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void openLibraryActivity() {
        Intent intent = new Intent(this, LibraryActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openBooksActivity() {
        Intent intent = new Intent(this, BooksActivity.class);
        startActivity(intent);
    }
}