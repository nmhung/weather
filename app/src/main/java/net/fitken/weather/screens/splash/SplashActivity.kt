package net.fitken.weather.screens.splash

import android.animation.Animator
import android.os.Bundle
import net.fitken.base.activity.BaseActivity
import net.fitken.base.start
import net.fitken.weather.R
import net.fitken.weather.databinding.ActivitySplashBinding
import net.fitken.weather.screens.dashboard.DashboardActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getLayoutResource() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.lavHello.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                start<DashboardActivity>(true)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }

}