package parking.refactored;

import java.util.ArrayList;
import java.util.List;

public class MemoryReceiptLog implements ReceiptLog{
    private final List<String> lines = new ArrayList<>();

    @Override
    public void log(String line){
        lines.add(line);
    }

    public List<String> getLines(){
        return List.copyOf(lines);
    }
}
