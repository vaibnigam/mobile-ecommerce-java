package ecommerce.loader;

public class DataLoader {

    private final ProductDataLoader productDataLoader;
    private final CustomerDataLoader customerDataLoader;

    public DataLoader(
            ProductDataLoader productDataLoader,
            CustomerDataLoader customerDataLoader) {

        if (productDataLoader == null) {
            throw new IllegalArgumentException(
                    "Product data loader cannot be null"
            );
        }

        if (customerDataLoader == null) {
            throw new IllegalArgumentException(
                    "Customer data loader cannot be null"
            );
        }

        this.productDataLoader = productDataLoader;
        this.customerDataLoader = customerDataLoader;
    }

    public void loadAll() {

        customerDataLoader.load();
        productDataLoader.load();
    }
}