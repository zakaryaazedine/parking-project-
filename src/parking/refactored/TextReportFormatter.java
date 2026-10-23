package parking.refactored;

import java.util.List;

public class TextReportFormatter implements ReportFormatter{
    @Override
    public String formatReceipt(Receipt x){
        return x.plate() + " | " + x.type() + " | " + x.hours() + " h | " + x.amount() + " MAD | " + x.payment();
    }

    @Override
    public String dailyReport(List<Receipt> receipts, double total){
        StringBuilder sb = new StringBuilder("=== Daily report ===\n");
        for (int i = 0; i < receipts.size(); i++) sb.append(formatReceipt(receipts.get(i))).append("\n");
        sb.append("Total: ").append(total).append(" MAD");
        return sb.toString();
    }

    @Override
    public String deskRefund(String plate, double amount){
        return "Refund " + amount + " MAD at the desk for " + plate;
    }
}
