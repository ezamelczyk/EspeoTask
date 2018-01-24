package me.ernestzamelczyk.espeotask.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.ernestzamelczyk.espeotask.ui.main.apps.AppsFragment
import me.ernestzamelczyk.espeotask.ui.main.education.EducationFragment
import me.ernestzamelczyk.espeotask.ui.main.experience.ExperienceFragment

class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> ExperienceFragment.create()
        1 -> EducationFragment.create()
        2 -> AppsFragment.create()
        else -> throw RuntimeException("No fragment on position nr.$position")
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Experience"
        1 -> "Education"
        2 -> "Showcase"
        else -> throw RuntimeException("No fragment on position nr.$position")
    }

}