import java.util.ArrayList;

public class SkladTests {
    ProductsSaver saver;
    ArrayList<Product> products;

    public SkladTests(ProductsSaver saver) {
        this.saver = saver;
        products = saver.getProducts();
    }

    public void createNewProduct(String name, int count) {
        Product product = new Product(name, count);
        products.add(product);
        saver.saveProducts(products);
    }

    public Product getProduct(String name) {
        for (Product p : products) {
            if (p.name.equals(name))
                return p;
        }

        return null;
    }

    public void deleteProduct(String name) {
        Product p = getProduct(name);

        if (p != null)
            products.remove(p);

        saver.saveProducts(products);
    }

    public void printAllProducts() {
        for (Product p : products) {
            System.out.println(p.name + ": " + p.count);
        }
    }
}
