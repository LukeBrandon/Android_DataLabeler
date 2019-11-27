package dev.threepebbles.datalabeler.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHandler {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ACCOUNT_ID = "accountId";

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
    }

    public static int getStoredAccountId(Context context){
        SharedPreferences sharedPreferences = getSharedPreferencesReader(context);
        return sharedPreferences.getInt(ACCOUNT_ID, -1);
    }
}
