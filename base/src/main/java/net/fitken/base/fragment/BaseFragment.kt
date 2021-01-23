package net.fitken.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import net.fitken.base.R
import net.fitken.base.activity.BaseActivity
import net.fitken.base.exception.NoInternetException


abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected lateinit var mViewDataBinding: V

    abstract fun getLayoutResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.requestApplyInsets(view)
    }

    fun pushFragment(fragment: Fragment, sharedElement: View? = null, transitionName: String? = null, containerId: Int = R.id.fl_base_tab) {
        if (sharedElement != null && transitionName != null) {
            fragment.postponeEnterTransition();
            fragment.sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            fragment.enterTransition = Fade()
            exitTransition = Fade()
            fragment.sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            parentFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out)
                    .addSharedElement(sharedElement, transitionName)
                    .addToBackStack(fragment.tag)
                    .replace(containerId, fragment).commit()
        } else {
            parentFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out)
                    .add(containerId, fragment).addToBackStack(fragment.tag).commit()
        }
    }

    override fun onDestroyView() {
        mViewDataBinding.unbind()
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
        super.onDestroyView()
    }

    fun showLockedLoading(isShow: Boolean) {
        if (isShow) {
            (requireActivity() as BaseActivity<*>).showLockedLoading()
        } else {
            (requireActivity() as BaseActivity<*>).hideLockedLoading()
        }
    }

    fun showError(exception: Exception?) {
        exception?.let {
            var message = String.format(getString(R.string.unknown_error), it.localizedMessage)
            when (it) {
                is NoInternetException -> {
                    message = getString(R.string.internet_not_available)
                }
                else -> {

                }
            }
            Snackbar.make(mViewDataBinding.root, message, Snackbar.LENGTH_LONG)
                    .show()
        }
    }
}