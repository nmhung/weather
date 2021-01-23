package net.fitken.base.dialog

import android.content.Context
import android.os.Bundle
import net.fitken.base.R
import net.fitken.base.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : BaseDialog<DialogLoadingBinding>(context) {

    override fun getLayoutId(): Int = R.layout.dialog_loading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
    }
}