package ATM;

import ATM.controller.ATMController;
import ATM.view.ATMView;

public class Main {
    public static void main(String[] args) {
        ATMView view = new ATMView();
        ATMController controller = new ATMController(view);
        controller.runATM();
    }
}