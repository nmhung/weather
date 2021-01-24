package net.fitken.base.activity

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.Moshi
import net.fitken.base.R
import net.fitken.base.dialog.LoadingDialog
import net.fitken.base.exception.NoInternetException
import net.fitken.base.exception.ServerException
import retrofit2.HttpException


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

    fun showError(exception: Exception?) {
        exception?.let {
            var message = String.format(getString(R.string.unknown_error), it.localizedMessage)
            when (it) {
                is NoInternetException -> {
                    message = getString(R.string.internet_not_available)
                }
                is HttpException -> {
                    message = getHttpExceptionMessage(it)
                }
                else -> {

                }
            }
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mViewDataBinding.root.windowToken, 0)
            Snackbar.make(mViewDataBinding.root, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun getHttpExceptionMessage(httpException: HttpException): String {
        try {
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(ServerException::class.java)

            val serverException =
                jsonAdapter.fromJson(httpException.response()?.errorBody()?.string() ?: "")
                    ?: return String.format(getString(R.string.unknown_error), httpException.code())
            return serverException.message
                ?: String.format(getString(R.string.unknown_error), httpException.code())
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}