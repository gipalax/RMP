package com.javarush.island.entities

import java.util.concurrent.ThreadLocalRandom

abstract class Animal(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double
) {
    protected var currentFood: Double = foodRequired * 0.8
    var weight: Double = weight
        private set
    protected val maxPopulationPerCell: Int = maxPopulationPerCell
    protected val speed: Int = speed
    protected val foodRequired: Double = foodRequired
    
    var isAlive = true
        private set
    
    abstract fun eat(location: Location): Any?
    
    fun move(currentLocation: Location, island: Island): Location {
        if (!isAlive) return currentLocation
        
        val possibleMoves = island.getPossibleMoves(currentLocation, speed)
        if (possibleMoves.isEmpty()) return currentLocation
        
        val newLocation = possibleMoves.random()
        if (newLocation != currentLocation) {
            EventLogger.logMovement(this, 
                currentLocation.x, currentLocation.y,
                newLocation.x, newLocation.y)
        }
        return newLocation
    }
    
    open fun reproduce(location: Location): Animal? {
        if (!isAlive || currentFood < foodRequired * 0.8) return null
        
        val animals = location.getAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null
        
        return try {
            val constructor = this::class.constructors.first()
            constructor.call() as Animal
        } catch (e: Exception) {
            null
        }
    }
    
    fun isHungry(): Boolean {
        if (!isAlive) return false
        currentFood -= foodRequired * 0.2
        return currentFood <= 0
    }
    
    fun die(cause: String = "голод") {
        if (isAlive) {
            isAlive = false
            EventLogger.logDeath(this, cause)
        }
    }
} 