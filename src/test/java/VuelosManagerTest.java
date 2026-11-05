import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.repository.VuelosManager;
import edu.upc.dsa.repository.VuelosManagerImpl;

import static org.junit.Assert.*;

public class VuelosManagerTest {
    VuelosManager vm;

    @Before
    public void setUp() {
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
    public void testAddPiloto() {
        Piloto piloto = vm.addPiloto("P5", "Name5", "Apellido5");
        assertNotNull(piloto);
        assertEquals("P5", piloto.getId());
        assertEquals("Name5", piloto.getNombre());
        assertEquals("Apellido5", piloto.getApellido());
    }

    @Test
    public void testAddDron() {
        Dron dron = vm.addDron("D5", "Dron5", "fabr5", "mod5");
        assertNotNull(dron);
        assertEquals("D5", dron.getId());
        assertEquals("Dron5", dron.getNombre());
        assertEquals("fabr5", dron.getFabricante());
        assertEquals("mod5", dron.getModelo());
    }

    @Test
    public void dronAlmacen() {
        Dron dron = vm.addDron("D5", "Dron5", "fabr5", "mod5");
        vm.dronAlmacen("D5");
        assertTrue(dron.getMantenimiento());
    }

    @Test
    public void almacenReparar() {
        vm.dronAlmacen("D1");
        vm.dronAlmacen("D2");
        vm.dronAlmacen("D3");
        Dron dron = vm.almacenReparar();
        assertEquals("D1", dron.getId());
    }

}
