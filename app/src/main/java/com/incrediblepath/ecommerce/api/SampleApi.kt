package com.incrediblepath.architecturecomponents.api

import com.incrediblepath.ecommerce.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface SampleApi {



    @GET("products.json")
    suspend fun getproducts(

    ):Response<List<Product>>




}