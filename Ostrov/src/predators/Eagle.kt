package com.javarush.island.entities.animals.predators

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Eagle as EagleConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Eagle : Predator(
    weight = EagleConfig.WEIGHT,
    maxPopulationPerCell = EagleConfig.MAX_POPULATION,
    speed = EagleConfig.SPEED,
    foodRequired = EagleConfig.FOOD_REQUIRED,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 90
        Mouse::class.java.simpleName -> 90
        Duck::class.java.simpleName -> 80
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Eagle()
    }
} 