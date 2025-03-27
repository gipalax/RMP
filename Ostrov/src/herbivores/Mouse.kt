package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Mouse as MouseConfig
import com.javarush.island.entities.*
import com.javarush.island.entities.animals.herbivores.*

class Mouse : Herbivore(
    weight = MouseConfig.WEIGHT,
    maxPopulationPerCell = MouseConfig.MAX_POPULATION,
    speed = MouseConfig.SPEED,
    foodRequired = MouseConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Mouse()
    }
} 