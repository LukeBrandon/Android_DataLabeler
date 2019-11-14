package dev.threepebbles.datalabeler.remote;

import dev.threepebbles.datalabeler.model.Post;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/login")
    @FormUrlEncoded
    Call<Post> loginPost(@Field("email") String email, @Field("password") String password);
}