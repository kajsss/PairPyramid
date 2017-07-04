package msl.com.pairpyramid.view.entry

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_make_entry.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.view.adapter.PlayerListAdapter

class MakeEntryActivity : AppCompatActivity(), MakeEntryContract.View {

    lateinit var makeEntryPresenter: MakeEntryPresenter
    lateinit var playerListAdapter: PlayerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_entry)

        playerListAdapter = PlayerListAdapter()
        playerRecyclerView.layoutManager = LinearLayoutManager(this)

        makeEntryPresenter = MakeEntryPresenter(this)
        playerRecyclerView.adapter = playerListAdapter

        makeEntryPresenter.loadPlayerList { playerList ->
            playerListAdapter.apply { item = playerList }
            playerListAdapter.notifyDataSetChanged()
        }


        btn_cancel.setOnClickListener {
            moveToMainActivity()
        }

        btn_ok.setOnClickListener {
            var matchingPartners = makeEntryPresenter.matchingPartners(playerListAdapter.item!!.filter { it.checked == true })
            matchingPartners.forEach { it ->
//                d("# " , it.toString())
            }
        }

    }

    fun moveToMainActivity() {
        finish()
    }

    override fun getContext(): Context {
        return this@MakeEntryActivity
    }

}