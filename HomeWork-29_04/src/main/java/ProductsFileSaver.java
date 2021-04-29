import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductsFileSaver implements ProductsSaver {
    private File file;

    public ProductsFileSaver(File file) {
        this.file = file;
    }

    public ProductsFileSaver(String fileName) {
        file = new File(fileName);
    }

    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> result = new ArrayList<>();

        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file))) {
            Product[] temp = (Product[]) oin.readObject();

            result.addAll(Arrays.asList(temp));
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void saveProducts(ArrayList<Product> products) {
        try (ObjectOutputStream oin = new ObjectOutputStream(new FileOutputStream(file))) {
            Product[] temp = new Product[products.size()];
            for (int i = 0; i < products.size(); i++) {
                temp[i] = products.get(i);
            }

            oin.writeObject(temp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
