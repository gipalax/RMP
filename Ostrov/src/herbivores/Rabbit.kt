package com.javarush.island.entities.animals.herbivores

import com.javarush.island.config.SimulationConfig.AnimalCharacteristics.Rabbit as RabbitConfig
import com.javarush.island.entities.*

class Rabbit : Herbivore(
    weight = RabbitConfig.WEIGHT,
    maxPopulationPerCell = RabbitConfig.MAX_POPULATION,
    speed = RabbitConfig.SPEED,
    foodRequired = RabbitConfig.FOOD_REQUIRED
) {
    override fun createOffspring(): Animal {
        return Rabbit()
    }
} 