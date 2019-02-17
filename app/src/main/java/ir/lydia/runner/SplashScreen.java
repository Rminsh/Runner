package ir.lydia.runner;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent i = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(i);
    finish();
  }

}