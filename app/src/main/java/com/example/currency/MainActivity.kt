package com.example.currency

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.currency.api.CurrencyApp
import com.example.currency.data.Response
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    private val service = CurrencyApp.create()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCreate()

        button.setOnClickListener {
            service.getRates(spinnerBase.selectedItem.toString(), "sandbox_c9farriad3iampagd6cg")
                .enqueue(
                    object : retrofit2.Callback<Response> {
                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            textView.text =
                                response.body()?.quote?.get(spinner.selectedItem.toString())
                        }

                        override fun onFailure(call: Call<Response>, t: Throwable) {
                        }
                    }
                )
        }
    }

    private fun spinnerCreate() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.currencyName)
        )
        spinner.adapter = adapter
        spinnerBase.adapter = adapter

        spinner.setSelection(resources.getStringArray(R.array.currencyName).indexOf("RUB"))
        spinnerBase.setSelection(resources.getStringArray(R.array.currencyName).indexOf("USD"))
    }
}