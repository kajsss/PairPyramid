package msl.com.pairpyramid.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory

data class Player constructor(var id : Int, var name : String, var email : String){

    constructor(id : Int,name : String, email : String, pictureBytes: ByteArray ) : this(id,name,email){
        this.picture = BitmapFactory.decodeByteArray(pictureBytes, 0 ,pictureBytes.size)
    }
    var useYn : Boolean = true
    var checked : Boolean = true
    var picture : Bitmap? = null
}