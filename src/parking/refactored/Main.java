package parking.refactored;

import java.io.IOException;
import parking.PaymentMethod;
import parking.CardPayment;
import parking.CashPayment;

public class Main{
    public static void main(String[] args) throws IOException{
        ParkingService garage = new ParkingService(new TextReportFormatter(), new FileReceiptLog("parking.log"));
        Vehicle car = new Vehicle("12345-A-6", new CarTariff());
        Vehicle bike = new Vehicle("67890-B-1", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", new TruckTariff());

        PaymentMethod card = new CardPayment("4242");
        PaymentMethod cash = new CashPayment();

        garage.checkout(car, 3, card);
        garage.checkout(bike, 2, cash);
        garage.checkout(truck, 4, card);
        garage.cancel(bike, 2, cash);
        garage.cancel(truck, 4, card);

        System.out.println(garage.dailyReport());
    }
}
