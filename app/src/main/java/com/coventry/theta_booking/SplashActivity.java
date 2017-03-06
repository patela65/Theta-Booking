package com.coventry.theta_booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class SplashActivity extends Activity {

    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread logoTimer = new Thread(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {

                    sleep(2500);
                    Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    finish(); // Call once you redirect to another activity

                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally{
                    finish();
                }
            }
        };
        logoTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

}

