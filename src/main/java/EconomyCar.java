/**
 * Клас, що описує економ-клас автомобіля (нащадок {@link Car}).
 *
 */
public class EconomyCar extends Car {

    /** Кількість місць для пасажирів */
    private int passengerSeats;

    /**
     * Конструктор економ-класу автомобіля.
     *
     * @param brand           марка
     * @param model           модель
     * @param year            рік випуску
     * @param price           ціна
     * @param fuelConsumption витрати палива
     * @param maxSpeed        максимальна швидкість
     * @param passengerSeats  кількість пасажирських місць
     * @throws IllegalArgumentException якщо кількість місць < 1 або > 5
     */
    public EconomyCar(String brand, String model, int year, double price, double fuelConsumption, int maxSpeed, int passengerSeats) {
        super(brand, model, year, price, fuelConsumption, maxSpeed);
        if (passengerSeats < 1 || passengerSeats > 5)
            throw new IllegalArgumentException("Кількість місць має бути від 1 до 5: " + passengerSeats);
        this.passengerSeats = passengerSeats;
    }

    /** @return кількість пасажирських місць */
    public int getPassengerSeats() { return passengerSeats; }

    /**
     * {@inheritDoc}
     *
     * @return "Економ-клас"
     */
    @Override
    public String getCarType() { return "Економ-клас"; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Місць: %d", passengerSeats);
    }
}