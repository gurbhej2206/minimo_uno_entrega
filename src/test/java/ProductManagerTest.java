import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.repository.ProductManager;
import edu.upc.dsa.repository.ProductManagerImpl;

import java.util.List;

public class ProductManagerTest {
    ProductManager pm;

    @Before
    public void setUp() {
        pm = ProductManagerImpl.getInstance();
        pm.addProduct("C1", "Coca-cola zero", 2);
        pm.addProduct("C2", "Coca-cola", 2.5);
        pm.addProduct("B1", "Lomo queso", 3);
        pm.addProduct("B2", "bacon queso", 3.5);
        pm.addProduct("D1", "Donut", 3);
        pm.addProduct("D2", "Donut xoco", 3.5);
        pm.addProduct("CF1", "Cafe", 2);
        pm.addProduct("CF2", "Tallat", 2.25);

        pm.addUser("NIF1", "El Chaval", "de la Peca", "chaval.peca@gmail.com");
        pm.addUser("NIF2", "Manolo", "Vinilo", "manolo.vinilo@gmail.com");
        pm.addUser("NIF3", "Paco", "Synth", "paco.synth@gmail.com");
        pm.addUser("NIF4", "Rafa", "Theremín", "rafa.theremin@gmail.com");
        pm.addUser("NIF5", "Lola", "Neón", "lola.neon@gmail.com");
        pm.addUser("NIF6", "Toni", "Cassette", "toni.cassette@gmail.com");
        pm.addUser("NIF7", "Juanjo", "Kazoo", "juanjo.kazoo@gmail.com");
        pm.addUser("NIF8", "Santi", "Prog", "santi.prog@gmail.com");
        pm.addUser("NIF9", "Marta", "Moog", "marta.moog@gmail.com");
        pm.addUser("NIF10", "Carlos", "Distorsión", "carlos.distorsion@gmail.com");
        pm.addUser("NIF11", "Nuria", "Vinilo", "nuria.vinilo@gmail.com");
        pm.addUser("NIF12", "Óscar", "Synthwave", "oscar.synthwave@gmail.com");
        pm.addUser("NIF13", "David", "Frikipop", "david.frikipop@gmail.com");
        pm.addUser("NIF14", "Miguel", "Guitarra", "miguel.guitarra@gmail.com");
        pm.addUser("NIF15", "Ferran", "Rockabilly", "ferran.rockabilly@gmail.com");
    }

    @After
    public void tearDown() {
        ProductManagerImpl.getInstance().clear();
    }

    @Test
    public void addProductTest() {
        Assert.assertEquals(8, pm.numProducts());
        pm.addProduct("W1", "Aigua", 1.5);
        pm.addProduct("W2", "Aigua amb gas", 2.5);
        Assert.assertEquals(10, pm.numProducts());
    }
}
