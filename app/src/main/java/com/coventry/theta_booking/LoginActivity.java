package com.coventry.theta_booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coventry.theta_booking.backgroundprocess.UserLoginTask;


public class LoginActivity extends Activity {
    private EditText email,pass;
    private TextView signupLinkTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setReferences(); //setting all button references
        setClickListener();


    }

    public void setReferences(){
        email = (EditText)findViewById(R.id.emailEt);
        pass = (EditText)findViewById(R.id.passwordEt);
        signupLinkTv = (TextView)findViewById(R.id.link_signup);
    }

    public void loginClick(View v){
        String emailStr = email.getText().toString();
        String passStr  = pass.getText().toString();

        try {
            UserLoginTask mAuthTask = new UserLoginTask(LoginActivity.this, emailStr, passStr);
            mAuthTask.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setClickListener(){
        signupLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }


}
