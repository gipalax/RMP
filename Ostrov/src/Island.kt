package com.javarush.island.entities

class Island(val width: Int, val height: Int) {
    private val locations: Array<Array<Location>> = Array(width) { x ->
        Array(height) { y ->
            Location(x, y)
        }
    }

    fun getLocation(x: Int, y: Int): Location {
        return locations[x][y]
    }

    fun getAllLocations(): List<Location> {
        return locations.flatten()
    }

    fun addAnimal(animal: Animal, x: Int, y: Int) {
        locations[x][y].addAnimal(animal)
    }

    fun addPlant(plant: Plant, x: Int, y: Int) {
        locations[x][y].addPlant(plant)
    }

    fun getStatistics(): Map<String, Int> {
        val stats = mutableMapOf<String, Int>()

        getAllLocations().forEach { location ->
            location.getAnimals().forEach { (type, animals) ->
                val key = type.simpleName ?: "Unknown"
                stats[key] = (stats[key] ?: 0) + animals.size
            }

            location.getPlants().forEach { (type, plants) ->
                val key = type.simpleName ?: "Unknown"
                stats[key] = (stats[key] ?: 0) + plants.size
            }
        }

        return stats
    }

    fun getPossibleMoves(currentLocation: Location, speed: Int): List<Location> {
        val possibleMoves = mutableListOf<Location>()
        val currentX = currentLocation.x
        val currentY = currentLocation.y

        for (x in maxOf(0, currentX - speed)..minOf(width - 1, currentX + speed)) {
            for (y in maxOf(0, currentY - speed)..minOf(height - 1, currentY + speed)) {
                if (x != currentX || y != currentY) {
                    possibleMoves.add(getLocation(x, y))
                }
            }
        }

        return possibleMoves
    }
} 