package msl.com.pairpyramid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log.d
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "PairPyramidDatabase", null, 15) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createPlayerTable(db)
        createPartnerTable(db)
        defaultDataGenerate(db)
    }

    private fun createPartnerTable(db: SQLiteDatabase) {
        db.createTable("Partner", true,
                "id" to TEXT + PRIMARY_KEY,
                "player_1" to INTEGER,
                "player_2" to INTEGER,
                "create_date" to TEXT).run {
            d("#createPartner : ", "Create Table !! and run !!")

        }
    }

    private fun createPlayerTable(db: SQLiteDatabase) {
        db.createTable("Player", true,
                "id" to INTEGER + PRIMARY_KEY,
                "name" to TEXT,
                "email" to TEXT,
                "useYn" to INTEGER,
                "picture" to BLOB).run {
            d("#createPartner : ", "Create Table !! and run !!")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.dropTable("Player")
            db.dropTable("Partner")
            createPlayerTable(db)
            createPartnerTable(db)
            defaultDataGenerate(db)
        }
    }

    private fun defaultDataGenerate(db: SQLiteDatabase) {

        var player1 = Player(1, "Milo", "player1@gmail.com")
        var player2 = Player(2, "Lamos", "player2@gmail.com")
        var player3 = Player(3, "Woody", "player3@gmail.com")
        var player4 = Player(4, "Steve", "player4@gmail.com")
        var player5 = Player(5, "Dave", "player5@gmail.com")
        var player6 = Player(6, "Sue", "player6@gmail.com")
        //var player7 = Player(7, "Kafri", "player7@gmail.com")
        //var player8 = Player(8, "Hubert", "player8@gmail.com")

        var partner1 = Partner(player1.id, player2.id)
        var partner2 = Partner(player2.id, player6.id)
        var partner3 = Partner(player1.id, player3.id)
        var partner4 = Partner(player3.id, player4.id)
        //var partner5 = Partner(player7.id, player7.id)
        //var partner6 = Partner(player5.id, player8.id)
        //var partner7 = Partner(player1.id, player7.id)
        var partner8 = Partner(player2.id, player4.id)
        var partner9 = Partner(player2.id, player1.id)
        var partner0 = Partner(player1.id, player2.id)

        //Insert Player Data
        db.insert("Player", "id" to player1.id, "name" to player1.name, "email" to player1.email, "useYn" to 1)
        db.insert("Player", "id" to player2.id, "name" to player2.name, "email" to player2.email, "useYn" to 1)
        db.insert("Player", "id" to player3.id, "name" to player3.name, "email" to player3.email, "useYn" to 1)
        db.insert("Player", "id" to player4.id, "name" to player4.name, "email" to player4.email, "useYn" to 1)
        db.insert("Player", "id" to player5.id, "name" to player5.name, "email" to player5.email, "useYn" to 1)
        db.insert("Player", "id" to player6.id, "name" to player6.name, "email" to player6.email, "useYn" to 1)
        //db.insert("Player", "id" to player7.id, "name" to player7.name, "email" to player7.email, "useYn" to 1)
        //db.insert("Player", "id" to player8.id, "name" to player8.name, "email" to player8.email, "useYn" to 1)

        //Insert Partner Data
        db.insert("Partner", "id" to partner1.id, "player_1" to partner1.player_1, "player_2" to partner1.player_2, "create_date" to partner1.createDate)
        db.insert("Partner", "id" to partner2.id, "player_1" to partner2.player_1, "player_2" to partner2.player_2, "create_date" to partner2.createDate)
        db.insert("Partner", "id" to partner3.id, "player_1" to partner3.player_1, "player_2" to partner3.player_2, "create_date" to partner3.createDate)
        db.insert("Partner", "id" to partner4.id, "player_1" to partner4.player_1, "player_2" to partner4.player_2, "create_date" to partner4.createDate)
        //db.insert("Partner", "id" to partner5.id, "player_1" to partner5.player_1, "player_2" to partner5.player_2, "create_date" to partner5.createDate)
        //db.insert("Partner", "id" to partner6.id, "player_1" to partner6.player_1, "player_2" to partner6.player_2, "create_date" to partner6.createDate)
        //db.insert("Partner", "id" to partner7.id, "player_1" to partner7.player_1, "player_2" to partner7.player_2, "create_date" to partner7.createDate)
        db.insert("Partner", "id" to partner8.id, "player_1" to partner8.player_1, "player_2" to partner8.player_2, "create_date" to partner8.createDate)
        db.insert("Partner", "id" to partner9.id, "player_1" to partner9.player_1, "player_2" to partner9.player_2, "create_date" to partner9.createDate)
        db.insert("Partner", "id" to partner0.id, "player_1" to partner0.player_1, "player_2" to partner0.player_2, "create_date" to partner0.createDate)
    }
}