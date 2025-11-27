package gestor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.habitacionDAO;
import entidad.estadoHabitacion;
import entidad.habitacion;

public class gestorHabitacion {

    // pedirle al DAO todas las habitaciones que sean del tipo, para poner las columnas de las tablas
    public List<String> obtenerColumnas(String tipo){
        habitacionDAO habitacionDAO = new habitacionDAO();

        List<habitacion> habitaciones = habitacionDAO.buscarPorTipo(tipo);
        List<String> resultado = new ArrayList<>();
        resultado.add("Fecha");
        for (habitacion h : habitaciones) {
            resultado.add(h.getNumero());
        }
        // resultado = list<String> con ["Fecha", IE01, IE02, IE03,...]
        return resultado;
    }

    // pedirle al DAO todas las habitaciones de tipo 'tipo', y que retorne
    public List<Object[]> obtenerDisponibilidad(LocalDate desde, LocalDate hasta, String tipo){
        habitacionDAO habitacionDAO = new habitacionDAO();
        List<habitacion> habitaciones = habitacionDAO.buscarPorTipo(tipo);
        List<estadoHabitacion> estados = habitacionDAO.obtenerEstadosRango(tipo, desde, hasta);

        Map<String, String> mapa = new HashMap<>();
        for (estadoHabitacion e : estados) {
            String key = e.getNumero() + "_" + e.getFecha();
            mapa.put(key, e.getEstado());
        }

        List<Object[]> filas = new ArrayList<>();
        // Recorrer fechas
        LocalDate actual = desde;
        while (!actual.isAfter(hasta)) {
            Object[] fila = new Object[habitaciones.size() + 1];
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fila[0] = actual.format(f);
            //Por cada habitación, buscar estado para ese día
            for (int i = 0; i < habitaciones.size(); i++) {
                String numero = habitaciones.get(i).getNumero();
                String key = numero + "_" + actual.toString();
                String estado = mapa.getOrDefault(key, "libre");
                fila[i + 1] = estado;  // libre / reservado / ocupado / fuera
            }

            filas.add(fila);
            actual = actual.plusDays(1);
        }

        return filas;
    }

    public long obtenerIdPorNumero(String numero) {
        habitacionDAO habitacionDAO = new habitacionDAO();

        return habitacionDAO.obtenerIdPorNumero(numero);
    }

    public void ocuparHabitacion(){}

    private void mostrarEstado(){}

    public void actualizarEstadoHabitacion(List<Object[]> datos) {
        habitacionDAO habitacionDAO = new habitacionDAO();
        for (Object[] fila : datos) {
            long idHabitacion = (long) fila[0];
            LocalDate desde = (LocalDate) fila[1];
            LocalDate hasta = (LocalDate) fila[2];
            habitacionDAO.modificarEstadoHabitacion(idHabitacion, "reservado", desde, hasta);
        }
    }
}
