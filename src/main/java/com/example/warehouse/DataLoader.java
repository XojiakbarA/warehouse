package com.example.warehouse;

import com.example.warehouse.entity.*;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Autowired
    private RemindBeforeOptionService remindBeforeOptionService;

    private final Long warehouseMax = 5L;
    private final Long parentCategoryMax = 3L;
    private final Long subCategoryMax = 5L;
    private final long categoryMax = (parentCategoryMax * subCategoryMax) + parentCategoryMax;
    private final long subCategoryMin = parentCategoryMax + 1;
    private final Long productMax = 5L;
    private final Long supplierMax = 10L;
    private final Long currencyMax = 3L;
    private final Long measurementMax = 5L;
    private final Long inputMax = 12L;
    private final Long inputProductMax = 10L;
    private final double inputAmountMax = 100;

    @Override
    public void run(String... args) throws Exception {

        RemindBeforeOption remindBeforeOption1 = new RemindBeforeOption();
        remindBeforeOption1.setValue(15);
        remindBeforeOption1.setSelected(true);
        remindBeforeOptionService.save(remindBeforeOption1);

        RemindBeforeOption remindBeforeOption2 = new RemindBeforeOption();
        remindBeforeOption2.setValue(30);
        remindBeforeOptionService.save(remindBeforeOption2);

        RemindBeforeOption remindBeforeOption3 = new RemindBeforeOption();
        remindBeforeOption3.setValue(60);
        remindBeforeOptionService.save(remindBeforeOption3);

        createWarehouses();
        createUsers();
        createCategories();
        createCurrencies();
        createMeasurements();
        createProducts();
        createSuppliers();
        createClients();
        createInputs();
        createOutputs();
    }
    private void createWarehouses() {
        for (int i = 1; i <= warehouseMax; i++) {
            Warehouse warehouse = new Warehouse();
            warehouse.setName("Warehouse" + i);
            warehouseService.save(warehouse);
        }
    }
    private void createUsers() {
        for (int i = 1; i <= 15; i++) {
            Random random = new Random();
            User user = new User();
            user.setFirstName("username" + i);
            user.setLastName("lastname" + i);
            user.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            user.setPassword(Base64.getEncoder().encodeToString("123".getBytes()));
            user.setCode(UUID.randomUUID().toString());
            user.setWarehouses(Set.of(warehouseService.findById(random.nextLong(warehouseMax - 1) + 1)));
            userService.save(user);
        }
    }
    private void createCategories() {
        for (int i = 1; i <= parentCategoryMax; i++) {
            Category category = new Category();
            category.setName("Category" + i);
            categoryService.save(category);
        }
        for (int j = 1; j <= parentCategoryMax; j++) {
            for (int k = 1; k <= subCategoryMax; k++) {
                Random random = new Random();
                Category category = new Category();
                category.setName("SubCategory" + j + k);
                category.setParentCategory(categoryService.findById(random.nextLong(parentCategoryMax - 1) + 1));
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
        for (int i = (int) subCategoryMin; i <= categoryMax; i++) {
            for (int j = 1; j <= productMax; j++) {
                Random random = new Random();
                Product product = new Product();
                product.setCategory(categoryService.findById(random.nextLong(categoryMax - subCategoryMin) + subCategoryMin));
                product.setMeasurement(measurementService.findById(random.nextLong(measurementMax - 1) + 1));
                product.setName("Product" + i + j);
                product.setCode(UUID.randomUUID().toString());
                productService.save(product);
            }
        }
    }
    private void createSuppliers() {
        for (int i = 1; i <= supplierMax; i++) {
            Supplier supplier = new Supplier();
            supplier.setName("Supplier" + i);
            supplier.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            supplierService.save(supplier);
        }
    }
    private void createClients() {
        for (int i = 1; i <= supplierMax; i++) {
            Client client = new Client();
            client.setName("Client" + i);
            client.setPhoneNumber(String.valueOf(100000000 + new Random().nextInt(900000000)));
            clientService.save(client);
        }
    }
    private void createInputs() {
        for (int i = 1; i <= inputMax; i++) {
            Random random = new Random();
            Input input = new Input();
            input.setWarehouse(warehouseService.findById(random.nextLong(warehouseMax - 1) + 1));
            input.setCurrency(currencyService.findById(random.nextLong(currencyMax - 1) + 1));
            input.setSupplier(supplierService.findById(random.nextLong(supplierMax - 1) + 1));
            input.setDate(new Timestamp(new Date().getTime()));
            input.setCode(UUID.randomUUID().toString());
            input.setFactureNumber(random.nextInt(900000 - 100000) + 100000);
            inputService.save(input);
        }
        for (int i = 1; i <= inputMax; i++) {
            for (int j = 1; j <= inputProductMax; j++) {
                Random random = new Random();
                InputProduct inputProduct = new InputProduct();
                inputProduct.setInput(inputService.findById(random.nextLong(inputMax - 1) + 1));
                inputProduct.setProduct(productService.findById(random.nextLong(productMax * (categoryMax - subCategoryMin) - 1) + 1));
                double amount = random.nextDouble(inputAmountMax - 1) + 1;
                inputProduct.setAmount(amount);
                inputProduct.setRemaining(amount);
                inputProduct.setPrice(random.nextDouble(1000 - 10) + 10);
                inputProduct.setExpireDate(Timestamp.valueOf(LocalDateTime.now().plusDays(20)));
                inputProductService.save(inputProduct);
            }
        }
    }
    private void createOutputs() {
        for (int i = 1; i <= inputMax; i++) {
            Random random = new Random();
            Output output = new Output();
            output.setWarehouse(warehouseService.findById(random.nextLong(warehouseMax - 1) + 1));
            output.setCurrency(currencyService.findById(random.nextLong(currencyMax - 1) + 1));
            output.setClient(clientService.findById(random.nextLong(supplierMax - 1) + 1));
            output.setDate(new Timestamp(new Date().getTime()));
            output.setCode(UUID.randomUUID().toString());
            output.setFactureNumber(random.nextInt(900000 - 100000) + 100000);
            outputService.save(output);
        }
        for (int i = 1; i <= inputMax; i++) {
            for (int j = 1; j <= inputProductMax; j++) {
                Random random = new Random();
                OutputProduct outputProduct = new OutputProduct();
                outputProduct.setOutput(outputService.findById(random.nextLong(inputMax - 1) + 1));
                InputProduct inputProduct = inputProductService.findById(random.nextLong(inputMax * inputProductMax - 1) + 1);
                outputProduct.setInputProduct(inputProduct);
                outputProduct.setAmount(random.nextDouble(inputProduct.getAmount() - 1) + 1);
                outputProduct.setPrice(random.nextDouble(2000 - inputProduct.getPrice()) + inputProduct.getPrice());
                outputProductService.save(outputProduct);
            }
        }
    }
}
