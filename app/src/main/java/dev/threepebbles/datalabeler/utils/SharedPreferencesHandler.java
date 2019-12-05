package dev.threepebbles.datalabeler.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesHandler {
    private static final String TAG = "SharedPreferencesHandle";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ACCOUNT_ID = "accountId";
    public static final String FIREBASE_TOKEN = "firebaseToken";

    public static SharedPreferences.Editor getSharedPreferencesEditor(Context context){
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE).edit();
    }

    public static SharedPreferences getSharedPreferencesReader(Context context){
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static void saveAccountId(Context context, int accountId){
        SharedPreferences.Editor editor = getSharedPreferencesEditor(context);
        editor.putInt(ACCOUNT_ID, accountId);

        editor.commit();

        Log.d(TAG, "saveAccountId: saved accountId in SharedPreferences as: " + SharedPreferencesHandler.getStoredAccountId(context));
    }

    public static int getStoredAccountId(Context context){
        SharedPreferences sharedPreferences = getSharedPreferencesReader(context);
        return sharedPreferences.getInt(ACCOUNT_ID, -1);
    }

    public static void saveFirebaseToken(Context context, String firebaseToken){
        SharedPreferences.Editor editor = getSharedPreferencesEditor(context);
        editor.putString(FIREBASE_TOKEN, firebaseToken);

        editor.commit();
    }

    public static String getStoredFirebaseToken(Context context){
        SharedPreferences sharedPreferences = getSharedPreferencesReader(context);
        return sharedPreferences.getString(FIREBASE_TOKEN, "");
    }
}
