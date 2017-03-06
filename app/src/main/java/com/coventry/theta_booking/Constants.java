package com.coventry.theta_booking;

/**
 * Created by patela65 on 6/03/17.
 */
public class Constants {

    public static final String URL = "https://api.mongolab.com/api/1/databases/302cem/collections/";
    public static final String USERS_COLLECTION = "users";
    public static final String MONGO_API_KEY = "?apiKey=5dcrCiIuComWUa3hyM3Z5RyL_4OhSZc1";
    public static final String API_KEY = "&apiKey=Y5dcrCiIuComWUa3hyM3Z5RyL_4OhSZc1";

    public static String QUERY_USERS = URL+USERS_COLLECTION+"?q=";
    public static String UPDATE_USERS = URL+USERS_COLLECTION+MONGO_API_KEY+"&q=";

    public static String USERS_URL = URL+USERS_COLLECTION+MONGO_API_KEY;
    // not really a constant
    // username of sender
    public static String USER_EMAIL = null;

}