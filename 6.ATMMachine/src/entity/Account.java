package entity;

public class Account {

    private String accNo;
    private String accHolderName;
    private int balance;

    public Account(String accNo, String accHolderName) {
        this.accNo = accNo;
        this.accHolderName = accHolderName;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
