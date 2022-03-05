package com.incrediblepath.ecommerce.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.incrediblepath.ecommerce.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    lateinit var fragmentViewPagerBinding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentViewPagerBinding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return fragmentViewPagerBinding.root
    }
}