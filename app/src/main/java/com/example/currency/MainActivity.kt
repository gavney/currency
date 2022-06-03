package com.example.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.currency.api.CurrencyApp
import com.example.currency.data.Response
import com.example.currency.selecter.MyDialogFragment
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
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

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val manager = supportFragmentManager

        buttonConvert.setOnClickListener {
            service.getRates(buttonBase.text.toString(), "sandbox_c9farriad3iampagd6cg")
                .enqueue(
                    object : retrofit2.Callback<Response> {
                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            val result =
                                response.body()?.quote?.get(buttonCurrency.text.toString())
                                    ?.toDouble()
                                    ?.times(textViewBase.text.toString().toDouble())
                            textViewValue.text = result.toString()
                        }

                        override fun onFailure(call: Call<Response>, t: Throwable) {
                        }
                    }
                )
        }

        buttonBase.setOnClickListener {
            val myDialogFragment = MyDialogFragment(true)
            myDialogFragment.show(manager, "myDialog")
        }

        buttonCurrency.setOnClickListener {
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.show(manager, "myDialog")
        }

        button1.setOnClickListener { appendValue("1") }
        button2.setOnClickListener { appendValue("2") }
        button3.setOnClickListener { appendValue("3") }
        button4.setOnClickListener { appendValue("4") }
        button5.setOnClickListener { appendValue("5") }
        button6.setOnClickListener { appendValue("6") }
        button7.setOnClickListener { appendValue("7") }
        button8.setOnClickListener { appendValue("8") }
        button9.setOnClickListener { appendValue("9") }
        button0.setOnClickListener { appendValue("0") }

        buttonDelete.setOnClickListener { appendValue("", true) }
    }


    fun getDataFromFragment(base: Boolean, currency: String) {
        if (!base) {
            buttonCurrency.text = currency
            return
        }
        buttonBase.text = currency
    }


    private fun appendValue(string: String, isClear: Boolean = false) {
        if (isClear && textViewBase.text.isNotEmpty()) {
            textViewBase.text = textViewBase.text.substring(0, textViewBase.text.length - 1)
        }
        if (textViewBase.text.isNotEmpty() && textViewBase.text[0] == '0') {
            textViewBase.text = ""
        }
        textViewBase.append(string)

        if (textViewBase.text.isEmpty()) {
            textViewBase.text = "0"
        }
    }

//    fun updateGraph(array: List<String>) {
//        var arrayData = arrayOf<DataPoint>()
//
//        (0..array.size - 2).forEach { i ->
//            arrayData += DataPoint(i.toDouble(), array[i].toDouble())
//        }
//
//        graph.addSeries(BarGraphSeries(arrayData))
//    }
}

