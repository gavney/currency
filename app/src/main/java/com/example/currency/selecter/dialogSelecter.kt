package com.example.currency.selecter

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.currency.R

class MyDialogFragment(public val base: Boolean = false) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val bundle = Bundle()
            builder.setSingleChoiceItems(R.array.currencyName, -1
                ) { dialog, item ->
                    Toast.makeText(activity, "Selected:  ${resources.getStringArray(R.array.currencyName)[item]} $base",
                        Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("select"
                ) { dialog, item ->
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}