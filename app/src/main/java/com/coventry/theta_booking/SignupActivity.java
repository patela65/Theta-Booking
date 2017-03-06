package com.coventry.theta_booking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coventry.theta_booking.backgroundprocess.RegistrationProcess;

/*
*  @author patel65
*
*  In this class we are getting the values from users
*  and starting a registration process after validating
*  the data
*
*  @date: 6 March 2017
*/


public class SignupActivity extends Activity {

    private RegistrationProcess create;
    private EditText nameEt,emailEt,passEt;
    private TextView loginTv;
    private RadioGroup genderRadioGrp;
    private RadioButton genderSelectedBtn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setReferences(); //setting references
        setClickListeners(); //set click listener for login link
    }

    private void setReferences() {
        nameEt = (EditText)findViewById(R.id.nameEt);
        emailEt = (EditText)findViewById(R.id.emailEt);
        passEt  = (EditText)findViewById(R.id.passEt);
        loginTv = (TextView)findViewById(R.id.login_linkTv);
        genderRadioGrp = (RadioGroup)findViewById(R.id.genderGrp);

    }

    private void setClickListeners(){
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //finishing sign up activity will open up the previous login activity
            }
        });
    }
    
    public void createAccountClick(View v){
        signup(); //start sign up process on click
    }

    public void signup() {

        String name       = nameEt.getText().toString();  //get name
        String email      = emailEt.getText().toString(); //get email
        String password   = passEt.getText().toString();  // get pass
        int selectedBtnId = genderRadioGrp.getCheckedRadioButtonId();
        genderSelectedBtn = (RadioButton)findViewById(selectedBtnId);

        String gender   = genderSelectedBtn.getText().toString(); //get gender

        if (!validate(name,email,password)) { //check valid data
            onSignupFailed(); //show error
            return;
        }

        try {
            create = new RegistrationProcess(SignupActivity.this, name,
                    email, password, gender);
            create.execute(); //start a registration process in background

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(SignupActivity.this,"Unable to Register", Toast.LENGTH_SHORT).show();//upon exception
        }

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate(String name, String email, String password) {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            nameEt.setError("at least 3 characters");
            valid = false;
        } else {
            nameEt.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("enter a valid email address");
            valid = false;
        } else {
            emailEt.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passEt.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passEt.setError(null);
        }

        return valid;
    }
}