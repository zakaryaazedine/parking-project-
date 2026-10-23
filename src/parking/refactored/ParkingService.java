package parking.refactored;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import parking.Vehicle;
import parking.PaymentMethod;
import parking.CashPayment;

public class ParkingService{
    private final ReportFormatter formatter;
    private final ReceiptLog log;
    private final List<Receipt> receipts = new ArrayList<>();
    private double total;

    public ParkingService(ReportFormatter formatter, ReceiptLog log){
        this.formatter = formatter;
        this.log = log;
    }

    public double fee(Vehicle x, int hours){
        return switch (x.getType()){
            case "CAR" -> hours * 5.0;
            case "MOTORBIKE" -> hours * 2.0;
            case "TRUCK" -> 20 + hours * 10.0;
            default -> throw new IllegalArgumentException(x.getType());
        };
    }

    public void checkout(Vehicle x, int hours, PaymentMethod method) throws IOException{
        double amount = fee(x, hours);
        method.pay(amount);
        total += amount;
        Receipt receipt = new Receipt(x.getPlate(), x.getType(), hours, amount, method.name());
        receipts.add(receipt);
        log.log(formatter.formatReceipt(receipt));
    }

    public void cancel(Vehicle x, int hours, PaymentMethod method){
        double amount = fee(x, hours);
        if (method instanceof CashPayment) System.out.println(formatter.deskRefund(x.getPlate(), amount));
        else method.refund(amount);
        total -= amount;
    }

    public String dailyReport(){
        return formatter.dailyReport(List.copyOf(receipts), total);
    }

    public double getTotal(){
        return total;
    }
}
