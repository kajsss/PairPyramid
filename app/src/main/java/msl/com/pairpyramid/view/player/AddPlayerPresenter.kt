package msl.com.pairpyramid.view.entry

import android.graphics.Bitmap
import msl.com.pairpyramid.database.dao.PlayerDao
import msl.com.pairpyramid.model.Player


class AddPlayerPresenter constructor(override var view: AddPlayerContract.View) : AddPlayerContract.Presenter {


    override fun addPlayer(name: String, email: String, picture: Bitmap?) {

        val playerDao = PlayerDao(view.getContext())
        val selectAllPlayerList = playerDao.selectAllPlayerList()
        //Duplicate email Check

        selectAllPlayerList?.forEach {
            if (it.email.equals(email)) {
                view.showErrorMessage(AddPlayerContract.View.DUPLICATE_ERROR_MESSAGE)
                return@addPlayer
            }
        }

        var newId = if (selectAllPlayerList == null) 1 else selectAllPlayerList.size + 1
        var newPlayer = Player(newId, name, email)
        if (picture != null) newPlayer.picture = picture

        val result = playerDao.insertPlayer(newPlayer)
        if(result > 0){
                view.showCompleteMessage()
        }else{
                view.showErrorMessage(AddPlayerContract.View.INSERT_ERROR_MESSAGE)
        }


    }
}