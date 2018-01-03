package org.kutulu.erbium

import android.app.Activity
import android.os.Bundle

import org.kutulu.erbium.models.Game
import org.kutulu.erbium.models.Player

class GameActivity : Activity() {

    private var game: Game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_game)

        if (savedInstanceState == null) {
            val count = this.intent.getIntExtra("players", 0)
            val life = this.intent.getIntExtra("life", 0)
            val commander = this.intent.getBooleanExtra("isCommander", false)

            this.game = Game(IntRange(1, count).map { n -> Player("Player $n", life) }, commander)

            var transaction = this.fragmentManager.beginTransaction()

            var index = 0
            for (player in game.players) {
                val id = "player_$index"
                val fragment = PlayerFragment.newInstance(index, player.name, life, if (commander) count - 1 else 0)
                transaction = transaction.add(R.id.playerGrid, fragment, id)
            }

            transaction.commit()
        }
    }
}
