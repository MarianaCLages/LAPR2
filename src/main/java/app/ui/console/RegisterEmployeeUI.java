package app.ui.console;

import app.controller.ParameterController;
import app.controller.RegisterEmployeeController;
import app.domain.model.ParameterCategory;
import app.domain.model.Role;
import app.domain.model.RoleStore;
import app.ui.console.utils.Utils;

public class RegisterEmployeeUI implements Runnable{

        private RegisterEmployeeController ctrl;
        private RoleStore roleList;
        public RegisterEmployeeUI() {
            this.ctrl = new RegisterEmployeeController();
            this.roleList = new RoleStore();
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
            String DoctorIndexNumber ="";
            boolean cont = true;
            do {
                boolean exception = false;
                do {
                    try {
                        String name = Utils.readLineFromConsole("Please enter the name of the new Employee");
                        String address = Utils.readLineFromConsole("Please enter the address of the new Employee");
                        String phonenumber = Utils.readLineFromConsole("Please enter the phonenumber of the new Employee");
                        String email = Utils.readLineFromConsole("Please enter the email of the new Employee");
                        String SOC = Utils.readLineFromConsole("Please enter the SOC of the new Employee");


                        int role = Utils.showAndSelectIndex(roleList.getRoleList(),"Select a Role");
                        if(role == 4){
                             DoctorIndexNumber = Utils.readLineFromConsole("Please enter the DoctorIndexNumber of the new Employee");

                        }
                        ctrl.createEmployee(name, address, phonenumber, email,SOC,DoctorIndexNumber ,role);
                        exception = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An error occurred during the creation during the creation of the Employee please try again");
                        exception = true;
                    }
                } while (exception);

                cont = Utils.confirm("The following Employee was created do you want to save?" + ctrl.getEm().toString());
                if (cont) {

                    if (ctrl.saveEmployee()) {
                        System.out.println("The Employee was saved with success");
                    }

                } else {
                    System.out.println("Couldn't save the Employee please try again ");
                }


            } while (!cont);

        }
    }





