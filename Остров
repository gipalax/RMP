import java.util.*;
import java.util.concurrent.*;

// Базовый класс для всех животных
abstract class Animal {
    protected String name;
    protected int speed;
    protected double maxFood;
    protected double weight;
    protected boolean isAlive = true;

    public Animal(String name, int speed, double maxFood, double weight) {
        this.name = name;
        this.speed = speed;
        this.maxFood = maxFood;
        this.weight = weight;
    }

    public String getName() { return name; }
    public abstract void eat(Location location);
    public void move(Island island, int x, int y) {
        Random random = new Random();
        int newX = Math.max(0, Math.min(island.getWidth() - 1, x + random.nextInt(3) - 1));
        int newY = Math.max(0, Math.min(island.getHeight() - 1, y + random.nextInt(3) - 1));
        island.moveAnimal(this, x, y, newX, newY);
    }
    public abstract Animal reproduce();
}

// Хищники
class Wolf extends Predator {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Rabbit.class, 60, Deer.class, 40, Mouse.class, 80, Horse.class, 10, Goat.class, 60, Sheep.class, 70, Hog.class,15, Buffalo.class, 10, Duck.class, 40);

    public Wolf() { super("Wolf", 3, 8, 50.0, diet); }

    @Override
    public void eat(Location location) {
        hunt(location);
    }

    @Override
    protected Animal createNew() {
        return new Wolf();
    }

    @Override public Animal reproduce() { return new Wolf(); }
}

class Boa extends Predator {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Fox.class,15,Rabbit.class, 20, Mouse.class, 40, Duck.class, 10);

    public Boa() { super("Boa", 1, 3, 15, diet); }

    @Override
    public void eat(Location location) {
        hunt(location);
    }

    @Override
    protected Animal createNew() {
        return new Boa();
    }

    @Override public Animal reproduce() { return new Boa(); }
}

class Fox extends Predator {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Caterpillar.class, 40);

    public Fox() { super("Fox", 2, 2, 8, diet); }

    @Override
    public void eat(Location location) {
        hunt(location);
    }

    @Override
    protected Animal createNew() {
        return new Fox();
    }

    @Override public Animal reproduce() { return new Fox(); }
}

class Bear extends Predator {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Boa.class, 80, Horse.class, 40, Deer.class, 80, Rabbit.class, 80, Mouse.class,90, Goat.class, 70, Sheep.class, 70, Hog.class, 50, Buffalo.class,20, Duck.class,10);

    public Bear() { super("Bear", 2, 15, 200.0, diet); }

    @Override
    public void eat(Location location) {
        hunt(location);
    }

    @Override
    protected Animal createNew() {
        return new Bear();
    }

    @Override public Animal reproduce() { return new Bear(); }
}

class Eagle extends Predator {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Fox.class, 10,Rabbit.class, 90, Mouse.class, 90, Duck.class, 80);

    public Eagle() { super("Eagle", 4, 3, 6.0, diet); }

    @Override
    public void eat(Location location) {
        hunt(location);
    }

    @Override
    protected Animal createNew() {
        return new Eagle();
    }



    @Override public Animal reproduce() { return new Eagle(); }
}

// Метод охоты для хищников
abstract class Predator extends Animal {
    protected final Map<Class<? extends Animal>, Integer> diet;
    private boolean isHungry = false;
    private int hungerLevel = 5; // Новая переменная голода. Если 0 — хищник умирает.

    public Predator(String name, int speed, int maxFood, double weight, Map<Class<? extends Animal>, Integer> diet) {
        super(name, speed, maxFood, weight);
        this.diet = diet;
    }

    protected void hunt(Location location) {
        Iterator<Animal> iterator = location.animals.iterator();
        boolean ate = false;

        while (iterator.hasNext()) {
            Animal neighbor = iterator.next();
            if (diet.containsKey(neighbor.getClass()) && Math.random() * 100 < diet.get(neighbor.getClass())) {
                iterator.remove();
                isHungry = true;
                hungerLevel = 5; // Восстанавливаем сытость после еды
                System.out.println(name + " съел " + neighbor.name);
                ate = true;
                break;
            }
        }

        if (!ate) {
            hungerLevel--; // Если не нашел еду, становится голоднее
            if (hungerLevel <= 0) {
                System.out.println(name + " умер от голода.");
                location.animals.remove(this); // Удаляем хищника из списка
            }
        }
    }

    @Override
    public Animal reproduce() {
        if (isHungry && hungerLevel > 2 && Math.random() < 0.1) { // Размножение только если хищник сыт
            isHungry = false;
            System.out.println(name + " размножился.");
            return createNew();
        }
        return null;
    }

    protected abstract Animal createNew();
}


//Травоядные
class Rabbit extends Animal {
    public Rabbit() { super("Rabbit", 2, 4, 2.5); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Rabbit(); }
}

class Mouse extends Animal {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Caterpillar.class, 90 );

    public Mouse() { super("Mouse", 1, 2, 0.5); }

    @Override
    public void eat(Location location) {
        Iterator<Animal> iterator = location.animals.iterator();
        boolean ate = false;

        // Проверяем диету
        while (iterator.hasNext()) {
            Animal neighbor = iterator.next();
            if (diet.containsKey(neighbor.getClass()) && Math.random() * 100 < diet.get(neighbor.getClass())) {
                iterator.remove();
                System.out.println(name + " съел " + neighbor.getName());
                ate = true;
                break;
            }
        }

        // Если не съел, ест траву (если растения доступны)
        if (!ate && location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }

    @Override public Animal reproduce() { return new Mouse(); }
}


class Deer extends Animal {
    public Deer() { super("Deer", 3, 6, 80.0); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Deer(); }
}

class Horse extends Animal {
    public Horse() { super("Horse", 4, 10, 120.0); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Horse(); }
}

class Goat extends Animal {
    public Goat() { super("Goat", 3, 10, 60); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Rabbit(); }
}

class Sheep extends Animal {
    public Sheep() { super("Sheep", 3, 15, 70); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Rabbit(); }
}

class Hog extends Animal {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Mouse.class, 50, Caterpillar.class, 90 );

    public Hog() { super("Hog", 2, 50, 400); }

    @Override
    public void eat(Location location) {
        Iterator<Animal> iterator = location.animals.iterator();
        boolean ate = false;

        // Проверяем диету
        while (iterator.hasNext()) {
            Animal neighbor = iterator.next();
            if (diet.containsKey(neighbor.getClass()) && Math.random() * 100 < diet.get(neighbor.getClass())) {
                iterator.remove();
                System.out.println(name + " съел " + neighbor.getName());
                ate = true;
                break;
            }
        }

        // Если не съел, ест траву (если растения доступны)
        if (!ate && location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }

    @Override public Animal reproduce() { return new Hog(); }
}


class Buffalo extends Animal {
    public Buffalo() { super("Buffalo", 3, 100, 700); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Rabbit(); }
}

class Duck extends Animal {
    private static final Map<Class<? extends Animal>, Integer> diet = Map.of(Caterpillar.class, 90);

    public Duck() { super("Duck", 4, 0.15, 1); }

    @Override
    public void eat(Location location) {
        Iterator<Animal> iterator = location.animals.iterator();
        boolean ate = false;

        // Проверяем диету
        while (iterator.hasNext()) {
            Animal neighbor = iterator.next();
            if (diet.containsKey(neighbor.getClass()) && Math.random() * 100 < diet.get(neighbor.getClass())) {
                iterator.remove();
                System.out.println(name + " съел " + neighbor.getName());
                ate = true;
                break;
            }
        }

        // Если не съел, ест траву (если растения доступны)
        if (!ate && location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }

    @Override public Animal reproduce() { return new Duck(); }
}


class Caterpillar extends Animal {
    public Caterpillar() { super("Caterpillar", 0, 0, 0.01); }
    @Override
    public void eat(Location location) {
        if (location.plantCount > 0) {
            location.plantCount--;
            System.out.println(name + " ест траву.");
        }
    }
    @Override public Animal reproduce() { return new Rabbit(); }
}

// Класс для локаций
class Location {
    List<Animal> animals = new ArrayList<>();
    int plantCount = 5; // Количество растений
}

// Класс Остров
class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                locations[i][j] = new Location();
            }
        }
        populateIsland();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private void populateIsland() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (random.nextDouble() < 0.4) locations[i][j].animals.add(new Wolf());
                if (random.nextDouble() < 0.4) locations[i][j].animals.add(new Bear());
                if (random.nextDouble() < 0.4) locations[i][j].animals.add(new Boa());
                if (random.nextDouble() < 0.4) locations[i][j].animals.add(new Fox());
                if (random.nextDouble() < 0.4) locations[i][j].animals.add(new Eagle());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Deer());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Horse());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Rabbit());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Mouse());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Buffalo());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Duck());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Caterpillar());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Sheep());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Goat());
                if (random.nextDouble() < 0.6) locations[i][j].animals.add(new Hog());
            }
        }
    }

    public void moveAnimal(Animal animal, int oldX, int oldY, int newX, int newY) {
        if (locations[oldX][oldY].animals.remove(animal)) {
            locations[newX][newY].animals.add(animal);
        }
    }

    public void simulate() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        scheduler.scheduleAtFixedRate(this::updateAnimals, 0, 2, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::printStatistics, 0, 3, TimeUnit.SECONDS);
    }

    private void updateAnimals() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Растения растут
                locations[i][j].plantCount = Math.min(10, locations[i][j].plantCount + 1);

                // Животные едят, двигаются и размножаются
                List<Animal> animalsCopy = new ArrayList<>(locations[i][j].animals);
                for (Animal animal : animalsCopy) {
                    animal.eat(locations[i][j]);
                    animal.move(this, i, j);
                    if (Math.random() < 0.1) {
                        locations[i][j].animals.add(animal.reproduce());
                    }
                }
            }
        }
    }

    private void printStatistics() {
        try {
            System.out.println("=== Статистика по острову ===");
            boolean hasAnimals = false;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    List<Animal> animalsCopy = new ArrayList<>(locations[i][j].animals); // Копия списка

                    if (!animalsCopy.isEmpty()) hasAnimals = true;

                    Map<String, Integer> speciesCount = new HashMap<>();
                    for (Animal animal : animalsCopy) {
                        if (animal != null) { // Проверяем, что animal не null
                            speciesCount.put(animal.getName(), speciesCount.getOrDefault(animal.getName(), 0) + 1);
                        }
                    }
                    System.out.println("Локация [" + i + ", " + j + "]: " + speciesCount + ", Растений: " + locations[i][j].plantCount);
                }
            }

            if (!hasAnimals) {
                System.out.println("Все животные вымерли. Завершаем симуляцию.");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

public class Main {
    public static void main(String[] args) {
        Island island = new Island(10, 10);
        island.simulate();
    }
}
