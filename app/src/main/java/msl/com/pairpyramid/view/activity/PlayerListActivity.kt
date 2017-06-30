package msl.com.pairpyramid.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_player_list.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.view.adapter.PlayerListAdapter
import msl.com.pairpyramid.view.presenter.PlayerListContract
import msl.com.pairpyramid.view.presenter.PlayerListPresenter

class PlayerListActivity : AppCompatActivity(), PlayerListContract.View {

    lateinit var playerListPresenter : PlayerListPresenter
    lateinit var playerListAdapter : PlayerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)

        playerListAdapter = PlayerListAdapter()
        playerRecyclerView.layoutManager = LinearLayoutManager(this)
        playerRecyclerView.adapter = playerListAdapter

        playerListPresenter = PlayerListPresenter(this)
        playerListPresenter.loadPlayerList() {
            playerListAdapter.apply { item = it }
            playerListAdapter.notifyDataSetChanged()
        }

        findViewById(R.id.btn_cancel).setOnClickListener { v ->
            moveToMainActivity()
        }


    }


    override fun moveToMainActivity() {
        finish()
    }



}