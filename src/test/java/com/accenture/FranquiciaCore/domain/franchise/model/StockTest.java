package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stock Tests")
class StockTest {

    @Test
    @DisplayName("Should create Stock with valid data")
    void shouldCreateStockWithValidData() {
        StockId stockId = new StockId("stock-123");
        int quantity = 100;
        
        Stock stock = Stock.builder()
            .id(stockId)
            .quantity(quantity)
            .build();
        
        assertEquals(stockId, stock.getId());
        assertEquals(quantity, stock.getQuantity());
    }

    @Test
    @DisplayName("Should add quantity to stock")
    void shouldAddQuantityToStock() {
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
        int quantityToAdd = 50;
        
        stock.addQuantity(quantityToAdd);
        
        assertEquals(150, stock.getQuantity());
    }

    @Test
    @DisplayName("Should remove quantity from stock")
    void shouldRemoveQuantityFromStock() {
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
        int quantityToRemove = 30;
        
        stock.removeQuantity(quantityToRemove);
        
        assertEquals(70, stock.getQuantity());
    }

    @Test
    @DisplayName("Should throw exception when removing more quantity than available")
    void shouldThrowExceptionWhenRemovingMoreQuantityThanAvailable() {
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        int quantityToRemove = 100;
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> stock.removeQuantity(quantityToRemove)
        );
        assertEquals("Quantity cannot be greater than the stock", exception.getMessage());
        assertEquals(50, stock.getQuantity());
    }

    @Test
    @DisplayName("Should create new Stock with ProductId")
    void shouldCreateNewStockWithProductId() {
        Stock originalStock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
        ProductId productId = new ProductId("product-456");
        
        Stock newStock = originalStock.withProductId(productId);
        
        assertEquals(productId.getValue(), newStock.getId().getValue());
        assertEquals(100, newStock.getQuantity());
        assertNotSame(originalStock, newStock);
    }

    @Test
    @DisplayName("Should be equal when ids are the same")
    void shouldBeEqualWhenIdsAreTheSame() {
        StockId stockId = new StockId("stock-123");
        Stock stock1 = Stock.builder()
            .id(stockId)
            .quantity(100)
            .build();
        Stock stock2 = Stock.builder()
            .id(stockId)
            .quantity(200)
            .build();
        
        assertEquals(stock1, stock2);
        assertEquals(stock1.hashCode(), stock2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when ids are different")
    void shouldNotBeEqualWhenIdsAreDifferent() {
        Stock stock1 = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
        Stock stock2 = Stock.builder()
            .id(new StockId("stock-456"))
            .quantity(100)
            .build();
        
        assertNotEquals(stock1, stock2);
        assertNotEquals(stock1.hashCode(), stock2.hashCode());
    }

    @Test
    @DisplayName("Should handle zero quantity operations")
    void shouldHandleZeroQuantityOperations() {
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        
        stock.addQuantity(0);
        stock.removeQuantity(0);
        
        assertEquals(50, stock.getQuantity());
    }

    @Test
    @DisplayName("Should handle removing exact quantity")
    void shouldHandleRemovingExactQuantity() {
        Stock stock = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(50)
            .build();
        
        stock.removeQuantity(50);
        
        assertEquals(0, stock.getQuantity());
    }
}

