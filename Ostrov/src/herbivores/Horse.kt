package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Horse as HorseConfig
import com.javarush.island.entities.*

class Horse : Herbivore(
    weight = HorseConfig.WEIGHT,
    maxPopulationPerCell = HorseConfig.MAX_POPULATION,
    speed = HorseConfig.SPEED,
    foodRequired = HorseConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Horse()
    }
} 