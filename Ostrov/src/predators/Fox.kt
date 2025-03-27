package com.javarush.island.entities.animals.predators

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Fox as FoxConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Fox : Predator(
    weight = FoxConfig.WEIGHT,
    maxPopulationPerCell = FoxConfig.MAX_POPULATION,
    speed = FoxConfig.SPEED,
    foodRequired = FoxConfig.FOOD_REQUIRED,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName,
        Caterpillar::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 70
        Mouse::class.java.simpleName -> 90
        Duck::class.java.simpleName -> 60
        Caterpillar::class.java.simpleName -> 40
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Fox()
    }
} 