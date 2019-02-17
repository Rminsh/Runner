package ir.lydia.runner;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

  SensorManager sensorManager;
  Boolean running = false;
  CircularProgressIndicator circularProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    TextView currentDate = findViewById(R.id.current_date);
    circularProgress = findViewById(R.id.circular_progress);
    circularProgress.setMaxProgress(6400);

    String date = getCurrentDay() + ", " +  new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(new Date());
    currentDate.setText(date);
  }

  @Override
  protected void onResume() {
    super.onResume();
    running = true;
    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    if(countSensor != null) {
      sensorManager.registerListener(this , countSensor , SensorManager.SENSOR_DELAY_UI);
    } else {
      Toast.makeText(this , "Sensor not found!" , Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    running = false;
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if(running) {
      circularProgress.setCurrentProgress(event.values[0]);
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

  public static String getCurrentDay(){
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
    Calendar calendar = Calendar.getInstance();
    return dayFormat.format(calendar.getTime());

  }
}
