package net.fitken.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import net.fitken.base.R
import net.fitken.base.databinding.FragmentBaseTabBinding

abstract class BaseTabFragment : BaseFragment<FragmentBaseTabBinding>() {
    override fun getLayoutResource(): Int {
        return R.layout.fragment_base_tab
    }

    abstract fun getContentFragment(): Fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
                .replace(R.id.fl_base_tab, getContentFragment()).commit()
    }
}