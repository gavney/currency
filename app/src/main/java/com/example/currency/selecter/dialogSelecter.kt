package com.example.currency.selecter

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.currency.MainActivity
import com.example.currency.R

class MyDialogFragment(private val base: Boolean = false) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setSingleChoiceItems(
                R.array.currencyName, -1
            ) { _, item ->
                (activity as MainActivity?)?.getDataFromFragment(
                    base,
                    resources.getStringArray(R.array.currencyName)[item]
                )
            }
                .setPositiveButton(
                    "select"
                ) { _, item ->
                    (activity as MainActivity?)?.updateGraph()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}