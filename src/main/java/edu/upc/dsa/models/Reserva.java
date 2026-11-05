package edu.upc.dsa.models;

import java.util.Date;

public class Reserva {
    int duracion;
    String posIn;
    String posDst;
    Date fecha;
    public String idPiloto;
    public String idDron;

    public Reserva() {}

    public Reserva(int duracion, String posIn, String posDst, Date fecha, String idPiloto, String idDron) {
        this.duracion = duracion;
        this.posIn = posIn;
        this.posDst = posDst;
        this.fecha = fecha;
        this.idPiloto = idPiloto;
        this.idDron = idDron;
    }

    public int getDuracion() {return duracion;}
    public void setDuracion(int duracion) {this.duracion = duracion;}

    public String getPosIn() {return posIn;}
    public void setPosIn(String posIn) {this.posIn = posIn;}

    public String getPosDst() {return posDst;}
    public void setPosDst(String posDst) {this.posDst = posDst;}

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}

    public String getIdPiloto() {return idPiloto;}
    public void setIdPiloto(String idPiloto) {this.idPiloto = idPiloto;}

    public String getIdDron() {return idDron;}
    public void setIdDron(String idDron) {this.idDron = idDron;}
}
