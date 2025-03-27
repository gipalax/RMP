package com.javarush.island.entities

import java.util.concurrent.ThreadLocalRandom

abstract class Herbivore(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double
) : Animal(weight, maxPopulationPerCell, speed, foodRequired) {

    override fun eat(location: Location): Any? {
        if (!isAlive || currentFood >= foodRequired * 0.8) return null

        // Пытаемся съесть растение
        val plants = location.getPlants()
        val plant = plants.values
            .flatten()
            .filter { it.isAlive }
            .shuffled()
            .firstOrNull() ?: return null

        plant.die()
        currentFood = minOf(foodRequired, currentFood + plant.weight)
        return plant
    }

    override fun reproduce(location: Location): Animal? {
        if (currentFood < foodRequired * 0.8) return null  // Размножаемся только если достаточно сыты

        val animals = location.getAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        val offspring = createOffspring()
        EventLogger.logBirth(this, offspring)
        return offspring
    }

    protected abstract fun createOffspring(): Animal
} 