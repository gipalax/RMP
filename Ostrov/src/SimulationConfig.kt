package com.javarush.island.config

object SimulationConfig {
    // Размеры острова
    const val ISLAND_WIDTH = 100
    const val ISLAND_HEIGHT = 20

    // Период симуляции в миллисекундах
    const val SIMULATION_PERIOD = 1000L

    // Характеристики животных
    object AnimalCharacteristics {
        // Хищники
        object Wolf {
            const val WEIGHT = 50.0
            const val MAX_POPULATION = 30
            const val SPEED = 3
            const val FOOD_REQUIRED = 8.0
            const val OFFSPRING_COUNT = 3
        }

        object Snake {
            const val WEIGHT = 15.0
            const val MAX_POPULATION = 30
            const val SPEED = 1
            const val FOOD_REQUIRED = 3.0
            const val OFFSPRING_COUNT = 2
        }

        object Fox {
            const val WEIGHT = 8.0
            const val MAX_POPULATION = 30
            const val SPEED = 2
            const val FOOD_REQUIRED = 2.0
            const val OFFSPRING_COUNT = 3
        }

        object Bear {
            const val WEIGHT = 500.0
            const val MAX_POPULATION = 5
            const val SPEED = 2
            const val FOOD_REQUIRED = 80.0
            const val OFFSPRING_COUNT = 1
        }

        object Eagle {
            const val WEIGHT = 6.0
            const val MAX_POPULATION = 20
            const val SPEED = 3
            const val FOOD_REQUIRED = 1.0
            const val OFFSPRING_COUNT = 2
        }

        // Травоядные
        object Horse {
            const val WEIGHT = 400.0
            const val MAX_POPULATION = 20
            const val SPEED = 4
            const val FOOD_REQUIRED = 60.0
            const val OFFSPRING_COUNT = 1
        }

        object Deer {
            const val WEIGHT = 300.0
            const val MAX_POPULATION = 20
            const val SPEED = 4
            const val FOOD_REQUIRED = 50.0
            const val OFFSPRING_COUNT = 1
        }

        object Rabbit {
            const val WEIGHT = 2.0
            const val MAX_POPULATION = 150
            const val SPEED = 2
            const val FOOD_REQUIRED = 0.45
            const val OFFSPRING_COUNT = 4
        }

        object Mouse {
            const val WEIGHT = 0.05
            const val MAX_POPULATION = 500
            const val SPEED = 1
            const val FOOD_REQUIRED = 0.01
            const val OFFSPRING_COUNT = 5
        }

        object Goat {
            const val WEIGHT = 60.0
            const val MAX_POPULATION = 140
            const val SPEED = 3
            const val FOOD_REQUIRED = 10.0
            const val OFFSPRING_COUNT = 2
        }

        object Sheep {
            const val WEIGHT = 70.0
            const val MAX_POPULATION = 140
            const val SPEED = 3
            const val FOOD_REQUIRED = 15.0
            const val OFFSPRING_COUNT = 2
        }

        object Boar {
            const val WEIGHT = 400.0
            const val MAX_POPULATION = 50
            const val SPEED = 2
            const val FOOD_REQUIRED = 50.0
            const val OFFSPRING_COUNT = 3
        }

        object Buffalo {
            const val WEIGHT = 700.0
            const val MAX_POPULATION = 10
            const val SPEED = 3
            const val FOOD_REQUIRED = 100.0
            const val OFFSPRING_COUNT = 1
        }

        object Duck {
            const val WEIGHT = 1.0
            const val MAX_POPULATION = 200
            const val SPEED = 4
            const val FOOD_REQUIRED = 0.15
            const val OFFSPRING_COUNT = 4
        }

        object Caterpillar {
            const val WEIGHT = 0.01
            const val MAX_POPULATION = 1000
            const val SPEED = 0
            const val FOOD_REQUIRED = 0.0
            const val OFFSPRING_COUNT = 5
        }
    }

    // Характеристики растений
    object PlantCharacteristics {
        const val WEIGHT = 1.0
        const val MAX_POPULATION = 200
    }

    // Начальное количество каждого вида
    const val INITIAL_PREDATOR_COUNT = 5
    const val INITIAL_HERBIVORE_COUNT = 10
    const val INITIAL_PLANT_COUNT = 1000

    // Условие остановки симуляции
    const val MIN_TOTAL_ANIMALS = 10  // Минимальное общее количество животных
} 