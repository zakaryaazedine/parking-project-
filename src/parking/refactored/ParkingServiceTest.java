package parking.refactored;

import java.io.IOException;

public class ParkingServiceTest{
    public static void main(String[] args) throws IOException{
        MemoryReceiptLog log = new MemoryReceiptLog();
        ParkingService x = new ParkingService(new TextReportFormatter(), log);
        Vehicle car = new Vehicle("12345-A-6", new CarTariff());
        Vehicle bike = new Vehicle("67890-B-1", new MotorbikeTariff());
        Vehicle truck = new Vehicle("24680-D-8", new TruckTariff());
        RefundablePayment card = new CardPayment("4242");

        if (x.fee(car, 3) == 15) System.out.println("Car fee: OK");
        else System.out.println("Car fee: ERROR");
        if (x.fee(bike, 2) == 4) System.out.println("Motorbike fee: OK");
        else System.out.println("Motorbike fee: ERROR");
        if (x.fee(truck, 4) == 60) System.out.println("Truck fee: OK");
        else System.out.println("Truck fee: ERROR");

        x.checkout(car, 3, card);
        x.checkout(bike, 2, new CashPayment());
        x.checkout(truck, 4, card);
        if (x.getTotal() == 79) System.out.println("Total before refunds: OK");
        else System.out.println("Total before refunds: ERROR");

        x.cancelAtDesk(bike, 2);
        x.cancel(truck, 4, card);
        if (x.getTotal() == 15 && x.dailyReport().endsWith("Total: 15.0 MAD")) System.out.println("Daily report total: OK");
        else System.out.println("Daily report total: ERROR");
        if (log.getLines().size() == 3) System.out.println("Three receipts logged in memory: OK");
        else System.out.println("Three receipts logged in memory: ERROR");
    }
}
