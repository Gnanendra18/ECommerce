package com.incrediblepath.ecommerce.onboarding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.incrediblepath.ecommerce.R
import com.incrediblepath.ecommerce.databinding.FragmentCommonBinding

class CommonFragment(private val i: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val cfBinding = FragmentCommonBinding.inflate(inflater, container, false)

        when (i) {
            0 -> {
                cfBinding.imageView.setImageResource(R.drawable.shopping)
                cfBinding.title.text = "Shop"
                cfBinding.subtitle.text = "select your products from list of variety"
            }
            1 -> {
                cfBinding.imageView.setImageResource(R.drawable.shopping_basket)
                cfBinding.title.text = "Shop"
                cfBinding.subtitle.text = "select your products from list of variety"
            }

            2 -> {
                cfBinding.imageView.setImageResource(R.drawable.payment)
                cfBinding.title.text = "Shop"
                cfBinding.subtitle.text = "select your products from list of variety"
            }

        }



        return cfBinding.root


    }
}