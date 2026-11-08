package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    private String id;
    private String nombre;
    private String apellido;
    private double horasVuelo;

    private List<Reserva> reservas;

    public Piloto() {}

    public Piloto(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public double getHorasVuelo() {return horasVuelo;}
    public void setHorasVuelo(double horasVuelo) {this.horasVuelo = horasVuelo;}

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }
}
