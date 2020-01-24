package com.aad.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("add_service.php")
    Call<User> add_Service(@Field("services_name") String name);

    @FormUrlEncoded
    @POST("add_product.php")
    Call<User> add_product(@Field("products_name") String name,
                            @Field("price") String price
    );

    @GET("get_services.php")
    Call<List<ServicePojo>> get_services();


}
