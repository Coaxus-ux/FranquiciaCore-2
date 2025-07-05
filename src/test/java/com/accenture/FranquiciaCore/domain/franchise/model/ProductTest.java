package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
class ProductTest {

    @Test
    @DisplayName("Should create Product with valid data")
    void shouldCreateProductWithValidData() {
        ProductId productId = new ProductId("product-123");
        String name = "Big Mac";
        CategoryProduct category = CategoryProduct.FOOD;
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-456");
        
        Product product = Product.builder()
            .id(productId)
            .name(name)
            .category(category)
            .stock(stock)
            .subsidiaryId(subsidiaryId)
            .build();
        
        assertEquals(productId, product.getId());
        assertEquals(name, product.getName());
        assertEquals(category, product.getCategory());
        assertEquals(stock, product.getStock());
        assertEquals(subsidiaryId, product.getSubsidiaryId());
    }

    @Test
    @DisplayName("Should create new Product with different stock using withStock")
    void shouldCreateNewProductWithDifferentStockUsingWithStock() {
        Product originalProduct = Product.builder()
            .id(new ProductId("product-123"))
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(Stock.builder()
                .id(new StockId("stock-123"))
                .quantity(50)
                .build())
            .subsidiaryId(new SubsidiaryId("subsidiary-456"))
            .build();
        
        Stock newStock = Stock.builder()
            .id(new StockId("stock-456"))
            .quantity(100)
            .build();
        
        Product newProduct = originalProduct.withStock(newStock);
        
        assertEquals(originalProduct.getId(), newProduct.getId());
        assertEquals(originalProduct.getName(), newProduct.getName());
        assertEquals(originalProduct.getCategory(), newProduct.getCategory());
        assertEquals(originalProduct.getSubsidiaryId(), newProduct.getSubsidiaryId());
        assertEquals(newStock, newProduct.getStock());
        assertNotSame(originalProduct, newProduct);
        assertEquals(50, originalProduct.getStock().getQuantity());
    }

    @Test
    @DisplayName("Should be equal when ids are the same")
    void shouldBeEqualWhenIdsAreTheSame() {
        ProductId productId = new ProductId("product-123");
        
        Product product1 = Product.builder()
            .id(productId)
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(Stock.builder()
                .id(new StockId("stock-123"))
                .quantity(50)
                .build())
            .subsidiaryId(new SubsidiaryId("subsidiary-456"))
            .build();
        
        Product product2 = Product.builder()
            .id(productId)
            .name("Quarter Pounder")
            .category(CategoryProduct.BEVERAGE)
            .stock(Stock.builder()
                .id(new StockId("stock-789"))
                .quantity(100)
                .build())
            .subsidiaryId(new SubsidiaryId("subsidiary-789"))
            .build();
        
        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when ids are different")
    void shouldNotBeEqualWhenIdsAreDifferent() {
        Stock sameStock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        SubsidiaryId sameSubsidiaryId = new SubsidiaryId("subsidiary-456");
        
        Product product1 = Product.builder()
            .id(new ProductId("product-123"))
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(sameStock)
            .subsidiaryId(sameSubsidiaryId)
            .build();
        
        Product product2 = Product.builder()
            .id(new ProductId("product-456"))
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(sameStock)
            .subsidiaryId(sameSubsidiaryId)
            .build();
        
        assertNotEquals(product1, product2);
        assertNotEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    @DisplayName("Should handle all category types")
    void shouldHandleAllCategoryTypes() {
        ProductId productId = new ProductId("product-123");
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-456");
        
        for (CategoryProduct category : CategoryProduct.values()) {
            Product product = Product.builder()
                .id(productId)
                .name("Test Product")
                .category(category)
                .stock(stock)
                .subsidiaryId(subsidiaryId)
                .build();
            
            assertEquals(category, product.getCategory());
        }
    }

    @Test
    @DisplayName("Should have proper toString representation")
    void shouldHaveProperToStringRepresentation() {
        Product product = Product.builder()
            .id(new ProductId("product-123"))
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(Stock.builder()
                .id(new StockId("stock-123"))
                .quantity(50)
                .build())
            .subsidiaryId(new SubsidiaryId("subsidiary-456"))
            .build();
        
        String toStringResult = product.toString();
        
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("product-123"));
        assertTrue(toStringResult.contains("Big Mac"));
        assertTrue(toStringResult.contains("FOOD"));
    }

    @Test
    @DisplayName("Should throw exception when name is null in builder")
    void shouldThrowExceptionWhenNameIsNullInBuilder() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            Product.builder()
                .id(null)
                .name(null)
                .category(null)
                .stock(null)
                .subsidiaryId(null)
                .build()
        );
        assertEquals("Product name cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should not allow creation of product with empty name")
    void shouldNotAllowCreationOfProductWithEmptyName() {
        ProductId productId = new ProductId("product-123");
        String emptyName = "";

        assertThrows(IllegalArgumentException.class, () -> 
            new Product(
                productId,
                emptyName,
                CategoryProduct.FOOD,
                Stock.builder()
                    .id(new StockId("stock-123"))
                    .quantity(50)
                    .build(),
                new SubsidiaryId("subsidiary-456")
            )
        );
    }

    @Test
    @DisplayName("Should preserve immutability when using withStock")
    void shouldPreserveImmutabilityWhenUsingWithStock() {
        Stock originalStock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        
        Product originalProduct = Product.builder()
            .id(new ProductId("product-123"))
            .name("Big Mac")
            .category(CategoryProduct.FOOD)
            .stock(originalStock)
            .subsidiaryId(new SubsidiaryId("subsidiary-456"))
            .build();
        
        Stock newStock = Stock.builder()
            .id(new StockId("stock-456"))
            .quantity(100)
            .build();
        
        Product newProduct = originalProduct.withStock(newStock);
        originalStock.addQuantity(25);

        assertEquals(75, originalProduct.getStock().getQuantity());
        assertEquals(100, newProduct.getStock().getQuantity());
    }
}

