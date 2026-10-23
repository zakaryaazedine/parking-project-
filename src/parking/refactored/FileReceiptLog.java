package parking.refactored;

import java.io.FileWriter;
import java.io.IOException;

public class FileReceiptLog implements ReceiptLog{
    private final String filename;

    public FileReceiptLog(String filename){
        this.filename = filename;
    }

    @Override
    public void log(String line) throws IOException{
        try (FileWriter x = new FileWriter(filename, true)){
            x.write(line + System.lineSeparator());
        }
    }
}
