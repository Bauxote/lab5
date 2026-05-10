import java.util.List;

/** Демонстрація роботи таксопарку. */
public class Main {

    public static void main(String[] args) {

        // 1. Створення таксопарку
        TaxiPark park = new TaxiPark("Kyiv Taxi Fleet");

        // 2. Додавання автомобілів (поліморфізм: різні підкласи Car)
        try {
            park.addCar(new EconomyCar("Toyota",  "Yaris",     2020, 350_000,   5.8, 175, 4));
            park.addCar(new EconomyCar("Skoda",   "Fabia",     2021, 390_000,   5.4, 180, 4));
            park.addCar(new BusinessCar("Toyota", "Camry",     2022, 1_200_000, 8.5, 210, true, true));
            park.addCar(new BusinessCar("BMW",    "5 Series",  2021, 2_100_000, 9.2, 250, true, true));
            park.addCar(new PremiumCar("Mercedes","S-Class",   2023, 4_800_000, 10.5, 250, 435, "Автомат"));
            park.addCar(new PremiumCar("Lexus",  "LS 500",    2023, 4_000_000,  9.8, 250, 415, "Автомат"));
            park.addCar(new ElectricCar("Tesla",  "Model 3",   2022, 2_200_000, 14.5, 225, 580, 0.5));
            park.addCar(new ElectricCar("BYD",    "Atto 3",    2023, 1_600_000, 15.8, 180, 480, 1.0));
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка при створенні автомобіля: " + e.getMessage());
            return;
        }

        // 3. Виведення парку
        park.printInfo();

        // 4. Загальна вартість
        try {
            double total = park.getTotalValue();
            System.out.printf("%nЗагальна вартість парку: %,.0f грн (%.1f млн грн)%n",
                total, total / 1_000_000);
        } catch (IllegalStateException e) {
            System.err.println("Помилка: " + e.getMessage());
        }

        // 5. Сортування за витратами палива
        System.out.println("\nСортування за витратами палива:");
        park.sortByFuelConsumption().forEach(c ->
            System.out.printf("  %.1f л/100км — %s %s%n",
                c.getFuelConsumption(), c.getBrand(), c.getModel()));

        // 6. Пошук за діапазоном швидкості
        System.out.println("\nАвтомобілі зі швидкістю 200–230 км/год:");
        try {
            List<Car> found = park.findBySpeedRange(200, 230);
            if (found.isEmpty()) {
                System.out.println("  Не знайдено.");
            } else {
                found.forEach(c -> System.out.printf("  → %s %s (%d км/год)%n",
                    c.getBrand(), c.getModel(), c.getMaxSpeed()));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка пошуку: " + e.getMessage());
        }

        // 7. Демонстрація виключних ситуацій
        System.out.println("\nДемонстрація виключних ситуацій:");
        try { park.findBySpeedRange(300, 100); }
        catch (IllegalArgumentException e) { System.out.println("  " + e.getMessage()); }

        try { park.addCar(null); }
        catch (IllegalArgumentException e) { System.out.println("  " + e.getMessage()); }

        try { park.removeCar(999); }
        catch (IndexOutOfBoundsException e) { System.out.println("  " + e.getMessage()); }
    }
}