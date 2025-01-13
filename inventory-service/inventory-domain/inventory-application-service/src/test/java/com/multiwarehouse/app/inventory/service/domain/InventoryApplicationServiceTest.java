package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.*;
import com.multiwarehouse.app.inventory.service.domain.entity.*;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryNotFoundException;
import com.multiwarehouse.app.inventory.service.domain.mapper.InventoryDataMapper;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import com.multiwarehouse.app.inventory.service.domain.ports.input.service.InventoryApplicationService;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.InventoryRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.ProductRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.StockMutationRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.WarehouseRepository;
import com.multiwarehouse.app.inventory.service.domain.valueobject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = InventoryTestConfiguration.class)
public class InventoryApplicationServiceTest {
    @Autowired
    private InventoryApplicationService inventoryApplicationService;

    @Autowired
    private InventoryDataMapper inventoryDataMapper;

    @Autowired
    private StockMutationDataMapper stockMutationDataMapper;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockMutationRepository stockMutationRepository;

    private AddStockToInventoryCommand addStockToInventoryCommand;
    private AddStockToInventoryCommand addStockToInventoryCommandUnregisteredWarehouse;
    private AddStockToInventoryCommand addStockToInventoryCommandUnregisteredProduct;
    private AddStockToInventoryCommand addStockToInventoryCommandZeroQuantity;
    private AddStockToInventoryCommand addStockToInventoryCommandLessThanZeroQuantity;

    private ReduceStockFromInventoryCommand reduceStockFromInventoryCommand;
    private ReduceStockFromInventoryCommand reduceStockToInventoryCommandUnregisteredWarehouse;
    private ReduceStockFromInventoryCommand reduceStockToInventoryCommandUnregisteredProduct;
    private ReduceStockFromInventoryCommand reduceStockToInventoryCommandZeroQuantity;
    private ReduceStockFromInventoryCommand reduceStockToInventoryCommandLessThanZeroQuantity;
    private ReduceStockFromInventoryCommand reduceStockToInventoryCommandMoreThanCurrentStockQuantity;

    private RequestStockMutationCommand requestStockMutationCommand;
    private RequestStockMutationCommand requestStockMutationCommandUnregisteredSourceWarehouse;
    private RequestStockMutationCommand requestStockMutationCommandUnregisteredTargetWarehouse;
    private RequestStockMutationCommand requestStockMutationCommandSameSourceAndTargetWarehouse;
    private RequestStockMutationCommand requestStockMutationCommandUnregisteredProduct;
    private RequestStockMutationCommand requestStockMutationCommandZeroQuantity;
    private RequestStockMutationCommand requestStockMutationCommandLessThanZeroQuantity;
    private RequestStockMutationCommand requestStockMutationCommandMoreThanCurrentStockQuantity;

    private RejectStockMutationCommand rejectStockMutationCommand;
    private RejectStockMutationCommand rejectStockMutationCommandUnregisteredStockMutation;
    private RejectStockMutationCommand rejectStockMutationCommandRejectedStockMutation;
    private RejectStockMutationCommand rejectStockMutationCommandApprovedStockMutation;
    private ApproveStockMutationCommand approveStockMutationCommand;
    private ApproveStockMutationCommand approveStockMutationCommandUnregisteredStockMutation;
    private ApproveStockMutationCommand approveStockMutationCommandRejectedStockMutation;
    private ApproveStockMutationCommand approveStockMutationCommandApprovedStockMutation;
    private ApproveStockMutationCommand approveStockMutationCommandMoreThanCurrentStockQuantity;

    private final UUID WAREHOUSE_ID = UUID.fromString("3cb6f2df-065b-4f75-9171-364d791f899a");
    private final UUID INVENTORY_ID = UUID.fromString("ff9a420f-5082-45b9-8a45-ea8b0f82c1b8");
    private final UUID TARGET_WAREHOUSE_ID = UUID.fromString("c01953ac-4c12-491c-abfd-ed0084c0008e");
    private final UUID TARGET_INVENTORY_ID = UUID.fromString("fcdd4f04-8556-4bbc-8253-932c7e19ecf4");
    private final UUID PRODUCT_ID = UUID.fromString("4a286d93-4ff0-4898-ac2e-80543591c4cb");
    private final UUID INVENTORY_STOCK_ID = UUID.fromString("ed546444-83b6-4c06-b1c0-bd77e12d1298");
    private final UUID FIRST_STOCK_JOURNAL_ID = UUID.fromString("ac9b82c1-b764-42d5-abc4-b97978dfb12c");
    private final int QUANTITY = 200;

    private final UUID UNREGISTERED_WAREHOUSE_ID = UUID.fromString("97fb2f3f-30c8-4e8b-83d7-8437a138a24f");
    private final UUID UNREGISTERED_TARGET_WAREHOUSE_ID = UUID.fromString("e4283433-742b-4e0d-9046-63d4e8214aea");
    private final UUID UNREGISTERED_PRODUCT_ID = UUID.fromString("80ae1264-81ff-42a5-99e0-fd27bf4b7be9");
    private final UUID UNREGISTERED_STOCK_MUTATION_ID = UUID.fromString("4b8604e4-38d7-4f56-989a-78844ca4bb0d");
    private final UUID UNREJECTED_STOCK_MUTATION_ID = UUID.fromString("e9d27d4a-056a-4b6d-97c3-1913b8472a98");
    private final UUID REJECTED_STOCK_MUTATION_ID = UUID.fromString("c08bae02-b1d4-4e32-b16b-9d4a8786aa75");
    private final UUID UNAPPROVED_STOCK_MUTATION_ID = UUID.fromString("3760babe-65e4-48ee-ab93-bd25e08010dc");
    private final UUID APPROVED_STOCK_MUTATION_ID = UUID.fromString("186c4d1d-50ec-487b-bff2-50052986ba26");
    private final UUID OVER_QUANTITY_STOCK_MUTATION_ID = UUID.fromString("37114058-134e-43b4-b302-91bdfa97ad12");

    @BeforeAll
    public void init() {
        addStockToInventoryCommand = AddStockToInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(100)
                .build();

        addStockToInventoryCommandUnregisteredWarehouse = AddStockToInventoryCommand.builder()
                .warehouseId(UNREGISTERED_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(100)
                .build();

        addStockToInventoryCommandUnregisteredProduct = AddStockToInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(UNREGISTERED_PRODUCT_ID)
                .quantity(100)
                .build();

        addStockToInventoryCommandZeroQuantity = AddStockToInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(0)
                .build();

        addStockToInventoryCommandLessThanZeroQuantity = AddStockToInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(-10)
                .build();

        reduceStockFromInventoryCommand = ReduceStockFromInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(100)
                .build();

        reduceStockToInventoryCommandUnregisteredWarehouse = ReduceStockFromInventoryCommand.builder()
                .warehouseId(UNREGISTERED_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(100)
                .build();

        reduceStockToInventoryCommandUnregisteredProduct = ReduceStockFromInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(UNREGISTERED_PRODUCT_ID)
                .quantity(100)
                .build();

        reduceStockToInventoryCommandZeroQuantity = ReduceStockFromInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(0)
                .build();

        reduceStockToInventoryCommandLessThanZeroQuantity = ReduceStockFromInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(-10)
                .build();

        reduceStockToInventoryCommandMoreThanCurrentStockQuantity = ReduceStockFromInventoryCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(QUANTITY + 100)
                .build();

        requestStockMutationCommand = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(TARGET_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(10)
                .build();

        requestStockMutationCommandUnregisteredSourceWarehouse = RequestStockMutationCommand.builder()
                .sourceWarehouseId(UNREGISTERED_WAREHOUSE_ID)
                .targetWarehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(10)
                .build();

        requestStockMutationCommandUnregisteredTargetWarehouse = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(UNREGISTERED_TARGET_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(10)
                .build();

        requestStockMutationCommandSameSourceAndTargetWarehouse = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(10)
                .build();

        requestStockMutationCommandUnregisteredProduct = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(TARGET_WAREHOUSE_ID)
                .productId(UNREGISTERED_PRODUCT_ID)
                .quantity(10)
                .build();

        requestStockMutationCommandZeroQuantity = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(TARGET_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(0)
                .build();

        requestStockMutationCommandLessThanZeroQuantity = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(TARGET_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(-10)
                .build();

        requestStockMutationCommandMoreThanCurrentStockQuantity = RequestStockMutationCommand.builder()
                .sourceWarehouseId(WAREHOUSE_ID)
                .targetWarehouseId(TARGET_WAREHOUSE_ID)
                .productId(PRODUCT_ID)
                .quantity(QUANTITY + 100)
                .build();

        rejectStockMutationCommand = RejectStockMutationCommand.builder()
                .id(UNREJECTED_STOCK_MUTATION_ID)
                .build();

        rejectStockMutationCommandUnregisteredStockMutation = RejectStockMutationCommand.builder()
                .id(UNREGISTERED_STOCK_MUTATION_ID)
                .build();

        rejectStockMutationCommandRejectedStockMutation = RejectStockMutationCommand.builder()
                .id(REJECTED_STOCK_MUTATION_ID)
                .build();

        rejectStockMutationCommandApprovedStockMutation = RejectStockMutationCommand.builder()
                .id(APPROVED_STOCK_MUTATION_ID)
                .build();

        approveStockMutationCommand = ApproveStockMutationCommand.builder()
                .id(UNAPPROVED_STOCK_MUTATION_ID)
                .build();

        approveStockMutationCommandUnregisteredStockMutation = ApproveStockMutationCommand.builder()
                .id(UNREGISTERED_STOCK_MUTATION_ID)
                .build();

        approveStockMutationCommandRejectedStockMutation = ApproveStockMutationCommand.builder()
                .id(REJECTED_STOCK_MUTATION_ID)
                .build();

        approveStockMutationCommandApprovedStockMutation = ApproveStockMutationCommand.builder()
                .id(APPROVED_STOCK_MUTATION_ID)
                .build();

        approveStockMutationCommandMoreThanCurrentStockQuantity = ApproveStockMutationCommand.builder()
                .id(OVER_QUANTITY_STOCK_MUTATION_ID)
                .build();

        Product product = Product.builder()
                .withId(new ProductId(PRODUCT_ID))
                .withCode("SKU0001")
                .withName("Chair")
                .withPrice(new Money(new BigDecimal(199000)))
                .withActive(true)
                .withSoftDeleted(false)
                .build();

        StockJournal stockJournal = StockJournal.builder()
                .withId(new StockJournalId(FIRST_STOCK_JOURNAL_ID))
                .withInventoryStockId(new InventoryStockId(INVENTORY_STOCK_ID))
                .withQuantity(QUANTITY)
                .withType(StockJournalType.ADDICTION)
                .withCreatedAt(Instant.now())
                .build();

        List<StockJournal> stockJournals = new ArrayList<>();
        stockJournals.add(stockJournal);

        InventoryStock inventoryStock = InventoryStock.builder()
                .withId(new InventoryStockId(INVENTORY_STOCK_ID))
                .withInventoryId(new InventoryId(INVENTORY_ID))
                .withProduct(product)
                .withQuantity(QUANTITY)
                .withJournals(stockJournals)
                .build();

        List<InventoryStock> inventoryStocks = new ArrayList<>();
        inventoryStocks.add(inventoryStock);

        Warehouse warehouse = Warehouse.builder()
                .withId(new WarehouseId(WAREHOUSE_ID))
                .withCode("IKIAEKBP")
                .withName("IKIAE Kota Baru Parayangan")
                .withActive(true)
//                .withSoftDeleted(false)
                .build();

        Inventory inventory = Inventory.builder()
                .withId(new InventoryId(INVENTORY_ID))
                .withWarehouse(warehouse)
                .withStocks(inventoryStocks)
                .withActive(true)
                .build();

        Warehouse targetWarehouse = Warehouse.builder()
                .withId(new WarehouseId(TARGET_WAREHOUSE_ID))
                .withCode("IKIAEJGC")
                .withName("IKIAE Jakarta Garden City")
                .withActive(true)
//                .withSoftDeleted(false)
                .build();

        Inventory targetInventory = Inventory.builder()
                .withId(new InventoryId(TARGET_INVENTORY_ID))
                .withWarehouse(targetWarehouse)
                .withStocks(new ArrayList<>())
                .withActive(true)
                .build();

        StockMutation unrejectedStockMutation = StockMutation.builder()
                .withId(new StockMutationId(UNREJECTED_STOCK_MUTATION_ID))
                .withSourceInventory(inventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(10)
                .withStatus(StockMutationStatus.PENDING)
                .build();

        StockMutation rejectedStockMutation = StockMutation.builder()
                .withId(new StockMutationId(REJECTED_STOCK_MUTATION_ID))
                .withSourceInventory(inventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(10)
                .withStatus(StockMutationStatus.REJECTED)
                .build();

        StockMutation unrapprovedStockMutation = StockMutation.builder()
                .withId(new StockMutationId(UNAPPROVED_STOCK_MUTATION_ID))
                .withSourceInventory(inventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(10)
                .withStatus(StockMutationStatus.PENDING)
                .build();

        StockMutation approvedStockMutation = StockMutation.builder()
                .withId(new StockMutationId(APPROVED_STOCK_MUTATION_ID))
                .withSourceInventory(inventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(10)
                .withStatus(StockMutationStatus.APPROVED)
                .build();

        StockMutation overQuantityStockMutation = StockMutation.builder()
                .withId(new StockMutationId(OVER_QUANTITY_STOCK_MUTATION_ID))
                .withSourceInventory(inventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(QUANTITY + 100)
                .withStatus(StockMutationStatus.PENDING)
                .build();



        Mockito.when(productRepository.findById(new ProductId(PRODUCT_ID))).thenReturn(Optional.of(product));

        Mockito.when(inventoryRepository.findByWarehouseId(new WarehouseId(WAREHOUSE_ID))).thenReturn(Optional.of(inventory));
        Mockito.when(inventoryRepository.findByWarehouseId(new WarehouseId(TARGET_WAREHOUSE_ID))).thenReturn(Optional.of(targetInventory));
        Mockito.when(inventoryRepository.save(Mockito.any(Inventory.class))).thenReturn(inventory);

        Mockito.when(stockMutationRepository.findById(new StockMutationId(UNREJECTED_STOCK_MUTATION_ID))).thenReturn(Optional.of(unrejectedStockMutation));
        Mockito.when(stockMutationRepository.findById(new StockMutationId(REJECTED_STOCK_MUTATION_ID))).thenReturn(Optional.of(rejectedStockMutation));
        Mockito.when(stockMutationRepository.findById(new StockMutationId(UNAPPROVED_STOCK_MUTATION_ID))).thenReturn(Optional.of(unrapprovedStockMutation));
        Mockito.when(stockMutationRepository.findById(new StockMutationId(APPROVED_STOCK_MUTATION_ID))).thenReturn(Optional.of(approvedStockMutation));
        Mockito.when(stockMutationRepository.findById(new StockMutationId(OVER_QUANTITY_STOCK_MUTATION_ID))).thenReturn(Optional.of(overQuantityStockMutation));
        Mockito.when(stockMutationRepository.save(Mockito.any(StockMutation.class))).thenReturn(approvedStockMutation);
    }

    @Test
    public void testAddStockToInventory() {
        AddStockToInventoryResponse addStockToInventoryResponse = inventoryApplicationService.addStockToInventory(addStockToInventoryCommand);
        Assertions.assertNotNull(addStockToInventoryResponse.getId());
    }

    @Test
    public void addStockToInventoryUnregisteredWarehouse() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.addStockToInventory(addStockToInventoryCommandUnregisteredWarehouse));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Inventory with warehouseId: " + UNREGISTERED_WAREHOUSE_ID);
    }

    @Test
    public void addStockToInventoryUnregisteredProduct() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.addStockToInventory(addStockToInventoryCommandUnregisteredProduct));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Product with productId: " + UNREGISTERED_PRODUCT_ID);
    }

    @Test
    public void addStockToInventoryZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.addStockToInventory(addStockToInventoryCommandZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "Inventory.Input.Quantity must be greater than zero!");
    }

    @Test
    public void addStockToInventoryLessThanZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.addStockToInventory(addStockToInventoryCommandLessThanZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "Inventory.Input.Quantity must be greater than zero!");
    }

    @Test
    public void reduceStockFromInventory() {
        ReduceStockFromInventoryResponse reduceStockFromInventoryResponse = inventoryApplicationService.reduceStockFromInventory(reduceStockFromInventoryCommand);
        Assertions.assertNotNull(reduceStockFromInventoryResponse.getId());
    }

    @Test
    public void reduceStockToInventoryUnregisteredWarehouse() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.reduceStockFromInventory(reduceStockToInventoryCommandUnregisteredWarehouse));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Inventory with warehouseId: " + UNREGISTERED_WAREHOUSE_ID);
    }

    @Test
    public void reduceStockToInventoryUnregisteredProduct() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.reduceStockFromInventory(reduceStockToInventoryCommandUnregisteredProduct));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Product with productId: " + UNREGISTERED_PRODUCT_ID);
    }

    @Test
    public void reduceStockToInventoryZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.reduceStockFromInventory(reduceStockToInventoryCommandZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "Inventory.Input.Quantity must be greater than zero!");
    }

    @Test
    public void reduceStockToInventoryLessThanZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.reduceStockFromInventory(reduceStockToInventoryCommandLessThanZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "Inventory.Input.Quantity must be greater than zero!");
    }

    @Test
    public void reduceStockToInventoryMoreThanCurrentStockQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.reduceStockFromInventory(reduceStockToInventoryCommandMoreThanCurrentStockQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "ProductStock.Quantity is insufficient stock!");
    }

    @Test
    public void requestStockMutation() {
        RequestStockMutationResponse requestStockMutationResponse = inventoryApplicationService.requestStockMutation(requestStockMutationCommand);
        Assertions.assertNotNull(requestStockMutationResponse.getId());
    }

    @Test
    public void requestStockMutationUnregisteredSourceWarehouse() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandUnregisteredSourceWarehouse));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Inventory with warehouseId: " + UNREGISTERED_WAREHOUSE_ID);
    }

    @Test
    public void requestStockMutationUnregisteredTargetWarehouse() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandUnregisteredTargetWarehouse));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Inventory with warehouseId: " + UNREGISTERED_TARGET_WAREHOUSE_ID);
    }

    @Test
    public void requestStockMutationSameSourceAndTargetWarehouse() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandSameSourceAndTargetWarehouse));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "Couldn't use same Source Inventory and Target Inventory with warehouseId: " + WAREHOUSE_ID);
    }

    @Test
    public void requestStockMutationUnregisteredProduct() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandUnregisteredProduct));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(), "Couldn't find Product with productId: " + UNREGISTERED_PRODUCT_ID);
    }

    @Test
    public void requestStockMutationZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "StockMutation.Quantity must be greater than zero!");
    }

    @Test
    public void requestStockMutationLessThanZeroQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.requestStockMutation(requestStockMutationCommandLessThanZeroQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(), "StockMutation.Quantity must be greater than zero!");
    }

    @Test
    public void requestStockMutationMoreThanCurrentStockQuantity() {
        RequestStockMutationResponse requestStockMutationResponse = inventoryApplicationService.requestStockMutation(requestStockMutationCommandMoreThanCurrentStockQuantity);
        Assertions.assertNotNull(requestStockMutationResponse.getId());
    }

    @Test
    public void rejectStockMutation() {
        RejectStockMutationResponse rejectStockMutationResponse = inventoryApplicationService.rejectStockMutation(rejectStockMutationCommand);
        Assertions.assertNotNull(rejectStockMutationResponse.getId());
    }

    @Test
    public void rejectStockMutationUnregisteredStockMutation() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.rejectStockMutation(rejectStockMutationCommandUnregisteredStockMutation));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(),  "Couldn't find StockMutation with id: " + UNREGISTERED_STOCK_MUTATION_ID);
    }

    @Test
    public void rejectRejectedStockMutation() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.rejectStockMutation(rejectStockMutationCommandRejectedStockMutation));
        Assertions.assertEquals(inventoryDomainException.getMessage(),  "StockMutation.Request cannot be rejected in its current state!");
    }

    @Test
    public void rejectApprovedStockMutation() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.rejectStockMutation(rejectStockMutationCommandApprovedStockMutation));
        Assertions.assertEquals(inventoryDomainException.getMessage(),  "StockMutation.Request cannot be rejected in its current state!");
    }

    @Test
    public void approveStockMutation() {
        ApproveStockMutationResponse approveStockMutationResponse = inventoryApplicationService.approveStockMutation(approveStockMutationCommand);
        Assertions.assertNotNull(approveStockMutationResponse.getId());
    }

    @Test
    public void approveStockMutationUnregisteredStockMutation() {
        InventoryNotFoundException inventoryNotFoundException = Assertions.assertThrows(InventoryNotFoundException.class,
                () -> inventoryApplicationService.approveStockMutation(approveStockMutationCommandUnregisteredStockMutation));
        Assertions.assertEquals(inventoryNotFoundException.getMessage(),  "Couldn't find StockMutation with id: " + UNREGISTERED_STOCK_MUTATION_ID);
    }

    @Test
    public void approvetRejectedStockMutation() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.approveStockMutation(approveStockMutationCommandRejectedStockMutation));
        Assertions.assertEquals(inventoryDomainException.getMessage(),  "StockMutation.Request cannot be approved in its current state!");
    }

    @Test
    public void approveApprovedStockMutation() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.approveStockMutation(approveStockMutationCommandApprovedStockMutation));
        Assertions.assertEquals(inventoryDomainException.getMessage(),  "StockMutation.Request cannot be approved in its current state!");
    }

    @Test
    public void approveStockMutationMoreThanCurrentStockQuantity() {
        InventoryDomainException inventoryDomainException = Assertions.assertThrows(InventoryDomainException.class,
                () -> inventoryApplicationService.approveStockMutation(approveStockMutationCommandMoreThanCurrentStockQuantity));
        Assertions.assertEquals(inventoryDomainException.getMessage(),  "ProductStock.Quantity is insufficient stock!");
    }
}
