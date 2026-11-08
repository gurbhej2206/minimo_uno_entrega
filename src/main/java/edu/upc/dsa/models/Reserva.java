package edu.upc.dsa.models;

import java.time.LocalDateTime;
import java.util.List;

public class Reserva {
    private double duracion;
    private List<Coordenada> trayecto;
    private LocalDateTime fechaIn;
    private String idPiloto;
    private String idDron;

    public Reserva() {}

    public Reserva(double duracion, List<Coordenada> trayecto, LocalDateTime fechaIn, String idPiloto, String idDron) {
        this.duracion = duracion;
        this.trayecto = trayecto;
        this.fechaIn = fechaIn;
        this.idPiloto = idPiloto;
        this.idDron = idDron;
    }

    public LocalDateTime getFechaFin() {
        long minutes = Math.round(duracion * 60);
        return fechaIn.plusMinutes(minutes);
    }

    public boolean checkIntervalo(LocalDateTime fechaIn2, double duracion2) {
        long minutes = Math.round(duracion2 * 60);
        LocalDateTime nuevaFechaFin = fechaIn2.plusMinutes(minutes);

        return fechaIn2.isBefore(this.getFechaFin()) && nuevaFechaFin.isAfter(fechaIn);
    }

    public double getDuracion() {return duracion;}
    public void setDuracion(double duracion) {this.duracion = duracion;}

    public List<Coordenada> getTrayecto() {return trayecto;}
    public void setTrayecto(List<Coordenada> trayecto) {this.trayecto = trayecto;}

    public LocalDateTime getFechaIn() {return fechaIn;}
    public void setFechaIn(LocalDateTime fechaIn) {this.fechaIn = fechaIn;}

    public String getIdPiloto() {return idPiloto;}
    public void setIdPiloto(String idPiloto) {this.idPiloto = idPiloto;}

    public String getIdDron() {return idDron;}
    public void setIdDron(String idDron) {this.idDron = idDron;}
}
