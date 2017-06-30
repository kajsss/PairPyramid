package msl.com.pairpyramid.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import msl.com.pairpyramid.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            var intent = Intent(this@SplashActivity ,MainActivity::class.java)
            this@SplashActivity.startActivity(intent)
            finish()


        },SPLASH_TIMEOUT.toLong())
    }

    companion object{
        private val SPLASH_TIMEOUT = 2000
    }
}