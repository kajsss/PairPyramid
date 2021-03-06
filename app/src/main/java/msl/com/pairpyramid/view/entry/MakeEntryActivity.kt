package msl.com.pairpyramid.view.entry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_make_entry.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player
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

        playerListAdapter = PlayerListAdapter(this, DeleteItemListener(this))
        playerRecyclerView.layoutManager = LinearLayoutManager(this)

        makeEntryPresenter = MakeEntryPresenter(this)
        playerRecyclerView.adapter = playerListAdapter
        makeEntryPresenter.loadPlayerList()

        var helper = ItemTouchHelper(PlayerListItemTouchHelperCallback(playerListAdapter))
        helper.attachToRecyclerView(playerRecyclerView)

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


    override fun updateAdapter(playerList: List<Player>) {
        playerListAdapter.item = playerList
        playerListAdapter.notifyDataSetChanged()
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
                            textSize = 18f
                        }                    }
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

    private val REQUEST_ADD_PLAYER = 1

    fun moveToAddPlayerActivity() {

        val  playerList = PlayerDao(this).selectAllPlayerList()

        if( playerList.size < 8) startActivityForResult(Intent(this, AddPlayerActivity::class.java), REQUEST_ADD_PLAYER)
        else toast("Maximum player number is 8 !")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(REQUEST_ADD_PLAYER == requestCode) {
            makeEntryPresenter.loadPlayerList()
        }
    }

    override fun getContext(): Context {
        return this@MakeEntryActivity
    }

    class DeleteItemListener constructor (var context : Context) {
        fun doAction(userId : Int) {
            PlayerDao(context).removePlayer(userId.toString())
        }
    }

}