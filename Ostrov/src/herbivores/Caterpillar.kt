package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Caterpillar as CaterpillarConfig
import com.javarush.island.entities.*

class Caterpillar : Herbivore(
    weight = CaterpillarConfig.WEIGHT,
    maxPopulationPerCell = CaterpillarConfig.MAX_POPULATION,
    speed = CaterpillarConfig.SPEED,
    foodRequired = CaterpillarConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Caterpillar()
    }
} 