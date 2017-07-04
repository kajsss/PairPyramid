package msl.com.pairpyramid.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.custom.PyramidView
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private val INSERT_INDEX = 1
    private val PLAYER_COUNT = 8

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameList = arrayOf("Milo", "Lamos", "Woody", "Dave", "Steve", "Sue", "Kafri", "Hubert")
        main_layout.addView(PyramidView(this@MainActivity, PLAYER_COUNT, nameList), INSERT_INDEX)

        btn_matching.setOnClickListener { v ->
            startActivity<MakeEntryActivity>()
        }


    }

}

