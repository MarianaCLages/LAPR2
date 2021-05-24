package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

    public class ReceptionistUI implements Runnable{
        public ReceptionistUI()
        {
        }

        public void run()
        {
            List<MenuItem> options = new ArrayList<MenuItem>();
            options.add(new MenuItem("Create a new Client", new CreateClientUI()));
            options.add(new MenuItem("Create a new Test", new RegisterTestUI()));

            int option = 0;
            do
            {
                option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            }
            while (option != -1 );
        }
    }

