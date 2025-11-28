package gestor;
import DAO.estadiaDAO;

import javax.swing.*;

public class gestorEstadia {

    public static void iniciarEstadia(Long idReserva, String DNI){
        estadiaDAO estadiaDAO = new estadiaDAO();
        try{
        estadiaDAO.nuevaEstadia(idReserva, Long.parseLong(DNI));
            JOptionPane.showMessageDialog(
                    null,
                    "Estadía registrada correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        catch (NumberFormatException e) {
            System.out.println("ERROR EN ESTADIA");
        }
    }

    public void finalizarEstadia(long IDEstadia){}

    public void agregarConsumo(){}

}
