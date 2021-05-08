package app.ui.console;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {

    }

    public void run() {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t Eduardo Novo - 1200737@isep.ipp.pt \n");
        System.out.printf("\t Eduardo Sousa - 1200920@isep.ipp.pt \n");
        System.out.printf("\t Joana Lima - 1201600@isep.ipp.pt \n");
        System.out.printf("\t Júlia Conceição - 1201614@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
