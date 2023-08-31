package com.example.warehouse;

import com.example.warehouse.entity.*;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private InputService inputService;
    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private OutputService outputService;
    @Autowired
    private OutputProductService outputProductService;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        createWarehouses();
        createCategories();
        createCurrencies();
        createMeasurements();
        createProducts();
        createSuppliers();
        createClients();
        createInputs();
        createOutputs();
        createUsers();
    }
    private void createWarehouses() {
        for (int i = 1; i <= 10; i++) {
            Warehouse warehouse = new Warehouse();
            warehouse.setName("Warehouse" + i);
            warehouseService.save(warehouse);
        }
    }
    private void createCategories() {
        for (int i = 1; i <= 5; i++) {
            Category category = new Category();
            category.setName("Category" + i);
            categoryService.save(category);
        }
        for (int j = 1; j <= 5; j++) {
            for (int k = 1; k <= 7; k++) {
                Random random = new Random();
                Category category = new Category();
                category.setName("SubCategory" + j + k);
                category.setParentCategory(categoryService.findById(random.nextLong(5 - 1) + 1));
                categoryService.save(category);
            }
        }
    }
    private void createCurrencies() {
        Currency currency1 = new Currency();
        currency1.setCurrencyCode(java.util.Currency.getInstance("UZS"));

        Currency currency2 = new Currency();
        currency2.setCurrencyCode(java.util.Currency.getInstance("RUB"));

        Currency currency3 = new Currency();
        currency3.setCurrencyCode(java.util.Currency.getInstance("USD"));
        currencyService.save(currency1);
        currencyService.save(currency2);
        currencyService.save(currency3);
    }
    private void createMeasurements() {
        for (int i = 1; i <= 5; i++) {
            Measurement measurement = new Measurement();
            measurement.setName("Measurement" + i);
            measurementService.save(measurement);
        }
    }
    private void createProducts() {
        for (int i = 6; i <= 40; i++) {
            for (int j = 1; j <= 10; j++) {
                Random random = new Random();
                Product product = new Product();
                product.setCategory(categoryService.findById(random.nextLong(40 - 6) + 6));
                product.setMeasurement(measurementService.findById(random.nextLong(5 - 1) + 1));
                product.setName("Product" + i + j);
                product.setCode(UUID.randomUUID().toString());
                productService.save(product);
            }
        }
    }
    private void createSuppliers() {
        for (int i = 1; i <= 15; i++) {
            Supplier supplier = new Supplier();
            supplier.setName("Supplier" + i);
            supplier.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            supplierService.save(supplier);
        }
    }
    private void createClients() {
        for (int i = 1; i <= 15; i++) {
            Client client = new Client();
            client.setName("Client" + i);
            client.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            clientService.save(client);
        }
    }
    private void createInputs() {
        for (int i = 1; i <= 50; i++) {
            Random random = new Random();
            Input input = new Input();
            input.setWarehouse(warehouseService.findById(random.nextLong(10 - 1) + 1));
            input.setCurrency(currencyService.findById(random.nextLong(3 - 1) + 1));
            input.setSupplier(supplierService.findById(random.nextLong(15 - 1) + 1));
            input.setDate(new Timestamp(new Date().getTime()));
            input.setCode(UUID.randomUUID().toString());
            input.setFactureNumber(random.nextInt(900000 - 100000) + 100000);
            inputService.save(input);
        }
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 15; j++) {
                Random random = new Random();
                InputProduct inputProduct = new InputProduct();
                inputProduct.setInput(inputService.findById(random.nextLong(50 - 1) + 1));
                inputProduct.setProduct(productService.findById(random.nextLong(350 - 1) + 1));
                inputProduct.setAmount(random.nextDouble(20 - 1) + 1);
                inputProduct.setPrice(random.nextDouble(1000 - 10) + 10);
                inputProduct.setExpireDate(new Date());
                inputProductService.save(inputProduct);
            }
        }
    }
    private void createOutputs() {
        for (int i = 1; i <= 50; i++) {
            Random random = new Random();
            Output output = new Output();
            output.setWarehouse(warehouseService.findById(random.nextLong(10 - 1) + 1));
            output.setCurrency(currencyService.findById(random.nextLong(3 - 1) + 1));
            output.setClient(clientService.findById(random.nextLong(15 - 1) + 1));
            output.setDate(new Timestamp(new Date().getTime()));
            output.setCode(UUID.randomUUID().toString());
            output.setFactureNumber(random.nextInt(900000 - 100000) + 100000);
            outputService.save(output);
        }
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 15; j++) {
                Random random = new Random();
                OutputProduct outputProduct = new OutputProduct();
                outputProduct.setOutput(outputService.findById(random.nextLong(50 - 1) + 1));
                outputProduct.setProduct(productService.findById(random.nextLong(350 - 1) + 1));
                outputProduct.setAmount(random.nextDouble(20 - 1) + 1);
                outputProduct.setPrice(random.nextDouble(1000 - 10) + 10);
                outputProductService.save(outputProduct);
            }
        }
    }
    private void createUsers() {
        for (int i = 1; i <= 20; i++) {
            Random random = new Random();
            User user = new User();
            user.setFirstName("username" + i);
            user.setLastName("lastname" + i);
            user.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            user.setPassword(Base64.getEncoder().encodeToString("123".getBytes()));
            user.setCode(UUID.randomUUID().toString());
            user.setWarehouses(Set.of(warehouseService.findById(random.nextLong(10 - 1) + 1)));
            userService.save(user);
        }
    }
}
