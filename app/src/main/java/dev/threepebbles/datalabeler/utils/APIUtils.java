package dev.threepebbles.datalabeler.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtils {
    private APIUtils() {}
    public static final String BASE_URL = "https://vast-taiga-78775.herokuapp.com/";
    private static Retrofit retrofit;


    public static APIService getAPIService() {
        return getClient(BASE_URL).create(APIService.class);
    }


    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}