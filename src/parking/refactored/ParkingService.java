package parking.refactored;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return x.getTariff().fee(hours);
    }

    public void checkout(Vehicle x, int hours, PaymentMethod method) throws IOException{
        double amount = fee(x, hours);
        method.pay(amount);
        total += amount;
        Receipt receipt = new Receipt(x.getPlate(), x.getType(), hours, amount, method.name());
        receipts.add(receipt);
        log.log(formatter.formatReceipt(receipt));
    }

    public void cancel(Vehicle x, int hours, RefundablePayment method){
        double amount = fee(x, hours);
        method.refund(amount);
        total -= amount;
    }

    public void cancelAtDesk(Vehicle x, int hours){
        double amount = fee(x, hours);
        System.out.println(formatter.deskRefund(x.getPlate(), amount));
        total -= amount;
    }

    public String dailyReport(){
        return formatter.dailyReport(List.copyOf(receipts), total);
    }

    public double getTotal(){
        return total;
    }
}
