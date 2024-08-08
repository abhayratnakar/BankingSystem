package com.pay.abhay.transfer;

import java.util.Scanner;
import com.pay.abhay.service.*;
import com.pay.abhay.userpack.*;
import com.pay.abhay.utils.Utility;
import com.pay.abhay.valid.ValidateUser;

public class AccountFundTransfer implements UserService {

    User user1, user2;
    int user, activeAcc;
    private String accType;
    Scanner scanner = new Scanner(System.in);

    // init block it runs before the construc tor
    {
        user1 = new User();
        user2 = new User();
    }

    public static void main(String[] args) {
        AccountFundTransfer fundTransfer = new AccountFundTransfer();
        fundTransfer.mainMenu();
    }

    private void mainMenu() {
        System.out.println("");

        if (activeAcc != 0) {
            System.out.println("1. Logout");
            System.out.println("2. My Account Details");
            System.out.println("3. Account Activity");
            System.out.println("4. Fund Transfer");
            System.out.println("5. Withdraw");
            System.out.println("6. Change Pin");
        } else {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
        }

        System.out.println("");
        int menuChoice = scanner.nextInt();
        if (menuChoice == 1) {
            if (activeAcc == 1) {
                Logout(user1);
            } else if (activeAcc == 2) {
                Logout(user2);
            } else {
                this.login();
            }
        } else if (menuChoice == 2) {
            if (activeAcc != 0) {
                if (activeAcc == 1) {
                    accountInfo(user1);
                    mainMenu();
                } else {
                    accountInfo(user2);
                    mainMenu();
                }
            } else {
                createAccount();
            }
        } else if (menuChoice == 3) {
            if (activeAcc != 0) {
                if (activeAcc == 1) {
                    print(user1.getHistory());
                    mainMenu();
                } else {
                    print(user2.getHistory());
                    mainMenu();
                }
            } else {
                createAccount();
            }
        } else if (menuChoice == 4) {
            if (activeAcc != 0) {
                if (activeAcc == 1) {
                    checkAccount(user1, user2);
                } else {
                    checkAccount(user2, user1);
                }
            } else {
                createAccount();
            }
        } else if (menuChoice == 5) {
            if (activeAcc != 0) {
                if (activeAcc == 1) {
                    withdraw(user1);
                } else{
                    withdraw(user2);
                }
            } else {
                createAccount();
            }
        } else if (menuChoice == 6) {
            if (activeAcc != 0) {
                if (activeAcc == 1) {
                    ChangePin(user1);
                }else {
                    ChangePin(user2);
                }
            } else {
                createAccount();
            }
        } else {
            this.createAccount();
        }
    }

    @Override
    public void login() {
        System.out.println("Welcome to bank");
        System.out.println("Enter your Bank Account Number");
        String accNumber = scanner.next();
        if (accNumber.equalsIgnoreCase(user1.getAccountNumber())) {
            System.out.println("Enter 6 Digit pin !!!");
            int pin = scanner.nextInt();
            if (ValidateUser.verifyPin(pin, user1)) {
                activeAcc = 1;
                System.out.println("!!Login Successfully   !!");
                createLog(user1, "Account Login");
                mainMenu();
            } else {
                System.out.println("! Wrong pin try Again!");
                login();
            }
        } else if (accNumber.equalsIgnoreCase(user2.getAccountNumber())) {
            System.out.println("Enter 6 Digit pin !!!");
            int pin = scanner.nextInt();
            if (ValidateUser.verifyPin(pin, user2)) {
                activeAcc = 2;
                System.out.println("!!Login Successfully   !!");
                createLog(user2, "Account Login");
                mainMenu();
            } else {
                System.out.println("! Wrong pin try Again!");
                login();
            }
        }else {
            print("!! Your Account Does Not Exist !!");
            mainMenu();
        }
    }

    @Override
    public void createAccount() {
        if (user1.getUserName() == null) {
            user = 1;
        } else if (user2.getUserName() == null) {
            user = 2;
        } else {
            System.out.println("!! OOps Only two user Can be created...");
            mainMenu();
        }

        System.out.println("----------------Fill Detail to Continue---------------");

        System.out.println("===========|  Enter Bank Name  |===========");
        String bankName = scanner.next();
        user1.setBankName(bankName);
        if (!ValidateUser.checkLength(3, bankName, false)) {
            print("[ !! Bank Name is Not Valid or Empty !! ]");
            createAccount();
        }

        System.out.println("===========|  Full Name  |===========");
        String name = scanner.next();
        if (!ValidateUser.checkLength(2, name, false)) {
            print("[!! Name is Not Valid or Empty !! ]");
            createAccount();
        }

        System.out.println("===========|  Email  |===========");
        String email = scanner.next();
        if (!ValidateUser.checkLength(10, email, false) && !ValidateUser.validateEmail(email)) {
            print("[!! Email is Not Valid or Empty !! ]");
            createAccount();
        }

        System.out.println("===========|  Mobile Number  |===========");
        String mobile = scanner.next();
        if (ValidateUser.validateMaxMobile(mobile) && ValidateUser.validateMinMobile(mobile)) {
            print("[!! Mobile Number must be 10 digit !! ]");
            createAccount();
        }

        System.out.println("===========|  Create IFSC Code  |===========");
        String ifsc = scanner.next();
        if (!ValidateUser.checkLength(11, ifsc, true)) {
            print("[!! IFSC is not Valid or Empty !!]");
            createAccount();
        }

        System.out.println("===========|  Select Account Type  |===========");
        System.out.println("1. Saving");
        System.out.println("2. Current");
        int accountType = scanner.nextInt();
        if (accountType != 0 && accountType <= 2) {
            if (accountType == 1) {
                accType = "Saving";
            } else {
                accType = "Current";
            }
        } else {
            print("[!! Account Type is Not Valid !!]");
        }

        System.out.println("===========|  Enter Amount You Want to Save  |===========");
        int amount = scanner.nextInt();
        if (amount <= 0) {
            print("[!! Sorry You Can Not Open an Account with 0(Zero)]");
        }

        System.out.println("===========|  Create 6 Digit Pin  |===========");
        int pin = scanner.nextInt();
        if (!ValidateUser.checkLength(6, String.valueOf(pin), true)) {
            print("[!! Pin must be 6 Digit !!]");
            createAccount();
        }

        System.out.println("=====| Generating 11 Digit Account Number  |=====");
        String acNum = Utility.generateAcNum();
        System.out.println("---------------****------------------");
        System.out.println("---------------****[ Account Created Successfully ]****---------------");

        if (user == 1) {

            user1.setBankName(bankName);
            user1.setUserName(name);
            user1.setEmail(email);
            user1.setMobile(mobile);
            user1.setIfscCode(ifsc);
            user1.setAccountBalance(amount);
            user1.setAccountPin(pin);
            user1.setAccountNumber(acNum);
            user1.setAccountType(accType);
            user1.setHistory(Utility.getTimestamp());
            this.createLog(user1, " Account Created ");
            this.accountInfo(user1);
        } else {
            user2.setBankName(bankName);
            user2.setUserName(name);
            user2.setEmail(email);
            user2.setMobile(mobile);
            user2.setIfscCode(ifsc);
            user2.setAccountBalance(amount);
            user2.setAccountPin(pin);
            user2.setAccountNumber(acNum);
            user2.setAccountType(accType);
            user2.setHistory(Utility.getTimestamp());
            this.createLog(user2, " Account Created ");
            this.accountInfo(user2);
        }
    }

    private void accountInfo(User user) {
        System.out.println("!!~ Account Details ~!!");
        System.out.println("!!~ Bank Name => " + user.getBankName());
        System.out.println("!!~ User Name => " + user.getUserName());
        System.out.println("!!~ Email => " + user.getEmail());
        System.out.println("!!~ Mobile => " + user.getMobile());
        System.out.println("!!~ Account Number => " + user.getAccountNumber());
        System.out.println("!!~ IFSC Code => " + user.getIfscCode());
        System.out.println("!!~ Account Type => " + user.getAccountType());
        System.out.println("!!~ Account Pin => " + user.getAccountPin());
        System.out.println("!!~ Account Balance => " + user.getAccountBalance());
        System.out.println("------------------******----------------");
        this.mainMenu();
    }

    public void createLog(User user, String msg) {
        String history = null;
        if (user.getHistory() == null) {
            history = "";
        } else {
            history = user.getHistory();
        }
        user.setHistory(msg + " on " + Utility.getTimestamp() + "\n" + history);
    }

    private void print(String string) {
        System.out.println(string);
    }

    @Override
    public void Logout(User user) {
        activeAcc=0;
        System.out.println("!!Logout Successfully !!!");
        createLog(user, "Account Logout");
        mainMenu();
    }

    @Override
    public void FundTransfer(int amount, int pin, User fromUser, User toUser) {
        if (ValidateUser.verifyPin(pin, fromUser)) {
            if (amount <= fromUser.getAccountBalance()) {
                toUser.setAccountBalance(toUser.getAccountBalance() + amount);
                fromUser.setAccountBalance(fromUser.getAccountBalance() - amount);
                print("---------------****[Fund Transfer Successfully]****------------------");
                print("!!~ Account Balance =: " + fromUser.getAccountBalance());
                createLog(fromUser, amount + " Transfer to " + toUser.getUserName());
                createLog(toUser, amount + " Recevied " + fromUser.getUserName());
                mainMenu();
            } else {
                System.out.println("Insufficient Balance...  !!!");
                System.out.println("------------------------------");
                mainMenu();
            }
        } else {
            System.out.println("Your Pin is incorrect..  !!!");
            mainMenu();
        }
    }

    @Override
    public void withdraw(User user) {
        System.out.println("!! Enter Amount ");
        int amount = scanner.nextInt();
        System.out.println("!! Enter 6 Digit Pin ");
        int pin = scanner.nextInt();
        if (ValidateUser.verifyPin(pin, user)) {
            if (amount <= user.getAccountBalance()) {
                user.setAccountBalance(user.getAccountBalance() - amount);
                print("----------****[ Withdraw Successfully ]****------------------");
                print("!!~ Account Balance =: " + user.getAccountBalance());
                createLog(user, amount + " Withdraw");
                mainMenu();
            } else {
                System.out.println("Insufficient Balance...  !!!");
                mainMenu();
            }
        } else {
            System.out.println("[!! Pin is Not Valid !!]");
            mainMenu();
        }
    }

    @Override
    public void ChangePin(User user) {
        print("!! Enter Your Old Pin ");
        int oldpin = scanner.nextInt();
        if(oldpin == user.getAccountPin()){
            print("!! Enter Your New Pin ");
            int newpin = scanner.nextInt();
            user.setAccountPin(newpin);
            print("***********Your Pin has Updated successfully********");
            mainMenu();
        }else{
            print("!! You have Enterred Wrong Pin ");
            mainMenu();
        }
    }

    @Override
    public void checkAccount(User fromUser, User toUser) {
        System.out.println("!!~ Enter Receiver Account Number You want to send Money ");
        String accountNo = scanner.next();
        if(accountNo.equals(fromUser.getAccountNumber())){
            System.out.println("!!~ You can not send Money to Own Account... ");
            mainMenu();
        }else if(accountNo.equals(toUser.getAccountNumber())){
            print("!!~ You are sending Money to " + toUser.getUserName());
            print("!!~ Enter Amount ");
            int amount = scanner.nextInt();
            print("!!~ Enter 6 Digit Pin ");
            int pin = scanner.nextInt();
            FundTransfer(amount, pin, fromUser, toUser);
        }else {
            System.out.println("!! This Account Number Does Not Exist.... ");
            mainMenu();
        }
    }

}
