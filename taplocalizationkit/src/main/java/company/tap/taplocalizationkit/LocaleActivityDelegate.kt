package company.tap.taplocalizationkit

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.core.text.layoutDirection
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

/**
 * Created by AhlaamK on 5/4/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
/**
 * Listens to the activity life cyle events .A class that has an  Android lifecycle.
 *  These events can be used by custom components to handle lifecycle changes without
 *  implementing any code inside the Activity or the Fragment.
 * **/
class LocaleActivityDelegate : LifecycleObserver {
    private var locale: Locale = Locale.getDefault()
    private var localeChangeListner: LocaleChangeListner? = null
    private lateinit var activity: Activity
    fun setLocale(activity: Activity, mLocale: Locale) {
        if (mLocale == Locale.getDefault()) {
            return
        }
        if (localeChangeListner != null) {
            localeChangeListner?.let { it.onLocaleChanged(locale, mLocale) }
        }
        LocaleHelper.setlocale(activity, mLocale)
        locale = mLocale
        activity.recreate()
    }

    fun initialise(lifecycleOwner: LifecycleOwner, activity: Activity) {
        this.activity=activity
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        activity.window.decorView.layoutDirection = Locale.getDefault().layoutDirection
    }

    fun attachBaseContext(ctx: Context): Context {
        return LocaleHelper.onAttachBaseContext(ctx)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        locale = Locale.getDefault()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (locale == Locale.getDefault())
            return
        else
            activity.recreate()
    }

    fun setLocaleChangeListner(listner: LocaleChangeListner) {
        localeChangeListner = listner
    }
}

class LocaleApplicationDelegates {
    fun attachBaseContext(ctx: Context): Context {
        return LocaleHelper.onAttachBaseContext(ctx)
    }

    fun onConfigurationChanged(ctx: Context) {
        LocaleHelper.onAttachBaseContext(ctx)
    }
}

interface LocaleChangeListner {
    fun onLocaleChanged(mOldLocale: Locale, mNewLocale: Locale)
}