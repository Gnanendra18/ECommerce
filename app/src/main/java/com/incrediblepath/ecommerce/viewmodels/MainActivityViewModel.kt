package com.incrediblepath.ecommerce.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incrediblepath.architecturecomponents.repository.Repository
import com.incrediblepath.ecommerce.home.ProductAdapter
import com.incrediblepath.ecommerce.model.Product
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    fun refreshdata() {
        val repo = Repository()
        viewModelScope.launch {
            listOfPosts.value = repo.getAllProducts()
        }

    }

    fun filterData(query: String,productList : List<Product>, productAdapter : ProductAdapter ) {
        val newList:MutableList<Product> = mutableListOf()
        for (pd : Product in productList){
            if(pd.pd_name.toLowerCase().contains(query)){

                newList.add(pd)

            }
        }

            productAdapter.filterData(newList)

    }


    val listOfPosts = MutableLiveData<Response<List<Product>>>()
    init {
            refreshdata()

    }




}