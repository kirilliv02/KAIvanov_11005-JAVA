import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SkladToFileTests {
    private Sklad sklad;
    private ArrayList<Product> products;

    @Before
    public void prepareProducts() {
        products = new ArrayList<>();
        products.add(new Product("pen", 20));
        products.add(new Product("notebook", 40));

        ProductsFileSaver saver = new ProductsFileSaver("orders.txt");
        saver.saveProducts(products);

        sklad = new Sklad(saver);
    }

    @Test
    public void readToSkladTest() {
        assertEquals(products.size(), sklad.products.size());

        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).name, sklad.products.get(i).name);
            assertEquals(products.get(i).count, sklad.products.get(i).count);
        }
    }

    @Test
    public void createProductTest() {
        products.add(new Product("pen", 20));
        sklad.createNewProduct("pen", 20);

        ArrayList<Product> temp = sklad.saver.getProducts();

        assertEquals(products.size(), temp.size());

        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).name, temp.get(i).name);
            assertEquals(products.get(i).count, temp.get(i).count);
        }
    }
}
