package msl.com.pairpyramid.model

import java.util.*


open class Partner {

    var id : String = UUID.randomUUID().toString()
    var players : ArrayList<Player> = ArrayList()
    var date : Date = Date()

    fun addPlayer(vararg player : Player){
        players.addAll(player)
    }
}