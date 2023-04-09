package com.bignerdranch.nyethack

import java.lang.IllegalStateException
import kotlin.system.exitProcess

fun main() {
    Game.play()
    }
object Game {
    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            // Состояние игрока
            printPlayerStatus(player)
            print("> Enter your command:  ")
            println(GameInput(readLine()).processCommand())
        }
    }


    private val player = Player("Madrigal")
   var currentRoom: Room = TownSquare()


    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Black Room")),
        listOf(Room("Long Corridor"), Room("Gemeric Room")))

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n ${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput."
        }

    private fun mapPlace() {
        var navigation11 = when (currentRoom.name) {
            "Black Room" -> Navigation.BLACKROOM.plaseDrawing
            "Gemeric Room" -> Navigation.GEMERICROOM.plaseDrawing
            "Long Corridor" -> Navigation.LONGCORRIDOR.plaseDrawing
            "Tavern" -> Navigation.TAVERN.plaseDrawing
            "Town Square" -> Navigation.TOWNSQUAER.plaseDrawing
            else -> {" NOT IS IT"}
        }
         println("$navigation11")

    }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothing here to fight."

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage !")
        println("${player.name} did ${player.attack(monster)} damage !")
        if(player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing.")
            exitProcess(0)
        }

        if(monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(arg:String?){
        private val input = arg?:""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{" "})

        fun processCommand() = when (command.toLowerCase()){
            "fight" -> fight()
            "map" -> mapPlace()
            "move" -> move(argument)
            else -> commandNotFound()
        }

        private fun commandNotFound()="I'm not quite sure what you're trying to do!"
    }
    init{
        println("Welcome, adventure.")
        player.castFireball()
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }
    }










