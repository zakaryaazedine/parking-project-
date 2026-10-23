package parking.refactored;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseReceiptLog implements ReceiptLog{
    private final Connection connection;

    public DatabaseReceiptLog(Connection connection){
        this.connection = connection;
    }

    @Override
    public void log(String line) throws IOException{
        try (PreparedStatement x = connection.prepareStatement("INSERT INTO receipts (receipt) VALUES (?)")){
            x.setString(1, line);
            x.executeUpdate();
        } catch (SQLException e){
            throw new IOException("Cannot save receipt", e);
        }
    }
}
