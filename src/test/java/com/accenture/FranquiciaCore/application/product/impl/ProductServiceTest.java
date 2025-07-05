package com.accenture.franquiciaCore.application.product.impl;

import com.accenture.franquiciaCore.application.product.AddProductCommand;
import com.accenture.franquiciaCore.application.product.DeleteProductCommand;
import com.accenture.franquiciaCore.application.product.FindMaxStockCommand;
import com.accenture.franquiciaCore.application.product.UpdateProductNameCommand;
import com.accenture.franquiciaCore.application.product.UpdateProductStockCommand;
import com.accenture.franquiciaCore.application.product.imp.ProductService;
import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService Tests")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private SubsidiaryRepository subsidiaryRepository;
    
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductService productService;

    private Subsidiary testSubsidiary;
    private Product testProduct;
    private Stock testStock;
    private SubsidiaryId subsidiaryId;
    private ProductId productId;

    @BeforeEach
    void setUp() {
        subsidiaryId = new SubsidiaryId("subsidiary-123");
        productId = new ProductId("product-123");
        
        testSubsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name("Test Subsidiary")
            .franchiseId(new FranchiseId("franchise-123"))
            .build();
            
        testStock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
            
        testProduct = Product.builder()
            .id(productId)
            .name("Test Product")
            .category(CategoryProduct.FOOD)
            .stock(testStock)
            .subsidiaryId(subsidiaryId)
            .build();
    }

    @Test
    @DisplayName("Should add product successfully")
    void shouldAddProductSuccessfully() {

        AddProductCommand command = new AddProductCommand("Big Mac", "subsidiary-123", "FOOD", 50);
        
        when(subsidiaryRepository.findById(subsidiaryId.getValue())).thenReturn(Mono.just(testSubsidiary));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(testProduct));
        when(stockRepository.save(any(Stock.class))).thenReturn(Mono.just(testStock));


        Mono<Product> result = productService.addProduct(command);


        StepVerifier.create(result)
            .assertNext(product -> {
                assertEquals("Test Product", product.getName());
                assertEquals(CategoryProduct.FOOD, product.getCategory());
                assertEquals(subsidiaryId, product.getSubsidiaryId());
                assertNotNull(product.getStock());
            })
            .verifyComplete();

        verify(subsidiaryRepository).findById(subsidiaryId.getValue());
        verify(productRepository).save(any(Product.class));
        verify(stockRepository).save(any(Stock.class));
    }

    @Test
    @DisplayName("Should throw exception when subsidiary not found during add product")
    void shouldThrowExceptionWhenSubsidiaryNotFoundDuringAddProduct() {

        AddProductCommand command = new AddProductCommand("Big Mac", "subsidiary-123", "FOOD", 50);
        when(subsidiaryRepository.findById(subsidiaryId.getValue())).thenReturn(Mono.empty());


        Mono<Product> result = productService.addProduct(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof NoSuchElementException &&
                throwable.getMessage().contains("Subsidiary not found"))
            .verify();

        verify(subsidiaryRepository).findById(subsidiaryId.getValue());
        verify(productRepository, never()).save(any(Product.class));
        verify(stockRepository, never()).save(any(Stock.class));
    }

    @Test
    @DisplayName("Should delete product successfully")
    void shouldDeleteProductSuccessfully() {

        DeleteProductCommand command = new DeleteProductCommand("subsidiary-123", "product-123");
        
        when(productRepository.delete("product-123")).thenReturn(Mono.empty());
        when(stockRepository.deleteByProductId(any(ProductId.class))).thenReturn(Mono.empty());


        Mono<Void> result = productService.deleteProduct(command);


        StepVerifier.create(result)
            .verifyComplete();

        verify(productRepository).delete("product-123");
        verify(stockRepository).deleteByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should update stock successfully")
    void shouldUpdateStockSuccessfully() {

        UpdateProductStockCommand command = new UpdateProductStockCommand("subsidiary-123", "product-123", 200);
        Stock updatedStock = Stock.builder()
            .id(testStock.getId())
            .quantity(200)
            .build();
        Product productWithUpdatedStock = testProduct.withStock(updatedStock);
        
        when(stockRepository.findByProductId(productId)).thenReturn(Mono.just(testStock));
        when(stockRepository.update(any(Stock.class))).thenReturn(Mono.just(updatedStock));
        when(productRepository.findById("product-123")).thenReturn(Mono.just(testProduct));


        Mono<Product> result = productService.updateStock(command);


        StepVerifier.create(result)
            .assertNext(product -> {
                assertEquals(200, product.getStock().getQuantity());
                assertEquals(testProduct.getName(), product.getName());
                assertEquals(testProduct.getCategory(), product.getCategory());
            })
            .verifyComplete();

        verify(stockRepository).findByProductId(productId);
        verify(stockRepository).update(any(Stock.class));
        verify(productRepository).findById("product-123");
    }

    @Test
    @DisplayName("Should throw exception when stock not found during update")
    void shouldThrowExceptionWhenStockNotFoundDuringUpdate() {

        UpdateProductStockCommand command = new UpdateProductStockCommand("subsidiary-123", "product-123", 200);
        when(stockRepository.findByProductId(productId)).thenReturn(Mono.empty());


        Mono<Product> result = productService.updateStock(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof NoSuchElementException &&
                throwable.getMessage().contains("Stock not found for product"))
            .verify();

        verify(stockRepository).findByProductId(productId);
        verify(stockRepository, never()).update(any(Stock.class));
        verify(productRepository, never()).findById(anyString());
    }

    @Test
    @DisplayName("Should update product name successfully")
    void shouldUpdateProductNameSuccessfully() {

        UpdateProductNameCommand command = new UpdateProductNameCommand("subsidiary-123", "product-123", "Updated Product Name");
        Product updatedProduct = Product.builder()
            .id(testProduct.getId())
            .name("Updated Product Name")
            .category(testProduct.getCategory())
            .stock(testProduct.getStock())
            .subsidiaryId(testProduct.getSubsidiaryId())
            .build();
        
        when(productRepository.findById("product-123")).thenReturn(Mono.just(testProduct));
        when(productRepository.update(any(Product.class))).thenReturn(Mono.just(updatedProduct));
        when(stockRepository.findByProductId(any(ProductId.class))).thenReturn(Mono.just(testStock));


        Mono<Product> result = productService.updateName(command);


        StepVerifier.create(result)
            .assertNext(product -> {
                assertEquals("Updated Product Name", product.getName());
                assertEquals(testProduct.getCategory(), product.getCategory());
                assertEquals(testProduct.getSubsidiaryId(), product.getSubsidiaryId());
                assertNotNull(product.getStock());
            })
            .verifyComplete();

        verify(productRepository).findById("product-123");
        verify(productRepository).update(any(Product.class));
        verify(stockRepository).findByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should throw exception when product not found during name update")
    void shouldThrowExceptionWhenProductNotFoundDuringNameUpdate() {

        UpdateProductNameCommand command = new UpdateProductNameCommand("subsidiary-123", "product-123", "Updated Name");
        when(productRepository.findById("product-123")).thenReturn(Mono.empty());


        Mono<Product> result = productService.updateName(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof NoSuchElementException &&
                throwable.getMessage().contains("Product not found"))
            .verify();

        verify(productRepository).findById("product-123");
        verify(productRepository, never()).update(any(Product.class));
        verify(stockRepository, never()).findByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should find max stock by franchise successfully")
    void shouldFindMaxStockByFranchiseSuccessfully() {

        FindMaxStockCommand command = new FindMaxStockCommand("franchise-123");
        
        Product product1 = Product.builder()
            .id(new ProductId("product-1"))
            .name("Product 1")
            .category(CategoryProduct.FOOD)
            .subsidiaryId(subsidiaryId)
            .build();
        
        Product product2 = Product.builder()
            .id(new ProductId("product-2"))
            .name("Product 2")
            .category(CategoryProduct.BEVERAGE)
            .subsidiaryId(subsidiaryId)
            .build();
        
        Stock stock1 = Stock.builder()
            .id(new StockId("stock-1"))
            .quantity(100)
            .build();
        
        Stock stock2 = Stock.builder()
            .id(new StockId("stock-2"))
            .quantity(200)
            .build();
        
        when(subsidiaryRepository.findByFranchiseId("franchise-123")).thenReturn(Flux.just(testSubsidiary));
        when(productRepository.findBySubsidiaryId(subsidiaryId.getValue())).thenReturn(Flux.just(product1, product2));
        when(stockRepository.findByProductId(new ProductId("product-1"))).thenReturn(Mono.just(stock1));
        when(stockRepository.findByProductId(new ProductId("product-2"))).thenReturn(Mono.just(stock2));


        Flux<Product> result = productService.findMaxStockByFranchise(command);


        StepVerifier.create(result)
            .assertNext(product -> {
                assertEquals("Product 2", product.getName());
                assertEquals(200, product.getStock().getQuantity());
            })
            .verifyComplete();

        verify(subsidiaryRepository).findByFranchiseId("franchise-123");
        verify(productRepository).findBySubsidiaryId(subsidiaryId.getValue());
        verify(stockRepository).findByProductId(new ProductId("product-1"));
        verify(stockRepository).findByProductId(new ProductId("product-2"));
    }

    @Test
    @DisplayName("Should handle empty subsidiaries when finding max stock")
    void shouldHandleEmptySubsidiariesWhenFindingMaxStock() {

        FindMaxStockCommand command = new FindMaxStockCommand("franchise-123");
        when(subsidiaryRepository.findByFranchiseId("franchise-123")).thenReturn(Flux.empty());


        Flux<Product> result = productService.findMaxStockByFranchise(command);


        StepVerifier.create(result)
            .verifyComplete();

        verify(subsidiaryRepository).findByFranchiseId("franchise-123");
        verify(productRepository, never()).findBySubsidiaryId(anyString());
        verify(stockRepository, never()).findByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should handle invalid category in add product")
    void shouldHandleInvalidCategoryInAddProduct() {

        AddProductCommand command = new AddProductCommand("Test Product", "subsidiary-123", "INVALID_CATEGORY", 50);
        when(subsidiaryRepository.findById(subsidiaryId.getValue())).thenReturn(Mono.just(testSubsidiary));


        Mono<Product> result = productService.addProduct(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException)
            .verify();

        verify(subsidiaryRepository).findById(subsidiaryId.getValue());
        verify(productRepository, never()).save(any(Product.class));
        verify(stockRepository, never()).save(any(Stock.class));
    }
}

