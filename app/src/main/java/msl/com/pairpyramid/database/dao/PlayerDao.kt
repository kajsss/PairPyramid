package msl.com.pairpyramid.database.dao

import android.content.Context
import android.graphics.Bitmap
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PlayerRowParser
import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.io.ByteArrayOutputStream


class PlayerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context.applicationContext)

    fun selectAllPlayerList(): List<Player>? {

        var playerList: List<Player>? = null
        database.use {
            playerList = select("Player").parseList(PlayerRowParser())
        }
        return playerList
    }


    fun insertPlayer(player: Player): Long {

        if(player.picture != null){
            var bitmap = player.picture as Bitmap
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)

            return database.writableDatabase.insert("Player", "id" to player.id, "name" to player.name, "email" to player.email, "useYn" to 1, "picture" to bos.toByteArray())

        }else{

            return database.writableDatabase.insert("Player", "id" to player.id, "name" to player.name, "email" to player.email, "useYn" to 1)

        }

    }

}