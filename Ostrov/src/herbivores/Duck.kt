package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Duck as DuckConfig
import com.javarush.island.entities.*

class Duck : Herbivore(
    weight = DuckConfig.WEIGHT,
    maxPopulationPerCell = DuckConfig.MAX_POPULATION,
    speed = DuckConfig.SPEED,
    foodRequired = DuckConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Duck()
    }
} 