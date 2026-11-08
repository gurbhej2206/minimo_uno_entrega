package edu.upc.dsa.models;

public class Coordenada {
    double lat;
    double len;

    public Coordenada() {}

    public Coordenada(double lat, double len) {
        this.lat = lat;
        this.len = len;
    }

    public double getLat() {return lat;}
    public void setLat(double lat) {this.lat = lat;}

    public Double getLen() {return len;}
    public void setLen(double len) {this.len = len;}
}