package parking.refactored;

public class MobileWalletPayment implements RefundablePayment{
    private final String phone;

    public MobileWalletPayment(String phone){
        this.phone = phone;
    }

    @Override
    public String name(){
        return "WALLET";
    }

    @Override
    public void pay(double amount){
        System.out.println("[WALLET " + phone + "] paid " + amount + " MAD");
    }

    @Override
    public void refund(double amount){
        System.out.println("[WALLET " + phone + "] refunded " + amount + " MAD");
    }
}
