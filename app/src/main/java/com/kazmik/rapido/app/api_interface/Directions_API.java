package com.kazmik.rapido.app.api_interface;

import com.kazmik.rapido.app.api_response.directions.Directions_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kazmik on 05/08/17.
 */

public interface Directions_API {


    @GET("directions/json?")
    Call<Directions_Response> getRoutes(@Query("key")String api_key,@Query("origin")String origin,@Query("destination")String destination,@Query("alternatives")String alternatives);

}
