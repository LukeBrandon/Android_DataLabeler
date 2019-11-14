package dev.threepebbles.datalabeler.remote;

public class APIUtils {
    private APIUtils() {}
    public static final String BASE_URL = "https://vast-taiga-78775.herokuapp.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}