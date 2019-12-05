package dev.threepebbles.datalabeler.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dev.threepebbles.datalabeler.model.DataLabelSubmission;
import dev.threepebbles.datalabeler.model.SimpleResponse;
import dev.threepebbles.datalabeler.utils.APIService;
import dev.threepebbles.datalabeler.utils.APIUtils;
import dev.threepebbles.datalabeler.view.LabelActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabelActivityPresenter {
    private static final String TAG = "LabelActivityPresenter";
    final long ONE_MEGABYTE = 1024 * 1024;


    private LabelActivity view;
    private Context context;
    private APIService apiService;
    private StorageReference storageReference;


    public LabelActivityPresenter(LabelActivity activity){
        this.view = activity;
        this.context = view.getApplicationContext();
        this.apiService = APIUtils.getAPIService();
        this.storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void postAnswer(DataLabelSubmission dataLabelSubmission){
        this.apiService.dataLabelSubmit(dataLabelSubmission).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                boolean wasSuccess = response.body().getSuccess();
                if(wasSuccess) {
                    view.showSuccessMessage();

                    // Here because needs to wait for response on submission
                    view.launchRewardActivity();
                    view.finish();
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                view.showInternetFailed();
            }
        });
    }

    public void getImageFromFileName(String fileName, ImageView imageView){
        StorageReference imageRef = storageReference.child(fileName);
        Log.d(TAG, "getImageFromFileName: imageRef: " + imageRef.toString());

        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
            view.setImageBitMap(scaledBitmap);

        }).addOnFailureListener(exception -> {

            Log.d(TAG, "getImageFromFileName: Retrieved image failed");
        });
    }
}
