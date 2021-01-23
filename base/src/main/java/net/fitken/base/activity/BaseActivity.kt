package net.fitken.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.fitken.base.dialog.LoadingDialog

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mViewDataBinding: V
    private var mLoadingDialog: LoadingDialog? = null

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
    }

    open fun popFragment(): Boolean {
        return false
    }

    override fun onBackPressed() {
        if (!popFragment()) {
            super.onBackPressed()
        }
    }

    fun showLockedLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
        }
        if (mLoadingDialog!!.isShowing) {
            return
        }
        mLoadingDialog!!.show()
    }

    fun hideLockedLoading() {
        mLoadingDialog?.dismiss()
    }
}