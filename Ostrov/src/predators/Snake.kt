package com.javarush.island.entities.animals.predators

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Snake as SnakeConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Snake : Predator(
    weight = SnakeConfig.WEIGHT,
    maxPopulationPerCell = SnakeConfig.MAX_POPULATION,
    speed = SnakeConfig.SPEED,
    foodRequired = SnakeConfig.FOOD_REQUIRED,
    preyTypes = listOf(
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Duck::class.java.simpleName,
        Caterpillar::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Rabbit::class.java.simpleName -> 20
        Mouse::class.java.simpleName -> 40
        Duck::class.java.simpleName -> 10
        Caterpillar::class.java.simpleName -> 80
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Snake()
    }
} 