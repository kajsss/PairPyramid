package msl.com.pairpyramid.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.custom.PyramidView
import msl.com.pairpyramid.database.dao.PartnerDao
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.PyramidInfo
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private val INSERT_INDEX = 1

    lateinit var playerNameList: Array<String>
    lateinit var pairCountsHashMap: HashMap<Pair<Int, Int>, PyramidInfo>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.btn_matching).setOnClickListener { v ->
            startActivity<MakeEntryActivity>()
        }
    }

    private var pyramidView : PyramidView? = null

    override fun onResume() {
        super.onResume()

        playerNameList = PlayerDao(this).selectAllPlayerList()!!
                .map{it.name}
                .toTypedArray()
        pairCountsHashMap = PartnerDao(this).selectPairCounts()

        if(pyramidView != null)
          main_layout.removeView(pyramidView)
        pyramidView = PyramidView(this@MainActivity, playerNameList, pairCountsHashMap)
        main_layout.addView(pyramidView, INSERT_INDEX)
    }
}

