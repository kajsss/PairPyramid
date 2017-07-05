package msl.com.pairpyramid.view.entry

import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player

interface MakeEntryContract {

    interface Presenter {
        var view : MakeEntryContract.View
        var playerList : List<Player>

        fun loadPlayerList(updateAdapter : (List<Player>) -> Unit)
        fun matchingPartners(checkedPlayerList: List<Player>): List<Partner>
        fun insertPartners(matchingPartners: List<Partner>)
        fun getPartnerText(partner: Partner): String
    }

    interface View {
        fun getContext() : android.content.Context
    }
}