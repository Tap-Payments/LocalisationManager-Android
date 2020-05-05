package company.tap.taplocalizationkit

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Created by AhlaamK on 5/4/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
open class LocaleAppCompatActivity : AppCompatActivity() {
    private val LocaleActivityDelegate = LocaleActivityDelegate()
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleActivityDelegate.attachBaseContext(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleActivityDelegate.initialise(this, this)
    }

    fun setLocale(locale: Locale) {
        LocaleActivityDelegate.setLocale(this, locale)
    }
}