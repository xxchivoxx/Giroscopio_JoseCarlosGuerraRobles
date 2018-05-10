package com.example.carlos.giroscopio_josecarlosguerrarobles;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtEjeX, txtEjeY, txtEjeZ;
    private Sensor giroscopio;
    private SensorManager sensorManager;
    private SensorEventListener gyroscopeEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEjeX = findViewById(R.id.txtEjeX);
        txtEjeY = findViewById(R.id.txtEjeY);
        txtEjeZ = findViewById(R.id.txtEjeZ);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //mysensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(giroscopio==null){
            Toast.makeText(this, "This device has no gyroscope", Toast.LENGTH_SHORT).show();

        }

        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[2] > 0.5f){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(event.values[2] < -0.5f) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }
                txtEjeX.setText("Eje X: "+ event.values[0]);
                txtEjeY.setText("Eje Y: "+ event.values[1]);
                txtEjeZ.setText("Eje Z: "+ event.values[2]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener,giroscopio,SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

}
