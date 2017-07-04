package msl.com.pairpyramid.model

import java.text.SimpleDateFormat
import java.util.*


data class Partner(var player_1: Int, var player_2: Int){

    var id : String = UUID.randomUUID().toString()
    var createDate : String = SimpleDateFormat("yyyyMMddhhmmss").format(Date())

    init {
        var temp : Int
        if(player_1 > player_2){  // 순서유지
            temp = player_1
            player_1 = player_2
            player_2 = temp
        }
    }
}