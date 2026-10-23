# Parking project

Zakarya AZE-DINE

## Question 1

I found four main actors: the team setting tariffs, the payment team, the people asking for reports, and the IT team managing storage. Each can ask for a different change in ParkingGarage.

## Question 2

Line numbers refer to the original files in `src/parking`.

| Principle | Class | Lines | Smell |
| --- | --- | --- | --- |
| SRP | ParkingGarage | 12-50 | Calculates fees, handles payments, formats reports and logs receipts. |
| OCP | ParkingGarage | 13-17 | Adding a vehicle type requires changing the switch. |
| LSP | CashPayment | 11-12 | Its refund method throws instead of doing the operation promised by PaymentMethod. |
| ISP | PaymentMethod / CashPayment | 4-6 / 11-12 | CashPayment is forced to implement refund even though the machine cannot refund cash. |
| DIP | ParkingGarage | 9, 30 | The class creates and directly uses a concrete FileLogger. |

## Question 3

| Request | Existing files to edit in the original version |
| --- | --- |
| BUS at 15 MAD per hour | ParkingGarage for the new switch case, Main to use it. |
| Refundable mobile wallet | Main to select a new payment class. The interface already allows a refundable implementation. |
| Database logging | ParkingGarage to replace its hard-coded FileLogger with the new logger. |
| CSV report | ParkingGarage, where the receipts and daily report are formatted. |

## Question 4

Calling refund directly for cash throws UnsupportedOperationException and stops the program. This breaks LSP. The instanceof check only hides the incompatible contract and makes Bank-style special cases necessary for new unsupported payment types.

## Question 5

The fee alone can be tested without a file, but checkout always calls the internally created FileLogger. There is no constructor parameter for replacing it with a memory log, so the normal checkout cannot be tested without file output.

## Question 6

[Refactored class diagram](docs/refactored-class-diagram.md)

## Question 7

The refactored classes are in `parking.refactored`. Tariffs calculate prices, payment classes handle their supported operations, the formatter builds text, and the service coordinates them. The original classes stay in `parking` for comparison.

## Question 8

No, a CashPayment cannot be passed to cancel: the compiler requires RefundablePayment. This catches the unsupported operation before running the program. Cash refunds use the separate desk operation.

## Question 9

I used MemoryReceiptLog, an in-memory test double. It records the receipt strings in a list instead of creating parking.log. The main method checks the three fees, the totals before and after refunds, and the logged receipts.

## Question 10

| Request | New class | Existing files to edit |
| --- | --- | --- |
| BUS | BusTariff | Only Main to choose it. |
| Mobile wallet | MobileWalletPayment | Only Main to choose it. |
| Database logging | DatabaseReceiptLog | Only Main to supply a JDBC connection. |
| CSV report | CsvReportFormatter | Only Main to choose it. |

DatabaseReceiptLog inserts into a table named receipts with a receipt text column. The JDBC driver and connection are selected outside the service. It does not print a pretend database message.

## Run in IntelliJ IDEA

Clone this repository with **New Project from Version Control**, or open its folder. Select JDK 17 or later if IntelliJ asks for an SDK.

The shared run configurations are **Original Main**, **Refactored Main**, and **Parking Service Test**. The two Main programs print the same output. The test uses only memory logging.
