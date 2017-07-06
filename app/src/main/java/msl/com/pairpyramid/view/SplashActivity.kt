package msl.com.pairpyramid.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import msl.com.pairpyramid.R
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import msl.com.pairpyramid.view.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
           startActivity<MainActivity>()
           finish()
        }, SPLASH_TIMEOUT.toLong())
    }

    companion object{
        private val SPLASH_TIMEOUT = 2000
    }
}