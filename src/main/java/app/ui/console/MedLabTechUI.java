package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MedLabTechUI implements Runnable {
    public MedLabTechUI() {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
      //  options.add(new MenuItem("Create a new Sample", new RecordSampleUI()));
        options.add(new MenuItem("Record Results", new RecordResultsUI()));

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
