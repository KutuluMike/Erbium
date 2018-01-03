package org.kutulu.erbium.models

data class Game(val players: List<Player> = listOf(), val isCommander: Boolean = false)