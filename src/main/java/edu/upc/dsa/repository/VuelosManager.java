package edu.upc.dsa.repository;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.time.LocalDateTime;
import java.util.List;

public interface VuelosManager {

    Piloto addPiloto(String id, String nombre, String apellido) throws InvalidIDException;

    Dron addDron(String id, String nombre, String fabricante, String modelo) throws InvalidIDException;

    List<Piloto> ordPilotosHorasVuelo();

    List<Dron> ordDronesHorasVuelo();

    void dronAlmacen(String idDron) throws DronMantenimientoException;

    Dron almacenReparar() throws EmptyAlmacenException;

    void addReserva(double duracion, List<Coordenada> trayecto, LocalDateTime fechaIn, String idPiloto, String idDron) throws InvalidIDException, ReservaIntervaloException;

    List<Reserva> listReservasPiloto(String idPiloto) throws InvalidIDException;

    List<Reserva> listReservasDron(String idDron) throws InvalidIDException;

    int getNum();
}
