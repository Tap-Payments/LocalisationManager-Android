# LocalisationManager-Android
Android Library for easy changing locale programmatically
### Features
- Changes language in the runtime
- Persists the changes in SharedPreferences automatically
- Detects Right-To-Left (RTL) languages and updates layout direction

## Download
- Add the JitPack repository to your build file

  ```groovy
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

- Add the dependency
 ```groovy

  ```

## Implementation
**(Option 1) Using base classes**
1. Extend your app class
```kotlin
class MyApplication : LocaleApplication() {
}
```
2. Extend your base activity class
```kotlin
open class BaseActivity : LocaleAppCompatActivity() {
}
```
It's Done

**(Option 2) Using delegates**

If you don't want to extend from base classes.
1. On your custom Application class override `onAttach` and `onConfiguration` change methods.
```kotlin
class MyApp : Application() {
    private val localeAppDelegate = LocaleApplicationDelegates()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }
}
```
2. On your base activity class override `onAttach`
```kotlin
open class BaseActivity : AppCompatActivity() {
    private val localeDelegate = EasyLocaleActivityDelegate()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.initialise(this,this)
    }
}
```
**Usage**
=
(Usage 1)

If you're using the base classes, just call `setLocale(newLocale)`. It will then update the locale and restart the activity.

Example:
```kotlin
 R.id.action_en -> {
             setLocale(Locale("en"))
             true
         }
         R.id.action_ar -> {
             setLocale(Locale("ar"))
             true
         }
         R.id.action_fr -> {
             setLocale(Locale("fr"))
             true
         }
```

(Usage 2)

To change the locale you can call `setLocale` on the delegate
```kotlin
localeDelegate.setLocale(this, newLocale)
```
The delegate will set the new locale and recreate the activity.

(Usage 3)

To get notified of `Locale` changes implement `LocaleChangeListner`
```kotlin
localeDelegate.setLocaleChangeListner(object:LocaleChangeListner{
  override fun onLocaleChanged(mOldLocale:Locale,mNewLocale:Locale){
    ...
  }
})
```
**Notes**
=
1. If your locale is Right-To-Left(RTL) don't forget to enable it in the `AndroidManifest.xml`
```xml
<application
	android:supportsRtl="true">
</application>
```
