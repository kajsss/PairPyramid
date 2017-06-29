package msl.com.pairpyramid.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import msl.com.pairpyramid.R
import msl.com.pairpyramid.database.MockDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,PlayerListActivity::class.java)
        startActivity(intent)
    }
}