package com.codelab.recyclerdialogapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class YesOrNo(val click:yesorno) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        return AlertDialog.Builder(context)
            .setMessage("Are you sure to delete?")
            .setPositiveButton("yes", DialogInterface.OnClickListener { _, _ ->
               click.yes()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
                click.no()
                dismiss()
            }).create()
    }

    interface yesorno{
        fun yes()
        fun no()
    }
}