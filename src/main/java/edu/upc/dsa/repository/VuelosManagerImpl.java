package edu.upc.dsa.repository;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.time.LocalDateTime;
import java.util.*;
import org.apache.log4j.Logger;

public class VuelosManagerImpl implements VuelosManager {
    final static Logger logger = Logger.getLogger(VuelosManagerImpl.class);
    private static VuelosManagerImpl instance;

    protected HashMap<String, Piloto> pilotos;
    protected HashMap<String, Dron> drones;
    protected Queue<Dron> almacen;
    protected List<Reserva> reservas;

    public static VuelosManagerImpl getInstance() {
        if (instance == null) {
            instance = new VuelosManagerImpl();
        }
        return instance;
    }

    public VuelosManagerImpl(){
        this.pilotos = new HashMap<>();
        this.drones = new HashMap<>();
        this.almacen = new LinkedList<>();
        this.reservas = new ArrayList<>();
        logger.info("VuelosManagerImpl() end: created new instance!");
    }

    public void clear() {
        pilotos.clear();
        drones.clear();
        almacen.clear();
        reservas.clear();
        logger.info("clear() end: cleared all instance values");
    }

    @Override
    public Piloto addPiloto(String id, String nombre, String apellido) throws InvalidIDException {
        logger.info("addPiloto() start: adding new piloto: "+id+"...");
        if (id == null || id.isEmpty()) {
            logger.error("addPiloto() end: Invalid ID!");
            throw new InvalidIDException();
        }
        Piloto pilot = new Piloto(id, nombre, apellido);
        pilotos.put(id, pilot);
        logger.info("addPiloto() end: added piloto: " + id);
        return pilot;
    }

    @Override
    public Dron addDron(String id, String nombre, String fabricante, String modelo) throws InvalidIDException {
        logger.info("addDron() start: adding new dron: "+id+"...");
        if (id == null || id.isEmpty()) {
            logger.error("addDron() end: Invalid ID!");
            throw new InvalidIDException();
        }
        Dron dron = new Dron(id, nombre, fabricante, modelo);
        drones.put(id, dron);
        logger.info("addDron() end: added dron: " + id);
        return dron;
    }

    @Override
    public List<Piloto> ordPilotosHorasVuelo() {
        logger.info("ordPilotosHorasVuelo() start:");
        List<Piloto> ord =new ArrayList<>(pilotos.values());
        ord.sort(Comparator.comparingDouble(Piloto::getHorasVuelo).reversed());
        logger.info("ordPilotosHorasVuelo() end: returned ordered list");
        return ord;
    }

    @Override
    public List<Dron> ordDronesHorasVuelo() {
        logger.info("ordDronesHorasVuelo() start:");
        List<Dron> ord =new ArrayList<>(drones.values());
        ord.sort(Comparator.comparingDouble(Dron::getHorasVuelo).reversed());
        logger.info("ordDronesHorasVuelo() end: returned ordered list");
        return ord;
    }

    @Override
    public void dronAlmacen(String idDron) throws DronMantenimientoException {
        logger.info("dronAlmacen() start: adding dron: "+idDron+" to almacen...");
        Dron dron = drones.get(idDron);
        if (dron.getMantenimiento())
        {
            logger.error("dronAlmacen() end: dron "+idDron+" is already in the almacen!");
            throw new DronMantenimientoException();
        }
        dron.setMantenimiento(true);
        logger.info("dronAlmacen() end: added dron: "+idDron+" to almacen");
        almacen.add(dron);
    }

    @Override
    public Dron almacenReparar() throws EmptyAlmacenException {
        logger.info("almacenReparar() start: ");
        if (almacen.isEmpty()) {
            logger.fatal("almacenReparar() end: No drons to repair!");
            throw new EmptyAlmacenException();
        }
        Dron dron = almacen.poll();
        String idDron = dron.getId();
        drones.get(idDron).setMantenimiento(false);
        logger.info("almacenReparar() end: repaired dron: "+idDron);
        return dron;
    }

    @Override
    public void addReserva(double duracion, List<Coordenada> trayecto, LocalDateTime fechaIn, String idPiloto, String idDron) throws InvalidIDException, ReservaIntervaloException {
        logger.info("addReserva() start: ");
        Piloto pilot = pilotos.get(idPiloto);
        Dron dron = drones.get(idDron);

        if((idPiloto == null || idPiloto.isEmpty()) || (idDron == null || idDron.isEmpty()))
        {
            logger.error("addReserva() end: Invalid ID!");
            throw new InvalidIDException();
        }
        else if (dron == null || pilot == null){
            logger.error("addReserva() end: Piloto or Dron not found!");
        }
        else if (dron.getMantenimiento()){
            logger.error("addReserva() end: El dron aún se encuentra en el almacen!");
        }
        else {
            List<Reserva> pilotoReservas = pilot.getReservas();
            List<Reserva> dronReservas  = dron.getReservas();

            for (Reserva r:pilotoReservas) {
                if(r.checkIntervalo(fechaIn, duracion)) {
                    logger.error("addReserva() end: El piloto "+idPiloto+" ya tiene una reserva en este intervalo!");
                    throw new ReservaIntervaloException();
                }
            }
            for (Reserva r:dronReservas) {
                if(r.checkIntervalo(fechaIn, duracion)) {
                    logger.error("addReserva() end: El dron "+idDron+" ya tiene una reserva en este intervalo!");
                    throw new ReservaIntervaloException();
                }
            }

            Reserva reserva = new Reserva(duracion, trayecto, fechaIn, pilot.getId(), dron.getId());
            reservas.add(reserva);
            dron.addReserva(reserva);
            pilot.addReserva(reserva);
            logger.info("addReserva() end: Reserva añadida correctamente para piloto "+idPiloto+" y dron "+idDron);
        }
    }

    @Override
    public List<Reserva> listReservasPiloto(String idPiloto) throws InvalidIDException {
        logger.info("listReservasPiloto() start:");
        if (idPiloto == null || idPiloto.isEmpty()) {
            logger.error("listReservasPiloto() end: Invalid ID!");
            throw new InvalidIDException();
        }
        logger.info("listReservasPiloto() end: Returned list");
        return pilotos.get(idPiloto).getReservas();
    }

    @Override
    public List<Reserva> listReservasDron(String idDron) throws InvalidIDException {
        logger.info("listReservasDron() start:");
        if (idDron == null || idDron.isEmpty()) {
            logger.error("listReservasDron() end: Invalid ID!");
            throw new InvalidIDException();
        }
        logger.info("listReservasDron() end: Returned list");
        return drones.get(idDron).getReservas();
    }

    @Override
    public int getNum() {
        if (pilotos.isEmpty() || drones.isEmpty())
        {
            return 0;
        }
        else {
            return 1;
        }
    }

}
