package ecommerce.loader;

import ecommerce.enums.ProductCategory;
import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public class ProductDataLoader {

    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        if (productRepository == null) {
            throw new IllegalArgumentException(
                    "Product repository cannot be null"
            );
        }

        this.productRepository = productRepository;
    }

    public void load() {

        List<Product> products = List.of(

                // =========================
                // APPLE - 1 to 15
                // =========================

                create(1, "Apple", "iPhone 16",
                        "Apple iPhone 16 smartphone",
                        "79999", ProductCategory.SMARTPHONE, 25, true),

                create(2, "Apple", "iPhone 16 Plus",
                        "Apple iPhone 16 Plus smartphone",
                        "89999", ProductCategory.SMARTPHONE, 18, true),

                create(3, "Apple", "iPhone 16 Pro",
                        "Apple iPhone 16 Pro smartphone",
                        "109999", ProductCategory.SMARTPHONE, 15, true),

                create(4, "Apple", "iPhone 16 Pro Max",
                        "Apple iPhone 16 Pro Max smartphone",
                        "119999", ProductCategory.SMARTPHONE, 12, true),

                create(5, "Apple", "iPhone 15",
                        "Apple iPhone 15 smartphone",
                        "69999", ProductCategory.SMARTPHONE, 30, true),

                create(6, "Apple", "iPhone 15 Plus",
                        "Apple iPhone 15 Plus smartphone",
                        "79999", ProductCategory.SMARTPHONE, 20, true),

                create(7, "Apple", "iPhone 15 Pro",
                        "Apple iPhone 15 Pro smartphone",
                        "99999", ProductCategory.SMARTPHONE, 10, true),

                create(8, "Apple", "iPhone 15 Pro Max",
                        "Apple iPhone 15 Pro Max smartphone",
                        "109999", ProductCategory.SMARTPHONE, 8, true),

                create(9, "Apple", "iPhone 14",
                        "Apple iPhone 14 smartphone",
                        "59999", ProductCategory.SMARTPHONE, 22, true),

                create(10, "Apple", "iPhone 14 Plus",
                        "Apple iPhone 14 Plus smartphone",
                        "69999", ProductCategory.SMARTPHONE, 15, true),

                create(11, "Apple", "iPad Air",
                        "Apple iPad Air tablet",
                        "59900", ProductCategory.TABLET, 14, true),

                create(12, "Apple", "iPad Pro",
                        "Apple iPad Pro tablet",
                        "99900", ProductCategory.TABLET, 8, true),

                create(13, "Apple", "Apple Watch Series 10",
                        "Apple Watch Series 10 smartwatch",
                        "46900", ProductCategory.SMARTWATCH, 16, true),

                create(14, "Apple", "AirPods Pro 2",
                        "Apple AirPods Pro wireless earbuds",
                        "24900", ProductCategory.ACCESSORY, 30, true),

                create(15, "Apple", "AirPods 4",
                        "Apple AirPods wireless earbuds",
                        "12900", ProductCategory.ACCESSORY, 35, true),

                // =========================
                // SAMSUNG - 16 to 30
                // =========================

                create(16, "Samsung", "Galaxy S25",
                        "Samsung Galaxy S25 smartphone",
                        "74999", ProductCategory.SMARTPHONE, 25, true),

                create(17, "Samsung", "Galaxy S25 Plus",
                        "Samsung Galaxy S25 Plus smartphone",
                        "84999", ProductCategory.SMARTPHONE, 18, true),

                create(18, "Samsung", "Galaxy S25 Ultra",
                        "Samsung Galaxy S25 Ultra smartphone",
                        "129999", ProductCategory.SMARTPHONE, 12, true),

                create(19, "Samsung", "Galaxy S24",
                        "Samsung Galaxy S24 smartphone",
                        "64999", ProductCategory.SMARTPHONE, 20, true),

                create(20, "Samsung", "Galaxy S24 Plus",
                        "Samsung Galaxy S24 Plus smartphone",
                        "74999", ProductCategory.SMARTPHONE, 15, true),

                create(21, "Samsung", "Galaxy S24 Ultra",
                        "Samsung Galaxy S24 Ultra smartphone",
                        "109999", ProductCategory.SMARTPHONE, 10, true),

                create(22, "Samsung", "Galaxy A55",
                        "Samsung Galaxy A55 smartphone",
                        "39999", ProductCategory.SMARTPHONE, 30, true),

                create(23, "Samsung", "Galaxy A35",
                        "Samsung Galaxy A35 smartphone",
                        "29999", ProductCategory.SMARTPHONE, 35, true),

                create(24, "Samsung", "Galaxy A25",
                        "Samsung Galaxy A25 smartphone",
                        "24999", ProductCategory.SMARTPHONE, 40, true),

                create(25, "Samsung", "Galaxy M35",
                        "Samsung Galaxy M35 smartphone",
                        "19999", ProductCategory.SMARTPHONE, 28, true),

                create(26, "Samsung", "Galaxy Tab S10",
                        "Samsung Galaxy Tab S10 tablet",
                        "89999", ProductCategory.TABLET, 12, true),

                create(27, "Samsung", "Galaxy Tab S9 FE",
                        "Samsung Galaxy Tab S9 FE tablet",
                        "44999", ProductCategory.TABLET, 16, true),

                create(28, "Samsung", "Galaxy Watch 7",
                        "Samsung Galaxy Watch 7 smartwatch",
                        "29999", ProductCategory.SMARTWATCH, 20, true),

                create(29, "Samsung", "Galaxy Buds 3 Pro",
                        "Samsung Galaxy Buds 3 Pro earbuds",
                        "19999", ProductCategory.ACCESSORY, 25, true),

                create(30, "Samsung", "Galaxy Buds 3",
                        "Samsung Galaxy Buds 3 wireless earbuds",
                        "14999", ProductCategory.ACCESSORY, 30, true),

                // =========================
                // ONEPLUS - 31 to 40
                // =========================

                create(31, "OnePlus", "OnePlus 13",
                        "OnePlus 13 flagship smartphone",
                        "69999", ProductCategory.SMARTPHONE, 20, true),

                create(32, "OnePlus", "OnePlus 13R",
                        "OnePlus 13R performance smartphone",
                        "42999", ProductCategory.SMARTPHONE, 25, true),

                create(33, "OnePlus", "OnePlus 12",
                        "OnePlus 12 flagship smartphone",
                        "59999", ProductCategory.SMARTPHONE, 18, true),

                create(34, "OnePlus", "OnePlus 12R",
                        "OnePlus 12R smartphone",
                        "39999", ProductCategory.SMARTPHONE, 22, true),

                create(35, "OnePlus", "OnePlus Nord 4",
                        "OnePlus Nord 4 smartphone",
                        "29999", ProductCategory.SMARTPHONE, 35, true),

                create(36, "OnePlus", "OnePlus Nord CE4",
                        "OnePlus Nord CE4 smartphone",
                        "24999", ProductCategory.SMARTPHONE, 40, true),

                create(37, "OnePlus", "OnePlus Pad 2",
                        "OnePlus Pad 2 tablet",
                        "39999", ProductCategory.TABLET, 15, true),

                create(38, "OnePlus", "OnePlus Watch 2",
                        "OnePlus Watch 2 smartwatch",
                        "24999", ProductCategory.SMARTWATCH, 18, true),

                create(39, "OnePlus", "OnePlus Buds Pro 3",
                        "OnePlus Buds Pro 3 wireless earbuds",
                        "11999", ProductCategory.ACCESSORY, 30, true),

                create(40, "OnePlus", "OnePlus Nord Buds 3",
                        "OnePlus Nord Buds 3 wireless earbuds",
                        "2999", ProductCategory.ACCESSORY, 45, true),

                // =========================
                // GOOGLE - 41 to 50
                // =========================

                create(41, "Google", "Pixel 9",
                        "Google Pixel 9 smartphone",
                        "79999", ProductCategory.SMARTPHONE, 15, true),

                create(42, "Google", "Pixel 9 Pro",
                        "Google Pixel 9 Pro smartphone",
                        "109999", ProductCategory.SMARTPHONE, 10, true),

                create(43, "Google", "Pixel 9 Pro XL",
                        "Google Pixel 9 Pro XL smartphone",
                        "119999", ProductCategory.SMARTPHONE, 8, true),

                create(44, "Google", "Pixel 9 Pro Fold",
                        "Google Pixel 9 Pro Fold foldable smartphone",
                        "172999", ProductCategory.SMARTPHONE, 5, true),

                create(45, "Google", "Pixel 8",
                        "Google Pixel 8 smartphone",
                        "59999", ProductCategory.SMARTPHONE, 20, true),

                create(46, "Google", "Pixel 8 Pro",
                        "Google Pixel 8 Pro smartphone",
                        "79999", ProductCategory.SMARTPHONE, 12, true),

                create(47, "Google", "Pixel 8a",
                        "Google Pixel 8a smartphone",
                        "52999", ProductCategory.SMARTPHONE, 25, true),

                create(48, "Google", "Pixel Tablet",
                        "Google Pixel Tablet",
                        "59999", ProductCategory.TABLET, 10, true),

                create(49, "Google", "Pixel Watch 3",
                        "Google Pixel Watch 3 smartwatch",
                        "39999", ProductCategory.SMARTWATCH, 14, true),

                create(50, "Google", "Pixel Buds Pro 2",
                        "Google Pixel Buds Pro 2 earbuds",
                        "22999", ProductCategory.ACCESSORY, 25, true),

                // =========================
                // XIAOMI - 51 to 60
                // =========================

                create(51, "Xiaomi", "Xiaomi 15",
                        "Xiaomi 15 flagship smartphone",
                        "64999", ProductCategory.SMARTPHONE, 20, true),

                create(52, "Xiaomi", "Xiaomi 15 Ultra",
                        "Xiaomi 15 Ultra flagship smartphone",
                        "99999", ProductCategory.SMARTPHONE, 8, true),

                create(53, "Xiaomi", "Xiaomi 14",
                        "Xiaomi 14 smartphone",
                        "54999", ProductCategory.SMARTPHONE, 18, true),

                create(54, "Xiaomi", "Xiaomi 14 Ultra",
                        "Xiaomi 14 Ultra smartphone",
                        "99999", ProductCategory.SMARTPHONE, 7, true),

                create(55, "Xiaomi", "Redmi Note 14 Pro",
                        "Redmi Note 14 Pro smartphone",
                        "29999", ProductCategory.SMARTPHONE, 30, true),

                create(56, "Xiaomi", "Redmi Note 14",
                        "Redmi Note 14 smartphone",
                        "19999", ProductCategory.SMARTPHONE, 40, true),

                create(57, "Xiaomi", "Redmi 14C",
                        "Redmi 14C smartphone",
                        "10999", ProductCategory.SMARTPHONE, 50, true),

                create(58, "Xiaomi", "Xiaomi Pad 7",
                        "Xiaomi Pad 7 tablet",
                        "27999", ProductCategory.TABLET, 20, true),

                create(59, "Xiaomi", "Xiaomi Watch S4",
                        "Xiaomi Watch S4 smartwatch",
                        "14999", ProductCategory.SMARTWATCH, 25, true),

                create(60, "Xiaomi", "Redmi Buds 6 Pro",
                        "Redmi Buds 6 Pro wireless earbuds",
                        "4999", ProductCategory.ACCESSORY, 40, true),

                // =========================
                // MOTOROLA - 61 to 70
                // =========================

                create(61, "Motorola", "Motorola Edge 60 Pro",
                        "Motorola Edge 60 Pro smartphone",
                        "34999", ProductCategory.SMARTPHONE, 20, true),

                create(62, "Motorola", "Motorola Edge 60",
                        "Motorola Edge 60 smartphone",
                        "29999", ProductCategory.SMARTPHONE, 25, true),

                create(63, "Motorola", "Motorola Edge 50 Pro",
                        "Motorola Edge 50 Pro smartphone",
                        "29999", ProductCategory.SMARTPHONE, 22, true),

                create(64, "Motorola", "Motorola Edge 50 Fusion",
                        "Motorola Edge 50 Fusion smartphone",
                        "22999", ProductCategory.SMARTPHONE, 30, true),

                create(65, "Motorola", "Moto G85",
                        "Motorola Moto G85 smartphone",
                        "17999", ProductCategory.SMARTPHONE, 35, true),

                create(66, "Motorola", "Moto G75",
                        "Motorola Moto G75 smartphone",
                        "19999", ProductCategory.SMARTPHONE, 28, true),

                create(67, "Motorola", "Moto G64",
                        "Motorola Moto G64 smartphone",
                        "14999", ProductCategory.SMARTPHONE, 40, true),

                create(68, "Motorola", "Moto Pad 60",
                        "Motorola tablet",
                        "24999", ProductCategory.TABLET, 15, true),

                create(69, "Motorola", "Moto Watch 100",
                        "Motorola smartwatch",
                        "8999", ProductCategory.SMARTWATCH, 20, true),

                create(70, "Motorola", "Moto Buds Plus",
                        "Motorola wireless earbuds",
                        "6999", ProductCategory.ACCESSORY, 30, true),

                // =========================
                // VIVO - 71 to 80
                // =========================

                create(71, "Vivo", "Vivo X200",
                        "Vivo X200 flagship smartphone",
                        "65999", ProductCategory.SMARTPHONE, 18, true),

                create(72, "Vivo", "Vivo X200 Pro",
                        "Vivo X200 Pro flagship smartphone",
                        "94999", ProductCategory.SMARTPHONE, 10, true),

                create(73, "Vivo", "Vivo V50",
                        "Vivo V50 smartphone",
                        "34999", ProductCategory.SMARTPHONE, 25, true),

                create(74, "Vivo", "Vivo V40",
                        "Vivo V40 smartphone",
                        "34999", ProductCategory.SMARTPHONE, 20, true),

                create(75, "Vivo", "Vivo V40 Pro",
                        "Vivo V40 Pro smartphone",
                        "49999", ProductCategory.SMARTPHONE, 12, true),

                create(76, "Vivo", "Vivo T4",
                        "Vivo T4 smartphone",
                        "24999", ProductCategory.SMARTPHONE, 30, true),

                create(77, "Vivo", "Vivo T3 Pro",
                        "Vivo T3 Pro smartphone",
                        "24999", ProductCategory.SMARTPHONE, 28, true),

                create(78, "Vivo", "Vivo Pad 3",
                        "Vivo Pad tablet",
                        "39999", ProductCategory.TABLET, 10, true),

                create(79, "Vivo", "Vivo Watch 3",
                        "Vivo smartwatch",
                        "15999", ProductCategory.SMARTWATCH, 15, true),

                create(80, "Vivo", "Vivo TWS 3",
                        "Vivo wireless earbuds",
                        "6999", ProductCategory.ACCESSORY, 25, true),

                // =========================
                // OPPO - 81 to 90
                // =========================

                create(81, "Oppo", "Oppo Find X8",
                        "Oppo Find X8 flagship smartphone",
                        "69999", ProductCategory.SMARTPHONE, 15, true),

                create(82, "Oppo", "Oppo Find X8 Pro",
                        "Oppo Find X8 Pro flagship smartphone",
                        "99999", ProductCategory.SMARTPHONE, 8, true),

                create(83, "Oppo", "Oppo Reno 13",
                        "Oppo Reno 13 smartphone",
                        "37999", ProductCategory.SMARTPHONE, 20, true),

                create(84, "Oppo", "Oppo Reno 13 Pro",
                        "Oppo Reno 13 Pro smartphone",
                        "49999", ProductCategory.SMARTPHONE, 15, true),

                create(85, "Oppo", "Oppo F27 Pro",
                        "Oppo F27 Pro smartphone",
                        "27999", ProductCategory.SMARTPHONE, 25, true),

                create(86, "Oppo", "Oppo F27",
                        "Oppo F27 smartphone",
                        "22999", ProductCategory.SMARTPHONE, 30, true),

                create(87, "Oppo", "Oppo A5 Pro",
                        "Oppo A5 Pro smartphone",
                        "17999", ProductCategory.SMARTPHONE, 35, true),

                create(88, "Oppo", "Oppo Pad 3",
                        "Oppo Pad 3 tablet",
                        "39999", ProductCategory.TABLET, 12, true),

                create(89, "Oppo", "Oppo Watch X",
                        "Oppo Watch X smartwatch",
                        "29999", ProductCategory.SMARTWATCH, 14, true),

                create(90, "Oppo", "Oppo Enco X3",
                        "Oppo Enco X3 wireless earbuds",
                        "11999", ProductCategory.ACCESSORY, 25, true),

                // =========================
                // REALME - 91 to 100
                // =========================

                create(91, "Realme", "Realme GT 7",
                        "Realme GT 7 performance smartphone",
                        "39999", ProductCategory.SMARTPHONE, 20, true),

                create(92, "Realme", "Realme GT 7T",
                        "Realme GT 7T performance smartphone",
                        "34999", ProductCategory.SMARTPHONE, 25, true),

                create(93, "Realme", "Realme GT 6",
                        "Realme GT 6 smartphone",
                        "34999", ProductCategory.SMARTPHONE, 18, true),

                create(94, "Realme", "Realme P3 Pro",
                        "Realme P3 Pro smartphone",
                        "26999", ProductCategory.SMARTPHONE, 30, true),

                create(95, "Realme", "Realme P3",
                        "Realme P3 smartphone",
                        "19999", ProductCategory.SMARTPHONE, 35, true),

                create(96, "Realme", "Realme 14 Pro",
                        "Realme 14 Pro smartphone",
                        "24999", ProductCategory.SMARTPHONE, 28, true),

                create(97, "Realme", "Realme 14",
                        "Realme 14 smartphone",
                        "19999", ProductCategory.SMARTPHONE, 35, true),

                create(98, "Realme", "Realme Pad 2",
                        "Realme Pad 2 tablet",
                        "19999", ProductCategory.TABLET, 16, true),

                create(99, "Realme", "Realme Watch 3 Pro",
                        "Realme Watch 3 Pro smartwatch",
                        "4999", ProductCategory.SMARTWATCH, 20, false),

                create(100, "Realme", "Realme Buds Air 6",
                        "Realme Buds Air 6 wireless earbuds",
                        "3999", ProductCategory.ACCESSORY, 40, true)
        );

        products.forEach(productRepository::save);
    }

    private Product create(
            long id,
            String brand,
            String name,
            String description,
            String price,
            ProductCategory category,
            int stockQuantity,
            boolean active) {

        return new Product(
                id,
                brand,
                name,
                description,
                new BigDecimal(price),
                category,
                stockQuantity,
                active
        );
    }
}