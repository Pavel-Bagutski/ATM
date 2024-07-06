package ATM.controller;

import ATM.model.Account;
import ATM.view.ATMView;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ATMController {
    private static final String DATA_FILE = "atm_data.txt";
    private static final Map<String, Account> accounts = new HashMap<>();
    private ATMView view;

    public ATMController(ATMView view) {
        this.view = view;
        loadAccountData();
    }

    public void runATM() {
        view.displayWelcomeMessage();
        while (true) {
            String cardNumber = view.promptCardNumber();
            if (cardNumber.equalsIgnoreCase("exit")) {
                saveAccountData();
                break;
            }
            if (isValidCardNumber(cardNumber)) {
                Account account = accounts.get(cardNumber);
                if (account != null) {
                    int pin = view.promptPIN();
                    if (account.validatePIN(pin)) {
                        handleAccountActions(account);
                    } else {
                        view.displayInvalidPINMessage();
                    }
                } else {
                    view.displayCardNotFoundMessage();
                }
            } else {
                view.displayInvalidCardNumberMessage();
            }
        }
        view.displayGoodbyeMessage();
    }

    private void handleAccountActions(Account account) {
        while (true) {
            int choice = view.promptAccountAction();
            switch (choice) {
                case 1:
                    view.displayBalance(account.getBalance());
                    break;
                case 2:
                    double withdrawAmount = view.promptWithdrawAmount();
                    if (account.withdraw(withdrawAmount)) {
                        view.displayWithdrawalSuccess(account.getBalance());
                    } else {
                        view.displayWithdrawalFailure();
                    }
                    break;
                case 3:
                    double depositAmount = view.promptDepositAmount();
                    if (account.deposit(depositAmount)) {
                        view.displayDepositSuccess(account.getBalance());
                    } else {
                        view.displayDepositFailure();
                    }
                    break;
                case 4:
                    saveAccountData();
                    return;
                default:
                    view.displayInvalidChoiceMessage();
            }
        }
    }

    private void loadAccountData() {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length == 3) {
                    String cardNumber = data[0];
                    int pin = Integer.parseInt(data[1]);
                    double balance = Double.parseDouble(data[2]);
                    accounts.put(cardNumber, new Account(cardNumber, pin, balance));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading account data: " + e.getMessage());
        }
    }

    private void saveAccountData() {
        try {
            File file = new File(DATA_FILE);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Account account : accounts.values()) {
                writer.write(account.getCardNumber() + " " + account.getPIN() + " " + account.getBalance());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
    }
}