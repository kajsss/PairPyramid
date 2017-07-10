package msl.com.pairpyramid.view.pyramid

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.model.Player
import msl.com.pairpyramid.model.PyramidInfo
import msl.com.pairpyramid.view.custom.layout.PyramidView
import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PairPyramidActivity : AppCompatActivity(), PairPyramidContract.View {

    private val INSERT_INDEX = 1
    private var pyramidView : PyramidView? = null

    lateinit var pairPyramidPresenter: PairPyramidPresenter

    override fun getContext(): Context {
        return this@PairPyramidActivity
    }

    private val PYRAMID_KEY = "msl.com.pairpyramid"


    private val sharedPreferences: SharedPreferences?
        get() {
            var sharedPreferences = this@PairPyramidActivity.getSharedPreferences(PYRAMID_KEY, 0)
            return sharedPreferences
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /*var title = sharedPreferences?.getString("pyramid_title_key","")
        if( title != null && title.isEmpty()){
            showTeamNamePopup()
        }else{
            text_pyramid_title.text = "Pyramid"
        }

        text_pyramid_title.onClick{
            showTeamNamePopup()
        }*/


        pairPyramidPresenter = PairPyramidPresenter(this)

        findViewById(R.id.btn_matching).onClick {
            startActivity<MakeEntryActivity>()
        }
    }

    private fun showTeamNamePopup() {
        alert {
            title = "Insert your Team Name"
            customView {

                verticalLayout {
                    var edit_team_name = editText {
                        hint = "Team Name"
                        textSize = 24f
                    }
                    edit_team_name.imeOptions = EditorInfo.IME_ACTION_DONE

                    padding = 80
                    negativeButton("CANCEL") { }
                    positiveButton("OK") {
                        text_pyramid_title.text = edit_team_name.text.append(" Pyramid")
                    }
                }
            }
        }.show()
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

