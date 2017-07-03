package msl.com.pairpyramid.view.presenter.entry

import android.content.Context
import msl.com.pairpyramid.model.Player

interface MakeEntryContract {

    interface Presenter {
        var view : MakeEntryContract.View
        var playerList : List<Player>

        fun loadPlayerList(updateAdapter : (List<Player>) -> Unit)
        fun matchingPartners(playerList : List<Player>)
    }

    interface View {
        fun getContext() : Context
    }
}