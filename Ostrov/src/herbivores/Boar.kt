package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Boar as BoarConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Boar : Herbivore(
    weight = BoarConfig.WEIGHT,
    maxPopulationPerCell = BoarConfig.MAX_POPULATION,
    speed = BoarConfig.SPEED,
    foodRequired = BoarConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Boar()
    }
} 