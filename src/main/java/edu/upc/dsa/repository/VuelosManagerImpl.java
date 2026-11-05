package edu.upc.dsa.repository;

import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;

import java.util.*;
import org.apache.log4j.Logger;

public class VuelosManagerImpl implements VuelosManager {
    final static Logger logger = Logger.getLogger(VuelosManagerImpl.class);
    private static VuelosManagerImpl instance;

    protected HashMap<String, Piloto> pilotos;
    private HashMap<String, Dron> drones;
    private Queue<Dron> almacen;
    private List<Reserva> reservas;

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
    }

    public void clear() {
        pilotos.clear();
        drones.clear();
        almacen.clear();
        reservas.clear();
    }

    @Override
    public Piloto addPiloto(String id, String nombre, String apellido) {
        try{
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("Id cannot be empty");
            }
            Piloto pilot = new Piloto(id, nombre, apellido);
            pilotos.put(id, pilot);
            logger.info("addPiloto(): added piloto_id=" + id);
            return pilot;
        } catch (IllegalArgumentException e) {
            logger.error("addPilot(): " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Dron addDron(String id, String nombre, String fabricante, String modelo) {
        try{
            if (id == null || id.isEmpty()) {
                throw new IllegalArgumentException("Id cannot be empty");
            }
            Dron dron = new Dron(id, nombre, fabricante, modelo);
            drones.put(id, dron);
            logger.info("addDron(): added dron_id=" + id);
            return dron;
        } catch (IllegalArgumentException e) {
            logger.error("addDron(): " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Piloto> ordPilotosHorasVuelo() {
        List<Piloto> ord =new ArrayList<>(pilotos.values());
        ord.sort(Comparator.comparingDouble(Piloto::getHorasVuelo).reversed());
        logger.info("ordPilotosHorasVuelo(): returning ordered list");
        return ord;
    }

    @Override
    public List<Dron> ordDronesHorasVuelo() {
        List<Dron> ord =new ArrayList<>(drones.values());
        ord.sort(Comparator.comparingDouble(Dron::getHorasVuelo).reversed());
        logger.info("ordDronesHorasVuelo(): returning ordered list");
        return ord;
    }

    @Override
    public void dronAlmacen(String idDron) {
        Dron dron = drones.get(idDron);
        dron.setMantenimiento(true);
        logger.info("dronAlmacen(): added dron: "+idDron+" to almacen");
        almacen.add(dron);
    }

    @Override
    public Dron almacenReparar() {
        Dron dron = almacen.poll();
        String idDron = dron.getId();
        drones.get(idDron).setMantenimiento(false);
        logger.info("almacenReparar(): repaired dron: "+idDron);
        return dron;
    }

    @Override
    public void addReserva(String idPiloto, String idDron, Date fecha, int duracion, String posIn, String posDst) {
        Piloto pilot = pilotos.get(idPiloto);
        Dron dron = drones.get(idDron);

        List<Reserva> dronReservas = dron.getReservas();

        if (dron == null || pilot == null){
            logger.info("addReserva(): Not found!");
        }
        else if (dron.getMantenimiento()){
            logger.info("addReserva(): El dron aún se encuentra en el almacen!");
        }
        else {
            Date newFecha = new Date(fecha.getTime() + duracion * 60 * 60 * 1000);
            for (Reserva reserva : reservas){
                if (reserva.getIdDron().equals(dron.getId()) || reserva.getIdPiloto().equals(pilot.getId())){
                    Date newDuracion = new Date(reserva.getFecha().getTime() + reserva.getDuracion() * 60 * 60 * 1000);
                    if ((fecha.before(newDuracion) && newFecha.after(reserva.getFecha()))){
                        logger.info("addReserva(): Ya hay una reserva!");
                    }
                    else {
                        Reserva newReserva = new Reserva(duracion, posIn, posDst, fecha, pilot.id, dron.id);
                        reservas.add(newReserva);
                        dron.addReserva(newReserva);
                        pilot.addReserva(newReserva);
                        logger.info("addReserva(): Reserva añadida correctamente!");
                    }
                }
            }
        }
    }

    @Override
    public List<Reserva> listReservasPiloto(String idPiloto) {
        logger.info("listReservasPiloto(): Returned list");
        return pilotos.get(idPiloto).getReservas();
    }

    @Override
    public List<Reserva> listReservasDron(String idDron) {
        logger.info("listReservasDron(): Returned list");
        return drones.get(idDron).getReservas();
    }

    @Override
    public int getNum() {
        if (pilotos.size() == 0 && drones.size() == 0)
        {
            return 0;
        }
        else {
            return 1;
        }
    }

}
