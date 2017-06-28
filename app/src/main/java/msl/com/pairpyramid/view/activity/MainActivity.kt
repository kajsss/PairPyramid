package msl.com.pairpyramid.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import msl.com.pairpyramid.R
import msl.com.pairpyramid.database.MockDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = MockDatabase()


        Log.d("###", "### ${db.playerList!!.get(0).name}")

        Log.d("###", "### ${db.partnerList.get(0).players.get(0).name}")
        Log.d("###",  "### ${db.partnerList.get(0).date.toString()}")

    }
}