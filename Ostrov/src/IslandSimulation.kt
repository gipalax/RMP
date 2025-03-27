package com.javarush.island.simulation

import com.javarush.island.entities.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Callable

class IslandSimulation(
    private val island: Island,
    private val simulationPeriod: Long = 1000L // период в миллисекундах
) {
    private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private val animalProcessor: ExecutorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    )
    private var isRunning = false
    private var periodCounter = 0

    fun start() {
        if (isRunning) return
        isRunning = true

        // Запускаем единый жизненный цикл
        scheduler.scheduleAtFixedRate({
            if (!isRunning) {
                scheduler.shutdown()
                animalProcessor.shutdown()
                return@scheduleAtFixedRate
            }

            println("\n=== Период симуляции ${++periodCounter} ===\n")
            
            synchronized(this) {
                // Сначала все животные пытаются поесть
                val eatingTasks = island.getAllLocations().flatMap { location ->
                    location.getAnimals().values.flatten()
                        .filter { it.isAlive }
                        .map { animal ->
                            Callable {
                                val prey = animal.eat(location)
                                prey?.let { eaten ->
                                    when (eaten) {
                                        is Animal -> {
                                            EventLogger.logEating(animal, eaten)
                                            location.removeAnimal(eaten)
                                        }
                                        is Plant -> {
                                            EventLogger.logEating(animal, eaten)
                                            location.removePlant(eaten)
                                        }
                                        else -> {
                                            // Неизвестный тип добычи, просто логируем
                                            EventLogger.logEating(animal, eaten)
                                        }
                                    }
                                }
                            }
                        }
                }
                animalProcessor.invokeAll(eatingTasks)

                // Затем размножаются
                val reproductionTasks = island.getAllLocations().flatMap { location ->
                    location.getAnimals().values.flatten()
                        .filter { it.isAlive }
                        .map { animal ->
                            Callable {
                                val offspring = animal.reproduce(location)
                                offspring?.let { newAnimal ->
                                    location.addAnimal(newAnimal)
                                    EventLogger.logBirth(animal, newAnimal)
                                }
                            }
                        }
                }
                animalProcessor.invokeAll(reproductionTasks)

                // Растения растут
                processPlants()

                // Потом все перемещаются
                val movementTasks = island.getAllLocations().flatMap { location ->
                    location.getAnimals().values.flatten()
                        .filter { it.isAlive }
                        .map { animal ->
                            Callable {
                                val newLocation = animal.move(location, island)
                                if (newLocation != location) {
                                    location.removeAnimal(animal)
                                    newLocation.addAnimal(animal)
                                }
                            }
                        }
                }
                animalProcessor.invokeAll(movementTasks)

                // В конце проверяем, кто умер от голода
                val hungryTasks = island.getAllLocations().flatMap { location ->
                    location.getAnimals().values.flatten()
                        .filter { it.isAlive }
                        .map { animal ->
                            Callable {
                                if (animal.isHungry()) {
                                    animal.die()
                                    EventLogger.logDeath(animal)
                                }
                            }
                        }
                }
                animalProcessor.invokeAll(hungryTasks)

                // Выводим статистику
                println("\nСтатистика за период $periodCounter:")
                println("=".repeat(30))
                printStatistics()
                println("=".repeat(30))
                println()

                // Добавляем задержку для лучшей читаемости
                Thread.sleep(100)
            }
        }, 0, simulationPeriod, TimeUnit.MILLISECONDS)
    }

    fun stop() {
        isRunning = false
        scheduler.shutdown()
        animalProcessor.shutdown()
    }

    private fun processPlants() {
        val plantTasks = island.getAllLocations().flatMap { location ->
            location.getPlants().values.flatten()
                .filter { it.isAlive }
                .map { plant ->
                    Callable {
                        val offspring = plant.reproduce(location)
                        offspring?.let { newPlant ->
                            location.addPlant(newPlant)
                        }
                    }
                }
        }
        animalProcessor.invokeAll(plantTasks)
    }

    private fun printStatistics() {
        val stats = island.getStatistics()
        stats.entries
            .filter { it.value > 0 }  // Показываем только существующих животных
            .sortedBy { it.key }      // Сортируем по алфавиту
            .forEach { (type, count) ->
                val emoji = when(type) {
                    "Wolf" -> "🐺"
                    "Snake" -> "🐍"
                    "Fox" -> "🦊"
                    "Bear" -> "🐻"
                    "Eagle" -> "🦅"
                    "Horse" -> "🐎"
                    "Deer" -> "🦌"
                    "Rabbit" -> "🐇"
                    "Mouse" -> "🐁"
                    "Goat" -> "🐐"
                    "Sheep" -> "🐑"
                    "Boar" -> "🐗"
                    "Buffalo" -> "🐃"
                    "Duck" -> "🦆"
                    "Caterpillar" -> "🐛"
                    "Plant" -> "🌿"
                    else -> "❓"
                }
                println("$emoji $type: $count")
            }
    }
} 