package com.coventry.theta_booking.backgroundprocess;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.coventry.theta_booking.Constants;
import com.coventry.theta_booking.HomeActivity;
import com.coventry.theta_booking.User;
import com.coventry.theta_booking.jsonhandle.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by patela65 on 6/03/17.
 */
public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
    private JSONParser jparser = new JSONParser();
    private Activity activity;
    private String email;
    private String pass;
    private ProgressDialog pDialog;
    public static ArrayList<User> profile = new ArrayList<>();
    public static ArrayList<User> locatedUser = new ArrayList<>();



    public UserLoginTask(Activity activity, String email, String pass) {
        this.activity = activity;
        this.email = email;
        this.pass  = pass;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Logging In...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    @Override
    protected Boolean doInBackground(Void... args) {
        // TODO: attempt authentication against a network service.

        profile.clear();
        locatedUser.clear();
        try {
            JSONObject EMAILOBJ = new JSONObject();
            // getting JSON string from URL
            EMAILOBJ.put("email", email);
            EMAILOBJ.put("password", pass);

           // Log.e("QUERY USERS = ",Constants.QUERY_USERS+EMAILOBJ+Constants.API_KEY);
            JSONArray jsonArray = jparser.makeHttpGETRequest(Constants.QUERY_USERS+EMAILOBJ+ Constants.API_KEY);

            if(jsonArray.getJSONObject(0).get("email").equals(email)) {

                String role = jsonArray.getJSONObject(0).getString("role");
                String name = jsonArray.getJSONObject(0).getString("name");
                JSONObject myJObj = new JSONObject();
                myJObj.put("name", name);
                myJObj.put("email", email);
                myJObj.put("role", role);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    //get all users
    public JSONArray getData() {

        return jparser.makeHttpGETRequest(Constants.USERS_URL);
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        try {
            pDialog.dismiss();
            if(success){

                Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity, HomeActivity.class));
                activity.finish();
            }else{
                Toast.makeText(activity, "Login Failed! Check Credentials", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(activity, "Check Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCancelled() {
        pDialog.dismiss();

    }
}
