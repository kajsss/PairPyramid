package msl.com.pairpyramid.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.custom.PyramidView
import msl.com.pairpyramid.database.dao.PartnerDao
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private val INSERT_INDEX = 1

    lateinit var playerNameList: Array<String>
    lateinit var pairCountsHashMap: HashMap<Pair<Int, Int>, Int>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerNameList = PlayerDao(this).selectAllPlayerList()!!
                .map{it.name}
                .toTypedArray()
        pairCountsHashMap = PartnerDao(this).selectPairCounts()


        pairCountsHashMap.forEach { t, u -> println(t.first.toString() + "-" + t.second.toString() + ":" + u) }

        main_layout.addView(PyramidView(this@MainActivity, playerNameList, pairCountsHashMap), INSERT_INDEX)

        findViewById(R.id.btn_matching).setOnClickListener { v ->
            startActivity<MakeEntryActivity>()
        }

    }

}

