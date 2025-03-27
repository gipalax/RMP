package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Sheep as SheepConfig
import com.javarush.island.entities.*

class Sheep : Herbivore(
    weight = SheepConfig.WEIGHT,
    maxPopulationPerCell = SheepConfig.MAX_POPULATION,
    speed = SheepConfig.SPEED,
    foodRequired = SheepConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Sheep()
    }
} 