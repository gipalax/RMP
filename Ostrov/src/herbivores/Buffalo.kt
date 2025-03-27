package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Buffalo as BuffaloConfig
import com.javarush.island.entities.*

class Buffalo : Herbivore(
    weight = BuffaloConfig.WEIGHT,
    maxPopulationPerCell = BuffaloConfig.MAX_POPULATION,
    speed = BuffaloConfig.SPEED,
    foodRequired = BuffaloConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Buffalo()
    }
} 