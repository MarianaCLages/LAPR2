package app.ui.console;

import app.controller.ClientController;
import app.ui.console.utils.Utils;

import java.util.Date;
import java.util.List;

public class CreateClientUI implements Runnable {
    private ClientController ctrl;
    private List<String> sexes;

    public CreateClientUI() {
        this.ctrl = new ClientController();
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        sexes.add("Male");
        sexes.add("Female");
        sexes.add("Other");
        boolean cont = true;
        do {
            boolean exception = false;
            do {
                try {

                    String cc = Utils.readLineFromConsole("Please enter the cc of the new Client");
                    String name = Utils.readLineFromConsole("Please enter the name of the new Client");
                    String nhs = Utils.readLineFromConsole("Please enter the nhs number of the new Client");
                    Date birthDate = Utils.readDateFromConsole("Please enter the birth date Client");
                    int sexIndex = Utils.showAndSelectIndex(sexes, "Please choose your sex");
                    char sex = ' ';
                    if (sexIndex == 0) {
                        sex = 'm';
                    } else if (sexIndex == 1) {
                        sex = 's';
                    }
                    String tif = Utils.readLineFromConsole("Please enter the TIF number of the new Client");
                    String phoneNumber = Utils.readLineFromConsole("Please enter the phone number of the new Client");
                    String email = Utils.readLineFromConsole("Please enter the email of the new Client");
                    ctrl.createClient(cc, nhs, birthDate, sex, tif, phoneNumber, email, name);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during the creation during the creation of the Parameter please try again");
                    exception = true;
                }
            } while (exception);

            cont = Utils.confirm("The following Client was created do you want to save?" + ctrl.getClient().toString());


        } while (!cont);

    }
}
