package company.tap.taplocalizationkit

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.edit
import java.util.*

/**
 * Created by AhlaamK on 5/4/20.

Copyright (c) 2020    Tap Payments.
All rights reserved.
 **/
internal object LocaleHelper {

    internal fun onAttachBaseContext(ctx: Context) = setlocale(ctx, getCurrentLocale(ctx))

     fun getCurrentLocale(ctx: Context): Locale {
        LocalePrefrences.getLocaleSharedPreference(ctx)?.let {
            return Locale(
                it.getString(LocalePrefrences.PREF_LANGUAGE, Locale.getDefault().language)!!,
                it.getString(
                    LocalePrefrences.PREF_COUNTRY, Locale.getDefault().country
                )!!
            )
        }
        return Locale.getDefault()
    }


    internal fun setlocale(ctx: Context, locale: Locale): Context {
        LocalePrefrences.setLocalePrefrences(ctx, locale)
        Locale.setDefault(locale)
        ctx.resources.configuration.apply {
            setLocale(locale)
            setLayoutDirection(locale)
            return ctx.createConfigurationContext(this)
        }
    }

    private object LocalePrefrences {
        internal const val PREF_LANGUAGE = "locale.language"
        internal const val PREF_COUNTRY = "locale.country"
        private const val PREF_NAME = "localPref"

        internal fun getLocaleSharedPreference(ctx: Context): SharedPreferences? {
            return ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }

        internal fun setLocalePrefrences(ctx: Context, locale: Locale) {
            getLocaleSharedPreference(ctx)?.edit {
                putString(PREF_LANGUAGE, locale.language)
                putString(PREF_COUNTRY, locale.country)
            }
        }
    }
}