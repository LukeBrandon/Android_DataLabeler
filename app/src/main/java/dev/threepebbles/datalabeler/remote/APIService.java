package dev.threepebbles.datalabeler.remote;

import java.util.List;

import dev.threepebbles.datalabeler.model.DataLabel;
import dev.threepebbles.datalabeler.model.Post;
import dev.threepebbles.datalabeler.model.DataLabelSubmission;
import dev.threepebbles.datalabeler.model.SimpleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("/labels")
    Call<List<DataLabel>> getDataLabels(@Query("id") int id);

    @POST("/login")
    @FormUrlEncoded
    Call<Post> loginPost(@Field("email") String email, @Field("password") String password);

    @POST("/submission")
    Call<SimpleResponse> dataLabelSubmit(@Body DataLabelSubmission dataLabelSubmission);
}