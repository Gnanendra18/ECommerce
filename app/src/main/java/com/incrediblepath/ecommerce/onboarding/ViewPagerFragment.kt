package com.incrediblepath.ecommerce.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.incrediblepath.ecommerce.R
import com.incrediblepath.ecommerce.onboarding.adapters.ViewPagerAdapter

import com.incrediblepath.ecommerce.databinding.FragmentViewPagerBinding
import com.incrediblepath.ecommerce.onboarding.fragments.CommonFragment


class ViewPagerFragment : Fragment() {
    private lateinit var fragmentViewPagerBinding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentViewPagerBinding = FragmentViewPagerBinding.inflate(inflater, container, false)
        fragmentViewPagerBinding.viewPager.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            arrayListOf(CommonFragment(),CommonFragment(),CommonFragment())
        )

        fragmentViewPagerBinding.leftNav.setOnClickListener {
            val currentItem = fragmentViewPagerBinding.viewPager.currentItem
            if(currentItem>0) {
                fragmentViewPagerBinding.viewPager.setCurrentItem(currentItem - 1)
            }
        }
        fragmentViewPagerBinding.rightNav.setOnClickListener {
            val currentItem = fragmentViewPagerBinding.viewPager.currentItem
            if(currentItem<2) {
                fragmentViewPagerBinding.viewPager.setCurrentItem(currentItem + 1)
            }
        }

        fragmentViewPagerBinding.viewPager.registerOnPageChangeCallback(object :OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position==0){
                    fragmentViewPagerBinding.leftNav.visibility = View.GONE
                    fragmentViewPagerBinding.rightNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.GONE
                }else if(position == 2){
                    fragmentViewPagerBinding.rightNav.visibility = View.GONE
                    fragmentViewPagerBinding.leftNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.VISIBLE
                }else{
                    fragmentViewPagerBinding.leftNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.rightNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.GONE
                }
            }
        })

        fragmentViewPagerBinding.finish.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }

        return fragmentViewPagerBinding.root
    }
}