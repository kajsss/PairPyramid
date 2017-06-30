package msl.com.pairpyramid.view.presenter

import msl.com.pairpyramid.model.Player

interface PlayerListContract {

    interface Presenter {
        var view : View
        var playerList : ArrayList<Player>

        fun loadPlayerList(updateAdapter : (ArrayList<Player>) -> Unit)
    }

    interface View {
        fun moveToMainActivity()
    }
}