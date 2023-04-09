package com.bignerdranch.nyethack
import com.bignerdranch.nyethack.Game.currentRoom

open class Room (val name: String) {
   open val dangerLevel = 5
    var monster:Monster?= Goblin ()
    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel\n" +
            "Creature: ${monster?.description?:"none"}"

    open fun load ()="Nothing mush to see here..."
    open fun place (){
        when (name) {
            "Black Room"   ->    Navigation.BLACKROOM
            "Gemeric Room"  ->  Navigation . GEMERICROOM
            "Long Corridor"   ->  Navigation.LONGCORRIDOR
            "Tavern"    ->      Navigation . TAVERN
            "Town Square" ->  Navigation.TOWNSQUAER
        }
    }
open fun Room.configurePitGoblin(block: Room.(Goblin) -> Goblin): Room{
    val goblin = block(Goblin("Pit Goblin", description = "An Evil Pit Goblin"))
    currentRoom.configurePitGoblin { goblin ->
        goblin.healthPoints = dangerLevel * 3
        goblin
    }
    monster = goblin
    return this
}

}


class TownSquare: Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"
    override fun load() = "The villagers rally and cheer as you enter!\n ${ringBell()}"
    private fun ringBell()= "The bell tower announces your arrival. $bellSound"
}
