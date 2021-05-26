/*
package app.ui.console;


import app.controller.RecordSampleController;

import app.domain.mappers.dto.CategoryListDTO;
import app.domain.mappers.dto.TestDTO;

import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.SampleStore;

import app.ui.console.utils.Utils;

import java.util.List;

public class RecordSampleUI implements Runnable{

        private RecordSampleController ctrl;
        private TestDTO test;
        private SampleStore SampleList;


        public RecordSampleUI() {
            this.ctrl = new RecordSampleController();
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
 /*       @Override
        public void run() {
            boolean cont = true;
            do {
                ctrl.getLists();
                boolean exception = false;
                do {
                    try {
                        this.test = (TestDTO) Utils.showAndSelectOne(ctrl.tList(), "Please select one Test");
                        String testID = this.test.getTestCode();



                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("An error occurred during the creation during the creation of the Employee please try again");
                        exception = true;
                    }
                } while (exception);

                cont = Utils.confirm("The following Employee was created do you want to save? (s/n) \n" + ctrl.getEm().toString());
                if (cont) {

                    if (ctrl.saveEmployee()) {
                        System.out.println("The Employee was saved with success");
                    } else {
                        System.out.println("Couldn't save the Employee please try again ");
                    }
                }

            } while (!cont);

        }
    }








}
        */