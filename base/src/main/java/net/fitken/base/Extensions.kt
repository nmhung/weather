package net.fitken.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


inline fun <reified T> AppCompatActivity.start(clearBackStack: Boolean = false, bundle: Bundle? = null,
                                               activityOptions: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    if (clearBackStack) {
        if (activityOptions == null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        } else {
            //need to delay for better UI performance in case no transition between activities
            GlobalScope.launch {
                delay(700)
                finish()
            }
        }
    }
    bundle?.let {
        intent.putExtras(bundle)
    }
    ActivityCompat.startActivity(this, intent, activityOptions)
}
