package com.trivagonytimes.repository;

import com.trivagonytimes.api.NyTimesApiService;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

  private static RetrofitClient instance = null;
  private Retrofit retrofit;
  private OkHttpClient client;

  private NyTimesApiService nyTimesApiService;
  public static final String API_KEY = "2149e52580e248ab8cf5d53b0ad5372e";
  public  static final String API_BASE_URL = "https://api.nytimes.com/svc/search/v2/";
  public  static final String MOST_VIEWED_URL = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/30.json";
  public static final String API_IMAGE_BASE_URL = "http://www.nytimes.com/";



  public RetrofitClient() {


    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    Interceptor apiKeyInterceptor = chain -> {
      Request request = chain.request();
      HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", RetrofitClient.API_KEY).build();
      request = request.newBuilder().url(url).build();
      return chain.proceed(request);
    };
    OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    okHttpBuilder.addInterceptor(loggingInterceptor);
    okHttpBuilder.addInterceptor(apiKeyInterceptor);
    client = okHttpBuilder.build();
    retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    nyTimesApiService = retrofit.create(NyTimesApiService.class);
  }

  public static RetrofitClient getInstance() {
    if (instance == null) {
      instance = new RetrofitClient();
    }

    return instance;
  }

  public NyTimesApiService getNyTimesApiService() {
    return nyTimesApiService;
  }
}