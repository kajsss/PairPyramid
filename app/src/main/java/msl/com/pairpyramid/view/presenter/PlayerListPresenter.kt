package msl.com.pairpyramid.view.presenter

import msl.com.pairpyramid.database.MockDatabase
import msl.com.pairpyramid.model.Player

class PlayerListPresenter constructor(override var view : PlayerListContract.View) : PlayerListContract.Presenter {

    lateinit override var playerList: ArrayList<Player>

    override fun loadPlayerList(updateAdapter: (ArrayList<Player>) -> Unit) {
        playerList = MockDatabase().playerList
        updateAdapter(playerList)
    }
}