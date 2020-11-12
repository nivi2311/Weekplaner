package com.example.weekplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    private TextView xText, yText;
    private Sensor sensor;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Sensormanager erstellen
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //register sencor listener
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //assign
        xText = (TextView)findViewById(R.id.xtext);
        yText = (TextView)findViewById(R.id.yText);

        Button button = (Button) findViewById(R.id.creatA);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_termin_formular);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(!xText.getText().equals("0.0") && !yText.equals("0.0")){
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.activity_weekansicht);
        }

        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}