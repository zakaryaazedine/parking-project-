package parking.refactored;

public class Vehicle{
    private final String plate;
    private final Tariff tariff;

    public Vehicle(String plate, Tariff tariff){
        this.plate = plate;
        this.tariff = tariff;
    }

    public String getPlate(){
        return plate;
    }

    public String getType(){
        return tariff.name();
    }

    public Tariff getTariff(){
        return tariff;
    }
}
