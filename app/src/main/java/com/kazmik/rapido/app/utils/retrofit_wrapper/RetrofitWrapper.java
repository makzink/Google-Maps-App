package com.kazmik.rapido.app.utils.retrofit_wrapper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.support.v4.content.IntentCompat;
import android.util.Log;

import com.kazmik.rapido.app.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by kazmik on 28/07/17.
 */

public class RetrofitWrapper {

    public RetrofitWrapper(){
    }

    public static Retrofit getRetrofitRequest(@NonNull final Context mContext) {

        final String TAG = "RetrofitWrapper";


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                Response response = chain.proceed(request);
                if (request != null) {
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "request: " + response.toString());
                    }
                }

                if (response.code() > 0) {
                    switch (response.code()) {
                        case 400: // Bad Request
                            break;
                        case 401: // Forbidden
                        case 403: // Forbidden
                            break;
                        case 404: // Not Found
                            break;
                        case 408: // Request Timeout
                            break;
                        case 429: // Too Many Requests
                            break;
                        case 500: // Internal Server Error
                            break;
                        case 302: // Invalid token
                            break;
                        case 200: // success
                            break;
                    }
                }

                return response;
            }
        });
        OkHttpClient okClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getResources().getString(R.string.maps_base_url))
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
