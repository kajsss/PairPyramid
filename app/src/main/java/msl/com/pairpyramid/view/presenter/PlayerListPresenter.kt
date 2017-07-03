package msl.com.pairpyramid.view.presenter

import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Player

class PlayerListPresenter constructor(override var view : PlayerListContract.View) : PlayerListContract.Presenter {

    lateinit override var playerList: List<Player>

    override fun loadPlayerList(updateAdapter: (List<Player>) -> Unit) {
        playerList = PlayerDao(view.getContext()).selectAllPlayerList()!!
        updateAdapter(playerList)
    }
}