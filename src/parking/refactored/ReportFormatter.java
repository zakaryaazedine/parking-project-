package parking.refactored;

import java.util.List;

public interface ReportFormatter{
    String formatReceipt(Receipt x);
    String dailyReport(List<Receipt> receipts, double total);
    String deskRefund(String plate, double amount);
}
