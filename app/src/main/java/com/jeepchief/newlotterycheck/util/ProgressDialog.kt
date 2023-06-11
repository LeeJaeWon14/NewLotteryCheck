package com.jeepchief.newlotterycheck.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.jeepchief.newlotterycheck.databinding.LayoutProgressDialogBinding

object ProgressDialog {
    private var progressDlg: AlertDialog? = null

    fun show(context: Context) {
        progressDlg?.show() ?: run {
            val dlgView = LayoutProgressDialogBinding.inflate(LayoutInflater.from(context))
            progressDlg = AlertDialog.Builder(context).create().apply {
                setCancelable(false)
                setView(dlgView.root)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDlg?.show()
        }
    }

    fun dismiss() {
        progressDlg?.dismiss() ?: return
    }
}