package msl.com.pairpyramid.database.dao

import android.content.Context
import android.graphics.Bitmap
import msl.com.pairpyramid.database.DatabaseHelper
import msl.com.pairpyramid.database.parser.PlayerRowParser
import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import java.io.ByteArrayOutputStream


class PlayerDao(var context: Context) {

    val database: DatabaseHelper
        get() = DatabaseHelper.getInstance(context.applicationContext)

    fun selectAllPlayerList(): List<Player> {

        var playerList: List<Player> = arrayListOf()
        database.use {
            playerList = select("Player").whereArgs("useYn=1").parseList(PlayerRowParser())
        }
        return playerList
    }

    fun selectPlayerNameById(id: Int): String {

        var name: String = ""
        database.use {
            select("Player", "name").whereArgs("id = $id").exec {
                moveToNext()
                name = getString(0)
            }
        }
        return name
    }
    fun selectMaxId(): Int {

        var maxId = 0
        database.use {
            select("Player", "id").orderBy("id", SqlOrderDirection.DESC).exec {
                moveToNext()
                maxId = getInt(0)
                println(maxId)
            }
        }
        return maxId+1
    }


    fun insertPlayer(player: Player): Long {

        if (player.picture != null) {
            var bitmap = player.picture as Bitmap
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)

            return database.writableDatabase.insert("Player", "id" to player.id, "name" to player.name, "email" to player.email, "useYn" to 1, "picture" to bos.toByteArray())

        } else {

            return database.writableDatabase.insert("Player", "id" to player.id, "name" to player.name, "email" to player.email, "useYn" to 1)

        }

    }


    fun removePlayer(playerId: String): Int {
        return database.writableDatabase.update("Player", "useYn" to "0").whereArgs("id=${playerId}").exec()
    }

}
