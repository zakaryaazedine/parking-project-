package parking.refactored;

import java.util.List;

public class CsvReportFormatter implements ReportFormatter{
    @Override
    public String formatReceipt(Receipt x){
        return csv(x.plate()) + "," + csv(x.type()) + "," + x.hours() + "," + x.amount() + "," + csv(x.payment());
    }

    @Override
    public String dailyReport(List<Receipt> receipts, double total){
        StringBuilder sb = new StringBuilder("plate,type,hours,amount,payment\n");
        for (int i = 0; i < receipts.size(); i++) sb.append(formatReceipt(receipts.get(i))).append("\n");
        sb.append("TOTAL,,,").append(total).append(",");
        return sb.toString();
    }

    @Override
    public String deskRefund(String plate, double amount){
        return "Refund " + amount + " MAD at the desk for " + plate;
    }

    private String csv(String value){
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
