package company.tap.taplocalizationkit

import android.app.Application
import android.content.Context
import android.content.res.Configuration

/**
 * Created by AhlaamK on 5/4/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/

/**
 * LocaleApplication class that extends Application and overrides
 * the base methods to detect locale change onAttach and onConfigChange
 * **/
open class LocaleApplication: Application() {
    private val localeApplicationDelegates=LocaleApplicationDelegates()
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(localeApplicationDelegates.attachBaseContext(base!!))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeApplicationDelegates.onConfigurationChanged(this)
    }
}