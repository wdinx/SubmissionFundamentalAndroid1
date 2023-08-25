package com.bangkit.githubuser.data.reponse.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkit.githubuser.ui.FollowFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var username : String = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_POSITION, position+1)
            putString(FollowFragment.ARG_USERNAME, username)
        }

        return fragment as Fragment
    }

}