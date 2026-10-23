package parking.refactored;

public class CarTariff implements Tariff{
    @Override
    public String name(){
        return "CAR";
    }

    @Override
    public double fee(int hours){
        return hours * 5.0;
    }
}
