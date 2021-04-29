public class Main {
    public static void main(String[] args) {
        ProductsFileSaver fileSaver = new ProductsFileSaver("orders.txt");
        Sklad sklad = new Sklad(fileSaver);

        sklad.createNewProduct("pen", 20);
        sklad.createNewProduct("notebook", 40);

        sklad.printAllProducts();
    }
}
