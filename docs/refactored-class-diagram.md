# Refactored class diagram

```mermaid
classDiagram
    class Tariff {
        <<interface>>
        +name() String
        +fee(int hours) double
    }
    class CarTariff
    class MotorbikeTariff
    class TruckTariff
    class Vehicle {
        -String plate
        -Tariff tariff
        +getPlate() String
        +getType() String
        +getTariff() Tariff
    }
    Tariff <|.. CarTariff
    Tariff <|.. MotorbikeTariff
    Tariff <|.. TruckTariff
    Vehicle --> Tariff

    class PaymentMethod {
        <<interface>>
        +name() String
        +pay(double amount) void
    }
    class RefundablePayment {
        <<interface>>
        +refund(double amount) void
    }
    class CardPayment
    class CashPayment
    PaymentMethod <|-- RefundablePayment
    RefundablePayment <|.. CardPayment
    PaymentMethod <|.. CashPayment

    class Receipt {
        <<record>>
        +String plate
        +String type
        +int hours
        +double amount
        +String payment
    }
    class ReportFormatter {
        <<interface>>
        +formatReceipt(Receipt x) String
        +dailyReport(List~Receipt~ receipts, double total) String
        +deskRefund(String plate, double amount) String
    }
    class TextReportFormatter
    ReportFormatter <|.. TextReportFormatter
    ReportFormatter ..> Receipt

    class ReceiptLog {
        <<interface>>
        +log(String line) void
    }
    class FileReceiptLog
    class MemoryReceiptLog
    ReceiptLog <|.. FileReceiptLog
    ReceiptLog <|.. MemoryReceiptLog

    class ParkingService {
        -List~Receipt~ receipts
        -double total
        +ParkingService(ReportFormatter formatter, ReceiptLog log)
        +fee(Vehicle x, int hours) double
        +checkout(Vehicle x, int hours, PaymentMethod method) void
        +cancel(Vehicle x, int hours, RefundablePayment method) void
        +cancelAtDesk(Vehicle x, int hours) void
        +dailyReport() String
        +getTotal() double
    }
    ParkingService --> ReportFormatter
    ParkingService --> ReceiptLog
    ParkingService o-- Receipt
    ParkingService ..> Vehicle
    ParkingService ..> PaymentMethod
    ParkingService ..> RefundablePayment
```

CashPayment has no refund operation. A cash refund is handled through cancelAtDesk. The log interface belongs to the parking package, so storage implementations depend on the business contract.
