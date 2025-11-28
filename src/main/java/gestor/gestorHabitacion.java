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
                String key = numero + "_" + actual;
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

    public void ocuparHabitacion(List<Object[]> reservaSeleccionada){
        actualizarEstadoHabitacion(reservaSeleccionada,"Ocupado");
    }


    public void actualizarEstadoHabitacion(List<Object[]> datos,String estado) {
        habitacionDAO habitacionDAO = new habitacionDAO();
        for (Object[] fila : datos) {
            long idHabitacion = (long) fila[0];
            String fechaCompleta = fila[1].toString().trim();         // ejemplo: "2025-08-01 00:00"
            String soloFecha = fechaCompleta.substring(0, 10); // "2025-08-01"
            LocalDate desde;
            if (soloFecha.contains("-")) {
                // Formato tipo "2025-08-01"
                desde = LocalDate.parse(soloFecha);
            } else {
                // Formato tipo "18/07/2025"
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                desde = LocalDate.parse(soloFecha, f);
            }

            String fechaCompleta2 = fila[2].toString();
            String soloFecha2 = fechaCompleta2.substring(0, 10);
            LocalDate hasta;
            if (soloFecha2.contains("-")) {
                // Formato tipo "2025-08-01"
                hasta = LocalDate.parse(soloFecha2);
            } else {
                // Formato tipo "18/07/2025"
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                hasta = LocalDate.parse(soloFecha2, f);
            }

            habitacionDAO.modificarEstadoHabitacion(idHabitacion, estado, desde, hasta);
        }
    }
    private LocalDate parseFecha(Object valor) {
        if (valor == null) return null;

        String fecha = valor.toString().trim();

        try {
            // FORMATO: yyyy-MM-dd
            if (fecha.contains("-")) {
                return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            // FORMATO: dd/MM/yyyy
            if (fecha.contains("/")) {
                return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Formato de fecha desconocido: " + fecha);
    }
}
