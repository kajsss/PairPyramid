package msl.com.pairpyramid.model

import java.util.*


data class Partner(var player_1 :String, var player_2 : String) {

    var id : String = UUID.randomUUID().toString()
    var createDate : Date = Date()
}