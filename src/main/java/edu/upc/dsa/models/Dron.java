package edu.upc.dsa.models;

import java.util.List;

public class Dron {
    public String id;
    public String nombre;
    public String fabricante;
    public String modelo;
    int horasVuelo;
    boolean mantenimiento;

    public List<Reserva> reservas;

    public Dron() {}

    public Dron(String id, String nombre, String fabricante, String modelo) {
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getFabricante() {return fabricante;}
    public void setFabricante(String fabricante) {this.fabricante = fabricante;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public int getHorasVuelo() {return horasVuelo;}
    public void setHorasVuelo(int horasVuelo) {this.horasVuelo = horasVuelo;}

    public boolean getMantenimiento() { return mantenimiento; }
    public void setMantenimiento(boolean mantenimiento) {this.mantenimiento = mantenimiento;}

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }
}
