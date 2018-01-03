package org.kutulu.erbium

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import kotlinx.android.synthetic.main.fragment_player.*
import org.kutulu.erbium.models.Counter
import org.kutulu.erbium.models.Player

class PlayerFragment : Fragment() {
    private val player: Player = Player("Player 0", 0)
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            this.player.life = arguments.getInt(ARG_INIT_LIFE)
            val commanders = arguments.getInt(ARG_INIT_COMMANDERS)
            repeat(commanders) {
                this.player.counters.add(Counter(it, it))
            }

            this.index = arguments.getInt(ARG_VIEW_INDEX)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view != null) {
            val params = GridLayout.LayoutParams(GridLayout.spec(index % 2), GridLayout.spec(index / 2))
            view.layoutParams = params
            view.rotation = if (index % 2 == 0) 90f else -90f

            playerName.text = this.player.name
            playerLife.text = this.player.life.toString()
        }
    }

    companion object {
        private val ARG_VIEW_INDEX = "view_index"
        private val ARG_INIT_NAME = "initial_name"
        private val ARG_INIT_LIFE = "initial_life"
        private val ARG_INIT_COMMANDERS = "initial_commanders"

        fun newInstance(index: Int, name: String, life: Int, commanders: Int): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putInt(ARG_VIEW_INDEX, index)
            args.putString(ARG_INIT_NAME, name)
            args.putInt(ARG_INIT_LIFE, life)
            args.putInt(ARG_INIT_COMMANDERS, commanders)
            fragment.arguments = args
            return fragment
        }
    }
}
