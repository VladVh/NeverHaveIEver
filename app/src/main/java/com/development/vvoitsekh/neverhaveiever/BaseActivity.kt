package com.development.vvoitsekh.neverhaveiever

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.TextView
import butterknife.BindView
import com.development.vvoitsekh.neverhaveiever.util.ConnectivityStatus
import com.development.vvoitsekh.neverhaveiever.util.LocaleHelper
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

open class BaseActivity : Activity() {

    @BindView(R.id.baseAppName)
    lateinit var mAppName: TextView

    @BindView(R.id.mainAdView)
    lateinit var mAdBanner: AdView

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (!ConnectivityStatus.isConnected(context)) {
                // no connection
            } else {
                mAdBanner.loadAd(AdRequest.Builder().build())
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        val decorView = window.decorView
//        // Hide the status bar.
//        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
//        decorView.systemUiVisibility = uiOptions

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val nightMode = pref.getBoolean("NeverIHaveEver.night_mode", false)
        setTheme(if (nightMode) R.style.AppThemeDark else R.style.AppTheme)
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this, "ca-app-pub-6434220602939969~9178893473")

//        mAdBanner.adListener = object: AdListener() {
//            override fun onAdFailedToLoad(errorCode: Int) {
//                when(errorCode) {
//                    AdRequest.ERROR_CODE_INTERNAL_ERROR -> Log.e("internal", "error")
//                    AdRequest.ERROR_CODE_NETWORK_ERROR -> Log.e("network", "error")
//                    AdRequest.ERROR_CODE_INVALID_REQUEST -> Log.e("invalid", "error")
//                }
//            }
//        }
    }

    override fun onPause() {
        mAdBanner.pause()
        super.onPause()
        this.unregisterReceiver(mReceiver)
    }

    override fun onResume() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mAdBanner.resume()
        super.onResume()
        this.registerReceiver(mReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        mAdBanner.destroy()
        super.onDestroy()
    }
}
