package parking.refactored;

public class MotorbikeTariff implements Tariff{
    @Override
    public String name(){
        return "MOTORBIKE";
    }

    @Override
    public double fee(int hours){
        return hours * 2.0;
    }
}
