package com.bagicode.alquranfan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bagicode.alquranfan.model.Ayah
import com.bagicode.alquranfan.model.MainModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import android.support.v7.widget.GridLayoutManager



class MainGridActivity : AppCompatActivity() {

    var baseUrl: String? = null
    val ayahes = ArrayList<Ayah>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = "1"
        baseUrl = "http://api.alquran.cloud/v1/surah/$key"
        getAyah()

        //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun getAyah() {
        ayahes.clear()
        recyclerView.adapter = null

        AndroidNetworking.get(baseUrl)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    val response_one = Gson().fromJson(response.toString(), MainModel::class.java)
                    for (i in response_one.data.ayahs.indices) {
                        ayahes.add(
                            Ayah(response_one.data.ayahs[i].hizbQuarter,
                                response_one.data.ayahs[i].juz,
                                response_one.data.ayahs[i].manzil,
                                response_one.data.ayahs[i].number,
                                response_one.data.ayahs[i].numberInSurah,
                                response_one.data.ayahs[i].page,
                                response_one.data.ayahs[i].ruku,
                                response_one.data.ayahs[i].sajda,
                                response_one.data.ayahs[i].text)
                        )

                    }
                    recyclerView.adapter = MainAdapter(ayahes)

                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

}
