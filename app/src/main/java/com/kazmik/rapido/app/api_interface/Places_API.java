package com.kazmik.rapido.app.api_interface;

import com.kazmik.rapido.app.api_response.places.Places_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kazmik on 05/08/17.
 */

public interface Places_API {

    @GET("place/autocomplete/json")
    Call<Places_Response> autoCompleteText(@Query("key")String key,@Query("types")String types,@Query("input")String input);
}
