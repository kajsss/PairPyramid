package msl.com.pairpyramid.view.presenter

import android.content.Context
import msl.com.pairpyramid.model.Player

interface PlayerListContract {

    interface Presenter {
        var view : View
        var playerList : List<Player>

        fun loadPlayerList(updateAdapter : (List<Player>) -> Unit)
    }

    interface View {
        fun getContext() : Context
    }
}