package com.incrediblepath.ecommerce.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.incrediblepath.ecommerce.R
import com.incrediblepath.ecommerce.databinding.FragmentHomeBinding
import com.incrediblepath.ecommerce.model.Product
import com.incrediblepath.ecommerce.viewmodels.MainActivityViewModel


class HomeFragment : Fragment() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var productAdapter: ProductAdapter
    lateinit var productList: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        // get references of required UI from XML
        val fHB = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        fHB.search.addTextChangedListener {
            if (this::productAdapter.isInitialized) {
                viewModel.filterData(it.toString(), productList, productAdapter)
            }

        }


        fHB.recyclerView.layoutManager = GridLayoutManager(context, 2)
        fHB.swiperefresh.setOnRefreshListener {
            fHB.swiperefresh.isRefreshing = true
            viewModel.refreshdata()
        }

        viewModel.listOfPosts.observe(viewLifecycleOwner, Observer {
            fHB.swiperefresh.isRefreshing = false
            if (it.isSuccessful) {

                productList = it.body()!!
                productAdapter = ProductAdapter(productList)
                fHB.recyclerView.adapter = productAdapter
            }

        })

        return fHB.root
    }


}