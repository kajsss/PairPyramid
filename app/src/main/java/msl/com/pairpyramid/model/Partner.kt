package msl.com.pairpyramid.model

import java.util.*


class Partner{

    var id : String = UUID.randomUUID().toString()
    var player_1 : Int=0
    var player_2 : Int=0
    var createDate : Date = Date()

    constructor(){}
    constructor(player_1 :Int, player_2 : Int){
        if(player_1 > player_2){  // 순서유지
            this.player_1 = player_2
            this.player_2 = player_1
        } else {
            this.player_1 = player_1
            this.player_2 = player_2
        }
    }

    override fun toString(): String {
        return "(" + id + " -> " + player_1 + "," + player_2 + ")";
    }
}