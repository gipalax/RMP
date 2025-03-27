package com.javarush.island.entities

import java.util.concurrent.ThreadLocalRandom

abstract class Predator(
    weight: Double,
    maxPopulationPerCell: Int,
    speed: Int,
    foodRequired: Double,
    private val preyTypes: List<String>
) : Animal(weight, maxPopulationPerCell, speed, foodRequired) {

    // Вероятности поедания для каждого типа жертвы
    protected abstract fun getEatingProbability(preyType: String): Int

    override fun eat(location: Location): Any? {
        if (!isAlive || currentFood >= foodRequired * 0.8) return null

        // Ищем подходящую добычу
        val animals = location.getAnimals()
            .mapKeys { it.key.simpleName }
        val possiblePrey = animals.entries
            .filter { (type, _) -> type in preyTypes }
            .flatMap { (type, preyList) -> 
                // Фильтруем по вероятности поедания
                preyList.filter { prey -> 
                    prey.isAlive && 
                    ThreadLocalRandom.current().nextInt(100) < getEatingProbability(type)
                }
            }
            .shuffled()
            .firstOrNull() ?: return null

        possiblePrey.die("охота")
        currentFood = minOf(foodRequired, currentFood + possiblePrey.weight)
        return possiblePrey
    }

    override fun reproduce(location: Location): Animal? {
        if (currentFood < foodRequired * 0.8) return null  // Размножаемся только если достаточно сыты

        val animals = location.getAnimals()
            .mapKeys { it.key.simpleName }
        val sameTypeAnimals = animals[javaClass.simpleName] ?: emptyList()
        if (sameTypeAnimals.size >= maxPopulationPerCell) return null

        val offspring = createOffspring()
        EventLogger.logBirth(this, offspring)
        return offspring
    }

    protected abstract fun createOffspring(): Animal
}