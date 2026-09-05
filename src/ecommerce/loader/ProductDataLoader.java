package ecommerce.loader;

import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

public final class ProductDataLoader {

    private ProductDataLoader() {
    }

    public static void loadProducts(ProductRepository repository) {

        String[][] mobiles = {
            {"Apple","iPhone 15","59999","6","128"},
            {"Apple","iPhone 15 Plus","69999","6","128"},
            {"Apple","iPhone 15 Pro","119999","8","128"},
            {"Apple","iPhone 15 Pro Max","139999","8","256"},
            {"Apple","iPhone 16","69999","8","128"},
            {"Apple","iPhone 16 Plus","79999","8","128"},
            {"Apple","iPhone 16 Pro","119999","8","256"},
            {"Apple","iPhone 16 Pro Max","139999","8","256"},

            {"Samsung","Galaxy S24","74999","8","256"},
            {"Samsung","Galaxy S24 Plus","99999","12","256"},
            {"Samsung","Galaxy S24 Ultra","129999","12","256"},
            {"Samsung","Galaxy S23","64999","8","128"},
            {"Samsung","Galaxy S23 Ultra","99999","12","256"},
            {"Samsung","Galaxy A55","39999","8","128"},
            {"Samsung","Galaxy A35","29999","8","128"},
            {"Samsung","Galaxy M55","26999","8","128"},
            {"Samsung","Galaxy M35","19999","8","128"},
            {"Samsung","Galaxy F55","24999","8","256"},
            {"Samsung","Galaxy A16","14999","6","128"},

            {"OnePlus","OnePlus 12","64999","12","256"},
            {"OnePlus","OnePlus 12R","39999","8","128"},
            {"OnePlus","OnePlus 11","56999","16","256"},
            {"OnePlus","OnePlus Nord 4","29999","8","128"},
            {"OnePlus","OnePlus Nord CE4","24999","8","128"},
            {"OnePlus","OnePlus Nord CE4 Lite","19999","8","128"},
            {"OnePlus","OnePlus Nord 3","27999","8","128"},
            {"OnePlus","OnePlus Nord CE3","21999","8","128"},

            {"Google","Pixel 8","54999","8","128"},
            {"Google","Pixel 8 Pro","99999","12","256"},
            {"Google","Pixel 8a","52999","8","128"},
            {"Google","Pixel 9","79999","12","128"},
            {"Google","Pixel 9 Pro","109999","16","256"},
            {"Google","Pixel 7a","39999","8","128"},

            {"Xiaomi","Xiaomi 14","69999","12","512"},
            {"Xiaomi","Xiaomi 14 Civi","44999","8","256"},
            {"Xiaomi","Redmi Note 13","19999","6","128"},
            {"Xiaomi","Redmi Note 13 Pro","29999","8","256"},
            {"Xiaomi","Redmi Note 13 Pro Plus","31999","12","256"},
            {"Xiaomi","Redmi Note 14 Pro","32999","8","256"},
            {"Xiaomi","Redmi Note 14 Pro Plus","36999","12","512"},

            {"Realme","Realme GT 6","40999","12","256"},
            {"Realme","Realme GT 6T","34999","8","128"},
            {"Realme","Realme 12 Pro","29999","8","128"},
            {"Realme","Realme 12 Pro Plus","32999","12","256"},
            {"Realme","Realme P1","17999","8","128"},
            {"Realme","Realme P2 Pro","21999","8","256"},
            {"Realme","Realme Narzo 70 Pro","24999","8","128"},
            {"Realme","Realme Narzo 70","17999","8","128"},

            {"Vivo","Vivo X100","63999","16","512"},
            {"Vivo","Vivo X100 Pro","89999","16","512"},
            {"Vivo","Vivo V30","33999","8","128"},
            {"Vivo","Vivo V30 Pro","41999","8","256"},
            {"Vivo","Vivo V40","34999","8","128"},
            {"Vivo","Vivo V40 Pro","49999","12","256"},
            {"Vivo","Vivo Y200","21999","8","128"},
            {"Vivo","Vivo Y200 Pro","24999","8","128"},

            {"Oppo","Oppo Find X7","69999","12","256"},
            {"Oppo","Oppo Reno 11","32999","8","128"},
            {"Oppo","Oppo Reno 11 Pro","39999","12","256"},
            {"Oppo","Oppo Reno 12","35999","8","256"},
            {"Oppo","Oppo Reno 12 Pro","44999","12","256"},
            {"Oppo","Oppo F27 Pro Plus","32999","8","128"},
            {"Oppo","Oppo F25 Pro","23999","8","128"},

            {"Motorola","Motorola Edge 50 Pro","31999","8","256"},
            {"Motorola","Motorola Edge 50 Fusion","22999","8","128"},
            {"Motorola","Motorola Edge 50 Ultra","54999","12","512"},
            {"Motorola","Moto G85","17999","8","128"},
            {"Motorola","Moto G64","14999","8","128"},
            {"Motorola","Motorola Edge 40","24999","8","256"},
            {"Motorola","Motorola Edge 40 Neo","22999","8","128"},

            {"Nothing","Nothing Phone 2","39999","12","256"},
            {"Nothing","Nothing Phone 2a","24999","8","128"},
            {"Nothing","Nothing Phone 2a Plus","27999","8","256"},
            {"Nothing","Nothing Phone 1","29999","8","128"},

            {"Poco","Poco F6","29999","8","256"},
            {"Poco","Poco F6 Pro","39999","12","512"},
            {"Poco","Poco X6","22999","8","256"},
            {"Poco","Poco X6 Pro","26999","8","256"},
            {"Poco","Poco X5 Pro","19999","8","256"},

            {"iQOO","iQOO 12","52999","12","256"},
            {"iQOO","iQOO Neo 9 Pro","34999","8","128"},
            {"iQOO","iQOO Z9","19999","8","128"},
            {"iQOO","iQOO Z9x","15999","8","128"},
            {"iQOO","iQOO Neo 7 Pro","29999","8","128"},
            {"iQOO","iQOO Z7 Pro","21999","8","128"},

            {"Honor","Honor 200","34999","8","256"},
            {"Honor","Honor 200 Pro","44999","12","512"},
            {"Honor","Honor X9b","24999","8","256"},
            {"Honor","Honor 90","27999","8","256"},
            {"Honor","Honor 90 Pro","39999","12","256"},

            {"Lava","Lava Blaze Curve","17999","8","128"},
            {"Lava","Lava Agni 2","21999","8","256"},
            {"Lava","Lava Blaze 5G","12999","6","128"},

            {"Sony","Sony Xperia 1 V","99999","12","256"},
            {"Sony","Sony Xperia 10 V","39999","8","128"},
            {"Sony","Sony Xperia 5 V","79999","8","128"},

            {"Asus","Asus ROG Phone 8","94999","16","512"},
            {"Asus","Asus Zenfone 11 Ultra","89999","12","256"},
            {"Asus","Asus ROG Phone 7","74999","12","256"},

            {"Nokia","Nokia X30","35999","8","256"},
            {"Nokia","Nokia G60","24999","6","128"}
        };

        long id = 1;

        for (String[] mobile : mobiles) {
            repository.save(new Product(
                    id++,
                    mobile[0],
                    mobile[1],
                    Double.parseDouble(mobile[2]),
                    Integer.parseInt(mobile[3]),
                    Integer.parseInt(mobile[4]),
                    10
            ));
        }

        System.out.println(mobiles.length + " mobile products loaded.");
    }
}
