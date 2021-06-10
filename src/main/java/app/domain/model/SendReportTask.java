package app.domain.model;


import java.util.TimerTask;

public class SendReportTask extends TimerTask {
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
