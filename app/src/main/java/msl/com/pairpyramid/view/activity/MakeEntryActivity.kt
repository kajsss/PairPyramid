package msl.com.pairpyramid.view.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_make_entry.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.view.adapter.PlayerListAdapter
import msl.com.pairpyramid.view.presenter.entry.MakeEntryContract
import msl.com.pairpyramid.view.presenter.entry.MakeEntryPresenter

class MakeEntryActivity : AppCompatActivity(), MakeEntryContract.View {

    lateinit var playerListPresenter : MakeEntryPresenter
    lateinit var playerListAdapter : PlayerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_entry)

        playerListAdapter = PlayerListAdapter()
        playerRecyclerView.layoutManager = LinearLayoutManager(this)
        playerRecyclerView.adapter = playerListAdapter

        playerListPresenter = MakeEntryPresenter(this)
        playerListPresenter.loadPlayerList { playerList ->
            playerListAdapter.apply { item = playerList }
            playerListAdapter.notifyDataSetChanged()
        }

        findViewById(R.id.btn_cancel).setOnClickListener {
            moveToMainActivity()
        }


    }

    fun moveToMainActivity() {
        finish()
    }

    override fun getContext(): Context {
        return this@MakeEntryActivity
    }
}