package com.volie.ghostlyprofiles.util

import android.app.AlertDialog
import android.content.Context

object AlertDialogUtil {

    fun showAlertDialog(
        context: Context,
        title: String,
        message: String,
        onPositiveConfirmed: () -> Unit
    ) {
        val alertDialog = AlertDialog.Builder(context)
        with(alertDialog) {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Yes") { dialog, _ ->
                onPositiveConfirmed()
                dialog.dismiss()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            setCancelable(false)
            show()
        }
    }
}