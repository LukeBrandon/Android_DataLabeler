package dev.threepebbles.datalabeler.remote;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthHandler {
    private static final String TAG = "FirebaseAuthHandler";

    public static FirebaseAuth getFireBaseAuth(){
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser getFirebaseUser(Context context){
        if(getFireBaseAuth().getCurrentUser() == null){
            signInAnonymously(context);
        }
        return getFireBaseAuth().getCurrentUser();

    }


    private static void signInAnonymously(Context context) {
        FirebaseAuth auth = getFireBaseAuth();
        auth.signInAnonymously().addOnCompleteListener((Activity) context, task -> {

            if (task.isSuccessful()) {
                Log.d(TAG, "signInAnonymously:success");
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInAnonymously:failure", task.getException());
            }

        });
    }

}
