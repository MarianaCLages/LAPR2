package app.domain.model;


import java.io.Serializable;
import java.util.TimerTask;

public class SendReportTask extends TimerTask implements Serializable {
    public SendReportTask() {
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println("a");
    }
}
