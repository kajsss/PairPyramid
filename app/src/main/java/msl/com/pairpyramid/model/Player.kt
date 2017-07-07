package msl.com.pairpyramid.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory

data class Player constructor(var id : Int, var name : String, var email : String){

    constructor(id : Int,name : String, email : String, useYn : Boolean ) : this(id,name,email){
        this.useYn = useYn
    }

    constructor(id : Int,name : String, email : String, useYn : Boolean , pictureBytes: ByteArray ) : this(id,name,email,useYn){
        this.picture = BitmapFactory.decodeByteArray(pictureBytes, 0 ,pictureBytes.size)
    }
    var useYn : Boolean = true
    var checked : Boolean = true
    var picture : Bitmap? = null
    var keep: Boolean = false
}