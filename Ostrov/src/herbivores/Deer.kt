package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Deer as DeerConfig
import com.javarush.island.entities.*

class Deer : Herbivore(
    weight = DeerConfig.WEIGHT,
    maxPopulationPerCell = DeerConfig.MAX_POPULATION,
    speed = DeerConfig.SPEED,
    foodRequired = DeerConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Deer()
    }
} 