package edu.upc.dsa.repository;

import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Reserva;

import java.util.List;
import java.util.Date;

public interface VuelosManager {

    Piloto addPiloto(String id, String nombre, String apellido);

    Dron addDron(String id, String nombre, String fabricante, String modelo);

    List<Piloto> ordPilotosHorasVuelo();

    List<Dron> ordDronesHorasVuelo();

    void dronAlmacen(String idDron);

    Dron almacenReparar();

    void addReserva(String idPiloto, String idDron, Date fecha, int duracion, String posIn, String posDst);

    List<Reserva> listReservasPiloto(String idPiloto);

    List<Reserva> listReservasDron(String idDron);

    int getNum();
}
