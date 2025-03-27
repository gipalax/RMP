package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Goat as GoatConfig
import com.javarush.island.entities.*

class Goat : Herbivore(
    weight = GoatConfig.WEIGHT,
    maxPopulationPerCell = GoatConfig.MAX_POPULATION,
    speed = GoatConfig.SPEED,
    foodRequired = GoatConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Goat()
    }
} 