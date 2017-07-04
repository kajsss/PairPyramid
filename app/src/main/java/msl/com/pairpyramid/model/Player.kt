package msl.com.pairpyramid.model

data class Player constructor(var id : Int, var name : String, var email : String){
    var useYn : Boolean = true
    var checked : Boolean = true
}