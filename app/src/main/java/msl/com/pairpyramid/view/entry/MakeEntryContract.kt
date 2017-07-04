package msl.com.pairpyramid.view.entry

import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player

interface MakeEntryContract {

    interface Presenter {
        var view : MakeEntryContract.View
        var playerList : List<Player>

        fun loadPlayerList(updateAdapter : (List<Player>) -> Unit)
        fun matchingPartners(players: List<Player>): List<Partner>
    }

    interface View {
        fun getContext() : android.content.Context
    }
}