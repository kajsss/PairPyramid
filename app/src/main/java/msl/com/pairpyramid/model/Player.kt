package msl.com.pairpyramid.model

import java.util.*

data class Player constructor(var name : String, var email : String){
    var id : String = UUID.randomUUID().toString()
}