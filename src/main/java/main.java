
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import interfaz.loginInterfaz;

import java.awt.*;

public class main {
    public static void main(String[] args) {
        /*TestConnection testConnection = new TestConnection();
        testConnection.testConnection(); */

        // Establecer el look and feel
        try {UIManager.setLookAndFeel(new FlatLightLaf());}
        catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}

        SwingUtilities.invokeLater(() -> {
            loginInterfaz loginInterfaz = new loginInterfaz();
            loginInterfaz.setVisible(true);
        });
    }
}