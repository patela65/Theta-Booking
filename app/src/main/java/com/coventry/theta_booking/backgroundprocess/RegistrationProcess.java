package com.coventry.theta_booking.backgroundprocess;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.coventry.theta_booking.Constants;
import com.coventry.theta_booking.jsonhandle.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by patela65 on 6/03/17.
 */

public class RegistrationProcess extends AsyncTask<String, String, String> {

    private JSONParser jparser=new JSONParser();
    private String name,email,pass,role;
    private Activity activity;
    private ProgressDialog progressDialog;
    String RETURN_VALUE ="";

    public RegistrationProcess(Activity activity, String name, String email, String pass, String role ) {
        this.activity    = activity;
        this.name        = name;
        this.email       = email;
        this.pass        = pass;
        this.role      = role;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try{
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Signing Up ...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected String doInBackground(String... arg) {
        // TODO: attempt authentication against a network service.

        try {
            JSONObject myJSON = new JSONObject();
            myJSON.put("name",name);
            myJSON.put("email",email);
            myJSON.put("password",pass);
            myJSON.put("role",role);
           


            //create a json object of email to check from mongolab
            JSONObject EMAILOBJ  = new JSONObject();
            EMAILOBJ.put("email",email);

            //check if email already exists
            JSONArray jsonArray = jparser.makeHttpGETRequest(Constants.QUERY_USERS+EMAILOBJ+Constants.API_KEY);
            try {
                if(jsonArray!=null) {
                    if (jsonArray.getJSONObject(0).get("email").equals(email)) {

                        RETURN_VALUE = "exists";
                        return RETURN_VALUE;
                    }
                }
            }catch(JSONException js){
                js.printStackTrace();
            }

                JSONObject json = jparser.makeHttpPOSTRequest(Constants.USERS_URL, myJSON);
                Log.e("JSON IS = ", json.toString());
                if (json != null) {

                    RETURN_VALUE = "success";
                }


        } catch (Exception e) {

            e.printStackTrace();

        }
        return RETURN_VALUE;
    }


    protected void onPostExecute(final String success) {
        try{
            progressDialog.dismiss();
            if(success.equals("success")){
                Toast.makeText(activity, "Registered Successfully", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            else if(success.equals("exists")){
                Toast.makeText(activity,"User Already Exists", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(activity,"Unable to Register! Check Network", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(activity, "Check Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCancelled() {
            progressDialog.dismiss();
    }
}