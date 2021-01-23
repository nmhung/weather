package net.fitken.base.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


const val DELAY_TRANSITION_TIME: Long = 700
inline fun <reified T> Activity.start(
        clearBackStack: Boolean = false,
        bundle: Bundle? = null,
        activityOptions: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    if (clearBackStack) {
        if (activityOptions == null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        } else {
            //need to delay for better UI performance in case no transition between activities
            Handler().postDelayed({ finish() }, DELAY_TRANSITION_TIME)
        }
    }
    bundle?.let {
        intent.putExtras(bundle)
    }
    ActivityCompat.startActivity(this, intent, activityOptions)
}

inline fun <reified T> Activity.startForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : Fragment> Context.newFragment(fm: FragmentManager, bundle: Bundle? = null): T? {
    val fragment = fm.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), T::class.java.name)
    fragment.arguments = bundle
    return T::class.java.cast(fragment)
}