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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bagicode.alquranfan.adapter.MainAdapter


class MainActivity : AppCompatActivity() {

    /*
    read about fan :
    https://amitshekhar.me/Fast-Android-Networking/post_request.html
     */

    var baseUrl: String? = null
    val ayahes = ArrayList<Ayah>()
    var ayates: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val key = "1"
        baseUrl = "http://api.alquran.cloud/v1/surah/$key"
        getAyahWithModel()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    /*
        this funct return with json object
        parse with manual
     */
    private fun getAyah() {
        ayahes.clear()
        recyclerView.adapter = null

        AndroidNetworking
            .get(baseUrl)
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

                        ayates += response_one.data.ayahs[i].text+" 0 "

                    }
                    recyclerView.adapter = MainAdapter(ayahes)

                    tv_ayar.text = ayates

                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    /*
        this funct return with json use model class
        parse with model class
     */
    private fun getAyahWithModel() {
        ayahes.clear()
        recyclerView.adapter = null

        AndroidNetworking.get(baseUrl)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(MainModel::class.java , object : ParsedRequestListener<MainModel>{
                override fun onResponse(response_one: MainModel) {
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

                        ayates += response_one.data.ayahs[i].text+" 0 "

                    }
                    recyclerView.adapter = MainAdapter(ayahes)

                    tv_ayar.text = ayates
                }
                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })


    }

}
