import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain {

    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount account = new BankAccount("Amy", 1000, 1500);
        account.Deposit(500);
        BankAccount account2 = new BankAccount("Barry", 1001, 100000);
        BankAccount account3 = new BankAccount("Cary", 1003, 4000);
        account2.trx(account3, 1000);
        account.Total();
        account2.Total();
        account3.Total();
        //adding new bank accounts
        accounts.add(new BankAccount("Dave", 1004, 4500));
        accounts.add(new BankAccount("Eri", 1005, 7000));
        accounts.add(new BankAccount("Fabio", 1006, 290));
        accounts.add(account);
        accounts.add(account2);
        accounts.add(account3);

        //welcome message and ask if existing customer
        System.out.println("Welcome to the Bank. Are you an existing customer? (-1 to exit)");
        System.out.println("1 = Yes \n2 = No");
        int response = Integer.valueOf(input.nextLine());
        while (true) {
            if (response == 1) {
                System.out.println("What is your account number?");
                int accountNumber = Integer.valueOf(input.nextLine());
                boolean isAccountHolder = false;
                int index = -1;
                for (int i = 0; i< accounts.size(); i++) {
                    BankAccount acc = accounts.get(i);
                    if (accountNumber == acc.getAccountNumber()) {
                        isAccountHolder = true;
                        index = i;
                    }
                }
                if (isAccountHolder) {
                    mainMenu(accounts.get(index));
                } else {
                    System.out.println("We couldn't find your account number. Please try again.");
                }
            } else if (response == 2) {
                BankAccount newAccount = new BankAccount();
                System.out.println("Let's create an account!");
                System.out.println("What is the name you would like on the account?");
                String inputName = input.nextLine();
                newAccount.setAccountHolderName(inputName);
                System.out.println("What is the starting balance for the account?");
                double inputAmount = Double.valueOf(input.nextLine());
                newAccount.setBalance(inputAmount);
                accounts.add(newAccount);
            // response if exits
                mainMenu(accounts.get(accounts.size() - 1));
            } else if (response == -1) {
                System.out.println("Okay, have a nice day!");
            }

        }

    }
    public static void mainMenu(BankAccount acc) {

        Scanner input = new Scanner(System.in);
        System.out.println("Hi " + acc.getAccountHolderName() + "!");
        System.out.println("Welcome to the main menu, what would you like to do today?");
        System.out.println("1. Check account balance \n 2. Make a withdrawal \n 3. Make a deposit \n 4. Make a transfer to another account \n 0. Exit ");

        int response = Integer.valueOf(input.nextLine());
        while (response != 0) {
            if (response == 1) {
                acc.getBalance();
                System.out.println("Current balance is: " + acc.getBalance());
                mainMenu(accounts.get(accounts.size() - 1));
            } else if (response == 2) {
                System.out.println("What amount would you like to withdraw?");
                double withdrawAmount = Double.valueOf(input.nextLine());
                acc.Withdrawal(withdrawAmount);
                System.out.println("The amount " + withdrawAmount + " has been withdrawn.");
                mainMenu(accounts.get(accounts.size() - 1));
            } else if (response == 3) {
                System.out.println("What amount would you like to deposit?");
                double departmentAmount = Double.valueOf(input.nextLine());
                acc.Deposit(departmentAmount);
                System.out.println("The amount " + departmentAmount + " has been deposited.");
                mainMenu(accounts.get(accounts.size() - 1));
            } else if (response == 4) {
                System.out.println("Please enter the account number you'd like to transfer money to");
                int accountToTransferTo = Integer.valueOf(input.nextLine());
                BankAccount toTransferTo = new BankAccount();
                boolean isAccountHolder = false;
                int index = -1;

                for (int i= 0; i< accounts.size(); i++) {
                    BankAccount accs = accounts.get(i);
                    if (accountToTransferTo == accs.getAccountNumber()) {
                        toTransferTo = accs;
                        isAccountHolder = true;
                        index = i;
                    }
                }

                if (isAccountHolder) {
                    System.out.println("How much would you like to transfer?");
                    double transferAmount = Double.valueOf(input.nextLine());

                    acc.transfer(toTransferTo, transferAmount);
                    System.out.println(acc.getHolderName() + " moved " + transferAmount + " and now has " + acc.getBalance());
                    System.out.println(toTransferTo.getAccountHolderName() + " was given " + transferAmount + " and now has " + toTransferTo.getBalance());
                    break;
                } else {
                    System.out.println("Account not found. Please try again");
                    mainMenu(acc);
                }
                break;
            } else if (response == 0) {
                System.out.println("Okay, see you later!");
                break;
            }
        }
    }
}