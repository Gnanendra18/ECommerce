package com.incrediblepath.architecturecomponents.repository

import com.incrediblepath.architecturecomponents.api.RetrofitInstance
import com.incrediblepath.ecommerce.model.Product
import retrofit2.Response

class Repository {


    suspend fun getAllProducts(): Response<List<Product>> {
        return RetrofitInstance.myApi.getproducts()
    }


}
