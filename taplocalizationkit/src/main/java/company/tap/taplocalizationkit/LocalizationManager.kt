package company.tap.taplocalizationkit

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.annotation.RawRes
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
object  LocalizationManager {
    private lateinit var localized: JSONObject

    fun loadTapLocale(resources: Resources, resId: Int) {
        val `is`: InputStream = resources.openRawResource(resId)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        `is`.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int?=null
            while (reader.read(buffer).also { n = it } != -1) {
                n?.let { writer.write(buffer, 0, it) }
            }
        }
        localized = JSONObject(writer.toString())
         println("localized is  $localized")

      }


    fun <T> getValue(path: String): T {
        val localeVal = (localized.get(Locale.getDefault().toString()) as JSONObject)
         val valuekey = localeVal.getString(path)
        return valuekey as T
    }

}

