import client.model.FileHandler;
import client.view.CGUI;
import server.model.Server;
import server.view.SGUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SGUI sgui = new SGUI();
        CGUI cgui1 = new CGUI(sgui.getServer());
        CGUI cgui2 = new CGUI(sgui.getServer());

    }

}