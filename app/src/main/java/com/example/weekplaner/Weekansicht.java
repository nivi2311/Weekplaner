package com.example.weekplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Weekansicht extends AppCompatActivity implements SensorEventListener {

    private TextView xText1 , yText2;
    private Sensor sensor;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekansicht);

        //Sensormanager erstellen
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //register sencor listener
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //assign
        xText1 = (TextView)findViewById(R.id.xWText);
        yText2 = (TextView)findViewById(R.id.yWText);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xText1.setText("X: " + event.values[0]);
        yText2.setText("Y: " + event.values[2]);


        if(xText1.equals("0.0") && !yText2.equals("0.0")){
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.activity_weekansicht);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //wird nicht benutzt
    }

}