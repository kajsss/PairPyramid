package msl.com.pairpyramid.view.presenter.entry

import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Player

class MakeEntryPresenter constructor(override var view : MakeEntryContract.View) : MakeEntryContract.Presenter {

    lateinit override var playerList: List<Player>

    override fun loadPlayerList(updateAdapter: (List<Player>) -> Unit) {
        playerList = PlayerDao(view.getContext()).selectAllPlayerList()!!
        updateAdapter(playerList)
    }
    override fun matchingPartners(playerList: List<Player>) {

    }
}