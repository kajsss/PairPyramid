package msl.com.pairpyramid.view.pyramid

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo
import msl.com.pairpyramid.view.custom.layout.PyramidView
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class PairPyramidActivity : AppCompatActivity(), PairPyramidContract.View {

    private val INSERT_INDEX = 1
    private var pyramidView : PyramidView? = null

    lateinit var pairPyramidPresenter: PairPyramidPresenter

    override fun getContext(): Context {
        return this@PairPyramidActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pairPyramidPresenter = PairPyramidPresenter(this)

        findViewById(R.id.btn_matching).onClick {
            startActivity<MakeEntryActivity>()
        }
    }

    override fun onResume() {
        super.onResume()

        pairPyramidPresenter.getPairPyramidData()
    }

    override fun drawPyramid(activePlayerList: Array<Player>, pairCountsHashMap: HashMap<Pair<Int, Int>, PyramidInfo>) {
        if(pyramidView != null){
            main_layout.removeView(pyramidView)
        }
        pyramidView = PyramidView(this@PairPyramidActivity, activePlayerList, pairCountsHashMap)
        main_layout.addView(pyramidView, INSERT_INDEX)
    }
}

