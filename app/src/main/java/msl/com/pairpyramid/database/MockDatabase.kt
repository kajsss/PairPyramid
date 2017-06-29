package msl.com.pairpyramid.database

import com.google.common.collect.Lists
import msl.com.pairpyramid.model.Partner
import msl.com.pairpyramid.model.Player


class MockDatabase  {

    constructor()

    var playerList: ArrayList<Player>
    var partnerList: ArrayList<Partner> = ArrayList()

    init {
        var player1 = Player("Player1", "player1@gmail.com")
        var player2 = Player("Player2", "player2@gmail.com")
        var player3 = Player("Player3", "player2@gmail.com")
        var player4 = Player("Player4", "player2@gmail.com")
        var player5 = Player("Player5", "player2@gmail.com")
        var player6 = Player("Player6", "player2@gmail.com")
        var player7 = Player("Player7", "player2@gmail.com")
        var player8 = Player("Player8", "player2@gmail.com")

        playerList = Lists.newArrayList(player1, player2, player3, player4, player5, player6, player7, player8)

        var partner1 = Partner()
        partner1.addPlayer(player1,player2)

        var partner2 = Partner()
        partner2.addPlayer(player3,player4)

        var partner3 = Partner()
        partner3.addPlayer(player5,player6)

        var partner4 = Partner()
        partner4.addPlayer(player7,player8)

        var partner5 = Partner()
        partner5.addPlayer(player1,player2)

        var partner6 = Partner()
        partner6.addPlayer(player3,player8)

        var partner7 = Partner()
        partner7.addPlayer(player5,player6)

        var partner8 = Partner()
        partner8.addPlayer(player4,player7)

        partnerList = Lists.newArrayList(partner1, partner2, partner3, partner4, partner5, partner6, partner7, partner8)
    }

}