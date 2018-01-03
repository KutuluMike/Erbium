package org.kutulu.erbium.models

data class Player(var name: String, var life: Int, val counters: MutableList<Counter> = mutableListOf())