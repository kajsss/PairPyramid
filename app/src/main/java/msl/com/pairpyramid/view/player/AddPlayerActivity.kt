package msl.com.pairpyramid.view.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_player.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.view.entry.AddPlayerContract
import msl.com.pairpyramid.view.entry.AddPlayerPresenter
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class AddPlayerActivity : AppCompatActivity(), AddPlayerContract.View{


    val REQUEST_IMAGE_CAPTURE = 1
    var mBitmapImage : Bitmap? = null

    lateinit var addPlayerPresenter: AddPlayerPresenter


    override fun getContext(): Context {
        return this@AddPlayerActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_player)
        addPlayerPresenter = AddPlayerPresenter(this)

        btn_cancel.onClick {
            finish()
        }

        btn_save.onClick {
            if( edit_name.length()>0 && edit_email.length() > 0){
                addPlayerPresenter.addPlayer( edit_name.text.toString(), edit_email.text.toString() , mBitmapImage)
            }else{
                toast("Name or Email is empty.")
            }
        }

        img_picture.onClick {
            takePicture()
        }
    }

    override fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data.extras
            val imageBitmap = extras.get("data") as Bitmap
            mBitmapImage = imageBitmap
            img_picture.imageTintList = null
            img_picture.imageBitmap = imageBitmap
        }
    }

    override fun showErrorMessage(errorCode : Int) {
        when(errorCode){
            AddPlayerContract.View.DUPLICATE_ERROR_MESSAGE -> toast("Duplicate Email Address !")
            AddPlayerContract.View.INSERT_ERROR_MESSAGE -> toast("Failed. Insert Player !")
        }

    }
    override fun showCompleteMessage() {
        toast("Insert complete !")
        finish()
    }

}
