package com.javarush.island.entities.animals.predators

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Wolf as WolfConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Wolf : Predator(
    weight = WolfConfig.WEIGHT,
    maxPopulationPerCell = WolfConfig.MAX_POPULATION,
    speed = WolfConfig.SPEED,
    foodRequired = WolfConfig.FOOD_REQUIRED,
    preyTypes = listOf(
        Horse::class.java.simpleName,
        Deer::class.java.simpleName,
        Rabbit::class.java.simpleName,
        Mouse::class.java.simpleName,
        Goat::class.java.simpleName,
        Sheep::class.java.simpleName,
        Boar::class.java.simpleName,
        Buffalo::class.java.simpleName,
        Duck::class.java.simpleName
    )
) {
    override fun getEatingProbability(preyType: String): Int = when(preyType) {
        Horse::class.java.simpleName -> 10
        Deer::class.java.simpleName -> 15
        Rabbit::class.java.simpleName -> 60
        Mouse::class.java.simpleName -> 80
        Goat::class.java.simpleName -> 60
        Sheep::class.java.simpleName -> 70
        Boar::class.java.simpleName -> 15
        Buffalo::class.java.simpleName -> 10
        Duck::class.java.simpleName -> 40
        else -> 0
    }

    override fun createOffspring(): Animal {
        return Wolf()
    }
} 