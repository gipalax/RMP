package com.javarush.island.entities

class Plant(
    val weight: Double = 1.0,
    val maxPopulationPerCell: Int = 200
) {
    var isAlive: Boolean = true

    fun reproduce(location: Location): Plant? {
        val plants = location.getPlantsByType(this::class.java)
        if (plants.size < maxPopulationPerCell) {
            val reproductionChance = 0.9 * (1.0 - plants.size.toDouble() / maxPopulationPerCell)
            val growthMultiplier = if (plants.size < maxPopulationPerCell / 4) 1.5 else 1.0
            
            if (Math.random() < reproductionChance * growthMultiplier) {
                return Plant(weight, maxPopulationPerCell)
            }
        }
        return null
    }

    fun die() {
        isAlive = false
    }
} 