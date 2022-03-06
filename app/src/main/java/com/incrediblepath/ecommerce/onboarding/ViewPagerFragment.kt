package com.incrediblepath.ecommerce.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.incrediblepath.ecommerce.R
import com.incrediblepath.ecommerce.onboarding.adapters.ViewPagerAdapter

import com.incrediblepath.ecommerce.databinding.FragmentViewPagerBinding
import com.incrediblepath.ecommerce.onboarding.fragments.CommonFragment


class ViewPagerFragment : Fragment() {
    private lateinit var fragmentViewPagerBinding: FragmentViewPagerBinding
    private lateinit var list: ArrayList<Fragment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        list = arrayListOf(CommonFragment(0), CommonFragment(1), CommonFragment(2),)
        fragmentViewPagerBinding = FragmentViewPagerBinding.inflate(inflater, container, false)
        fragmentViewPagerBinding.viewPager.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            list
        )

        fragmentViewPagerBinding.leftNav.setOnClickListener {
            val currentItem = fragmentViewPagerBinding.viewPager.currentItem
            if (currentItem > 0) {
                fragmentViewPagerBinding.viewPager.setCurrentItem(currentItem - 1)
            }
        }
        fragmentViewPagerBinding.rightNav.setOnClickListener {
            val currentItem = fragmentViewPagerBinding.viewPager.currentItem
            if (currentItem < list.size-1) {
                fragmentViewPagerBinding.viewPager.setCurrentItem(currentItem + 1)
            }
        }

        fragmentViewPagerBinding.viewPager.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    fragmentViewPagerBinding.leftNav.visibility = View.GONE
                    fragmentViewPagerBinding.rightNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.GONE
                } else if (position == list.size-1) {
                    fragmentViewPagerBinding.rightNav.visibility = View.GONE
                    fragmentViewPagerBinding.leftNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.VISIBLE
                } else {
                    fragmentViewPagerBinding.leftNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.rightNav.visibility = View.VISIBLE
                    fragmentViewPagerBinding.finish.visibility = View.GONE
                }
            }


        })

        fragmentViewPagerBinding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }

        TabLayoutMediator(fragmentViewPagerBinding.tabLayout, fragmentViewPagerBinding.viewPager) { tab, position ->
            //tab.text = "OBJECT ${(position + 1)}"
        }.attach()
        return fragmentViewPagerBinding.root
    }
}