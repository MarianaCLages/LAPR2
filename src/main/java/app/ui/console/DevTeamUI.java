package app.ui.console;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {
        //UI constructor
    }

    public void run() {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t Eduardo Novo - 1200737@isep.ipp.pt \n");
        System.out.print("\t Eduardo Sousa - 1200920@isep.ipp.pt \n");
        System.out.print("\t Joana Lima - 1201600@isep.ipp.pt \n");
        System.out.print("\t Mariana Lages - 1200902@isep.ipp.pt \n");
        System.out.print("\t Miguel Jordão - 1201487@isep.ipp.pt \n");
        System.out.print("\t Marcin Basinski - 1200300@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
