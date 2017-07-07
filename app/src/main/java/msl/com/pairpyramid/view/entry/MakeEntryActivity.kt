package msl.com.pairpyramid.view.entry

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_make_entry.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.view.custom.adapter.PlayerListAdapter
import msl.com.pairpyramid.view.custom.adapter.PlayerListItemTouchHelperCallback
import msl.com.pairpyramid.view.player.AddPlayerActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MakeEntryActivity : AppCompatActivity(), MakeEntryContract.View {


    lateinit var makeEntryPresenter: MakeEntryPresenter
    lateinit var playerListAdapter: PlayerListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_entry)

        playerListAdapter = PlayerListAdapter(this)
        playerRecyclerView.layoutManager = LinearLayoutManager(this)

        makeEntryPresenter = MakeEntryPresenter(this)
        playerRecyclerView.adapter = playerListAdapter
        var helper = ItemTouchHelper(PlayerListItemTouchHelperCallback(playerListAdapter))
        helper.attachToRecyclerView(playerRecyclerView)

        makeEntryPresenter.loadPlayerList { playerList ->
            playerListAdapter.item = playerList
            playerListAdapter.notifyDataSetChanged()
        }

        findViewById(R.id.btn_cancel).onClick {
            moveToMainActivity()
        }

        findViewById(R.id.btn_add).onClick {
            moveToAddPlayerActivity()
        }

        findViewById(R.id.btn_save).onClick {
            var matchingPartners = makeEntryPresenter.matchingPartners(playerListAdapter.item!!.filter { it.checked == true })
            showMatchingResultPopup(matchingPartners)
        }
    }

    private fun showMatchingResultPopup(matchingPartners: List<Partner>) {

        alert {
            title = "Matching Result"
            customView {
                verticalLayout {
                    padding = 80
                    matchingPartners.forEach { partner ->
                        textView {
                            text = makeEntryPresenter.getPartnerText(partner)
                        }
                    }
                    negativeButton("Rematching") {  }
                    positiveButton("Ok") {
                        makeEntryPresenter.insertPartners(matchingPartners)
                        moveToMainActivity()
                    }
                }
            }
        }.show()

    }



    fun moveToMainActivity() {
        finish()
    }

    fun moveToAddPlayerActivity() {

        val  playerList = PlayerDao(this).selectAllPlayerList()
        if( playerList.size < 8) startActivity<AddPlayerActivity>()
        else toast("Maximum player number is 8 !")

    }

    override fun getContext(): Context {
        return this@MakeEntryActivity
    }

}