package net.fitken.base.fragment

import androidx.fragment.app.FragmentManager
import net.fitken.base.R

class BasePager(
        private var mContentFragments: ArrayList<BaseTabFragment>, //HydrangeaTabFragment have childFragmentManager to handle add more fragment to a page
        private var mContainerId: Int, //the id of FrameLayout to put fragment
        private var mFragmentManager: FragmentManager
) {


    private var mPageIndex: Int = 0

    init {
        val fragment = mContentFragments[mPageIndex]
        if (fragment.isAdded || fragment.isHidden || fragment.isDetached) {
            this.showTab(mPageIndex)
        } else {
            val transaction = mFragmentManager.beginTransaction()
            transaction.add(mContainerId, fragment)
            transaction.commitAllowingStateLoss()
        }
    }

    fun showTab(position: Int) {
        if (position == mPageIndex) {
            return
        }
        if (position >= mContentFragments.size) {
            return
        }

        val hideFragment = mContentFragments[mPageIndex]
        val showFragment = mContentFragments[position]
        val transaction = mFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out)
        if (showFragment.isAdded) {
            if (showFragment.isDetached) {
                transaction.attach(showFragment)
            } else if (showFragment.isHidden) {
                transaction.show(showFragment)
            }
        } else {
            transaction.add(mContainerId, showFragment)
        }
        transaction.hide(hideFragment)
        mPageIndex = position
        transaction.commitAllowingStateLoss()
    }

    fun popFragment(): Boolean {
        val currentFragmentManager = mContentFragments[mPageIndex].childFragmentManager
        return if (currentFragmentManager.backStackEntryCount > 0) {
            currentFragmentManager.popBackStack()
            true
        } else {
            false
        }
    }
}