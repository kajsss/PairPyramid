package msl.com.pairpyramid.model

import java.util.*

data class Player constructor(var email : String, var name : String){
    var id : String = UUID.randomUUID().toString()
}