import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileSaverTests {
    private ProductsFileSaver saver;
    private ArrayList<Product> products;

    @Rule
    TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void openFile() throws IOException {
        File f = folder.newFile();
        saver = new ProductsFileSaver(f);

        products = new ArrayList<>();
        products.add(new Product("pen", 20));
        products.add(new Product("notebook", 40));
    }

    @Test
    public void readWriteProductsTest() {
        saver.saveProducts(products);
        ArrayList<Product> temp = saver.getProducts();

        assertEquals(products.size(), temp.size());

        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).name, temp.get(i).name);
            assertEquals(products.get(i).count, temp.get(i).count);
        }
    }
}
