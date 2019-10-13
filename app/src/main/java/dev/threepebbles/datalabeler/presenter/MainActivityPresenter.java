package dev.threepebbles.datalabeler.presenter;

import android.util.Log;

import dev.threepebbles.datalabeler.contact.MainContract;

public class MainActivityPresenter implements MainContract.Presenter {
    private static final String TAG = "MainActivityPresenter";

    MainContract.View mainView;
    String data;

    public MainActivityPresenter(MainContract.View mainView){
        this.mainView = mainView;
    }

    public void updateTextView(String data){
        Log.d(TAG, "updateTextView: Data is:" + data);

        if(data.matches(""))
            this.data = "ERROR";
        else
            this.data = data;
        this.mainView.updateTextView(data);
    }

    public void clearTextView(){

    }

}
