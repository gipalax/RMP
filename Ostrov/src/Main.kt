    package com.javarush.island

    import com.javarush.island.config.SimulationConfig
    import com.javarush.island.entities.*
    import com.javarush.island.entities.animals.predators.*
    import com.javarush.island.entities.animals.herbivores.*
    import com.javarush.island.simulation.IslandSimulation
    import kotlin.random.Random

    fun main() {
        // Создаем остров
        val island = Island(SimulationConfig.ISLAND_WIDTH, SimulationConfig.ISLAND_HEIGHT)

        // Заполняем остров начальными животными и растениями
        repeat(SimulationConfig.INITIAL_PREDATOR_COUNT) {
            // Добавляем хищников
            addRandomly(island, Wolf())
            addRandomly(island, Bear())
            addRandomly(island, Fox())
            addRandomly(island, Eagle())
            addRandomly(island, Snake())
        }

        repeat(SimulationConfig.INITIAL_HERBIVORE_COUNT) {
            // Добавляем травоядных
            addRandomly(island, Horse())
            addRandomly(island, Deer())
            addRandomly(island, Rabbit())
            addRandomly(island,
                Mouse())
            addRandomly(island, Goat())
            addRandomly(island, Sheep())
            addRandomly(island, Boar())
            addRandomly(island, Buffalo())
            addRandomly(island, Duck())
            addRandomly(island, Caterpillar())
        }

        repeat(SimulationConfig.INITIAL_PLANT_COUNT) {
            // Добавляем растения
            val x = Random.nextInt(SimulationConfig.ISLAND_WIDTH)
            val y = Random.nextInt(SimulationConfig.ISLAND_HEIGHT)
            island.addPlant(Plant(), x, y)
        }

        // Создаем и запускаем симуляцию
        val simulation = IslandSimulation(island, SimulationConfig.SIMULATION_PERIOD)
        simulation.start()

        // Ждем ввода пользователя для остановки
        println("Нажмите Enter для остановки симуляции...")
        readLine()
        simulation.stop()
    }

    private fun addRandomly(island: Island, animal: Animal) {
        val x = Random.nextInt(SimulationConfig.ISLAND_WIDTH)
        val y = Random.nextInt(SimulationConfig.ISLAND_HEIGHT)
        island.addAnimal(animal, x, y)
    }