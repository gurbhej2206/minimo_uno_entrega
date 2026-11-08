import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.repository.VuelosManager;
import edu.upc.dsa.repository.VuelosManagerImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class VuelosManagerTest {
    VuelosManager vm;

    @Before
    public void setUp() throws InvalidIDException{
        vm = VuelosManagerImpl.getInstance();

        vm.addPiloto("P1", "Name1", "Apellido1");
        vm.addPiloto("P2", "Name2", "Apellido2");
        vm.addPiloto("P3", "Name3", "Apellido3");
        vm.addPiloto("P4", "Name4", "Apellido4");

        vm.addDron("D1", "Dron1", "fabr1", "mod1");
        vm.addDron("D2", "Dron2", "fabr2", "mod2");
        vm.addDron("D3", "Dron3", "fabr3", "mod3");
        vm.addDron("D4", "Dron4", "fabr4", "mod4");
    }

    @After
    public void tearDown() {
        VuelosManagerImpl.getInstance().clear();
    }

    @Test
    public void testAddPiloto() throws InvalidIDException{
        Piloto piloto = vm.addPiloto("P5", "Name5", "Apellido5");
        assertNotNull(piloto);
        assertEquals("P5", piloto.getId());
        assertEquals("Name5", piloto.getNombre());
        assertEquals("Apellido5", piloto.getApellido());
        assertThrows(InvalidIDException.class, () -> vm.addPiloto(null, "Name6", "Apellido6"));
    }

    @Test
    public void testAddDron() throws InvalidIDException{
        Dron dron = vm.addDron("D5", "Dron5", "fabr5", "mod5");
        assertNotNull(dron);
        assertEquals("D5", dron.getId());
        assertEquals("Dron5", dron.getNombre());
        assertEquals("fabr5", dron.getFabricante());
        assertEquals("mod5", dron.getModelo());
        assertThrows(InvalidIDException.class, () -> vm.addDron("", "Dron6", "fabr6", "mod6"));
    }

    @Test
    public void testDronAlmacen() throws InvalidIDException, DronMantenimientoException {
        Dron dron = vm.addDron("D5", "Dron5", "fabr5", "mod5");
        vm.dronAlmacen("D5");

        assertTrue(dron.getMantenimiento());

        assertThrows(DronMantenimientoException.class, () -> vm.dronAlmacen("D5"));
    }

    @Test
    public void testAlmacenReparar() throws EmptyAlmacenException, DronMantenimientoException {
        vm.dronAlmacen("D1");
        vm.dronAlmacen("D3");
        vm.dronAlmacen("D2");

        Dron d1 = vm.almacenReparar() ;
        assertEquals("D1", d1.getId());

        Dron d2 = vm.almacenReparar();
        assertEquals("D3", d2.getId());

        Dron d3 = vm.almacenReparar();
        assertEquals("D2", d3.getId());

        assertThrows(EmptyAlmacenException.class, () -> vm.almacenReparar());
    }

    @Test
    public void testAddReserva() throws InvalidIDException, ReservaIntervaloException {

        List<Coordenada> trayecto = Arrays.asList(new Coordenada(2, 1), new Coordenada(3, 5));
        LocalDateTime fechaIn = LocalDateTime.of(2026, 11, 7, 21, 20);

        vm.addReserva(2, trayecto, fechaIn, "P4", "D4");
        assertEquals(1, vm.listReservasPiloto("P4").size());
        assertEquals(1,vm.listReservasDron("D4").size());

        List<Coordenada> trayecto2 = Arrays.asList(new Coordenada(5, 6), new Coordenada(10, 3));
        LocalDateTime fechaIn2 = LocalDateTime.of(2026, 11, 7, 21, 0);

        assertThrows(ReservaIntervaloException.class, () -> vm.addReserva(3, trayecto, fechaIn2, "P4", "D1"));
    }
}
