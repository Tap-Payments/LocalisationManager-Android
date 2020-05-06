package company.tap.taplocalizationsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import company.tap.taplocalizationkit.LocaleAppCompatActivity
import company.tap.taplocalizationkit.LocalizationManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : LocaleAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAppLocale(R.raw.lang)
        card_number_et.hint = LocalizationManager.getValue("cardNumberPlaceHolder","TapCardInputKit")
        hello_text.text = LocalizationManager.getValue("next","Common")
    }

    private fun initAppLocale(lang: Int) {
        LocalizationManager.loadTapLocale(resources, lang)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_en -> {
            setLocale(Locale("en"))
            true
        }
        R.id.action_ar -> {
            setLocale(Locale("ar"))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
