package msl.com.pairpyramid.view.entry

import android.graphics.Bitmap

interface AddPlayerContract {

    interface Presenter {
        var view : AddPlayerContract.View
        fun addPlayer(name : String, email : String, picture : Bitmap?)
    }

    interface View {

        companion object{
            val DUPLICATE_ERROR_MESSAGE = 1
            val INSERT_ERROR_MESSAGE = 2
        }


        fun getContext() : android.content.Context
        fun takePicture()
        fun showErrorMessage(errorCode : Int)
        fun showCompleteMessage()
    }
}