package edu.upc.dsa.models;

import java.util.List;

public class Piloto {
    public String id;
    public String nombre;
    public String apellido;
    int horasVuelo;

    public List<Reserva> reservas;

    public Piloto() {}

    public Piloto(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
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

    public int getHorasVuelo() {return horasVuelo;}
    public void setHorasVuelo(int horasVuelo) {this.horasVuelo = horasVuelo;}

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }
}
