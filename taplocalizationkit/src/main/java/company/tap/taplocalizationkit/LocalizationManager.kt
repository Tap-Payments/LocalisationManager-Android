package company.tap.taplocalizationkit

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import org.json.JSONObject
import java.io.*
import java.util.*


/**
 * Created by AhlaamK on 5/3/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/

/**
 * LocalizationManager loads the json file and helps in getting the
 * value from strings based on Locale
 * **/
object LocalizationManager {
    private lateinit var localized: JSONObject
    private lateinit var localeVal: JSONObject
    var currentLocalized:JSONObject= JSONObject()
    fun loadTapLocale(resources: Resources, resId: Int) {
        val `is`: InputStream = resources.openRawResource(resId)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        `is`.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int? = null
            while (reader.read(buffer).also { n = it } != -1) {
                n?.let { writer.write(buffer, 0, it) }
            }
        }
        localized = JSONObject(writer.toString())
        currentLocalized = localized
        Log.d("currentLocalized", currentLocalized.toString())
      
    }


    fun loadTapLocale(context: Context, url: String) {
        Ion.with(context)
            .load(url)
            .asJsonObject()
            .setCallback { e, result ->
                if (e != null) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                } else {
                    localized = JSONObject(result.toString())
                    currentLocalized = localized
                }
                println("currentLocalized"+currentLocalized)
                Log.d("currentLocalized", currentLocalized.toString())
            }
    }



    fun <T> getValue(path: String, componentName: String, subcomponentName: String?=null): T {
        if (Locale.getDefault().toString().contains("en")) {
            localeVal = (localized.get("en") as JSONObject)
        } else {
            localeVal = (localized.get(Locale.getDefault().toString()) as JSONObject)
        }
       // println("localeVal $localeVal")
        val valuekey: Any
        if (subcomponentName == "" || subcomponentName.isNullOrEmpty()) {
          //  valuekey = localeVal.getJSONObject(componentName).getJSONObject(path)
            valuekey =localeVal.getJSONObject(componentName).getString(path)


        } else {
            valuekey =
                localeVal.getJSONObject(componentName).getJSONObject(path).get(subcomponentName)

        }
        //  val valuekey = localeVal.getJSONObject(componentName).getJSONObject(path).get(subcomponentName)
      //  println("valuekey $valuekey")
        return valuekey as T
    }

    fun setLocale(context: Context,locale: Locale){
        LocaleHelper.setlocale(context, locale)
    }

    fun getLocale(context: Context) : Locale{
       return LocaleHelper.getCurrentLocale(context)
    }


}

